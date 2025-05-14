import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Partie } from './partie'

@Injectable({
  providedIn: 'root'
})
export class PartieService {
  private API_URL: string =  `http://localhost:8080/partie`;
 
  constructor(private http: HttpClient) { }

  public createPartie(partie: any){
    if (partie.id){
        return this.http.put<Partie>(`${this.API_URL}/${partie.id}`, partie)
    }
    return this.http.post<Partie>(this.API_URL, partie)


  }


}
