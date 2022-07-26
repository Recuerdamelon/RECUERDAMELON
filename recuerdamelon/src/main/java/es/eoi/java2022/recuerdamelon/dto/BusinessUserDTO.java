package es.eoi.java2022.recuerdamelon.dto;

public class BusinessUserDTO {
    private Integer idTrabajador;

    private String name;

    private String tipoTrabajador;

    private UserDTO user;

    private BusinessDTO businessDTO;



    public Integer getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(Integer idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTipoTrabajador() {
        return tipoTrabajador;
    }

    public void setTipoTrabajador(String tipoTrabajador) {
        this.tipoTrabajador = tipoTrabajador;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }


    public BusinessDTO getBusinessDTO() {
        return businessDTO;
    }

    public void setBusinessDTO(BusinessDTO businessDTO) {
        this.businessDTO = businessDTO;
    }
}
