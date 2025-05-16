import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';

import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { CompteRequest } from '../compte-request';
import { CompteResponse } from '../compte-response';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-se-connecter',
  standalone: false,
  templateUrl: './se-connecter.component.html',
  styleUrl: './se-connecter.component.css'
})


export class SeConnecterComponent implements OnInit {
  public authForm!: FormGroup;
  public usernameCtrl!: FormControl;
  public passwordCtrl!: FormControl;
  public isError: boolean = false;

  constructor(
    private service: AuthService,
    private router: Router,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.usernameCtrl = this.formBuilder.control('', Validators.required);
    this.passwordCtrl = this.formBuilder.control('', Validators.required);

    this.authForm = this.formBuilder.group({
      username: this.usernameCtrl,
      password: this.passwordCtrl
    });
  }

  public authenticate(): void {
    const request = new CompteRequest(
      this.authForm.value.username,
      this.authForm.value.password
    );

    this.service.authenticate(request).subscribe({
      next: (response: CompteResponse) => {
        if (response.token) {
          const role = this.service.getRole();
          const userId = response.getId;
          const userUsername = request.username;

          // console.log('[menu-partie.component]',request);
          // console.log('[menu-partie.component]',response);
          // console.log('[menu-partie.component]',role);
          // console.log('[menu-partie.component]',userId);
          // console.log('[menu-partie.component]',response.token);
          // console.log('[menu-partie.component]',userUsername);

          if (role === 'ROLE_ADMIN') {
            this.router.navigate(['/menuAdmin']);
          } else if (role === 'ROLE_UTILISATEUR') {
            //this.router.navigate(['/menuPartie']);
            // this.router.navigate(['/menuPartie',userId]);
            
            this.router.navigate(['/menuPartie',userUsername]);
          } else {
            this.isError = true;
            console.error('Rôle non reconnu:', role);
          }
        } else {
          this.isError = true;
        }
      },
      error: () => {
        this.isError = true;
      }
    });
  }
}