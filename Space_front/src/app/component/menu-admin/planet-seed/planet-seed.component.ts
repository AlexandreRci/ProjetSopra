import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable, Subscription } from 'rxjs';
import { PlanetSeedService } from '../../../service/planet-seed.service';
import { PlanetSeed } from '../../../class/planet-seed';
import { PlaneteService } from '../../../service/planete.service';
import { JoueurService } from '../../../service/joueur.service';
import { BatimentService } from '../../../service/batiment.service';
import { Planete } from '../../../class/planete';
import { Joueur } from '../../../class/joueur';
import { Batiment } from '../../../class/batiment';

@Component({
  selector: 'app-planet-seed',
  templateUrl: './planet-seed.component.html',
  styleUrls: ['./planet-seed.component.css'],
  standalone: false
})
export class PlanetSeedComponent implements OnInit, OnDestroy {
  planetSeedForm!: FormGroup;
  planetSeeds$!: Observable<PlanetSeed[]>;
  editingPlanetSeed: PlanetSeed | null = null;
  subscriptions: { [key: string]: Subscription | null } = {};

  planeteList$!: Observable<Planete[]>;
  joueurList$!: Observable<Joueur[]>;
  batimentList$!: Observable<Batiment[]>;

  constructor(
    private fb: FormBuilder,
    private planetSeedService: PlanetSeedService,
    private planeteService: PlaneteService,
    private joueurService: JoueurService,
    private batimentService: BatimentService
  ) {}

  ngOnInit(): void {
    this.planetSeedForm = this.fb.group({
      population: [0, [Validators.required, Validators.min(0)]],
      arme: [0, [Validators.required, Validators.min(0)]],
      mineraiRestant: [0, [Validators.required, Validators.min(0)]],
      idPlanete: [null, Validators.required],
      idJoueur: [null],
      idBatiments: [[]]
    });

    this.planetSeeds$ = this.planetSeedService.findAll();
    this.planeteList$ = this.planeteService.findAll();
    this.joueurList$ = this.joueurService.findAll();
    this.batimentList$ = this.batimentService.findAll();
  }

  ngOnDestroy(): void {
    this.unsub('save');
    this.unsub('delete');
  }

  addOrEditPlanetSeed(): void {
    this.unsub('save');

    const payload = {
      id: this.editingPlanetSeed?.id,
      ...this.planetSeedForm.value
    };

    this.subscriptions['save'] = this.planetSeedService.save(payload).subscribe(() => {
      this.planetSeedService.refresh();
      this.planetSeedForm.reset();
      this.editingPlanetSeed = null;
    });
  }

  editPlanetSeed(planetSeed: PlanetSeed): void {
    this.editingPlanetSeed = planetSeed;

    this.planetSeedForm.patchValue({
      population: planetSeed.population,
      arme: planetSeed.arme,
      mineraiRestant: planetSeed.mineraiRestant,
      idPlanete: planetSeed.idPlanete,
      idJoueur: planetSeed.idJoueur,
      idBatiments: planetSeed.idBatiments
    });
  }

  deletePlanetSeed(planetSeed: PlanetSeed): void {
    this.unsub('delete');

    this.subscriptions['delete'] = this.planetSeedService.delete(planetSeed).subscribe(() => {
      this.planetSeedService.refresh();
    });
  }

  private unsub(name: string): void {
    if (this.subscriptions[name]) {
      this.subscriptions[name]?.unsubscribe();
      this.subscriptions[name] = null;
    }
  }
}
