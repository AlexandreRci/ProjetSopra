package space.rest.response;

import org.springframework.beans.BeanUtils;

import space.model.Batiment;
public class BatimentResponse {

	private Integer id;
	private String nom;	
	private String taille;
	private String ressource;
	
	
	public BatimentResponse() {
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
	
	public static BatimentResponse convert(Batiment batiment) {
		BatimentResponse batimentResponse = new BatimentResponse();
		
		BeanUtils.copyProperties(batiment, batimentResponse);
		
		batimentResponse.setTaille(batiment.getTaille().toString());
		batimentResponse.setRessource(batiment.getRessource().toString());
		
		
		return batimentResponse;
	}
	
}