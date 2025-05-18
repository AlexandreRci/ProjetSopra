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

  public save(joueur: any): Observable<any> {
    console.log('[joueur.service] le joueur a bien été crée', joueur);
    return this.http.post(this.API_URL, joueur);
  }

  public getAll(): Observable<Joueur[]> {
    return this.http.get<Joueur[]>(this.API_URL);
  }

  public getById(id: number): Observable<Joueur> {
    return this.http.get<Joueur>(`${this.API_URL}/${id}`);
  }  

  public updateJoueur(joueur: Joueur): Observable<Joueur> {
    if (!joueur.id) {
      throw new Error('L\'ID du joueur est requis pour la mise à jour');
    }
    return this.http.put<Joueur>(`${this.API_URL}/${joueur.id}`, joueur);
  }

  public deleteJoueur(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/${id}`);
  }  

}
