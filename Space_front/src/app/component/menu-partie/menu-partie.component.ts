import { Component } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PartieService } from '../../service/partie.service';
import { tap } from 'rxjs/operators';
import { JoueurService } from '../../service/joueur.service';
import { CompteService } from '../../service/compte.service';
import { switchMap } from 'rxjs/operators';
import { StartRequest } from '../../class/request/start-request';
import { Partie } from '../../class/partie';

@Component({
  selector: 'app-menu-partie',
  standalone: false,
  templateUrl: './menu-partie.component.html',
  styleUrl: './menu-partie.component.css'
})

export class MenuPartieComponent {
  startChoiceEspece:boolean = false;
  idUser: number = localStorage.getItem('userId') ? parseInt(localStorage.getItem('userId') ?? '0') : 0;
  partieList: Partie[] = [];


  constructor(private partieService: PartieService, private router: Router) {}

  ngOnInit(): void {
    this.partieService.findAllByIdUser(this.idUser).subscribe({ 
      next: (response: any) => {
        this.partieList = response;
      },
      error: () => {
        console.error('Error fetching parties');
      }
    });
    console.log(this.partieList);
  }
  createNewPartie() {
    this.startChoiceEspece = true;
  }
  joinPartie(posPartie: number) {
    this.router.navigate(['/ecranJeu/' + this.partieList[posPartie].id + "/" + this.partieList[posPartie].joueurs[0]]);
  }





}
