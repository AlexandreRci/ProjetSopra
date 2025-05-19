import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Planete } from '../class/planete';
import { Observable, Subject, startWith, switchMap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PlaneteService {
  private API_URL = 'http://localhost:8080/planete';
  private refresh$: Subject<void> = new Subject<void>();

  constructor(private http: HttpClient) {}

  public refresh() {
    this.refresh$.next();
  }

  public findAll(): Observable<Planete[]> {
    return this.refresh$.pipe(
      startWith(null),
      switchMap(() => this.http.get<Planete[]>(this.API_URL))
    );
  }

  public save(planete: any): Observable<Planete> {
    if (planete.id) {
      return this.http.put<Planete>(`${this.API_URL}/${planete.id}`, planete);
    }
    return this.http.post<Planete>(this.API_URL, planete);
  }

  public delete(planete: Planete): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/${planete.id}`);
  }
}
