import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Joueur } from './joueur';

@Injectable({
  providedIn: 'root'
})
export class JoueurService {
  private API_URL: string = `http://localhost:8080/joueur`;

  constructor(private http: HttpClient) { }

  public createJoueur(joueur: Joueur): Observable<Joueur> {
    return this.http.post<Joueur>(this.API_URL, joueur);
  }
}