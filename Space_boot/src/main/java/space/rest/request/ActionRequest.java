package space.rest.request;

import space.model.Action;
import space.model.Ressource;
import space.model.Taille;

public class ActionRequest {
    private Integer idGame;
    private Integer idPlayer;
    private Action[] actions;
    private Integer[] targetPlanetsId;
    //should be null at indexes not corresponding to any attack
    private Integer[] nbAttackers;
    //should be null at indexes not corresponding to any building
    private Ressource[] buildingTypes;
    //should be null at indexes not corresponding to any building
    private Taille[] buildingSizes;

    public ActionRequest() {
    }

    public Integer[] getTargetPlanetsId() {
        return targetPlanetsId;
    }

    public void setTargetPlanetsId(Integer[] targetPlanetsId) {
        this.targetPlanetsId = targetPlanetsId;
    }

    public Integer getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(Integer idPlayer) {
        this.idPlayer = idPlayer;
    }

    public Action[] getActions() {
        return actions;
    }

    public void setActions(Action[] actions) {
        this.actions = actions;
    }

    public Integer getIdGame() {
        return idGame;
    }

    public void setIdGame(Integer idGame) {
        this.idGame = idGame;
    }

    public Integer[] getNbAttackers() {
        return nbAttackers;
    }

    public void setNbAttackers(Integer[] nbAttackers) {
        this.nbAttackers = nbAttackers;
    }

    public Ressource[] getBuildingTypes() {
        return buildingTypes;
    }

    public void setBuildingTypes(Ressource[] buildingTypes) {
        this.buildingTypes = buildingTypes;
    }

    public Taille[] getBuildingSizes() {
        return buildingSizes;
    }

    public void setBuildingSizes(Taille[] buildingSizes) {
        this.buildingSizes = buildingSizes;
    }
}
