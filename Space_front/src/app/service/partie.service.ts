import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Partie } from '../class/partie'
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { StartRequest } from '../class/request/start-request';
import { StartResponse } from '../class/response/start-response';

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

  public findAllByIdUser(idUser: number): Observable<Partie[]> {
    return this.refresh$.pipe(
      startWith(null),
      switchMap(() => this.http.get<Partie[]>(`${this.API_URL}/user/${idUser}`))
    );
  }

  public save(partie: any): Observable<Partie> {
    console.log('partie', partie);
    if (partie.id) {
      return this.http.put<Partie>(`${this.API_URL}/${partie.id}`, partie);
    }
    return this.http.post<Partie>(this.API_URL, partie);
  }

  public delete(partie: Partie): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/${partie.id}`);
  }

  public start(startRequest: StartRequest): Observable<StartResponse> {
    return this.http.post<StartResponse>(`${this.API_URL}/start`, startRequest);
  }

  public findById(id: number): Observable<Partie> {
    return this.http.get<Partie>(`${this.API_URL}/${id}`);
  }
}
