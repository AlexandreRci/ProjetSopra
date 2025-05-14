import { Component } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PartieService } from '../partie.service';

@Component({
  selector: 'app-menu-partie',
  standalone: false,
  templateUrl: './menu-partie.component.html',
  styleUrl: './menu-partie.component.css'
})

export class MenuPartieComponent {


  constructor(private partieService: PartieService, private router: Router){}

  createNewPartie(){
    const partieData = {currentPosition: 1, nbTour:1, nbJoueur:1};

    this.partieService.createPartie(partieData)
    .subscribe(
      response => {
        console.log('Partie créée',response)
        this.router.navigate(['/ecranJeu']);
      },
      error => {
        console.error('Erreur création partie', error);

      }
    )



  }


}
