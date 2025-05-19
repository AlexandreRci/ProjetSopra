import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable, Subscription } from 'rxjs';
import { PlanetSeedService } from '../../../service/planet-seed.service';
import { PlanetSeed } from '../../../class/planet-seed';
import { PlaneteService } from '../../../service/planete.service';
import { JoueurService } from '../../../service/joueur.service';
import { BatimentService } from '../../../service/batiment.service';
import { PartieService } from '../../../service/partie.service';
import { Planete } from '../../../class/planete';
import { Joueur } from '../../../class/joueur';
import { Batiment } from '../../../class/batiment';
import { Partie } from '../../../class/partie';

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
  partieList$!: Observable<Partie[]>;
  batimentList$!: Observable<Batiment[]>;

  constructor(
    private fb: FormBuilder,
    private planetSeedService: PlanetSeedService,
    private planeteService: PlaneteService,
    private joueurService: JoueurService,
    private partieService: PartieService,
    private batimentService: BatimentService
  ) { }

  ngOnInit(): void {
    this.planetSeedForm = this.fb.group({
      population: [0, [Validators.required, Validators.min(0)]],
      arme: [0, [Validators.required, Validators.min(0)]],
      mineraiRestant: [0, [Validators.required, Validators.min(0)]],
      idPlanete: [null, Validators.required],
      idPartie: [null, Validators.required],
      idJoueur: [null],
      batiment1: [null],
      batiment2: [null],
      batiment3: [null],
      batiment4: [null]
    });


    this.planetSeeds$ = this.planetSeedService.findAll();
    this.planeteList$ = this.planeteService.findAll();
    this.joueurList$ = this.joueurService.findAll();
    this.partieList$ = this.partieService.findAll();
    this.batimentList$ = this.batimentService.findAll();
  }

  ngOnDestroy(): void {
    this.unsub('save');
    this.unsub('delete');
  }

  addOrEditPlanetSeed(): void {
    this.unsub('save');

    const raw = this.planetSeedForm.value;

    const idBatiments: number[] = [
      raw.batiment1,
      raw.batiment2,
      raw.batiment3,
      raw.batiment4
    ].filter((id: number | null) => id != null); // Exclut les sélections vides

    const payload = {
      id: this.editingPlanetSeed?.id,
      population: raw.population,
      arme: raw.arme,
      mineraiRestant: raw.mineraiRestant,
      idPlanete: raw.idPlanete,
      idJoueur: raw.idJoueur,
      idPartie: raw.idPartie,
      idBatiments: idBatiments
    };

    this.subscriptions['save'] = this.planetSeedService.save(payload).subscribe(() => {
      this.planetSeedService.refresh();
      this.planetSeedForm.reset();
      this.editingPlanetSeed = null;
    });
  }


  editPlanetSeed(planetSeed: PlanetSeed): void {
    this.editingPlanetSeed = planetSeed;
    const ids = planetSeed.idBatiments;

    this.planetSeedForm.patchValue({
      population: planetSeed.population,
      arme: planetSeed.arme,
      mineraiRestant: planetSeed.mineraiRestant,
      idPlanete: planetSeed.idPlanete,
      idJoueur: planetSeed.idJoueur,
      idPartie: planetSeed.idPartie,
      batiment1: ids[0] ?? null,
      batiment2: ids[1] ?? null,
      batiment3: ids[2] ?? null,
      batiment4: ids[3] ?? null
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
