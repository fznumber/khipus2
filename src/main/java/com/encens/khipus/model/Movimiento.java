/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encens.khipus.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "movimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movimiento.findAll", query = "SELECT m FROM Movimiento m"),
    @NamedQuery(name = "Movimiento.findByIdmovimiento", query = "SELECT m FROM Movimiento m WHERE m.idmovimiento = :idmovimiento"),
    @NamedQuery(name = "Movimiento.findByGlosa", query = "SELECT m FROM Movimiento m WHERE m.glosa = :glosa"),
    @NamedQuery(name = "Movimiento.findByEstado", query = "SELECT m FROM Movimiento m WHERE m.estado = :estado"),
    @NamedQuery(name = "Movimiento.findByCodestruct", query = "SELECT m FROM Movimiento m WHERE m.codestruct = :codestruct"),
    @NamedQuery(name = "Movimiento.findByMoneda", query = "SELECT m FROM Movimiento m WHERE m.moneda = :moneda"),
    @NamedQuery(name = "Movimiento.findByNrofactura", query = "SELECT m FROM Movimiento m WHERE m.nrofactura = :nrofactura"),
    @NamedQuery(name = "Movimiento.findByCodigocontrol", query = "SELECT m FROM Movimiento m WHERE m.codigocontrol = :codigocontrol"),
    @NamedQuery(name = "Movimiento.findByTipopago", query = "SELECT m FROM Movimiento m WHERE m.tipopago = :tipopago"),
    @NamedQuery(name = "Movimiento.findByTipocambio", query = "SELECT m FROM Movimiento m WHERE m.tipocambio = :tipocambio"),
    @NamedQuery(name = "Movimiento.findByFecharegistro", query = "SELECT m FROM Movimiento m WHERE m.fecharegistro = :fecharegistro")})
public class Movimiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "IDMOVIMIENTO")
    private Long idmovimiento;
    @Size(max = 300)
    @Column(name = "GLOSA")
    private String glosa;
    @Size(max = 15)
    @Column(name = "ESTADO")
    private String estado;
    @Size(max = 30)
    @Column(name = "CODESTRUCT")
    private String codestruct;
    @Size(max = 10)
    @Column(name = "MONEDA")
    private String moneda;
    @Size(max = 30)
    @Column(name = "NROFACTURA")
    private String nrofactura;
    @Size(max = 30)
    @Column(name = "CODIGOCONTROL")
    private String codigocontrol;
    @Size(max = 10)
    @Column(name = "TIPOPAGO")
    private String tipopago;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TIPOCAMBIO")
    private Double tipocambio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAREGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecharegistro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movimiento")
    private Collection<Impresionfactura> impresionfacturaCollection;
    @JoinColumn(name = "IDPEDIDOS", referencedColumnName = "IDPEDIDOS")
    @ManyToOne(optional = false)
    private Pedidos pedidos;

    public Movimiento() {
    }

    public Movimiento(Long idmovimiento) {
        this.idmovimiento = idmovimiento;
    }

    public Movimiento(Long idmovimiento, Date fecharegistro) {
        this.idmovimiento = idmovimiento;
        this.fecharegistro = fecharegistro;
    }

    public Long getIdmovimiento() {
        return idmovimiento;
    }

    public void setIdmovimiento(Long idmovimiento) {
        this.idmovimiento = idmovimiento;
    }

    public String getGlosa() {
        return glosa;
    }

    public void setGlosa(String glosa) {
        this.glosa = glosa;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodestruct() {
        return codestruct;
    }

    public void setCodestruct(String codestruct) {
        this.codestruct = codestruct;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getNrofactura() {
        return nrofactura;
    }

    public void setNrofactura(String nrofactura) {
        this.nrofactura = nrofactura;
    }

    public String getCodigocontrol() {
        return codigocontrol;
    }

    public void setCodigocontrol(String codigocontrol) {
        this.codigocontrol = codigocontrol;
    }

    public String getTipopago() {
        return tipopago;
    }

    public void setTipopago(String tipopago) {
        this.tipopago = tipopago;
    }

    public Double getTipocambio() {
        return tipocambio;
    }

    public void setTipocambio(Double tipocambio) {
        this.tipocambio = tipocambio;
    }

    public Date getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    @XmlTransient
    public Collection<Impresionfactura> getImpresionfacturaCollection() {
        return impresionfacturaCollection;
    }

    public void setImpresionfacturaCollection(Collection<Impresionfactura> impresionfacturaCollection) {
        this.impresionfacturaCollection = impresionfacturaCollection;
    }

    public Pedidos getPedidos() {
        return pedidos;
    }

    public void setPedidos(Pedidos idpedidos) {
        this.pedidos = idpedidos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmovimiento != null ? idmovimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movimiento)) {
            return false;
        }
        Movimiento other = (Movimiento) object;
        if ((this.idmovimiento == null && other.idmovimiento != null) || (this.idmovimiento != null && !this.idmovimiento.equals(other.idmovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.encens.khipus.model.Movimiento[ idmovimiento=" + idmovimiento + " ]";
    }
    
}
