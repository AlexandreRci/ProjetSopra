import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subscription, Observable } from 'rxjs';
import { Joueur } from '../../../class/joueur';
import { JoueurService } from '../../../service/joueur.service';
import { PartieService } from '../../../service/partie.service';
import { EspeceService } from '../../../service/espece.service';
import { Partie } from '../../../class/partie';
import { Espece } from '../../../class/espece';

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
  editingJoueur: Joueur | null = null;
  subscriptions: any = {};

  constructor(
    private joueurService: JoueurService,
    private partieService: PartieService,
    private especeService: EspeceService,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.joueurForm = this.formBuilder.group({
      position: this.formBuilder.control('', [Validators.required, Validators.min(0)]),
      idPartie: this.formBuilder.control('', Validators.required),
      idEspece: this.formBuilder.control('', Validators.required)
    });

    this.joueurs$ = this.joueurService.findAll();
    this.parties$ = this.partieService.findAll();
    this.especes$ = this.especeService.findAll();
  }

  ngOnDestroy(): void {
    this.unsub('save');
    this.unsub('delete');
  }

  public addOrEditJoueur(): void {
    this.unsub('save');

    const joueurPayload = {
      id: this.editingJoueur?.id,
      ...this.joueurForm.value,
      idPlanetSeeds: this.editingJoueur?.idPlanetSeeds ?? [],
      idPossessions: this.editingJoueur?.idPossessions ?? []
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
      idEspece: joueur.idEspece
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
