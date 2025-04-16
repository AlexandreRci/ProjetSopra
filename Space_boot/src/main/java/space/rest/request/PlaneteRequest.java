package space.rest.request;

import java.util.List;

import org.springframework.beans.BeanUtils;

import space.model.Biome;
import space.model.Planete;

public class PlaneteRequest {
	private Integer id;
	private String nom;
	private int minerai;
	private List<Biome> biomes;

	public PlaneteRequest() {
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


	public int getMinerai() {
		return minerai;
	}


	public void setMinerai(int minerai) {
		this.minerai = minerai;
	}


	public List<Biome> getBiomes() {
		return biomes;
	}


	public void setBiomes(List<Biome> biomes) {
		this.biomes = biomes;
	}


	public static Planete convert(PlaneteRequest planeteRequest) {
		Planete planete = new Planete();
		BeanUtils.copyProperties(planeteRequest, planete);

		return planete;
	}
}
