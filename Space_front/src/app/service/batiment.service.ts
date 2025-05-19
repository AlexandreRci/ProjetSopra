import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Batiment } from '../class/batiment';
import { Observable, Subject, startWith, switchMap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BatimentService {
  private API_URL = 'http://localhost:8080/batiment';
  private refresh$: Subject<void> = new Subject<void>();

  constructor(private http: HttpClient) {}

  public refresh() {
    this.refresh$.next();
  }

  public findAll(): Observable<Batiment[]> {
    return this.refresh$.pipe(
      startWith(null),
      switchMap(() => this.http.get<Batiment[]>(this.API_URL))
    );
  }

  public save(batiment: any): Observable<Batiment> {
    if (batiment.id) {
      return this.http.put<Batiment>(`${this.API_URL}/${batiment.id}`, batiment);
    }
    return this.http.post<Batiment>(this.API_URL, batiment);
  }

  public delete(batiment: Batiment): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/${batiment.id}`);
  }
}
