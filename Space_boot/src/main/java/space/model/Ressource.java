package space.model;

public enum Ressource {

    ARME("Caserne"), NOURRITURE("Ferme"), ENERGIE("Centrale atomique"), ARGENT("Banque");

    private final String name;

    Ressource(String name) {
        this.name = name;
    }
}
