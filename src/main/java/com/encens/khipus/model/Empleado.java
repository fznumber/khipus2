/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encens.khipus.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Diego
 */
@Entity
@Table(name = "empleado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e"),
    @NamedQuery(name = "Empleado.findByIdempleado", query = "SELECT e FROM Empleado e WHERE e.idempleado = :idempleado"),
    @NamedQuery(name = "Empleado.findByFlagafp", query = "SELECT e FROM Empleado e WHERE e.flagafp = :flagafp"),
    @NamedQuery(name = "Empleado.findByFlagcontrol", query = "SELECT e FROM Empleado e WHERE e.flagcontrol = :flagcontrol"),
    @NamedQuery(name = "Empleado.findByCodigoempleado", query = "SELECT e FROM Empleado e WHERE e.codigoempleado = :codigoempleado"),
    @NamedQuery(name = "Empleado.findByFechaingreso", query = "SELECT e FROM Empleado e WHERE e.fechaingreso = :fechaingreso"),
    @NamedQuery(name = "Empleado.findByFlagjubilado", query = "SELECT e FROM Empleado e WHERE e.flagjubilado = :flagjubilado"),
    @NamedQuery(name = "Empleado.findByCodigomarcacion", query = "SELECT e FROM Empleado e WHERE e.codigomarcacion = :codigomarcacion"),
    @NamedQuery(name = "Empleado.findByTipodepago", query = "SELECT e FROM Empleado e WHERE e.tipodepago = :tipodepago"),
    @NamedQuery(name = "Empleado.findByFlagret", query = "SELECT e FROM Empleado e WHERE e.flagret = :flagret"),
    @NamedQuery(name = "Empleado.findByFechasalida", query = "SELECT e FROM Empleado e WHERE e.fechasalida = :fechasalida"),
    @NamedQuery(name = "Empleado.findBySalario", query = "SELECT e FROM Empleado e WHERE e.salario = :salario"),
    @NamedQuery(name = "Empleado.findByIdunidadnegocio", query = "SELECT e FROM Empleado e WHERE e.idunidadnegocio = :idunidadnegocio"),
    @NamedQuery(name = "Empleado.findByIdcompania", query = "SELECT e FROM Empleado e WHERE e.idcompania = :idcompania")})
public class Empleado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "IDEMPLEADO")
    private Long idempleado;
    @Column(name = "FLAGAFP")
    private BigInteger flagafp;
    @Column(name = "FLAGCONTROL")
    private BigInteger flagcontrol;
    @Size(max = 255)
    @Column(name = "CODIGOEMPLEADO")
    private String codigoempleado;
    @Column(name = "FECHAINGRESO")
    @Temporal(TemporalType.DATE)
    private Date fechaingreso;
    @Column(name = "FLAGJUBILADO")
    private BigInteger flagjubilado;
    @Size(max = 100)
    @Column(name = "CODIGOMARCACION")
    private String codigomarcacion;
    @Size(max = 20)
    @Column(name = "TIPODEPAGO")
    private String tipodepago;
    @Column(name = "FLAGRET")
    private BigInteger flagret;
    @Column(name = "FECHASALIDA")
    @Temporal(TemporalType.DATE)
    private Date fechasalida;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SALARIO")
    private Double salario;
    @Column(name = "IDUNIDADNEGOCIO")
    private BigInteger idunidadnegocio;
    @Column(name = "IDCOMPANIA")
    private BigInteger idcompania;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idempleado")
    private Collection<Pedidos> pedidosCollection;

    public Empleado() {
    }

    public Empleado(Long idempleado) {
        this.idempleado = idempleado;
    }

    public Long getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(Long idempleado) {
        this.idempleado = idempleado;
    }

    public BigInteger getFlagafp() {
        return flagafp;
    }

    public void setFlagafp(BigInteger flagafp) {
        this.flagafp = flagafp;
    }

    public BigInteger getFlagcontrol() {
        return flagcontrol;
    }

    public void setFlagcontrol(BigInteger flagcontrol) {
        this.flagcontrol = flagcontrol;
    }

    public String getCodigoempleado() {
        return codigoempleado;
    }

    public void setCodigoempleado(String codigoempleado) {
        this.codigoempleado = codigoempleado;
    }

    public Date getFechaingreso() {
        return fechaingreso;
    }

    public void setFechaingreso(Date fechaingreso) {
        this.fechaingreso = fechaingreso;
    }

    public BigInteger getFlagjubilado() {
        return flagjubilado;
    }

    public void setFlagjubilado(BigInteger flagjubilado) {
        this.flagjubilado = flagjubilado;
    }

    public String getCodigomarcacion() {
        return codigomarcacion;
    }

    public void setCodigomarcacion(String codigomarcacion) {
        this.codigomarcacion = codigomarcacion;
    }

    public String getTipodepago() {
        return tipodepago;
    }

    public void setTipodepago(String tipodepago) {
        this.tipodepago = tipodepago;
    }

    public BigInteger getFlagret() {
        return flagret;
    }

    public void setFlagret(BigInteger flagret) {
        this.flagret = flagret;
    }

    public Date getFechasalida() {
        return fechasalida;
    }

    public void setFechasalida(Date fechasalida) {
        this.fechasalida = fechasalida;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public BigInteger getIdunidadnegocio() {
        return idunidadnegocio;
    }

    public void setIdunidadnegocio(BigInteger idunidadnegocio) {
        this.idunidadnegocio = idunidadnegocio;
    }

    public BigInteger getIdcompania() {
        return idcompania;
    }

    public void setIdcompania(BigInteger idcompania) {
        this.idcompania = idcompania;
    }

    @XmlTransient
    public Collection<Pedidos> getPedidosCollection() {
        return pedidosCollection;
    }

    public void setPedidosCollection(Collection<Pedidos> pedidosCollection) {
        this.pedidosCollection = pedidosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idempleado != null ? idempleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.idempleado == null && other.idempleado != null) || (this.idempleado != null && !this.idempleado.equals(other.idempleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.encens.khipus.model.Empleado[ idempleado=" + idempleado + " ]";
    }
    
}
