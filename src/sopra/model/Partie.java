package sopra.model;

import java.util.List;

public class Partie {
	
	private Integer id;
	private int currentPosition;
	private int nbTour;
	private int nbJoueur;
	private List<Joueur> joueurs;
	private List<PlanetSeed> planetSeeds;
	private Statut statut;
	
	
	public Partie(Integer id, int currentPosition, int nbTour, int nbJoueur, List<PlanetSeed> planetSeeds,
			Statut statut) {
		this.id = id;
		this.currentPosition = currentPosition;
		this.nbTour = nbTour;
		this.nbJoueur = nbJoueur;
		this.planetSeeds = planetSeeds;
		this.statut = statut;
	}
	
	public Partie( int currentPosition, int nbTour, int nbJoueur, List<PlanetSeed> planetSeeds,
			Statut statut) {
		this.currentPosition = currentPosition;
		this.nbTour = nbTour;
		this.nbJoueur = nbJoueur;
		this.planetSeeds = planetSeeds;
		this.statut = statut;
	}



	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public int getCurrentPosition() {
		return currentPosition;
	}


	public void setCurrentPosition(int currentPosition) {
		this.currentPosition = currentPosition;
	}


	public int getNbTour() {
		return nbTour;
	}


	public void setNbTour(int nbTour) {
		this.nbTour = nbTour;
	}


	public int getNbJoueur() {
		return nbJoueur;
	}


	public void setNbJoueur(int nbJoueur) {
		this.nbJoueur = nbJoueur;
	}


	public List<Joueur> getJoueurs() {
		return joueurs;
	}


	public void setJoueurs(List<Joueur> joueurs) {
		this.joueurs = joueurs;
	}


	public List<PlanetSeed> getPlanetSeeds() {
		return planetSeeds;
	}


	public void setPlanetSeeds(List<PlanetSeed> planetSeeds) {
		this.planetSeeds = planetSeeds;
	}


	public Statut getStatut() {
		return statut;
	}


	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	@Override
	public String toString() {
		return "Partie [id=" + id + ", currentPosition=" + currentPosition + ", nbTour=" + nbTour + ", nbJoueur="
				+ nbJoueur + ", joueurs=" + joueurs + ", planetSeeds=" + planetSeeds + ", statut=" + statut + "]";
	}

	
	
	

}
