package sopra.model;

import java.util.HashMap;
import java.util.Map;

public class Espece {

	private Integer id;
	private String nom;
	private Map<Biome,Double> biomes = new HashMap<Biome,Double>();
	
	
	public Espece(Integer id, String nom, Map<Biome, Double> biomes) {
		this.id = id;
		this.nom = nom;
		this.biomes = biomes;
	}
	
	public Espece(String nom, Map<Biome, Double> biomes) {
		this.nom = nom;
		this.biomes = biomes;
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


	public Map<Biome, Double> getBiomes() {
		return biomes;
	}


	public void setBiomes(Map<Biome, Double> biomes) {
		this.biomes = biomes;
	}

	@Override
	public String toString() {
		return "Espece [id=" + id + ", nom=" + nom + ", biomes=" + biomes + "]";
	}

	

	
	
	
}
