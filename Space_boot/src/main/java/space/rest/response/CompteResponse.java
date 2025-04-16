package space.rest.response;

import org.springframework.beans.BeanUtils;

import space.model.Admin;
import space.model.Compte;
import space.model.Utilisateur;

public class CompteResponse {
	private Integer id;
	private String username;
	private String password;
	private CompteType compteType;
	// Utilisateur
	private String name;
	

	public CompteResponse() {
		super();
	}


	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	public String getUsername() {
		return username;
	}




	public void setUsername(String username) {
		this.username = username;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public CompteType getCompteType() {
		return compteType;
	}




	public void setCompteType(CompteType compteType) {
		this.compteType = compteType;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public static CompteResponse convert(Compte compte) {
		CompteResponse compteResponse = new CompteResponse();
		BeanUtils.copyProperties(compte, compteResponse);
		
		if(compte instanceof Utilisateur) {
			compteResponse.setCompteType(CompteType.UTILISATEUR);
			Utilisateur utilisateur = (Utilisateur) compte;
			BeanUtils.copyProperties(utilisateur, compteResponse);
		} else if(compte instanceof Admin) {
			compteResponse.setCompteType(CompteType.ADMIN);
		}
		
		return compteResponse; 
	}

	public enum CompteType {
		ADMIN, UTILISATEUR;
	}
}
