package space.rest.request;

import java.util.List;

import org.springframework.beans.BeanUtils;

import space.model.Espece;
import space.model.Joueur;
import space.model.Partie;
import space.model.PlanetSeed;
import space.model.Possession;

public class JoueurRequest {
	
    private Integer id;
    private int position;
    private List<Possession> possessions;
    private Partie partie;
    private Espece espece;
    private List<PlanetSeed> planetSeeds;
    

    
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



	public List<Possession> getPossessions() {
		return possessions;
	}



	public void setPossessions(List<Possession> possessions) {
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



	public static Joueur convert(JoueurRequest joueurRequest) {
		Joueur joueur = new Joueur();
		BeanUtils.copyProperties(joueurRequest, joueur);
			
		return joueur;
	}


}
