import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subscription, Observable } from 'rxjs';
import { Joueur } from '../../../class/joueur';
import { JoueurService } from '../../../service/joueur.service';
import { PartieService } from '../../../service/partie.service';
import { EspeceService } from '../../../service/espece.service';
import { Partie } from '../../../class/partie';
import { Espece } from '../../../class/espece';
import { PossessionService } from '../../../service/possession.service';
import { Possession } from '../../../class/possession';

@Component({
  selector: 'app-joueur',
  templateUrl: './joueur.component.html',
  styleUrls: ['./joueur.component.css'],
  standalone: false
})
export class JoueurComponent implements OnInit, OnDestroy {
  joueurForm!: FormGroup;
  joueurs$!: Observable<Joueur[]>;
  parties$!: Observable<Partie[]>;
  especes$!: Observable<Espece[]>;
  possessions$!: Observable<Possession[]>;
  editingJoueur: Joueur | null = null;
  subscriptions: any = {};

  constructor(
    private joueurService: JoueurService,
    private partieService: PartieService,
    private especeService: EspeceService,
    private possessionService: PossessionService,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.joueurForm = this.formBuilder.group({
      position: ['', [Validators.required, Validators.min(0)]],
      idPartie: ['', Validators.required],
      idEspece: ['', Validators.required],
      possession1: [null],
      possession2: [null],
      possession3: [null],
      possession4: [null]
    });

    this.joueurs$ = this.joueurService.findAll();
    this.parties$ = this.partieService.findAll();
    this.especes$ = this.especeService.findAll();
    this.possessions$ = this.possessionService.findAll();
  }

  ngOnDestroy(): void {
    this.unsub('save');
    this.unsub('delete');
  }

  public addOrEditJoueur(): void {
    this.unsub('save');

    const raw = this.joueurForm.value;

    const idPossessions: number[] = [
      raw.possession1,
      raw.possession2,
      raw.possession3,
      raw.possession4
    ].filter((id: number | null) => id != null); // Exclure les valeurs nulles

    const joueurPayload = {
      id: this.editingJoueur?.id,
      position: raw.position,
      idPartie: raw.idPartie,
      idEspece: raw.idEspece,
      idPossessions: idPossessions,
      idPlanetSeeds: this.editingJoueur?.idPlanetSeeds ?? []
    };


    this.subscriptions['save'] = this.joueurService.save(joueurPayload)
      .subscribe(() => {
        this.joueurService.refresh();
        this.joueurForm.reset();
        this.editingJoueur = null;
      });
  }

  public editJoueur(joueur: Joueur): void {
    this.editingJoueur = joueur;
    this.joueurForm.patchValue({
      position: joueur.position,
      idPartie: joueur.idPartie,
      idEspece: joueur.idEspece,
      possession1: joueur.idPossessions[0] ?? null,
      possession2: joueur.idPossessions[1] ?? null,
      possession3: joueur.idPossessions[2] ?? null,
      possession4: joueur.idPossessions[3] ?? null
    });

  }

  public deleteJoueur(joueur: Joueur): void {
    this.unsub('delete');

    this.subscriptions['delete'] = this.joueurService.delete(joueur)
      .subscribe(() => {
        this.joueurService.refresh();
      });
  }

  private unsub(name: string): void {
    if (this.subscriptions[name]) {
      this.subscriptions[name].unsubscribe();
      this.subscriptions[name] = null;
    }
  }
}
