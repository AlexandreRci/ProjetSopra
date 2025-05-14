import { HttpInterceptorFn } from '@angular/common/http';
import { AuthService } from './auth.service';
import { inject } from '@angular/core';

export const jwtHeaderInterceptor: HttpInterceptorFn = (req, next) => {
  const authService: AuthService = inject(AuthService);

  console.log('URL appelée par interceptor:',req.url);
  
  // Exclus certaines requêtes de l'ajout de l'en-tête d'autorisation
  // On fait return dès maintenant car pas intéressant de récupérer le token
  if (req.url.endsWith("/connexion")){
    console.log('Skip le l autorisation pour le header pour l URL:',req.url);
    return next(req);
  }

  // Clone la requête et ajoute l'en-tête d'autorisation
  const authRequest = req.clone({
    setHeaders: {
      'Authorization': `Bearer ${authService.token}`
    }
  });

  // Ces lignes sont doivent apparaître lorsque l'utilisateur créer une partie
  console.log('Ajouter l autorisation du header pour l URL:', req.url);
  console.log('Autorisation header', `Bearer ${authService.token}`);

  return next(authRequest);
};
