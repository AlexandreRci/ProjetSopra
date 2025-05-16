import { Component, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { PartieService } from '../partie.service';
import { JoueurService } from '../joueur.service';
import { CompteService } from '../compte.service';
import { switchMap } from 'rxjs/operators';
import { of } from 'rxjs';
import { Joueur } from '../joueur';
import { PlanetSeed } from '../planet-seed';
import { Partie } from '../partie';

@Component({
  selector: 'app-menu-partie',
  standalone: false,
  templateUrl: './menu-partie.component.html',
  styleUrls: ['./menu-partie.component.css']
})
export class MenuPartieComponent {
  partieData = new Partie  (0 , 1, 1, 1,  [],  "Debut");
  joueurData = new Joueur (1,1);

  constructor(
    private partieService: PartieService,
    private joueurService: JoueurService,
    private compteService: CompteService,
    private router: Router
  ) {}

  //Met le code pour créer le joueur ici

  


  createNewPartie() {
    // const userId = 1052; //l'ID de l'utilisateur connecté INUTILE c'est les joueurs qui sont liée aux partie par l'utilisateur/
    // Donc avant de créer une partie, je dois d'abord créer un joueur

  // const joueurBDD = this.joueurService.createJoueur(this.joueurData);
  // console.log('[menu-partie.component] vérification création joueur', joueurBDD);
  this.joueurService.createJoueur(this.joueurData).pipe(
    switchMap(createdJoueur => {
      console.log('[menu-partie.component] Joueur créé avec succès', createdJoueur);

      // Optionnel : ajouter le joueur à la partie si nécessaire
      // this.partieData.joueurs = [createdJoueur]; ← à activer si ton backend gère ça

      return this.partieService.getById(createdJoueur.id);
    }),  
    // this.partieService.getById(this.joueurData.id).pipe(
      switchMap(existingPartie => {
        if (existingPartie != null) {
          // Si une partie existe déjà, redirection de l'utilisateur vers cette partie
          console.log('[menu-partie.component] Partie existante trouvée on update', existingPartie);
          return this.partieService.updatePartie(existingPartie.id, this.partieData);
        } else {
          // Si aucune partie n'existe, création d'une nouvelle partie
          console.log('[menu-partie.component] Partie existante non trouvée on create', existingPartie);
          return this.partieService.createPartie(this.partieData);
        }
      })
    ).subscribe(
      response => {
        console.log('Partie créée', response);
        if (response) {
          // On balance la partie dans ecranJeu avec l'id de la partie en question
          this.router.navigate(['/ecranJeu', response.id], { state: { partie: response } });
        }
      },
      error => {
        console.error('Erreur création partie', error);
      }
    );
  }
}
