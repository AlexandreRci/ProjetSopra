import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EcranAccueilComponent } from './ecran-accueil/ecran-accueil.component';
import { CreationCompteComponent } from './creation-compte/creation-compte.component';
import { MenuPartieComponent } from './menu-partie/menu-partie.component';
import { SeConnecterComponent } from './se-connecter/se-connecter.component';

@NgModule({
  declarations: [
    AppComponent,
    EcranAccueilComponent,
    CreationCompteComponent,
    MenuPartieComponent,
    SeConnecterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
