import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EcranAccueilComponent } from './ecran-accueil/ecran-accueil.component';
import { CreationCompteComponent } from './creation-compte/creation-compte.component';
import { MenuPartieComponent } from './menu-partie/menu-partie.component';
import { SeConnecterComponent } from './se-connecter/se-connecter.component';

const routes: Routes = [
  { path: 'ecranAcceuil', component: EcranAccueilComponent },
  { path: 'seConnecter', component: SeConnecterComponent },
  { path: 'creationCompte', component: CreationCompteComponent },
  { path: 'menuPartie', component: MenuPartieComponent },
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
