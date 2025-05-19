import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable, Subscription } from 'rxjs';
import { Possession } from '../../possession';
import { PossessionService } from '../../possession.service';

@Component({
  selector: 'app-possession',
  templateUrl: './possession.component.html',
  styleUrls: ['./possession.component.css'],
  standalone: false
})
export class PossessionComponent implements OnInit, OnDestroy {
  possessionForm!: FormGroup;
  possessions$!: Observable<Possession[]>;
  editingPossession: Possession | null = null;
  subscriptions: any = {};

  constructor(private service: PossessionService, private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.possessionForm = this.formBuilder.group({
      quantite: ['', [Validators.required, Validators.min(0)]],
      ressource: ['', Validators.required]
    });

    this.possessions$ = this.service.findAll();
  }

  ngOnDestroy(): void {
    this.unsub('save');
    this.unsub('delete');
  }

  public addOrEditPossession(): void {
    this.unsub('save');

    const payload = {
      id: this.editingPossession?.id,
      ...this.possessionForm.value
    };

    this.subscriptions['save'] = this.service.save(payload).subscribe(() => {
      this.service.refresh();
      this.possessionForm.reset();
      this.editingPossession = null;
    });
  }

  public editPossession(possession: Possession): void {
    this.editingPossession = possession;
    this.possessionForm.patchValue({
      quantite: possession.quantite,
      ressource: possession.ressource
    });
  }

  public deletePossession(possession: Possession): void {
    this.unsub('delete');
    this.subscriptions['delete'] = this.service.delete(possession).subscribe(() => {
      this.service.refresh();
    });
  }

  private unsub(name: string): void {
    if (this.subscriptions[name]) {
      this.subscriptions[name].unsubscribe();
      this.subscriptions[name] = null;
    }
  }
}
