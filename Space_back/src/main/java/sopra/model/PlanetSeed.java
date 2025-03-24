package sopra.model;

import java.util.List;

public class PlanetSeed {

	private Integer id;
	private int population;
	private int arme;
	private int mineraiRestant;
	private Joueur joueur;
	private Planete planete;
	private List<Batiment> batiments;
	
	
	public PlanetSeed(Integer id, int population, int arme, int mineraiRestant, Planete planete) {
		this.id = id;
		this.population = population;
		this.arme = arme;
		this.mineraiRestant = mineraiRestant;
		this.planete = planete;
	}
	
	public PlanetSeed(int population, int arme, int mineraiRestant, Planete planete) {
		this.population = population;
		this.arme = arme;
		this.mineraiRestant = mineraiRestant;
		this.planete = planete;
	}



	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public int getPopulation() {
		return population;
	}


	public void setPopulation(int population) {
		this.population = population;
	}


	public int getArme() {
		return arme;
	}


	public void setArme(int arme) {
		this.arme = arme;
	}


	public int getMineraiRestant() {
		return mineraiRestant;
	}


	public void setMineraiRestant(int mineraiRestant) {
		this.mineraiRestant = mineraiRestant;
	}


	public Joueur getJoueur() {
		return joueur;
	}


	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}


	public Planete getPlanete() {
		return planete;
	}


	public void setPlanete(Planete planete) {
		this.planete = planete;
	}


	public List<Batiment> getBatiments() {
		return batiments;
	}


	public void setBatiments(List<Batiment> batiments) {
		this.batiments = batiments;
	}

	@Override
	public String toString() {
		return "PlanetSeed [id=" + id + ", population=" + population + ", arme=" + arme + ", mineraiRestant="
				+ mineraiRestant + ", joueur=" + joueur + ", planete=" + planete + ", batiments=" + batiments + "]";
	}

}
