package com.encens.khipus.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Diego on 11/08/2015.
 */
@Entity
public class Sucursal {
    @Id
    @Column(name = "IDSUCURSAL", nullable = false, insertable = true, updatable = true)
    private long idsucursal;
    @Basic
    @Column(name = "NOMBRE", nullable = true, insertable = true, updatable = true, length = 150)
    private String nombre;
    @Basic
    @Column(name = "descripcion", nullable = true, insertable = true, updatable = true, length = 30)
    private String descripcion;

    public long getIdsucursal() {
        return idsucursal;
    }

    public void setIdsucursal(long idsucursal) {
        this.idsucursal = idsucursal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

        Sucursal sucursal = (Sucursal) o;

        if (idsucursal != sucursal.idsucursal) return false;
        if (descripcion != null ? !descripcion.equals(sucursal.descripcion) : sucursal.descripcion != null)
            return false;
        if (nombre != null ? !nombre.equals(sucursal.nombre) : sucursal.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idsucursal ^ (idsucursal >>> 32));
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        return result;
    }
}
