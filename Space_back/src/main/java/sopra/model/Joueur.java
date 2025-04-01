package sopra.model;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

@Entity
@Table(name="joueur")
public class Joueur {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false)
	private int position;
	@OneToMany
	@ElementCollection(fetch = FetchType.EAGER, targetClass = Possession.class)
	@JoinTable(name = "joueur_possession",
	joinColumns = @JoinColumn(name = "joueur_id"),
	inverseJoinColumns = @JoinColumn(name = "possession_id"))
	private List<Possession> possessions;
	@ManyToOne
	@JoinColumn(name="partie_id",nullable = false)
	private Partie partie;
	@ManyToOne
	@JoinColumn(name="espece_id",nullable = false)
	private Espece espece;
	@OneToMany(mappedBy = "joueur")
	private List<PlanetSeed> planetSeeds;
	

	public Joueur() {
	}

	public Joueur(Integer id, int position, List<Possession> possessions, Partie partie, Espece espece) {
		this.id = id;
		this.position = position;
		this.possessions = possessions;
		this.partie = partie;
		this.espece = espece;
	}
	
	public Joueur(int position, List<Possession> possessions, Partie partie, Espece espece) {
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

	@Override
	public String toString() {
		return "Joueur [id=" + id + ", position=" + position + ", possessions="
				+ ", partie=" + partie + ", espece=" + espece + ", planetSeeds=" + planetSeeds + "]";
	}
	
	
	


	
	
	
}
