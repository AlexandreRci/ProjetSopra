package space.rest.request;

public class StartRequest {

    private Integer UserId;
    private Integer idEspece;

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }

    public Integer getIdEspece() {
        return idEspece;
    }

    public void setIdEspece(Integer idEspece) {
        this.idEspece = idEspece;
    }
}
