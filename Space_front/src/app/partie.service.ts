import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Partie } from './partie';

@Injectable({
  providedIn: 'root'
})
export class PartieService {
  private API_URL: string = `http://localhost:8080/partie`;

  constructor(private http: HttpClient) { }

  public createPartie(partie: any) {
    if (partie.id) {
      return this.http.put<Partie>(`${this.API_URL}/${partie.id}`, partie);
    }
    return this.http.post<Partie>(this.API_URL, partie);
  }

  public checkExistingPartie(id: number): Observable<Partie | null> {

    return this.http.get<Partie>(`${this.API_URL}/${id}`).pipe(
      map(partie => partie),
      catchError(() => of(null)) 
    );
  }

  public deletePartie(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/${id}`);
  }
}
