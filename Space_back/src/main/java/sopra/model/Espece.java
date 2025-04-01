package sopra.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.Table;

@Entity
@Table(name = "espece")
public class Espece {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false)
	private String nom;
	@ElementCollection(fetch = FetchType.EAGER)
    @MapKeyEnumerated(EnumType.STRING)
    @CollectionTable(name = "espece_biome", joinColumns = @JoinColumn(name = "espece_id"))
    @Column(name = "biome_value", nullable = false)
	private Map<Biome,Double> biomes = new HashMap<Biome,Double>();
	
	
	public Espece() {
	}

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
