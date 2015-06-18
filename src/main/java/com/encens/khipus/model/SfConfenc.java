package com.encens.khipus.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by Diego on 17/06/2015.
 */
@Entity
@Table(name = "sf_confenc")
public class SfConfenc implements Serializable {
    private long idSfConfenc;
    private String cuenta;
    private String descripcion;
    private String tipoDoc;
    private String glosa;
    private long idusuario;
    private Collection<Pago> pagosByIdSfConfenc;
    private Collection<Pedidos> pedidosesByIdSfConfenc;
    private Collection<SfConfdet> sfConfdetsByIdSfConfenc;

    @Id
    @Column(name = "id_sf_confenc", nullable = false, insertable = true, updatable = true)
    public long getIdSfConfenc() {
        return idSfConfenc;
    }

    public void setIdSfConfenc(long idSfConfenc) {
        this.idSfConfenc = idSfConfenc;
    }

    @Basic
    @Column(name = "cuenta", nullable = true, insertable = true, updatable = true, length = 31)
    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    @Basic
    @Column(name = "descripcion", nullable = true, insertable = true, updatable = true, length = 150)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Basic
    @Column(name = "tipo_doc", nullable = true, insertable = true, updatable = true, length = 10)
    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    @Basic
    @Column(name = "glosa", nullable = true, insertable = true, updatable = true, length = 150)
    public String getGlosa() {
        return glosa;
    }

    public void setGlosa(String glosa) {
        this.glosa = glosa;
    }

    @Basic
    @Column(name = "idusuario", nullable = false, insertable = true, updatable = true)
    public long getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(long idusuario) {
        this.idusuario = idusuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SfConfenc sfConfenc = (SfConfenc) o;

        if (idSfConfenc != sfConfenc.idSfConfenc) return false;
        if (idusuario != sfConfenc.idusuario) return false;
        if (cuenta != null ? !cuenta.equals(sfConfenc.cuenta) : sfConfenc.cuenta != null) return false;
        if (descripcion != null ? !descripcion.equals(sfConfenc.descripcion) : sfConfenc.descripcion != null)
            return false;
        if (glosa != null ? !glosa.equals(sfConfenc.glosa) : sfConfenc.glosa != null) return false;
        if (tipoDoc != null ? !tipoDoc.equals(sfConfenc.tipoDoc) : sfConfenc.tipoDoc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idSfConfenc ^ (idSfConfenc >>> 32));
        result = 31 * result + (cuenta != null ? cuenta.hashCode() : 0);
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        result = 31 * result + (tipoDoc != null ? tipoDoc.hashCode() : 0);
        result = 31 * result + (glosa != null ? glosa.hashCode() : 0);
        result = 31 * result + (int) (idusuario ^ (idusuario >>> 32));
        return result;
    }

    @OneToMany(mappedBy = "sfConfencByIdSfConfenc")
    public Collection<Pago> getPagosByIdSfConfenc() {
        return pagosByIdSfConfenc;
    }

    public void setPagosByIdSfConfenc(Collection<Pago> pagosByIdSfConfenc) {
        this.pagosByIdSfConfenc = pagosByIdSfConfenc;
    }

    @OneToMany(mappedBy = "sfConfenc")
    public Collection<Pedidos> getPedidosesByIdSfConfenc() {
        return pedidosesByIdSfConfenc;
    }

    public void setPedidosesByIdSfConfenc(Collection<Pedidos> pedidosesByIdSfConfenc) {
        this.pedidosesByIdSfConfenc = pedidosesByIdSfConfenc;
    }

    @OneToMany(mappedBy = "sfConfencByIdSfConfenc")
    public Collection<SfConfdet> getSfConfdetsByIdSfConfenc() {
        return sfConfdetsByIdSfConfenc;
    }

    public void setSfConfdetsByIdSfConfenc(Collection<SfConfdet> sfConfdetsByIdSfConfenc) {
        this.sfConfdetsByIdSfConfenc = sfConfdetsByIdSfConfenc;
    }
}
