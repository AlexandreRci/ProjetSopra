import { Component } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PartieService } from '../../service/partie.service';
import { tap } from 'rxjs/operators';
import { JoueurService } from '../../service/joueur.service';
import { CompteService } from '../../service/compte.service';
import { switchMap } from 'rxjs/operators';
import { StartRequest } from '../../class/request/start-request';

@Component({
  selector: 'app-menu-partie',
  standalone: false,
  templateUrl: './menu-partie.component.html',
  styleUrl: './menu-partie.component.css'
})

export class MenuPartieComponent {
  startChoiceEspece:boolean = false;

  constructor() {}
  createNewPartie() {
    this.startChoiceEspece = true;
  }

}
