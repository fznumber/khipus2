package encens.khipus.model.purchases;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by Diego on 07/02/2015.
 */
@Entity
public class Paquetes {
    private long idpaquetes;
    private String nombre;
    private Date fechaInicio;
    private Date fechaFin;
    private String activo;
    private Double total;
    private Long cuentaId;
    private Collection<ArticulosPaquete> articulosPaquetes;

    @Id
    @Column(name = "IDPAQUETES", nullable = false, insertable = true, updatable = true)
    public long getIdpaquetes() {
        return idpaquetes;
    }

    public void setIdpaquetes(long idpaquetes) {
        this.idpaquetes = idpaquetes;
    }

    @Basic
    @Column(name = "NOMBRE", nullable = true, insertable = true, updatable = true, length = 100)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "FECHA_INICIO", nullable = true, insertable = true, updatable = true)
    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Basic
    @Column(name = "FECHA_FIN", nullable = true, insertable = true, updatable = true)
    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Basic
    @Column(name = "ACTIVO", nullable = true, insertable = true, updatable = true, length = 2)
    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    @Basic
    @Column(name = "TOTAL", nullable = true, insertable = true, updatable = true, precision = 2)
    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Basic
    @Column(name = "CUENTA_ID", nullable = true, insertable = true, updatable = true)
    public Long getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(Long cuentaId) {
        this.cuentaId = cuentaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Paquetes paquetes = (Paquetes) o;

        if (idpaquetes != paquetes.idpaquetes) return false;
        if (activo != null ? !activo.equals(paquetes.activo) : paquetes.activo != null) return false;
        if (cuentaId != null ? !cuentaId.equals(paquetes.cuentaId) : paquetes.cuentaId != null) return false;
        if (fechaFin != null ? !fechaFin.equals(paquetes.fechaFin) : paquetes.fechaFin != null) return false;
        if (fechaInicio != null ? !fechaInicio.equals(paquetes.fechaInicio) : paquetes.fechaInicio != null)
            return false;
        if (nombre != null ? !nombre.equals(paquetes.nombre) : paquetes.nombre != null) return false;
        if (total != null ? !total.equals(paquetes.total) : paquetes.total != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idpaquetes ^ (idpaquetes >>> 32));
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (fechaInicio != null ? fechaInicio.hashCode() : 0);
        result = 31 * result + (fechaFin != null ? fechaFin.hashCode() : 0);
        result = 31 * result + (activo != null ? activo.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        result = 31 * result + (cuentaId != null ? cuentaId.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "paquete")
    public Collection<ArticulosPaquete> getArticulosPaquetes() {
        return articulosPaquetes;
    }

    public void setArticulosPaquetes(Collection<ArticulosPaquete> articulosPaquetesByIdpaquetes) {
        this.articulosPaquetes = articulosPaquetesByIdpaquetes;
    }
}
