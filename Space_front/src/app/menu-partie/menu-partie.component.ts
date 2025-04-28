import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-menu-partie',
  standalone: false,
  templateUrl: './menu-partie.component.html',
  styleUrls: ['./menu-partie.component.css']
})
export class MenuPartieComponent implements OnInit {
  username: string = '';

  ngOnInit(): void {
    const storedUsername = localStorage.getItem('username');
    if (storedUsername) {
      this.username = storedUsername;
    } else {
      this.username = 'Utilisateur inconnu'; // Valeur par défaut si non trouvé
    }
  }
}


