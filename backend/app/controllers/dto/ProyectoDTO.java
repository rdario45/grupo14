package controllers.dto;

public class ProyectoDTO {

    private int id;
    private String nombre;
    private String descripcion;
    private String valorEstimado;

    public ProyectoDTO() {
        // to json mapper
    }

    public ProyectoDTO(int id, String nombre, String descripcion, String valorEstimado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.valorEstimado = valorEstimado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getValorEstimado() {
        return valorEstimado;
    }

    public void setValorEstimado(String valorEstimado) {
        this.valorEstimado = valorEstimado;
    }
}
