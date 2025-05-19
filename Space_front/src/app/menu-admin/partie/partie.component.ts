import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Observable, Subscription } from 'rxjs';
import { Partie } from '../../partie';
import { PartieService } from '../../partie.service';

@Component({
  selector: 'app-partie',
  templateUrl: './partie.component.html',
  standalone: false,
  styleUrls: ['./partie.component.css']
})
export class PartieComponent implements OnInit, OnDestroy {
  partieForm!: FormGroup;
  parties$!: Observable<Partie[]>;
  editingPartie: Partie | null = null;
  subscriptions: any = {};

  constructor(private service: PartieService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.partieForm = this.formBuilder.group({
      nbJoueur: this.formBuilder.control('', [Validators.required, Validators.min(1)]),
      currentPosition: this.formBuilder.control('', [Validators.required, Validators.min(0)]),
      nbTour: this.formBuilder.control('', [Validators.required, Validators.min(1)]),
      statut: this.formBuilder.control('', Validators.required)
    });


    this.parties$ = this.service.findAll();
  }

  ngOnDestroy(): void {
    this.unsub('save');
    this.unsub('delete');
  }

  public addOrEditPartie(): void {
    this.unsub('save');

    const partiePayload = {
      id: this.editingPartie?.id,
      ...this.partieForm.value,
      joueurs: this.editingPartie?.joueurs ?? [],        // On garde les anciens si présents
      planetSeeds: this.editingPartie?.planetSeeds ?? [] // Idem
    };

    this.subscriptions['save'] = this.service.save(partiePayload)
      .subscribe(() => {
        this.service.refresh();
        this.partieForm.reset();
        this.editingPartie = null;
      });
  }

  public editPartie(partie: Partie): void {
    this.editingPartie = partie;
    this.partieForm.patchValue({
      currentPosition: partie.currentPosition,
      nbTour: partie.nbTour,
      nbJoueur: partie.nbJoueur,
      statut: partie.statut
    });
  }

  public deletePartie(partie: Partie): void {
    this.unsub('delete');

    this.subscriptions['delete'] = this.service.delete(partie)
      .subscribe(() => {
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
