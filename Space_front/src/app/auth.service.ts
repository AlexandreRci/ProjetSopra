import { Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { CompteRequest } from './compte-request';
import { CompteResponse } from './compte-response';

@Injectable({
  providedIn: 'root'
})
export class AuthService  {
  public token: string = "";
  private API_URL: string = `http://localhost:8080/api/connexion`;
  
  constructor(private http: HttpClient) {
    this.token = localStorage.getItem('token') as string;
  }


  

  public authenticate(authRequest: CompteRequest) {
    this.http.post<CompteResponse>(this.API_URL, {
      username: authRequest.username,
      password: authRequest.password
    }).subscribe(resp => {
      this.token = resp.getToken();
      localStorage.setItem('token', this.token)
    });
  }



}
