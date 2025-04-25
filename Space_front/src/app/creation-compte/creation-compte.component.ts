import { Component, OnDestroy, OnInit } from '@angular/core';
import { CompteService } from '../compte.service';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import {Compte} from '../compte';

@Component({
  selector: 'app-creation-compte',
  standalone: false,
  templateUrl: './creation-compte.component.html',
  styleUrl: './creation-compte.component.css'
})
export class CreationCompteComponent{
  public compteForm!: FormGroup;
  public name!:  FormControl;
  public username!: FormControl;
  public password!:  FormControl;
  


  constructor(private service: CompteService, private formBuilder: FormBuilder ){
    this.name = this.formBuilder.control('', Validators.required);
    this.username = this.formBuilder.control('', Validators.required);
    this.password = this.formBuilder.control('', [ Validators.required ]);

    this.compteForm = this.formBuilder.group({
      name: this.name,
      username: this.username,
      compteType:"UTILISATEUR",
      password: this.password
    });
  }

  public addCompte() {
    this.service.save({
      ...this.compteForm.value
    });
    console.log("compte ajouté");

  }






}
