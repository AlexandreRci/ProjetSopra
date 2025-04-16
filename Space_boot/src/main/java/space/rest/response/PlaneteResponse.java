package space.rest.response;

import java.util.List;

import org.springframework.beans.BeanUtils;

import space.model.Admin;
import space.model.Biome;
import space.model.Compte;
import space.model.Planete;
import space.model.Utilisateur;
import space.rest.response.CompteResponse.CompteType;

public class PlaneteResponse {
	private Integer id;
	private String nom;
	private int minerai;
	private List<Biome> biomes;

	public PlaneteResponse() {
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


	public static PlaneteResponse convert(Planete planete) {
		PlaneteResponse planeteResponse = new PlaneteResponse();
		BeanUtils.copyProperties(planete, planeteResponse);
		
		return planeteResponse; 
	}
}
