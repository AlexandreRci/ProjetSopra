import { Component } from '@angular/core';

@Component({
  selector: 'app-top-buttons-connected',
  standalone: false,
  templateUrl: './top-buttons-connected.component.html',
  styleUrl: './top-buttons-connected.component.css'
})
export class TopButtonsConnectedComponent {
  isOpen: boolean = false;
  isMuted: boolean = false; // ← nouvelle variable

  toggleMenu() {
    this.isOpen = !this.isOpen;
  }

  closeMenu() {
    this.isOpen = false;
  }

  removeToken() {
    localStorage.removeItem('token');
  }

  toggleVolume(): void {
    this.isMuted = !this.isMuted; // ← bascule volume on/off
  }
}

