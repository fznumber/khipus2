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

    private String tipomovimiento;
    private SfConfenc sfConfencByIdSfConfenc;
    @JoinColumn(name = "cuenta",referencedColumnName = "cuenta")
    @ManyToOne
    private Arcgms arcgms;

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
    @Column(name = "tipomovimiento", nullable = true, insertable = true, updatable = true, length = 20)
    public String getTipomovimiento() {
        return tipomovimiento;
    }

    public void setTipomovimiento(String tipomovimiento) {
        this.tipomovimiento = tipomovimiento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SfConfdet sfConfdet = (SfConfdet) o;

        if (idSfConfdet != sfConfdet.idSfConfdet) return false;
        if (tipomovimiento != null ? !tipomovimiento.equals(sfConfdet.tipomovimiento) : sfConfdet.tipomovimiento != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idSfConfdet ^ (idSfConfdet >>> 32));
        result = 31 * result + (tipomovimiento != null ? tipomovimiento.hashCode() : 0);
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

    public Arcgms getArcgms() {
        return arcgms;
    }

    public void setArcgms(Arcgms arcgms) {
        this.arcgms = arcgms;
    }
}
