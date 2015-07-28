package com.encens.khipus.model;

import java.util.Date;

/**
 * Created by Diego on 23/07/2015.
 */
public class Kardex {
    private Date fecha;
    private String tipoDoc;
    private Integer noDoc;
    private String glosa;
    private Double debe;
    private Double haber;
    private Double Saldo;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public Integer getNoDoc() {
        return noDoc;
    }

    public void setNoDoc(Integer noDoc) {
        this.noDoc = noDoc;
    }

    public String getGlosa() {
        return glosa;
    }

    public void setGlosa(String glosa) {
        this.glosa = glosa;
    }

    public Double getDebe() {
        return debe;
    }

    public void setDebe(Double debe) {
        this.debe = debe;
    }

    public Double getHaber() {
        return haber;
    }

    public void setHaber(Double haber) {
        this.haber = haber;
    }

    public Double getSaldo() {
        return Saldo;
    }

    public void setSaldo(Double saldo) {
        Saldo = saldo;
    }
}
