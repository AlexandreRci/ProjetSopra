import { Component, OnInit, OnDestroy } from '@angular/core';
import { CompteService } from '../../service/compte.service';
import { Planete } from '../../class/planete';
import { PlanetSeed } from '../../class/planet-seed';
import { ActivatedRoute } from '@angular/router';
import { PartieService } from '../../service/partie.service';
import { PlanetSeedService } from '../../service/planet-seed.service';
import { Partie } from '../../class/partie';
import { PlaneteService } from '../../service/planete.service';
import { JoueurService } from '../../service/joueur.service';
import { first, forkJoin, Subject, Subscription, switchMap, takeUntil } from 'rxjs';
import { PossessionService } from '../../service/possession.service';
import { Joueur } from '../../class/joueur';
import { Possession } from '../../class/possession';

@Component({
  selector: 'app-ecran-jeu',
  standalone: false,
  templateUrl: './ecran-jeu.component.html',
  styleUrl: './ecran-jeu.component.css'
})
export class EcranJeuComponent {
  isAnimated = true; // Contrôle l'état de l'animation planet-display
  expandedPlanetIndex: number | null = null; // Variable permettant de selectionner quel planete est "déplié"
  timeLeft: number = 90; // Temps restant en secondes
  timer: any;
  username: string = '';
  partieId: number = 0;
  playerId: number = 0;
  energie: number = 0;
  arme: number = 0;
  nourriture: number = 0;
  argent: number = 0;
  partie!: Partie;
  joueur!: Joueur;
  posessions: Possession[] = [];
  public planetSeeds: any[] = [];
  public planets: any[] = [];
  private destroy$ = new Subject<void>();
  


  constructor(
    private partieService: PartieService,
    private route: ActivatedRoute,
    private planetSeedService: PlanetSeedService,
    private planeteService: PlaneteService,
    private joueurService: JoueurService,
    private compteService: CompteService,
    private possessionService: PossessionService
  ) { }


  ngOnInit(): void {
    this.startTimer();
    this.route.params.pipe(takeUntil(this.destroy$)).subscribe(params => {
      this.partieId = +params['idPartie'];
      this.playerId = +params['idPlayer']; // Convert to number

      this.joueurService.findById(this.playerId).pipe(
        first(),
        takeUntil(this.destroy$),
        switchMap(joueur => {
          this.joueur = joueur;
          console.log(joueur);
          const possessionRequests = joueur.idPossessions.map(idpossession =>
            this.possessionService.findById(idpossession).pipe(first())
          );
          return forkJoin(possessionRequests);
        }
        )).subscribe(possessions => {this.posessions = possessions;
          console.log(possessions);
        });
        console.log(this.posessions);
        for (const possession of this.posessions) {
          if (possession.ressource === 'ENERGIE') {
            this.energie = possession.quantite;
          } else if (possession.ressource === 'ARME') {
            this.arme = possession.quantite;
          } else if (possession.ressource === 'NOURRITURE') {
            this.nourriture = possession.quantite;
          } else if (possession.ressource === 'ARGENT') {
            this.argent = possession.quantite;
          }
        }

      this.partieService.findById(this.partieId).pipe(
        first(),
        takeUntil(this.destroy$),
        switchMap(partie => {
          this.partie = partie;
          console.log(partie);

          const planetSeedRequests = partie.planetSeeds.map(idplanetSeed =>
            this.planetSeedService.getById(idplanetSeed).pipe(first())
          );

          return forkJoin(planetSeedRequests);
        })
      ).subscribe(planetSeeds => {
        this.planetSeeds = planetSeeds;

        const planetRequests = planetSeeds.map(planetSeed =>
          this.planeteService.findById(planetSeed.idPlanete).pipe(first())
        );

        forkJoin(planetRequests).subscribe(planets => {
          this.planets = planets;
        });
      });
    });
  }



  ngOnDestroy() {
    this.clearTimer();
    this.destroy$.next();
    this.destroy$.complete();
  }

  startTimer() {
    this.timer = setInterval(() => {
      if (this.timeLeft > 0) {
        this.timeLeft--;
      } else {
        this.clearTimer();
      }
    }, 1000);
  }

  clearTimer() {
    if (this.timer) {
      clearInterval(this.timer);
    }
  }

  formatTime(seconds: number): string {
    const minutes: number = Math.floor(seconds / 60);
    const remainingSeconds: number = seconds % 60;
    return `${minutes}:${remainingSeconds < 10 ? '0' : ''}${remainingSeconds}`;
  }

  togglePlanetInfo(index: number) {
    // Fonction permettant de déplier les informations d'une planete
    // En vrai, on peut le réduire à un ternaire mais c'est plus lisible pour moi
    if (this.expandedPlanetIndex === index) {
      this.expandedPlanetIndex = null;
    } else {
      this.expandedPlanetIndex = index;
    }
  }
  
}
