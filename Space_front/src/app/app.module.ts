import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { provideHttpClient, withFetch, withInterceptors } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { EcranAccueilComponent } from './component/ecran-accueil/ecran-accueil.component';
import { CreationCompteComponent } from './component/creation-compte/creation-compte.component';
import { MenuPartieComponent } from './component/menu-partie/menu-partie.component';
import { SeConnecterComponent } from './component/se-connecter/se-connecter.component';
import { TopButtonsComponent } from './component/top-buttons/top-buttons.component';
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
import { jwtHeaderInterceptor } from './jwt-header.interceptor';

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
  providers: [
    provideHttpClient(withFetch(), withInterceptors([ jwtHeaderInterceptor ]))
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
