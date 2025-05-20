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

    public static JoueurResponse convert(Joueur joueur) {
        JoueurResponse res = new JoueurResponse();
        res.setId(joueur.getId());
        res.setPosition(joueur.getPosition());
        res.setIdPartie(joueur.getPartie() != null ? joueur.getPartie().getId() : null);
        res.setIdEspece(joueur.getEspece() != null ? joueur.getEspece().getId() : null);

        // map possessions
        if (joueur.getPossessions() != null) {
            res.setIdPossessions(joueur.getPossessions().stream()
                    .filter(p -> p != null && p.getId() != null)
                    .map(Possession::getId)
                    .toList());
        } else {
            res.setIdPossessions(List.of());
        }

        // map planetSeeds
        if (joueur.getPlanetSeeds() != null) {
            res.setIdPlanetSeeds(joueur.getPlanetSeeds().stream()
                    .filter(p -> p != null && p.getId() != null)
                    .map(PlanetSeed::getId)
                    .toList());
        } else {
            res.setIdPlanetSeeds(List.of());
        }

        return res;
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
