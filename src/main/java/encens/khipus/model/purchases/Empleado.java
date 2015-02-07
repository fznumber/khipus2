package encens.khipus.model.purchases;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by Diego on 07/02/2015.
 */
@Entity
public class Empleado {
    private long idempleado;
    private Long flagafp;
    private Long flagcontrol;
    private String codigoempleado;
    private Date fechaingreso;
    private Long flagjubilado;
    private String codigomarcacion;
    private String tipodepago;
    private Long flagret;
    private Date fechasalida;
    private Double salario;
    private Long idunidadnegocio;
    private Long idcompania;
    private Collection<Pedidos> pedidosesByIdempleado;

    @Id
    @Column(name = "IDEMPLEADO", nullable = false, insertable = true, updatable = true)
    public long getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(long idempleado) {
        this.idempleado = idempleado;
    }

    @Basic
    @Column(name = "FLAGAFP", nullable = true, insertable = true, updatable = true)
    public Long getFlagafp() {
        return flagafp;
    }

    public void setFlagafp(Long flagafp) {
        this.flagafp = flagafp;
    }

    @Basic
    @Column(name = "FLAGCONTROL", nullable = true, insertable = true, updatable = true)
    public Long getFlagcontrol() {
        return flagcontrol;
    }

    public void setFlagcontrol(Long flagcontrol) {
        this.flagcontrol = flagcontrol;
    }

    @Basic
    @Column(name = "CODIGOEMPLEADO", nullable = true, insertable = true, updatable = true, length = 255)
    public String getCodigoempleado() {
        return codigoempleado;
    }

    public void setCodigoempleado(String codigoempleado) {
        this.codigoempleado = codigoempleado;
    }

    @Basic
    @Column(name = "FECHAINGRESO", nullable = true, insertable = true, updatable = true)
    public Date getFechaingreso() {
        return fechaingreso;
    }

    public void setFechaingreso(Date fechaingreso) {
        this.fechaingreso = fechaingreso;
    }

    @Basic
    @Column(name = "FLAGJUBILADO", nullable = true, insertable = true, updatable = true)
    public Long getFlagjubilado() {
        return flagjubilado;
    }

    public void setFlagjubilado(Long flagjubilado) {
        this.flagjubilado = flagjubilado;
    }

    @Basic
    @Column(name = "CODIGOMARCACION", nullable = true, insertable = true, updatable = true, length = 100)
    public String getCodigomarcacion() {
        return codigomarcacion;
    }

    public void setCodigomarcacion(String codigomarcacion) {
        this.codigomarcacion = codigomarcacion;
    }

    @Basic
    @Column(name = "TIPODEPAGO", nullable = true, insertable = true, updatable = true, length = 20)
    public String getTipodepago() {
        return tipodepago;
    }

    public void setTipodepago(String tipodepago) {
        this.tipodepago = tipodepago;
    }

    @Basic
    @Column(name = "FLAGRET", nullable = true, insertable = true, updatable = true)
    public Long getFlagret() {
        return flagret;
    }

    public void setFlagret(Long flagret) {
        this.flagret = flagret;
    }

    @Basic
    @Column(name = "FECHASALIDA", nullable = true, insertable = true, updatable = true)
    public Date getFechasalida() {
        return fechasalida;
    }

    public void setFechasalida(Date fechasalida) {
        this.fechasalida = fechasalida;
    }

    @Basic
    @Column(name = "SALARIO", nullable = true, insertable = true, updatable = true, precision = 2)
    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    @Basic
    @Column(name = "IDUNIDADNEGOCIO", nullable = true, insertable = true, updatable = true)
    public Long getIdunidadnegocio() {
        return idunidadnegocio;
    }

    public void setIdunidadnegocio(Long idunidadnegocio) {
        this.idunidadnegocio = idunidadnegocio;
    }

    @Basic
    @Column(name = "IDCOMPANIA", nullable = true, insertable = true, updatable = true)
    public Long getIdcompania() {
        return idcompania;
    }

    public void setIdcompania(Long idcompania) {
        this.idcompania = idcompania;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Empleado empleado = (Empleado) o;

        if (idempleado != empleado.idempleado) return false;
        if (codigoempleado != null ? !codigoempleado.equals(empleado.codigoempleado) : empleado.codigoempleado != null)
            return false;
        if (codigomarcacion != null ? !codigomarcacion.equals(empleado.codigomarcacion) : empleado.codigomarcacion != null)
            return false;
        if (fechaingreso != null ? !fechaingreso.equals(empleado.fechaingreso) : empleado.fechaingreso != null)
            return false;
        if (fechasalida != null ? !fechasalida.equals(empleado.fechasalida) : empleado.fechasalida != null)
            return false;
        if (flagafp != null ? !flagafp.equals(empleado.flagafp) : empleado.flagafp != null) return false;
        if (flagcontrol != null ? !flagcontrol.equals(empleado.flagcontrol) : empleado.flagcontrol != null)
            return false;
        if (flagjubilado != null ? !flagjubilado.equals(empleado.flagjubilado) : empleado.flagjubilado != null)
            return false;
        if (flagret != null ? !flagret.equals(empleado.flagret) : empleado.flagret != null) return false;
        if (idcompania != null ? !idcompania.equals(empleado.idcompania) : empleado.idcompania != null) return false;
        if (idunidadnegocio != null ? !idunidadnegocio.equals(empleado.idunidadnegocio) : empleado.idunidadnegocio != null)
            return false;
        if (salario != null ? !salario.equals(empleado.salario) : empleado.salario != null) return false;
        if (tipodepago != null ? !tipodepago.equals(empleado.tipodepago) : empleado.tipodepago != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idempleado ^ (idempleado >>> 32));
        result = 31 * result + (flagafp != null ? flagafp.hashCode() : 0);
        result = 31 * result + (flagcontrol != null ? flagcontrol.hashCode() : 0);
        result = 31 * result + (codigoempleado != null ? codigoempleado.hashCode() : 0);
        result = 31 * result + (fechaingreso != null ? fechaingreso.hashCode() : 0);
        result = 31 * result + (flagjubilado != null ? flagjubilado.hashCode() : 0);
        result = 31 * result + (codigomarcacion != null ? codigomarcacion.hashCode() : 0);
        result = 31 * result + (tipodepago != null ? tipodepago.hashCode() : 0);
        result = 31 * result + (flagret != null ? flagret.hashCode() : 0);
        result = 31 * result + (fechasalida != null ? fechasalida.hashCode() : 0);
        result = 31 * result + (salario != null ? salario.hashCode() : 0);
        result = 31 * result + (idunidadnegocio != null ? idunidadnegocio.hashCode() : 0);
        result = 31 * result + (idcompania != null ? idcompania.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "empleadoByIdempleado")
    public Collection<Pedidos> getPedidosesByIdempleado() {
        return pedidosesByIdempleado;
    }

    public void setPedidosesByIdempleado(Collection<Pedidos> pedidosesByIdempleado) {
        this.pedidosesByIdempleado = pedidosesByIdempleado;
    }
}
