import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { Partie } from './partie';

@Injectable({
  providedIn: 'root'
})
export class PartieService {
  private refresh$: Subject<void> = new Subject<void>();
  private API_URL: string = 'http://localhost:8080/partie';

  constructor(private http: HttpClient) { }

  public refresh() {
    this.refresh$.next();
  }

  public findAll(): Observable<Partie[]> {
    return this.refresh$.pipe(
      startWith(null),
      switchMap(() => this.http.get<Partie[]>(this.API_URL))
    );
  }

  public save(partie: any): Observable<Partie> {
    if (partie.id) {
      return this.http.put<Partie>(`${this.API_URL}/${partie.id}`, partie);
    }
    return this.http.post<Partie>(this.API_URL, partie);
  }

  public delete(partie: Partie): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/${partie.id}`);
  }
}
