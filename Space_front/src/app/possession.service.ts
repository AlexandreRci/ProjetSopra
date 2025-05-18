import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { Possession } from './possession';

@Injectable({
  providedIn: 'root'
})
export class PossessionService {
  private refresh$ = new Subject<void>();
  private API_URL = 'http://localhost:8080/possession';

  constructor(private http: HttpClient) {}

  public refresh() {
    this.refresh$.next();
  }

  public findAll(): Observable<Possession[]> {
    return this.refresh$.pipe(
      startWith(null),
      switchMap(() => this.http.get<Possession[]>(this.API_URL))
    );
  }

  public save(possession: any): Observable<Possession> {
    if (possession.id) {
      return this.http.put<Possession>(`${this.API_URL}/${possession.id}`, possession);
    }
    return this.http.post<Possession>(this.API_URL, possession);
  }

  public delete(possession: Possession): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/${possession.id}`);
  }
}
