import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable, Subscription } from 'rxjs';
import { Espece } from '../../../class/espece';
import { EspeceService } from '../../../service/espece.service';

@Component({
  selector: 'app-espece',
  templateUrl: './espece.component.html',
  styleUrls: ['./espece.component.css'],
  standalone: false
})
export class EspeceComponent implements OnInit, OnDestroy {
  especeForm!: FormGroup;
  especes$!: Observable<Espece[]>;
  editingEspece: Espece | null = null;
  subscriptions: any = {};
  biomes: string[] = ['PLAINE', 'FORET', 'OCEAN', 'DESERTIQUE'];

  constructor(private service: EspeceService, private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    const biomeGroup: any = {};
    this.biomes.forEach(b => {
      biomeGroup[b] = this.formBuilder.control('', [Validators.required, Validators.min(0)]);
    });

    this.especeForm = this.formBuilder.group({
      nom: ['', Validators.required],
      ...biomeGroup
    });

    this.especes$ = this.service.findAll();
  }

  ngOnDestroy(): void {
    this.unsub('save');
    this.unsub('delete');
  }

  public addOrEditEspece(): void {
  this.unsub('save');

  const biomes: { [key: string]: number } = {};
  this.biomes.forEach(b => {
    biomes[b] = this.especeForm.value[b];
  });

  const especePayload = {
    id: this.editingEspece?.id,
    nom: this.especeForm.value.nom,
    biomes
  };

  this.subscriptions['save'] = this.service.save(especePayload)
    .subscribe(() => {
      this.service.refresh();
      this.especeForm.reset();
      this.editingEspece = null;
    });
}



  public editEspece(espece: Espece): void {
    this.editingEspece = espece;

    this.especeForm.patchValue({
      nom: espece.nom,
      ...espece.biomes
    });
  }

  public deleteEspece(espece: Espece): void {
    this.unsub('delete');

    this.subscriptions['delete'] = this.service.delete(espece)
      .subscribe(() => this.service.refresh());
  }

  private unsub(name: string): void {
    if (this.subscriptions[name]) {
      this.subscriptions[name].unsubscribe();
      this.subscriptions[name] = null;
    }
  }
}
