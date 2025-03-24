package sopra.model;

public enum Taille {

	Petit(5,10),Moyen(10,30),Grand(20,50);
	
	private int prix;
	private int gain;
	
	
	private Taille(int prix, int gain) {
		this.prix = prix;
		this.gain = gain;
	}

	public int getPrix() {
		return prix;
	}

	public int getGain() {
		return gain;
	}

	
	
}
