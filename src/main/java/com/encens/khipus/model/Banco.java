package com.encens.khipus.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Diego on 11/07/2015.
 */
public class Banco {
    private String ctaBco;
    private String noCia;
    private String cuenta;
    private String codBco;
    private String moneda;
    private String descri;
    private String estado;

    public String getCtaBco() {
        return ctaBco;
    }

    public void setCtaBco(String ctaBco) {
        this.ctaBco = ctaBco;
    }

    public String getNoCia() {
        return noCia;
    }

    public void setNoCia(String noCia) {
        this.noCia = noCia;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getCodBco() {
        return codBco;
    }

    public void setCodBco(String codBco) {
        this.codBco = codBco;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return descri;
    }

}
