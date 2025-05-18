import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EcranAccueilComponent } from './component/ecran-accueil/ecran-accueil.component';
import { CreationCompteComponent } from './component/creation-compte/creation-compte.component';
import { MenuPartieComponent } from './component/menu-partie/menu-partie.component';
import { SeConnecterComponent } from './component/se-connecter/se-connecter.component';
import { EcranJeuComponent } from './component/ecran-jeu/ecran-jeu.component';
import { MenuAdminComponent } from './component/menu-admin/menu-admin.component';

import { PartieComponent } from './component/menu-admin/partie/partie.component';
import { JoueurComponent } from './component/menu-admin/joueur/joueur.component';
import { CompteComponent } from './component/menu-admin/compte/compte.component';
import { PlaneteComponent } from './component/menu-admin/planete/planete.component';
import { PlanetSeedComponent } from './component/menu-admin/planet-seed/planet-seed.component';
import { EspeceComponent } from './component/menu-admin/espece/espece.component';
import { PossessionComponent } from './component/menu-admin/possession/possession.component';
import { BatimentComponent } from './component/menu-admin/batiment/batiment.component';

const routes: Routes = [
  { path: 'ecranAccueil', component: EcranAccueilComponent },
  { path: 'seConnecter', component: SeConnecterComponent },
  { path: 'creationCompte', component: CreationCompteComponent },
  { path: 'menuPartie', component: MenuPartieComponent },
  { path: 'ecranJeu', component: EcranJeuComponent },
  { path: 'menuAdmin', component: MenuAdminComponent },

  { path: 'menuAdmin/parties', component: PartieComponent },
  { path: 'menuAdmin/joueurs', component: JoueurComponent },
  { path: 'menuAdmin/comptes', component: CompteComponent },
  { path: 'menuAdmin/planetes', component: PlaneteComponent },
  { path: 'menuAdmin/planetSeeds', component: PlanetSeedComponent },
  { path: 'menuAdmin/especes', component: EspeceComponent },
  { path: 'menuAdmin/possessions', component: PossessionComponent },
  { path: 'menuAdmin/batiments', component: BatimentComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
