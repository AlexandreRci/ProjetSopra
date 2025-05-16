import { Component, OnInit, OnDestroy  } from '@angular/core';
import { CompteService } from '../compte.service';
import { Compte } from '../compte';
import { tap } from 'rxjs/operators';
import { Planete } from '../planete';
import { PlanetSeed } from '../planet-seed';
import { PartieService } from '../partie.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-ecran-jeu',
  standalone: false,
  templateUrl: './ecran-jeu.component.html',
  styleUrl: './ecran-jeu.component.css'
})
export class EcranJeuComponent  implements OnInit, OnDestroy  {
  isAnimated = true; // Contrôle l'état de l'animation planet-display
  expandedPlanetIndex: number | null = null; // Variable permettant de selectionner quel planete est "déplié"
  timeLeft: number = 90; // Temps restant en secondes
  timer: any;
  username: string = '';
  planetSeeds: PlanetSeed[] = [];
  currentPartieId: number | null = null; // Stockez l'ID de la partie actuelle


  constructor(private compteService: CompteService, private partieService: PartieService,private router: Router) {}


  ngOnInit() {
    this.startTimer();
    // this.getUsername();
    // console.log('Username:', this.username);
    this.generateRandomPlanetSeeds();

    const navigationState = this.router.getCurrentNavigation()?.extras.state;
    if (navigationState && navigationState['partie']) {
      this.currentPartieId = navigationState['partie'].id;
    }
  }

  // ngOnDestroy() {
  //   this.clearTimer();
  // }

  ngOnDestroy() {
    this.clearTimer();
    if (this.currentPartieId) {
      this.partieService.deletePartie(this.currentPartieId).subscribe(
        () => {
          console.log('Partie supprimée', this.currentPartieId);
        },
        (        error: any) => {
          console.error('Erreur suppression partie', error);
        }
      );
    }
  }



  startTimer() {
    this.timer = setInterval(() => {
      if (this.timeLeft > 0) {
        this.timeLeft--;
      } else {
        this.clearTimer();
      }
    }, 1000);
  }

  clearTimer() {
    if (this.timer) {
      clearInterval(this.timer);
    }
  }

  formatTime(seconds: number): string {
    const minutes: number = Math.floor(seconds / 60);
    const remainingSeconds: number = seconds % 60;
    return `${minutes}:${remainingSeconds < 10 ? '0' : ''}${remainingSeconds}`;
  }

// getUsername() {
//   this.compteService.getCompte(1052).pipe(
//     tap((compte: Compte) => {
//       this.username = compte.getUsername();
//       console.log('Username:', this.username);
//     })
//   ).subscribe(
//     () => {},
//     error => {
//       console.error('Erreur lors de la récupération du compte', error);
//     }
//   );
// }

    generateRandomPlanetSeeds() {
    // Liste de planètes codées en dur
    const hardcodedPlanetes: Planete[] = [
      new Planete(1, 'Planète 1', 100, ['Desertique', 'Plaine', 'Ocean']),
      new Planete(2, 'Planète 2', 150, ['Foret', 'Desertique', 'Ocean']),
      new Planete(3, 'Planète 3', 200, ['Ocean', 'Desertique','Plaine']),
      new Planete(4, 'Planète 4', 250, ['Plaine', 'Foret', 'Foret']),
      new Planete(5, 'Planète 5', 300, ['Desertique', 'Ocean', 'Foret']),
      new Planete(6, 'Planète 6', 350, ['Desertique', 'Plaine', 'Plaine']),
      new Planete(7, 'Planète 7', 400, ['Foret', 'Desertique', 'Foret']),
      new Planete(8, 'Planète 8', 450, ['Ocean', 'Desertique', 'Ocean']),
      new Planete(9, 'Planète 9', 500, ['Plaine', 'Foret', 'Plaine']),
      new Planete(10, 'Planète 10', 550, ['Desertique', 'Ocean', 'Desertique']),
      new Planete(11, 'Planète 11', 600, ['Desertique', 'Plaine', 'Foret']),
      new Planete(12, 'Planète 12', 650, ['Foret', 'Desertique', 'Desertique'])
    ];

    // Mélanger la liste de planètes
    const shuffledPlanetes = this.shuffleArray(hardcodedPlanetes);

    // Sélectionner 8 planètes aléatoires
    this.planetSeeds = shuffledPlanetes.slice(0, 8).map(planete => {
      return new PlanetSeed(
        planete.id,
        Math.floor(Math.random() * 500) + 1,
        Math.floor(Math.random() * 100) + 1,
        Math.floor(Math.random() * 100) + 1,
        null, // Ici c'est Joueur
        planete,
        [] // Ici c'est une liste de Batiment
      );
    });
  }

  shuffleArray(array: any[]): any[] {
    for (let i = array.length - 1; i > 0; i--) {
      const j = Math.floor(Math.random() * (i + 1));
      [array[i], array[j]] = [array[j], array[i]];
    }
    return array;
  }



  togglePlanetInfo(index: number) {
    // Fonction permettant de déplier les informations d'une planete
    // En vrai, on peut le réduire à un ternaire mais c'est plus lisible pour moi
    if (this.expandedPlanetIndex === index) {
      this.expandedPlanetIndex = null;
    } else {
      this.expandedPlanetIndex = index;
    }
  }



}