import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CompteService } from '../../compte.service';
import { Compte } from '../../compte';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-compte',
  templateUrl: './compte.component.html',
  styleUrls: ['./compte.component.css'],
  standalone: false
})
export class CompteComponent implements OnInit, OnDestroy {
  compteForm!: FormGroup;
  comptes$!: Observable<Compte[]>;
  editingCompte: Compte | null = null;
  subscriptions: any = {};
  types = ['ADMIN', 'UTILISATEUR'];

  constructor(private service: CompteService, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.compteForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      compteType: ['', Validators.required],
      name: ['']
    });

    this.comptes$ = this.service.findAll();
  }

  ngOnDestroy(): void {
    this.unsub('save');
    this.unsub('delete');
  }

  addOrEditCompte(): void {
    this.unsub('save');

    const payload = {
      id: this.editingCompte?.id ?? null,
      username: this.compteForm.value.username,
      password: this.compteForm.value.password,
      compteType: this.compteForm.value.compteType,
      name: this.compteForm.value.name || null
    };

    this.subscriptions['save'] = this.service.save(payload).subscribe(() => {
      this.service.refresh();
      this.compteForm.reset();
      this.editingCompte = null;
    });
  }

  editCompte(compte: Compte): void {
    this.editingCompte = compte;
    this.compteForm.patchValue({
      username: compte.username,
      password: '', // Ne pas afficher le mot de passe pour des raisons de sécurité
      compteType: compte.compteType,
      name: compte.name ?? ''
    });
  }

  deleteCompte(compte: Compte): void {
    this.unsub('delete');
    this.subscriptions['delete'] = this.service.delete(compte).subscribe(() => {
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
