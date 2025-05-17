package space.model;

public enum Ressource {

    ARME("usine"), NOURRITURE("ferme"), ENERGIE("Moulin"), ARGENT("marché");

    private final String name;

    Ressource(String name) {
        this.name = name;
    }
}
