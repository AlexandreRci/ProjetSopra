import { Component } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { StartRequest } from '../../class/request/start-request';
import { PartieService } from '../../service/partie.service';
import { StartResponse } from '../../class/response/start-response';
import { Espece } from '../../class/espece';
import { EspeceService } from '../../service/espece.service';

@Component({
  selector: 'app-choice-espece',
  standalone: false,
  templateUrl: './choice-espece.component.html',
  styleUrl: './choice-espece.component.css'
})
export class ChoiceEspeceComponent {
  public selectForm!: FormGroup;
  public especeCtrl!: FormControl;
  public isError: boolean = false;
  public especeList: Espece[] = [];

  constructor(
    private service: PartieService,
    private especeService: EspeceService,
    private router: Router,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.especeCtrl = this.formBuilder.control('', Validators.required);

    this.selectForm = this.formBuilder.group({
      especeId: this.especeCtrl,
    });

    this.especeService.findAll().subscribe({
      next: (response: any) => {
        this.especeList = response;
      },
      error: () => {
        this.isError = true;
      }   
    });


  }

  public startGame(): void {
    console.log(this.selectForm.value.idEspece);
    const request = new StartRequest(
      parseInt(localStorage.getItem('userId') ?? '0'),
      this.selectForm.value.especeId
    );
    this.service.start(request).subscribe({
      next: (response: StartResponse) => {
        this.router.navigate(['/ecranJeu/' + response.idGame + "/" + response.idPlayer]);
      },
      error: () => {
        this.isError = true;
      }
    });
  }
}
