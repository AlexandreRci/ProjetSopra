package sopra.model;

public class Utilisateur extends Compte {

	protected String name;

	public Utilisateur(Integer id, String login, String password, String name) {
		super(id, login, password);
		this.name = name;

	}
	
	public Utilisateur(String login, String password, String name) {
		super(login, password);
		this.name = name;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", login=" + login + ", password=" + password + ", name=" + name + "]";
	}

	
}
