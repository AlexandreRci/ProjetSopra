import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Planete } from './planete';

@Injectable({
  providedIn: 'root'
})
export class PlaneteService {
  private API_URL: string = `http://localhost:8080/planete`;
  
  constructor(private http: HttpClient){} 
  
  public getPlanetes(): Observable<Planete[]> { 
    return this.http.get<Planete[]>(this.API_URL)
  }
}
