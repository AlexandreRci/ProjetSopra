package space.rest.request;

import org.springframework.beans.BeanUtils;
import space.model.*;

import java.util.ArrayList;
import java.util.List;

public class JoueurRequest {

    private Integer id;
    private int position;
    private List<Integer> idPossessions;
    private Integer idPartie;
    private Integer idEspece;
    private List<Integer> idPlanetSeeds;

    public static Joueur convert(JoueurRequest joueurRequest) {
        Joueur joueur = new Joueur();
        BeanUtils.copyProperties(joueurRequest, joueur);

        if (joueurRequest.getIdPartie() != null) {
            Partie partie = new Partie();
            partie.setId(joueurRequest.getIdPartie());
            joueur.setPartie(partie);
        }

        if (joueurRequest.getIdEspece() != null) {
            Espece espece = new Espece();
            espece.setId(joueurRequest.getIdEspece());
            joueur.setEspece(espece);
        }

        if (joueurRequest.getIdPossessions() != null && !joueurRequest.getIdPossessions().isEmpty()) {
            List<Possession> possessions = new ArrayList<>();
            for (Integer idPossession : joueurRequest.getIdPossessions()) {
                Possession possession = new Possession();
                possession.setId(idPossession);
                possessions.add(possession);
            }
            joueur.setPossessions(possessions);
        }
        if (joueurRequest.getIdPlanetSeeds() != null && !joueurRequest.getIdPlanetSeeds().isEmpty()) {
            List<PlanetSeed> planetSeeds = new ArrayList<>();
            for (Integer idPlanetSeed : joueurRequest.getIdPlanetSeeds()) {
                PlanetSeed planetSeed = new PlanetSeed();
                planetSeed.setId(idPlanetSeed);
                planetSeeds.add(planetSeed);
            }
            joueur.setPlanetSeeds(planetSeeds);
        }
        return joueur;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public List<Integer> getIdPossessions() {
        return idPossessions;
    }

    public void setIdPossessions(List<Integer> idPossessions) {
        this.idPossessions = idPossessions;
    }

    public Integer getIdPartie() {
        return idPartie;
    }

    public void setIdPartie(Integer idPartie) {
        this.idPartie = idPartie;
    }

    public Integer getIdEspece() {
        return idEspece;
    }

    public void setIdEspece(Integer idEspece) {
        this.idEspece = idEspece;
    }

    public List<Integer> getIdPlanetSeeds() {
        return idPlanetSeeds;
    }

    public void setIdPlanetSeeds(List<Integer> idPlanetSeeds) {
        this.idPlanetSeeds = idPlanetSeeds;
    }

}
