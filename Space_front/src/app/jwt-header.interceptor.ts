import { HttpInterceptorFn } from '@angular/common/http';
import { AuthService } from './auth.service';
import { inject } from '@angular/core';

export const jwtHeaderInterceptor: HttpInterceptorFn = (req, next) => {
  const authService: AuthService = inject(AuthService);

  console.log('URL appelée par interceptor:',req.url);
  
  if (req.url.endsWith("/connexion")){
    console.log('Skip le l autorisation pour le header pour l URL:',req.url);
    return next(req);
  }
  
  const authRequest = req.clone({
    setHeaders: {
      'Authorization': `Bearer ${authService.token}`
    }
  });

  console.log('Ajouter l autorisation du header pour l URL:', req.url);
  console.log('Autorisation header', `Bearer ${authService.token}`);

  return next(authRequest);
};
