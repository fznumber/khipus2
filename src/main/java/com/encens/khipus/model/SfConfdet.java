package com.encens.khipus.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Diego on 17/06/2015.
 */
@Entity
@Table(name = "sf_confdet")
public class SfConfdet implements Serializable {
    private long idSfConfdet;
    private String cuenta;
    private String tipomovimiento;
    private long idSfConfenc;
    private SfConfenc sfConfencByIdSfConfenc;

    @Id
    @Column(name = "id_sf_confdet", nullable = false, insertable = true, updatable = true)
    @TableGenerator(name = "SfConfdet_Gen"
            ,table="ID_GEN"
            ,pkColumnName = "GEN_NAME"
            ,valueColumnName = "GEN_VAL"
            ,initialValue = 1
            ,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "SfConfdet_Gen")
    public long getIdSfConfdet() {
        return idSfConfdet;
    }

    public void setIdSfConfdet(long idSfConfdet) {
        this.idSfConfdet = idSfConfdet;
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
    @Column(name = "tipomovimiento", nullable = true, insertable = true, updatable = true, length = 20)
    public String getTipomovimiento() {
        return tipomovimiento;
    }

    public void setTipomovimiento(String tipomovimiento) {
        this.tipomovimiento = tipomovimiento;
    }

    @Basic
    @Column(name = "id_sf_confenc", nullable = false, insertable = true, updatable = true)
    public long getIdSfConfenc() {
        return idSfConfenc;
    }

    public void setIdSfConfenc(long idSfConfenc) {
        this.idSfConfenc = idSfConfenc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SfConfdet sfConfdet = (SfConfdet) o;

        if (idSfConfdet != sfConfdet.idSfConfdet) return false;
        if (idSfConfenc != sfConfdet.idSfConfenc) return false;
        if (cuenta != null ? !cuenta.equals(sfConfdet.cuenta) : sfConfdet.cuenta != null) return false;
        if (tipomovimiento != null ? !tipomovimiento.equals(sfConfdet.tipomovimiento) : sfConfdet.tipomovimiento != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idSfConfdet ^ (idSfConfdet >>> 32));
        result = 31 * result + (cuenta != null ? cuenta.hashCode() : 0);
        result = 31 * result + (tipomovimiento != null ? tipomovimiento.hashCode() : 0);
        result = 31 * result + (int) (idSfConfenc ^ (idSfConfenc >>> 32));
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "id_sf_confenc", referencedColumnName = "id_sf_confenc", nullable = false)
    public SfConfenc getSfConfencByIdSfConfenc() {
        return sfConfencByIdSfConfenc;
    }

    public void setSfConfencByIdSfConfenc(SfConfenc sfConfencByIdSfConfenc) {
        this.sfConfencByIdSfConfenc = sfConfencByIdSfConfenc;
    }
}
