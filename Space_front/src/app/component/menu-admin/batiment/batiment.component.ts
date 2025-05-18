import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable, Subscription } from 'rxjs';
import { Batiment } from '../../batiment';
import { BatimentService } from '../../batiment.service';

@Component({
  selector: 'app-batiment',
  templateUrl: './batiment.component.html',
  styleUrls: ['./batiment.component.css'],
  standalone: false
})
export class BatimentComponent implements OnInit, OnDestroy {
  batimentForm!: FormGroup;
  batiments$!: Observable<Batiment[]>;
  editingBatiment: Batiment | null = null;
  subscriptions: any = {};
  tailles = ['Petit', 'Moyen', 'Grand'];
  ressources = ['Arme', 'Nourriture', 'Energie', 'Argent'];

  constructor(private service: BatimentService, private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.batimentForm = this.formBuilder.group({
      nom: ['', Validators.required],
      taille: ['', Validators.required],
      ressource: ['', Validators.required]
    });

    this.batiments$ = this.service.findAll();
  }

  ngOnDestroy(): void {
    this.unsub('save');
    this.unsub('delete');
  }

  public addOrEditBatiment(): void {
    this.unsub('save');

    const batimentPayload = {
      id: this.editingBatiment?.id,
      ...this.batimentForm.value
    };

    this.subscriptions['save'] = this.service.save(batimentPayload)
      .subscribe(() => {
        this.service.refresh();
        this.batimentForm.reset();
        this.editingBatiment = null;
      });
  }

  public editBatiment(batiment: Batiment): void {
    this.editingBatiment = batiment;
    this.batimentForm.patchValue({
      nom: batiment.nom,
      taille: batiment.taille,
      ressource: batiment.ressource
    });
  }

  public deleteBatiment(batiment: Batiment): void {
    this.unsub('delete');

    this.subscriptions['delete'] = this.service.delete(batiment)
      .subscribe(() => this.service.refresh());
  }

  private unsub(name: string): void {
    if (this.subscriptions[name]) {
      this.subscriptions[name].unsubscribe();
      this.subscriptions[name] = null;
    }
  }
}
