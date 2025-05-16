import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EcranAccueilComponent } from './ecran-accueil/ecran-accueil.component';
import { CreationCompteComponent } from './creation-compte/creation-compte.component';
import { MenuPartieComponent } from './menu-partie/menu-partie.component';
import { SeConnecterComponent } from './se-connecter/se-connecter.component';
import { EcranJeuComponent } from './ecran-jeu/ecran-jeu.component';
import { MenuAdminComponent } from './menu-admin/menu-admin.component';

import { PartieComponent } from './menu-admin/partie/partie.component';
import { JoueurComponent } from './menu-admin/joueur/joueur.component';
import { CompteComponent } from './menu-admin/compte/compte.component';
import { PlaneteComponent } from './menu-admin/planete/planete.component';
import { PlanetSeedComponent } from './menu-admin/planet-seed/planet-seed.component';
import { EspeceComponent } from './menu-admin/espece/espece.component';
import { PossessionComponent } from './menu-admin/possession/possession.component';
import { BatimentComponent } from './menu-admin/batiment/batiment.component';

const routes: Routes = [
  { path: 'ecranAccueil', component: EcranAccueilComponent },
  { path: 'seConnecter', component: SeConnecterComponent },
  { path: 'creationCompte', component: CreationCompteComponent },
  { path: 'menuPartie/:username', component: MenuPartieComponent },
  // { path: 'ecranJeu', component: EcranJeuComponent },
  { path: 'ecranJeu/:id', component: EcranJeuComponent },
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
