import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Espece } from './espece';
import { Observable, Subject, startWith, switchMap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EspeceService {
  private API_URL = 'http://localhost:8080/espece';
  private refresh$: Subject<void> = new Subject<void>();

  constructor(private http: HttpClient) {}

  public refresh() {
    this.refresh$.next();
  }

  public findAll(): Observable<Espece[]> {
    return this.refresh$.pipe(
      startWith(null),
      switchMap(() => this.http.get<Espece[]>(this.API_URL))
    );
  }

  public save(espece: any): Observable<Espece> {
    if (espece.id) {
      return this.http.put<Espece>(`${this.API_URL}/${espece.id}`, espece);
    }
    return this.http.post<Espece>(this.API_URL, espece);
  }

  public delete(espece: Espece): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/${espece.id}`);
  }
}
