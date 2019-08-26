package infraestructure.repository.proyecto.records;

import java.math.BigDecimal;

public class ProyectoRecord {

    private int id;
    private String nombre;
    private String descripcion;
    private BigDecimal valorEstimado;

    public ProyectoRecord(int id, String nombre, String descripcion, BigDecimal valorEstimado) {
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

    public BigDecimal getValorEstimado() {
        return valorEstimado;
    }

    public void setValorEstimado(BigDecimal valorEstimado) {
        this.valorEstimado = valorEstimado;
    }
}
