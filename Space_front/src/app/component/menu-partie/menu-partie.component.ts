import { Component } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PartieService } from '../../service/partie.service';
import { tap } from 'rxjs/operators';
import { JoueurService } from '../../service/joueur.service';
import { CompteService } from '../../service/compte.service';
import { Joueur } from '../../class/joueur';
import { switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-menu-partie',
  standalone: false,
  templateUrl: './menu-partie.component.html',
  styleUrl: './menu-partie.component.css'
})

export class MenuPartieComponent {

  constructor(private partieService: PartieService,private joueurService: JoueurService,private compteService: CompteService, private router: Router) {}

    createNewPartie() {
    const partieData = { currentPosition: 1, nbTour: 1, nbJoueur: 1, statut: "DEBUT" };

    this.partieService.save(partieData).pipe(
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
