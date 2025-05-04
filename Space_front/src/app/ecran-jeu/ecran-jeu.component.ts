import { Component } from '@angular/core';

@Component({
  selector: 'app-ecran-jeu',
  standalone: false,
  templateUrl: './ecran-jeu.component.html',
  styleUrl: './ecran-jeu.component.css'
})
export class EcranJeuComponent {
  isAnimated = true; // Contrôle l'état de l'animation planet-display
  expandedPlanetIndex: number | null = null; // Variable permettant de selectionner quel planete est "déplié"

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
