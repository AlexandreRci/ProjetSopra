import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { Compte } from './compte';

@Injectable({
  providedIn: 'root'
})

export class CompteService {
  private refresh$: Subject<void> = new Subject<void>();
  private API_URL: string =  `http://localhost:8080/compte`;

  constructor(private http: HttpClient) { }

  public refresh() {
    this.refresh$.next();
  }

  public save(compte: any) {
    if (compte.id) {
      return this.http.put<Compte>(`${ this.API_URL }/${ compte.id }`, compte);
    }
    return this.http.post<Compte>(this.API_URL, compte);
  }





}
