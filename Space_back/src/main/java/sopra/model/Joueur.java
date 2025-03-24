package sopra.model;

import java.util.Arrays;
import java.util.List;

public class Joueur {
	
	private Integer id;
	private int position;
	private Possession[] possessions = new Possession[4];
	private Partie partie;
	private Espece espece;
	private List<PlanetSeed> planetSeeds;
	

	public Joueur(Integer id, int position, Possession[] possessions, Partie partie, Espece espece) {
		this.id = id;
		this.position = position;
		this.possessions = possessions;
		this.partie = partie;
		this.espece = espece;
	}
	
	public Joueur(int position, Possession[] possessions, Partie partie, Espece espece) {
		this.position = position;
		this.possessions = possessions;
		this.partie = partie;
		this.espece = espece;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public int getPosition() {
		return position;
	}


	public void setPosition(int position) {
		this.position = position;
	}



	public Possession[] getPossessions() {
		return possessions;
	}


	public void setPossessions(Possession[] possessions) {
		this.possessions = possessions;
	}


	public Partie getPartie() {
		return partie;
	}


	public void setPartie(Partie partie) {
		this.partie = partie;
	}


	public Espece getEspece() {
		return espece;
	}


	public void setEspece(Espece espece) {
		this.espece = espece;
	}


	public List<PlanetSeed> getPlanetSeeds() {
		return planetSeeds;
	}


	public void setPlanetSeeds(List<PlanetSeed> planetSeeds) {
		this.planetSeeds = planetSeeds;
	}

	@Override
	public String toString() {
		return "Joueur [id=" + id + ", position=" + position + ", possessions=" + Arrays.toString(possessions)
				+ ", partie=" + partie + ", espece=" + espece + ", planetSeeds=" + planetSeeds + "]";
	}
	
	
	


	
	
	
}
