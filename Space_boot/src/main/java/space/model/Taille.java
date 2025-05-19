package space.model;

public enum Taille {

    PETIT(5, 10), MOYEN(10, 30), GRAND(20, 50);

    private final int prix;
    private final int gain;


    Taille(int prix, int gain) {
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
