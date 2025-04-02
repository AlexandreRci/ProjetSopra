package sopra.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import sopra.context.Singleton;
import sopra.model.*;
import sopra.service.*;

public class TestJPA {

    static CompteService compteService = Singleton.getInstance().getCompteSrv();
    static PartieService partieService = Singleton.getInstance().getPartieSrv();
    static JoueurService joueurService = Singleton.getInstance().getJoueurSrv();
    static EspeceService especeService = Singleton.getInstance().getEspeceSrv();
    static PlaneteService planeteService = Singleton.getInstance().getPlaneteSrv();
    static PlanetSeedService planetSeedService = Singleton.getInstance().getPlanetSeedSrv();
    static PossessionService possessionService = Singleton.getInstance().getPossessionSrv();

    public static void main(String[] args) {
        Utilisateur utilisateur1 = new Utilisateur("login_judy", "password_judy", "Judy");
        Utilisateur utilisateur2 = new Utilisateur("login_AlexL", "password_AlexL", "AlexL");
        Utilisateur utilisateur3 = new Utilisateur("login_AlexR", "password_AlexR", "AlexR");
        Utilisateur utilisateur4 = new Utilisateur("login_Yvan", "password_Yvan", "Yvan");

        utilisateur1 = (Utilisateur) compteService.create(utilisateur1);
        utilisateur2 = (Utilisateur) compteService.create(utilisateur2);
        utilisateur3 = (Utilisateur) compteService.create(utilisateur3);
        utilisateur4 = (Utilisateur) compteService.create(utilisateur4);

        System.out.println(utilisateur1);
        System.out.println(utilisateur2);
        System.out.println(utilisateur3);
        System.out.println(utilisateur4);


        Map<Biome, Double> biomesMap = new HashMap<>();
        biomesMap.put(Biome.Plaine, 1.0);
        biomesMap.put(Biome.Foret, 0.75);
        biomesMap.put(Biome.Desertique, 0.5);
        biomesMap.put(Biome.Ocean, 0.25);


        Partie partie1 = new Partie(1, 5, 2, Statut.Debut);
        Partie partie2 = new Partie(2, 7, 4, Statut.EnCours);
        Partie partie3 = new Partie(3, 11, 4, Statut.EnCours);
        Partie partie4 = new Partie(4, 15, 3, Statut.Fini);

        partie1 = partieService.create(partie1);
        partie2 = partieService.create(partie2);
        partie3 = partieService.create(partie3);
        partie4 = partieService.create(partie4);

        System.out.println(partie1);
        System.out.println(partie2);
        System.out.println(partie3);
        System.out.println(partie4);


        // A lire "1_1", "1_2"..."2_4"
        Possession possessionArme1 = new Possession(105, Ressource.Arme);
        Possession possessionArme2 = new Possession(293, Ressource.Arme);
        Possession possessionArme3 = new Possession(316, Ressource.Arme);
        Possession possessionArme4 = new Possession(413, Ressource.Arme);

        Possession possessionNourriture1 = new Possession(108, Ressource.Nourriture);
        Possession possessionNourriture2 = new Possession(210, Ressource.Nourriture);
        Possession possessionNourriture3 = new Possession(393, Ressource.Nourriture);
        Possession possessionNourriture4 = new Possession(453, Ressource.Nourriture);

        Possession possessionEnergie1 = new Possession(100, Ressource.Energie);
        Possession possessionEnergie2 = new Possession(230, Ressource.Energie);
        Possession possessionEnergie3 = new Possession(316, Ressource.Energie);
        Possession possessionEnergie4 = new Possession(453, Ressource.Energie);

        Possession possessionArgent1 = new Possession(125, Ressource.Argent);
        Possession possessionArgent2 = new Possession(240, Ressource.Argent);
        Possession possessionArgent3 = new Possession(345, Ressource.Argent);
        Possession possessionArgent4 = new Possession(484, Ressource.Argent);

        possessionArme1 = possessionService.create(possessionArme1);
        possessionArme2 = possessionService.create(possessionArme2);
        possessionArme3 = possessionService.create(possessionArme3);
        possessionArme4 = possessionService.create(possessionArme4);

        System.out.println(possessionArme1);
        System.out.println(possessionArme2);
        System.out.println(possessionArme3);
        System.out.println(possessionArme4);

        possessionNourriture1 = possessionService.create(possessionNourriture1);
        possessionNourriture2 = possessionService.create(possessionNourriture2);
        possessionNourriture3 = possessionService.create(possessionNourriture3);
        possessionNourriture4 = possessionService.create(possessionNourriture4);

        System.out.println(possessionNourriture1);
        System.out.println(possessionNourriture2);
        System.out.println(possessionNourriture3);
        System.out.println(possessionNourriture4);

        possessionEnergie1 = possessionService.create(possessionEnergie1);
        possessionEnergie2 = possessionService.create(possessionEnergie2);
        possessionEnergie3 = possessionService.create(possessionEnergie3);
        possessionEnergie4 = possessionService.create(possessionEnergie4);

        System.out.println(possessionEnergie1);
        System.out.println(possessionEnergie2);
        System.out.println(possessionEnergie3);
        System.out.println(possessionEnergie4);

        possessionArgent1 = possessionService.create(possessionArgent1);
        possessionArgent2 = possessionService.create(possessionArgent2);
        possessionArgent3 = possessionService.create(possessionArgent3);
        possessionArgent4 = possessionService.create(possessionArgent4);

        System.out.println(possessionArgent1);
        System.out.println(possessionArgent2);
        System.out.println(possessionArgent3);
        System.out.println(possessionArgent4);

		
		/*
		Possession [] possessionRessource1 = {possessionArme1,possessionNourriture1,possessionEnergie1,possessionArgent1};
		Possession [] possessionRessource2 = {possessionArme2,possessionNourriture2,possessionEnergie2,possessionArgent2};
		Possession [] possessionRessource3 = {possessionArme3,possessionNourriture3,possessionEnergie3,possessionArgent3};
		Possession [] possessionRessource4 = {possessionArme4,possessionNourriture4,possessionEnergie4,possessionArgent4};
		*/

        List<Possession> possessionRessource1 = new ArrayList<>();
        List<Possession> possessionRessource2 = new ArrayList<>();
        List<Possession> possessionRessource3 = new ArrayList<>();
        List<Possession> possessionRessource4 = new ArrayList<>();

        Collections.addAll(possessionRessource1, possessionArme1, possessionNourriture1, possessionEnergie1, possessionArgent1);
        Collections.addAll(possessionRessource2, possessionArme2, possessionNourriture2, possessionEnergie2, possessionArgent2);
        Collections.addAll(possessionRessource3, possessionArme3, possessionNourriture3, possessionEnergie3, possessionArgent3);
        Collections.addAll(possessionRessource4, possessionArme4, possessionNourriture4, possessionEnergie4, possessionArgent4);


        List<Biome> listeBiome1 = new ArrayList<>();
        List<Biome> listeBiome2 = new ArrayList<>();
        List<Biome> listeBiome3 = new ArrayList<>();
        List<Biome> listeBiome4 = new ArrayList<>();

        Collections.addAll(listeBiome1, Biome.Plaine, Biome.Foret, Biome.Desertique);
        Collections.addAll(listeBiome2, Biome.Plaine, Biome.Foret, Biome.Desertique);
        Collections.addAll(listeBiome3, Biome.Plaine, Biome.Foret, Biome.Desertique);
        Collections.addAll(listeBiome4, Biome.Plaine, Biome.Foret, Biome.Desertique);
		
		
		
		/*
		1 = {Biome.Plaine,Biome.Foret,Biome.Ocean};
		Collections.addAllBiome[] listeBiomes2 = {Biome.Plaine,Biome.Foret,Biome.Desertique};
		Collections.addAllBiome[] listeBiomes3 = {Biome.Plaine,Biome.Ocean,Biome.Desertique};
		Collections.addAllBiome[] listeBiomes4 = {Biome.Foret,Biome.Ocean,Biome.Desertique};
		*/


        Planete planete1 = new Planete("Planete A", 125, listeBiome1);
        Planete planete2 = new Planete("Planete B", 247, listeBiome2);
        Planete planete3 = new Planete("Planete C", 371, listeBiome3);
        Planete planete4 = new Planete("Planete D", 404, listeBiome4);

        planete1 = planeteService.create(planete1);
        planete2 = planeteService.create(planete2);
        planete3 = planeteService.create(planete3);
        planete4 = planeteService.create(planete4);

        System.out.println(planete1);
        System.out.println(planete2);
        System.out.println(planete3);
        System.out.println(planete4);


        Espece espece1 = new Espece("Espece A", biomesMap);
        Espece espece2 = new Espece("Espece B", biomesMap);
        Espece espece3 = new Espece("Espece C", biomesMap);
        Espece espece4 = new Espece("Espece D", biomesMap);

        espece1 = especeService.create(espece1);
        espece2 = especeService.create(espece2);
        espece3 = especeService.create(espece3);
        espece4 = especeService.create(espece4);

        System.out.println(espece1);
        System.out.println(espece2);
        System.out.println(espece3);
        System.out.println(espece4);


        Joueur joueur1 = new Joueur(1, possessionRessource1, partie1, espece1);
        Joueur joueur2 = new Joueur(1, possessionRessource2, partie1, espece2);
        Joueur joueur3 = new Joueur(1, possessionRessource3, partie2, espece3);
        Joueur joueur4 = new Joueur(1, possessionRessource4, partie3, espece4);


        joueur1 = joueurService.create(joueur1);
        joueur2 = joueurService.create(joueur2);
        joueur3 = joueurService.create(joueur3);
        joueur4 = joueurService.create(joueur4);

        System.out.println(joueur1);
        System.out.println(joueur2);
        System.out.println(joueur3);
        System.out.println(joueur4);


        PlanetSeed planeteSeed1 = new PlanetSeed(100, 12, 10, joueur1, planete1);
        PlanetSeed planeteSeed2 = new PlanetSeed(206, 22, 24, joueur2, planete2);
        PlanetSeed planeteSeed3 = new PlanetSeed(381, 39, 38, joueur3, planete3);
        PlanetSeed planeteSeed4 = new PlanetSeed(416, 40, 41, joueur4, planete4);

        planeteSeed1 = planetSeedService.create(planeteSeed1);
        planeteSeed2 = planetSeedService.create(planeteSeed2);
        planeteSeed3 = planetSeedService.create(planeteSeed3);
        planeteSeed4 = planetSeedService.create(planeteSeed4);

        System.out.println(planeteSeed1);
        System.out.println(planeteSeed2);
        System.out.println(planeteSeed3);
        System.out.println(planeteSeed4);


        List<PlanetSeed> listPlaneteSeed1 = new ArrayList<>();
        List<PlanetSeed> listPlaneteSeed2 = new ArrayList<>();
        List<PlanetSeed> listPlaneteSeed3 = new ArrayList<>();
        List<PlanetSeed> listPlaneteSeed4 = new ArrayList<>();


        Collections.addAll(listPlaneteSeed1, planeteSeed1);
        Collections.addAll(listPlaneteSeed2, planeteSeed2);
        Collections.addAll(listPlaneteSeed3, planeteSeed3);
        Collections.addAll(listPlaneteSeed4, planeteSeed4);

        partie1.setPlanetSeeds(listPlaneteSeed1);
        partie2.setPlanetSeeds(listPlaneteSeed2);
        partie3.setPlanetSeeds(listPlaneteSeed3);
        partie4.setPlanetSeeds(listPlaneteSeed4);

        partie1 = partieService.update(partie1);
        partie2 = partieService.update(partie2);
        partie3 = partieService.update(partie3);
        partie4 = partieService.update(partie4);

        System.out.println(partie1.getPlanetSeeds());
        System.out.println(partie2.getPlanetSeeds());
        System.out.println(partie3.getPlanetSeeds());
        System.out.println(partie4.getPlanetSeeds());


        Batiment batiment1 = new Batiment("Caserne", Taille.Petit, Ressource.Arme);
        Batiment batiment2 = new Batiment("Ferme", Taille.Moyen, Ressource.Nourriture);
        Batiment batiment3 = new Batiment("Centrale", Taille.Grand, Ressource.Energie);
        Batiment batiment4 = new Batiment("Caserne", Taille.Grand, Ressource.Arme);


//		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
//
//		em.getTransaction().begin();
//
//		em.persist(utilisateur1);
//		em.persist(utilisateur2);
//		em.persist(utilisateur3);
//		em.persist(utilisateur4);
//
//		em.persist(espece1);
//		em.persist(espece2);
//		em.persist(espece3);
//		em.persist(espece4);
//
//		em.persist(planete1);
//		em.persist(planete2);
//		em.persist(planete3);
//		em.persist(planete4);
//
//
//		em.persist(partie1);
//		em.persist(partie2);
//		em.persist(partie3);
//		em.persist(partie4);
//
//		em.persist(joueur1);
//		em.persist(joueur2);
//		em.persist(joueur3);
//		em.persist(joueur4);
//
//		em.persist(planeteSeed1);
//		em.persist(planeteSeed2);
//		em.persist(planeteSeed3);
//		em.persist(planeteSeed4);
//
//		em.persist(possessionArme1);
//		em.persist(possessionArme2);
//		em.persist(possessionArme3);
//		em.persist(possessionArme4);
//
//		em.persist(possessionNourriture1);
//		em.persist(possessionNourriture2);
//		em.persist(possessionNourriture3);
//		em.persist(possessionNourriture4);
//
//		em.persist(possessionEnergie1);
//		em.persist(possessionEnergie2);
//		em.persist(possessionEnergie3);
//		em.persist(possessionEnergie4);
//
//		em.persist(possessionArgent1);
//		em.persist(possessionArgent2);
//		em.persist(possessionArgent3);
//		em.persist(possessionArgent4);
//
//
//		em.persist(batiment1);
//		em.persist(batiment2);
//		em.persist(batiment3);
//		em.persist(batiment4);
//
//		em.getTransaction().commit();
//
//		Singleton.getInstance().closeEmf();


    }

}
