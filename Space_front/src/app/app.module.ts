import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { provideHttpClient, withFetch, withInterceptors } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { EcranAccueilComponent } from './ecran-accueil/ecran-accueil.component';
import { CreationCompteComponent } from './creation-compte/creation-compte.component';
import { MenuPartieComponent } from './menu-partie/menu-partie.component';
import { SeConnecterComponent } from './se-connecter/se-connecter.component';
import { TopButtonsComponent } from './top-buttons/top-buttons.component';
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

@NgModule({
  declarations: [
    AppComponent,
    EcranAccueilComponent,
    CreationCompteComponent,
    MenuPartieComponent,
    SeConnecterComponent,
    TopButtonsComponent,
    EcranJeuComponent,
    MenuAdminComponent,
    PartieComponent,
    JoueurComponent,
    CompteComponent,
    PlaneteComponent,
    PlanetSeedComponent,
    EspeceComponent,
    PossessionComponent,
    BatimentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [provideHttpClient(withFetch()) ],
  bootstrap: [AppComponent]
})
export class AppModule { }
