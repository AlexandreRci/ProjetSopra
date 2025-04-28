import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';

import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { CompteRequest } from '../compte-request';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-se-connecter',
  standalone: false,
  templateUrl: './se-connecter.component.html',
  styleUrl: './se-connecter.component.css'
})

// /!\ /!\ 
// Remplacer TOUS les "login" en "username" peu importe la casse 

export class SeConnecterComponent implements OnInit {
  public authForm!: FormGroup;
  public usernameCtrl!: FormControl;
  public passwordCtrl!: FormControl;
  public isError: boolean = false;

  constructor(private service: AuthService, private router: Router, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.usernameCtrl = this.formBuilder.control('', Validators.required);
    this.passwordCtrl = this.formBuilder.control('', [ Validators.required ]);

    this.authForm = this.formBuilder.group({
      username: this.usernameCtrl,
      password: this.passwordCtrl
    });
  }


  public authenticate() {
    this.service.authenticate(new CompteRequest(this.authForm.value.username, this.authForm.value.password));
    if (this.service.token === "")
    {
      this.isError = true;
    }else{
      this.router.navigate(['/menuPartie']);
    }


  // public authenticate() {
  //   this.service.authenticate(new CompteRequest(this.authForm.value.username, this.authForm.value.password))
  //     .subscribe({
  //       next: () => {
  //         this.router.navigate(['/menuPartie']);
  //       },
  //       error: (err) => {
  //         console.error('Identifiants invalides', err);
  //         Afficher un message d'erreur à l'utilisateur
  //       }
  //     });




    // FIXME : Si l'auth échoue, on est quand même redirigé
    //this.router.navigate([ '/home' ]);
  }
}
