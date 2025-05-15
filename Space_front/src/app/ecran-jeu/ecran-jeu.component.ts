import { Component, OnInit, OnDestroy  } from '@angular/core';
import { AuthService } from '../auth.service';
@Component({
  selector: 'app-ecran-jeu',
  standalone: false,
  templateUrl: './ecran-jeu.component.html',
  styleUrl: './ecran-jeu.component.css'
})
export class EcranJeuComponent {
  isAnimated = true; // Contrôle l'état de l'animation planet-display
  expandedPlanetIndex: number | null = null; // Variable permettant de selectionner quel planete est "déplié"
  timeLeft: number = 90; // Temps restant en secondes
  timer: any;
  username: string = '';


  constructor(private authService: AuthService) {}


  ngOnInit() {
    this.startTimer();
    // this.username = this.authService.getUsername();
    console.log('Username:', this.username);
  }

  ngOnDestroy() {
    this.clearTimer();
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
