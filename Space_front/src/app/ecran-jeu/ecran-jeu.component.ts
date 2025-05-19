import { Component } from '@angular/core';

@Component({
  selector: 'app-ecran-jeu',
  templateUrl: './ecran-jeu.component.html',
  styleUrls: ['./ecran-jeu.component.css'],
  standalone: false
})
export class EcranJeuComponent {
  // Juste un message pour que la page ne soit pas vide
  message: string = "Écran de jeu temporairement désactivé. En cours de maintenance.";
}
