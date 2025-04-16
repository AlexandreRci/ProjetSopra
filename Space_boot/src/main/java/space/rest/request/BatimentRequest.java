package space.rest.request;

import org.springframework.beans.BeanUtils;

import space.model.Batiment;
import space.model.Ressource;
import space.model.Taille;

public class BatimentRequest {
	private Integer id;
	private String nom;	
	private String taille;
	private String ressource;
	
	
	public BatimentRequest() {
		super();
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

	public String getTaille() {
		return taille;
	}

	public void setTaille(String taille) {
		this.taille = taille;
	}

	public String getRessource() {
		return ressource;
	}

	public void setRessource(String ressource) {
		this.ressource = ressource;
	}
	
	public static Batiment convert(BatimentRequest batimentRequest) {
		Batiment batiment = new Batiment();
		
		BeanUtils.copyProperties(batimentRequest, batiment);
		
		batiment.setTaille(Taille.valueOf(batimentRequest.getTaille()));
		batiment.setRessource(Ressource.valueOf(batimentRequest.getRessource()));
		
		return batiment;
	}
	
}
