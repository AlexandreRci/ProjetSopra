import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, Subject, startWith, switchMap } from 'rxjs';
import { PlanetSeed } from './planet-seed';

@Injectable({
  providedIn: 'root'
})
export class PlanetSeedService {
  private API_URL = 'http://localhost:8080/planetSeed';
  private refresh$: Subject<void> = new Subject<void>();

  constructor(private http: HttpClient) {}

  public refresh(): void {
    this.refresh$.next();
  }

  public findAll(): Observable<PlanetSeed[]> {
    return this.refresh$.pipe(
      startWith(null),
      switchMap(() => this.http.get<PlanetSeed[]>(this.API_URL))
    );
  }

  public save(planetSeed: any): Observable<PlanetSeed> {
    if (planetSeed.id) {
      return this.http.put<PlanetSeed>(`${this.API_URL}/${planetSeed.id}`, planetSeed);
    }
    return this.http.post<PlanetSeed>(this.API_URL, planetSeed);
  }

  public delete(planetSeed: PlanetSeed): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/${planetSeed.id}`);
  }

  public getById(id: number): Observable<PlanetSeed> {
    return this.http.get<PlanetSeed>(`${this.API_URL}/${id}`);
  }
}
