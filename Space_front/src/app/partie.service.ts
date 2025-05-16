import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of, Subject } from 'rxjs';
import { catchError, map, startWith, switchMap } from 'rxjs/operators';
import { Partie } from './partie';

@Injectable({
  providedIn: 'root'
})
export class PartieService {
  private refresh$: Subject<void> = new Subject<void>();
  private API_URL: string = `http://localhost:8080/partie`;

  constructor(private http: HttpClient) { }

  public refresh() {
    this.refresh$.next();
  }

  public findAll() {
  return this.refresh$.pipe(
    startWith(null),
    switchMap(() => this.http.get<Partie[]>(this.API_URL))
  );
}
  public createPartie(partie: Partie) {
    console.log('[partie.service.ts] Info sur partie depuis createPartie()',partie);
    if (partie.id) {
      console.log('[partie.service.ts] Info sur partie depuis createPartie() passage dans le if AVEC id',partie);
      return this.http.put<Partie>(`${this.API_URL}/${partie.id}`, partie);
    }
    console.log('[partie.service.ts] Info sur partie depuis createPartie() passage dans le if SANS id',partie);
    return this.http.post<Partie>(this.API_URL, partie);
  }

  public getById(id: number) {
    return this.http.get<Partie>(`${this.API_URL}/${id}`).pipe(
      map(partie => partie),
      catchError(() => of(null)) 
    );
  }

  public deletePartie(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/${id}`);
  }
  
public updatePartie(id: number, newData: Partial<Partie>) {
  return this.getById(id).pipe(
    switchMap(existingPartie => {
      if (!existingPartie) return of(null);

      const updatedPartie: Partie = new Partie(
        id,
        newData.currentPosition ?? existingPartie.currentPosition,
        newData.nbTour ?? existingPartie.nbTour,
        newData.nbJoueur ?? existingPartie.nbJoueur,
        newData.planetSeeds ?? existingPartie.planetSeeds,
        newData.statut ?? existingPartie.statut
      );

      return this.http.put<Partie>(`${this.API_URL}/${id}`, updatedPartie);
    })
  );
}


}
