import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable, Subscription } from 'rxjs';
import { Planete } from '../../planete';
import { PlaneteService } from '../../planete.service';

@Component({
  selector: 'app-planete',
  templateUrl: './planete.component.html',
  styleUrls: ['./planete.component.css'],
  standalone: false
})
export class PlaneteComponent implements OnInit, OnDestroy {
  planeteForm!: FormGroup;
  planetes$!: Observable<Planete[]>;
  editingPlanete: Planete | null = null;
  subscriptions: any = {};
  biomeOptions: string[] = ['Plaine', 'Foret', 'Ocean', 'Desertique'];

  constructor(private service: PlaneteService, private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.planeteForm = this.formBuilder.group({
      nom: ['', Validators.required],
      minerai: ['', [Validators.required, Validators.min(0)]],
      biome1: ['', Validators.required],
      biome2: ['', Validators.required],
      biome3: ['', Validators.required]
    });

    this.planetes$ = this.service.findAll();
  }

  ngOnDestroy(): void {
    this.unsub('save');
    this.unsub('delete');
  }

  public addOrEditPlanete(): void {
    this.unsub('save');

    const biomes = [
      this.planeteForm.value.biome1,
      this.planeteForm.value.biome2,
      this.planeteForm.value.biome3
    ];

    const payload = {
      id: this.editingPlanete?.id,
      nom: this.planeteForm.value.nom,
      minerai: this.planeteForm.value.minerai,
      biomes
    };

    this.subscriptions['save'] = this.service.save(payload).subscribe(() => {
      this.service.refresh();
      this.planeteForm.reset();
      this.editingPlanete = null;
    });
  }

  public editPlanete(planete: Planete): void {
    this.editingPlanete = planete;

    this.planeteForm.patchValue({
      nom: planete.nom,
      minerai: planete.minerai,
      biome1: planete.biomes[0] ?? '',
      biome2: planete.biomes[1] ?? '',
      biome3: planete.biomes[2] ?? ''
    });
  }

  public deletePlanete(planete: Planete): void {
    this.unsub('delete');

    this.subscriptions['delete'] = this.service.delete(planete).subscribe(() => {
      this.service.refresh();
    });
  }

  private unsub(name: string): void {
    if (this.subscriptions[name]) {
      this.subscriptions[name].unsubscribe();
      this.subscriptions[name] = null;
    }
  }
}
