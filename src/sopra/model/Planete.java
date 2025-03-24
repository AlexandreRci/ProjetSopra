package sopra.model;

import java.util.Arrays;

public class Planete {

	private Integer id;
	private String nom;
	private int minerai;
	private Biome[] biomes = new Biome[3];
	
	
	public Planete(Integer id, String nom, int minerai, Biome[] biomes) {
		this.id = id;
		this.nom = nom;
		this.minerai = minerai;
		this.biomes = biomes;
	}
	
	
	public Planete(String nom, int minerai, Biome[] biomes) {
		this.nom = nom;
		this.minerai = minerai;
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


	public int getMinerai() {
		return minerai;
	}


	public void setMinerai(int minerai) {
		this.minerai = minerai;
	}


	public Biome[] getBiomes() {
		return biomes;
	}


	public void setBiomes(Biome[] biomes) {
		this.biomes = biomes;
	}


	@Override
	public String toString() {
		return "Planete [id=" + id + ", nom=" + nom + ", minerai=" + minerai + ", biomes=" + Arrays.toString(biomes)
				+ "]";
	}
	
	
	
	
	

	

}
