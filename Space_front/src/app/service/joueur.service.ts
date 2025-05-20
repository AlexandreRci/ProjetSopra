import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Joueur } from '../class/joueur';
import { Observable, startWith, Subject, switchMap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class JoueurService {
  private API_URL = 'http://localhost:8080/joueur';
  private refresh$ = new Subject<void>();

  constructor(private http: HttpClient) {}

  public refresh(): void {
    this.refresh$.next();
  }

  public findAll(): Observable<Joueur[]> {
    return this.refresh$.pipe(
      startWith(null),
      switchMap(() => this.http.get<Joueur[]>(this.API_URL))
    );
  }

  public save(joueur: any): Observable<Joueur> {
    if (joueur.id) {
      return this.http.put<Joueur>(`${this.API_URL}/${joueur.id}`, joueur);
    }
    return this.http.post<Joueur>(this.API_URL, joueur);
  }

  public delete(joueur: Joueur): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/${joueur.id}`);
  }

  public findById(id: number): Observable<Joueur> {
    return this.http.get<Joueur>(`${this.API_URL}/${id}`);
  }
}
