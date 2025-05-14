import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { CompteRequest } from './compte-request';
import { CompteResponse } from './compte-response';
import { jwtDecode } from 'jwt-decode';

export interface JwtPayload {
  sub: string;
  role: string;
  exp: number;
  iat: number;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  public token: string = '';
  private API_URL: string = 'http://localhost:8080/connexion';

  constructor(private http: HttpClient) {
    this.token = localStorage.getItem('token') ?? '';
  }

  public authenticate(authRequest: CompteRequest): Observable<CompteResponse> {
    return this.http.post<CompteResponse>(this.API_URL, {
      username: authRequest.username,
      password: authRequest.password
    }).pipe(
      tap((resp: CompteResponse) => {
        if (resp.token) {
          this.token = resp.token;
          localStorage.setItem('token', this.token);

          const payload: JwtPayload = jwtDecode(this.token);
          localStorage.setItem('role', payload.role); // 👈 Enregistre le rôle aussi
        }
      })
    );
  }

  public getRole(): string | null {
    return localStorage.getItem('role');
  }
}
