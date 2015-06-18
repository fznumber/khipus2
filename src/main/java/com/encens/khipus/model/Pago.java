package com.encens.khipus.model;

import java.sql.Timestamp;

/**
 * Created by Diego on 17/06/2015.
 */
public class Pago {
    private long idPago;
    private Timestamp fecha;
    private Double monto;
    private String descripcion;
    private SfConfenc sfConfencByIdSfConfenc;

    public long getIdPago() {
        return idPago;
    }

    public void setIdPago(long idPago) {
        this.idPago = idPago;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

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

    public SfConfenc getSfConfencByIdSfConfenc() {
        return sfConfencByIdSfConfenc;
    }

    public void setSfConfencByIdSfConfenc(SfConfenc sfConfencByIdSfConfenc) {
        this.sfConfencByIdSfConfenc = sfConfencByIdSfConfenc;
    }
}
