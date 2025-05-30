package space.rest.response;

import org.springframework.beans.BeanUtils;
import space.model.Joueur;
import space.model.PlanetSeed;
import space.model.Possession;

import java.util.List;

public class JoueurResponse {

    private Integer id;
    private int position;
    private List<Integer> idPossessions;
    private Integer idPartie;
    private Integer idEspece;
    private List<Integer> idPlanetSeeds;
    private Integer idUtilisateur;

    public static JoueurResponse convert(Joueur joueur) {
        JoueurResponse joueurResponse = new JoueurResponse();
        BeanUtils.copyProperties(joueur, joueurResponse);

        if (joueur.getPartie() != null) {
            Integer idPartie = joueur.getPartie().getId();
            joueurResponse.setIdPartie(idPartie);
        }

        if (joueur.getEspece() != null) {
            Integer idEspece = joueur.getEspece().getId();
            joueurResponse.setIdEspece(idEspece);
        }

        // map possessions
        if (joueur.getPossessions() != null) {
            joueurResponse.setIdPossessions(joueur.getPossessions().stream()
                    .filter(p -> p != null && p.getId() != null)
                    .map(Possession::getId)
                    .toList());
        } else {
            joueurResponse.setIdPossessions(List.of());
        }

        // map planetSeeds
        if (joueur.getPlanetSeeds() != null) {
            joueurResponse.setIdPlanetSeeds(joueur.getPlanetSeeds().stream()
                    .filter(p -> p != null && p.getId() != null)
                    .map(PlanetSeed::getId)
                    .toList());
        } else {
            joueurResponse.setIdPlanetSeeds(List.of());
        }

        if (joueur.getUtilisateur() != null) {
            Integer idUtilisateur = joueur.getUtilisateur().getId();
            joueurResponse.setIdUtilisateur(idUtilisateur);
        }

        return joueurResponse;
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

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
}
