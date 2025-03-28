package sopra.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "batiment")
public class Batiment {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer id;
	private String nom;
	//à coder , pas sûr de moi!!!!!
	@Enumerated(EnumType.STRING)
	@Column(name="taille",columnDefinition = "ENUM('Petit','Moyen','Grand')", nullable = false)
	private Taille taille;
	@Enumerated(EnumType.STRING)
	@Column(name="ressource",columnDefinition = "ENUM('Arme','Nourriture','Energie','Argent')", nullable = false)
	private Ressource ressource;
	
	
	
	public Batiment() {
	}

	public Batiment(Integer id, String nom, Taille taille) {
		this.id = id;
		this.nom = nom;
		this.taille = taille;
	}
	
	public Batiment(String nom, Taille taille) {
		this.nom = nom;
		this.taille = taille;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public Taille getTaille() {
		return taille;
	}


	public void setTaille(Taille taille) {
		this.taille = taille;
	}
	

	public Ressource getRessource() {
		return ressource;
	}

	public void setRessource(Ressource ressource) {
		this.ressource = ressource;
	}

	@Override
	public String toString() {
		return "Batiment [id=" + id + ", nom=" + nom + ", taille=" + taille + ", ressource=" + ressource + "]";
	}




}
