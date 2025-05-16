import { Component, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { PartieService } from '../partie.service';
import { JoueurService } from '../joueur.service';
import { CompteService } from '../compte.service';
import { switchMap } from 'rxjs/operators';
import { of } from 'rxjs';

@Component({
  selector: 'app-menu-partie',
  standalone: false,
  templateUrl: './menu-partie.component.html',
  styleUrls: ['./menu-partie.component.css']
})
export class MenuPartieComponent {
  currentPartieId: number | null = null;

  constructor(
    private partieService: PartieService,
    private joueurService: JoueurService,
    private compteService: CompteService,
    private router: Router
  ) {}

  createNewPartie() {
    // Supposons que vous avez un moyen de récupérer l'ID de l'utilisateur connecté
    const userId = 1052; // Remplacez par l'ID de l'utilisateur connecté

    this.partieService.checkExistingPartie(userId).pipe(
      switchMap(existingPartie => {
        if (existingPartie) {
          // Si une partie existe déjà, redirigez l'utilisateur vers cette partie
          console.log('Partie existante trouvée', existingPartie);
          this.currentPartieId = existingPartie.id; // Stockez l'ID de la partie actuelle
          this.router.navigate(['/ecranJeu'], { state: { partie: existingPartie } });
          return of(null); // Retourne un observable vide pour compléter la chaîne
        } else {
          // Si aucune partie n'existe, créez une nouvelle partie
          const partieData = { currentPosition: 1, nbTour: 1, nbJoueur: 1, joueurs: [], planetSeeds: [], statut: "Debut" };
          return this.partieService.createPartie(partieData);
        }
      })
    ).subscribe(
      response => {
        if (response) {
          console.log('Partie créée', response);
          this.currentPartieId = response.id; // Stockez l'ID de la partie actuelle
          this.router.navigate(['/ecranJeu'], { state: { partie: response } });
        }
      },
      error => {
        console.error('Erreur création partie', error);
      }
    );
  }
}
