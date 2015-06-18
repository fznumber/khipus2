package com.encens.khipus.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Diego on 17/06/2015.
 */
@Entity
@Table(name = "pago")
public class Pago implements Serializable {
    private long idPago;
    private Timestamp fecha;
    private Double monto;
    private String descripcion;
    private Persona persona;
    private SfConfenc sfConfencByIdSfConfenc;
    private SfTmpenc sfTmpencByIdTmpenc;
    private Usuario usuarioByIdusuario;

    @Id
    @Column(name = "id_pago", nullable = false, insertable = true, updatable = true)
    @TableGenerator(name = "Pago_Gen"
            ,table="ID_GEN"
            ,pkColumnName = "GEN_NAME"
            ,valueColumnName = "GEN_VAL"
            ,initialValue = 1
            ,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Pago_Gen")
    public long getIdPago() {
        return idPago;
    }

    public void setIdPago(long idPago) {
        this.idPago = idPago;
    }

    @Basic
    @Column(name = "fecha", nullable = false, insertable = true, updatable = true)
    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    @Basic
    @Column(name = "monto", nullable = true, insertable = true, updatable = true, precision = 0)
    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    @Basic
    @Column(name = "descripcion", nullable = true, insertable = true, updatable = true, length = 150)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pago pago = (Pago) o;

        if (idPago != pago.idPago) return false;
        if (descripcion != null ? !descripcion.equals(pago.descripcion) : pago.descripcion != null) return false;
        if (fecha != null ? !fecha.equals(pago.fecha) : pago.fecha != null) return false;
        if (monto != null ? !monto.equals(pago.monto) : pago.monto != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idPago ^ (idPago >>> 32));
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        result = 31 * result + (monto != null ? monto.hashCode() : 0);
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "IDPERSONACLIENTE", referencedColumnName = "IDPERSONACLIENTE", nullable = false)
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona personaclienteByIdpersonacliente) {
        this.persona = personaclienteByIdpersonacliente;
    }

    @ManyToOne
    @JoinColumn(name = "id_sf_confenc", referencedColumnName = "id_sf_confenc", nullable = false)
    public SfConfenc getSfConfencByIdSfConfenc() {
        return sfConfencByIdSfConfenc;
    }

    public void setSfConfencByIdSfConfenc(SfConfenc sfConfencByIdSfConfenc) {
        this.sfConfencByIdSfConfenc = sfConfencByIdSfConfenc;
    }

    @ManyToOne
    @JoinColumn(name = "id_tmpenc", referencedColumnName = "id_tmpenc")
    public SfTmpenc getSfTmpencByIdTmpenc() {
        return sfTmpencByIdTmpenc;
    }

    public void setSfTmpencByIdTmpenc(SfTmpenc sfTmpencByIdTmpenc) {
        this.sfTmpencByIdTmpenc = sfTmpencByIdTmpenc;
    }

    @ManyToOne
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario", nullable = false)
    public Usuario getUsuarioByIdusuario() {
        return usuarioByIdusuario;
    }

    public void setUsuarioByIdusuario(Usuario usuarioByIdusuario) {
        this.usuarioByIdusuario = usuarioByIdusuario;
    }
}
