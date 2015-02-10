package encens.khipus.model.purchases;

import javax.persistence.*;

/**
 * Created by Diego on 07/02/2015.
 */
@Entity
public class Retencion {
    private long idretencion;
    private String nombre;
    private Double porcentage;
    private Cliente clienteRetencion;

    @Id
    @Column(name = "IDRETENCION", nullable = false, insertable = true, updatable = true)
    public long getIdretencion() {
        return idretencion;
    }

    public void setIdretencion(long idretencion) {
        this.idretencion = idretencion;
    }

    @Basic
    @Column(name = "NOMBRE", nullable = true, insertable = true, updatable = true, length = 50)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "PORCENTAGE", nullable = true, insertable = true, updatable = true, precision = 2)
    public Double getPorcentage() {
        return porcentage;
    }

    public void setPorcentage(Double porcentage) {
        this.porcentage = porcentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Retencion retencion = (Retencion) o;

        if (idretencion != retencion.idretencion) return false;
        if (nombre != null ? !nombre.equals(retencion.nombre) : retencion.nombre != null) return false;
        if (porcentage != null ? !porcentage.equals(retencion.porcentage) : retencion.porcentage != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idretencion ^ (idretencion >>> 32));
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (porcentage != null ? porcentage.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "IDCLIENTE", referencedColumnName = "IDCLIENTE", nullable = false)
    public Cliente getClienteRetencion() {
        return clienteRetencion;
    }

    public void setClienteRetencion(Cliente clienteByIdcliente) {
        this.clienteRetencion = clienteByIdcliente;
    }
}
