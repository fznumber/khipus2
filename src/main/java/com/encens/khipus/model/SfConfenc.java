package com.encens.khipus.model;

/**
 * Created by Diego on 17/06/2015.
 */
public class SfConfenc {
    private long idSfConfenc;
    private String cuenta;
    private String descripcion;
    private String tipoDoc;
    private String glosa;
    private long idusuario;

    public long getIdSfConfenc() {
        return idSfConfenc;
    }

    public void setIdSfConfenc(long idSfConfenc) {
        this.idSfConfenc = idSfConfenc;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getGlosa() {
        return glosa;
    }

    public void setGlosa(String glosa) {
        this.glosa = glosa;
    }

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
}
