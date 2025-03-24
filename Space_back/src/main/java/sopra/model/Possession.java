package sopra.model;

public class Possession {

	private Integer id;
	private int quantite;
	private Ressource ressource;
	
	
	public Possession(Integer id, int quantite, Ressource ressource) {
		this.id = id;
		this.quantite = quantite;
		this.ressource = ressource;
	}
	
	public Possession(int quantite, Ressource ressource) {
		this.quantite = quantite;
		this.ressource = ressource;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public Ressource getRessource() {
		return ressource;
	}
	public void setRessource(Ressource ressource) {
		this.ressource = ressource;
	}

	@Override
	public String toString() {
		return "Possession [id=" + id + ", quantite=" + quantite + ", ressource=" + ressource + "]";
	}

	
}
