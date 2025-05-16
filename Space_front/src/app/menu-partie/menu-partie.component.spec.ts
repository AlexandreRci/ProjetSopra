import { Component, OnDestroy  } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PartieService } from '../partie.service';
import { tap } from 'rxjs/operators';
import { JoueurService } from '../joueur.service';
import { CompteService } from '../compte.service';
import { Joueur } from '../joueur';
import { switchMap } from 'rxjs/operators';
import { of } from 'rxjs'; 

@Component({
  selector: 'app-menu-partie',
  standalone: false,
  templateUrl: './menu-partie.component.html',
  styleUrl: './menu-partie.component.css'
})

export class MenuPartieComponent  {
  currentPartieId: number | null = null;

  constructor(private partieService: PartieService,private joueurService: JoueurService,private compteService: CompteService, private router: Router) {}

    createNewPartie() {
    const partieData = { currentPosition: 1, nbTour: 1, nbJoueur: 1, joueurs: [], planetSeeds: [], statut: "Debut" };

    this.partieService.createPartie(partieData).pipe(
      tap(response => {
        console.log('Partie créée', response);
        this.router.navigate(['/ecranJeu']);
      })
    ).subscribe(
      () => {},
      error => {
        console.error('Erreur création partie', error);
      }
    );
  }

}


