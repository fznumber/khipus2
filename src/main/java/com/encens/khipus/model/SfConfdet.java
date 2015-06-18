package com.encens.khipus.model;

/**
 * Created by Diego on 17/06/2015.
 */
public class SfConfdet {
    private long idSfConfdet;
    private String cuenta;
    private String tipomovimiento;

    public long getIdSfConfdet() {
        return idSfConfdet;
    }

    public void setIdSfConfdet(long idSfConfdet) {
        this.idSfConfdet = idSfConfdet;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

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
        return result;
    }
}
