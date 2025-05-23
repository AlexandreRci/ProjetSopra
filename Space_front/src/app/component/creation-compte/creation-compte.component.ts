import { Component, OnInit } from '@angular/core';
import { CompteService } from '../../service/compte.service';
import { AbstractControl, FormBuilder, FormControl, FormGroup, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { Compte } from '../../class/compte';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-creation-compte',
  standalone: false,
  templateUrl: './creation-compte.component.html',
  styleUrl: './creation-compte.component.css'
})
export class CreationCompteComponent implements OnInit {
  public compteForm!: FormGroup;
  public name!: FormControl;
  public username!: FormControl;
  public password1!: FormControl;
  public password2!: FormControl;

  public compteCree: boolean = false;
  public usernameExiste: boolean = false;

  constructor(private service: CompteService, private formBuilder: FormBuilder, private router: Router) {}

  ngOnInit(): void {
    this.name = this.formBuilder.control('', Validators.required);
    this.username = this.formBuilder.control('', Validators.required);
    this.password1 = this.formBuilder.control('', [Validators.required, Validators.minLength(8), Validators.maxLength(20)]);
    this.password2 = this.formBuilder.control('', [Validators.required, Validators.pattern(this.password1.value)]);

    this.compteForm = this.formBuilder.group({
      name: this.name,
      username: this.username,
      password1: this.password1,
      password2: this.password2,
    }, { validators: passwordMatchValidator() });
  }

  public addCompte(): void {
    console.log("Création d'un compte", this.compteForm.value);

    this.service.save(new Compte(
      null,
      this.compteForm.value.name,
      this.compteForm.value.username,
      this.compteForm.value.password1,
      "UTILISATEUR"
    )).subscribe({
      next: (response) => {
        console.log("Réponse du serveur", response);
        this.compteForm.reset();
        this.usernameExiste = false;
        this.compteCree = true;

        setTimeout(() => {
          this.router.navigate(['/seConnecter']);
        }, 4000);
      },
      error: (error: HttpErrorResponse) => {
        console.error('Erreur lors de la création du compte', error);
        if (error.status === 409) {
          this.usernameExiste = true;
        }
      }
    });
  }
}

function passwordMatchValidator(): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    const password1 = control.get('password1')?.value;
    const password2 = control.get('password2')?.value;

    return password1 && password2 && password1 !== password2 ? { passwordMismatch: true } : null;
  };
}
