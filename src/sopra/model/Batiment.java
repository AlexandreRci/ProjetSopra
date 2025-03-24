package sopra.model;

public class Batiment {
	
	private Integer id;
	private String nom;
	private Taille taille;
	
	
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


	@Override
	public String toString() {
		return "Batiment [id=" + id + ", nom=" + nom + ", taille=" + taille + "]";
	}
	
	
	
	

}
