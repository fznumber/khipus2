/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encens.khipus.model;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Diego
 */
@Entity
@Table(name = "articulos_pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ArticulosPedido.findAll", query = "SELECT a FROM ArticulosPedido a"),
    @NamedQuery(name = "ArticulosPedido.findByIdarticulospedido", query = "SELECT a FROM ArticulosPedido a WHERE a.idarticulospedido = :idarticulospedido"),
    @NamedQuery(name = "ArticulosPedido.findByIdCuenta", query = "SELECT a FROM ArticulosPedido a WHERE a.idCuenta = :idCuenta"),
    @NamedQuery(name = "ArticulosPedido.findByCodArt", query = "SELECT a FROM ArticulosPedido a WHERE a.codArt = :codArt"),
    @NamedQuery(name = "ArticulosPedido.findByNoCia", query = "SELECT a FROM ArticulosPedido a WHERE a.noCia = :noCia"),
    @NamedQuery(name = "ArticulosPedido.findByPedido", query = "SELECT a FROM ArticulosPedido a WHERE a.pedido = :pedido"),
    @NamedQuery(name = "ArticulosPedido.findById", query = "SELECT a FROM ArticulosPedido a WHERE a.id = :id"),
    @NamedQuery(name = "ArticulosPedido.findById1", query = "SELECT a FROM ArticulosPedido a WHERE a.id1 = :id1"),
    @NamedQuery(name = "ArticulosPedido.findByCantidad", query = "SELECT a FROM ArticulosPedido a WHERE a.cantidad = :cantidad"),
    @NamedQuery(name = "ArticulosPedido.findByCodAlm", query = "SELECT a FROM ArticulosPedido a WHERE a.codAlm = :codAlm"),
    @NamedQuery(name = "ArticulosPedido.findByPrecio", query = "SELECT a FROM ArticulosPedido a WHERE a.precio = :precio"),
    @NamedQuery(name = "ArticulosPedido.findByReposicion", query = "SELECT a FROM ArticulosPedido a WHERE a.reposicion = :reposicion"),
    @NamedQuery(name = "ArticulosPedido.findByTotal", query = "SELECT a FROM ArticulosPedido a WHERE a.total = :total"),
    @NamedQuery(name = "ArticulosPedido.findByPromocion", query = "SELECT a FROM ArticulosPedido a WHERE a.promocion = :promocion"),
    @NamedQuery(name = "ArticulosPedido.findByCaja", query = "SELECT a FROM ArticulosPedido a WHERE a.caja = :caja"),
    @NamedQuery(name = "ArticulosPedido.findByPrecioInv", query = "SELECT a FROM ArticulosPedido a WHERE a.precioInv = :precioInv"),
    @NamedQuery(name = "ArticulosPedido.findByTotalInv", query = "SELECT a FROM ArticulosPedido a WHERE a.totalInv = :totalInv")})
public class ArticulosPedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "IDARTICULOSPEDIDO")
    private Long idarticulospedido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CUENTA")
    private long idCuenta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "COD_ART")
    private String codArt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "NO_CIA")
    private String noCia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PEDIDO")
    private long pedido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ID")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID1")
    private long id1;
    @Column(name = "CANTIDAD")
    private BigInteger cantidad;
    @Size(max = 6)
    @Column(name = "COD_ALM")
    private String codAlm;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRECIO")
    private Double precio;
    @Column(name = "REPOSICION")
    private BigInteger reposicion;
    @Column(name = "TOTAL")
    private Double total;
    @Column(name = "PROMOCION")
    private BigInteger promocion;
    @Column(name = "CAJA")
    private BigInteger caja;
    @Column(name = "PRECIO_INV")
    private BigInteger precioInv;
    @Column(name = "TOTAL_INV")
    private BigInteger totalInv;

    public ArticulosPedido() {
    }

    public ArticulosPedido(Long idarticulospedido) {
        this.idarticulospedido = idarticulospedido;
    }

    public ArticulosPedido(Long idarticulospedido, long idCuenta, String codArt, String noCia, long pedido, String id, long id1) {
        this.idarticulospedido = idarticulospedido;
        this.idCuenta = idCuenta;
        this.codArt = codArt;
        this.noCia = noCia;
        this.pedido = pedido;
        this.id = id;
        this.id1 = id1;
    }

    public Long getIdarticulospedido() {
        return idarticulospedido;
    }

    public void setIdarticulospedido(Long idarticulospedido) {
        this.idarticulospedido = idarticulospedido;
    }

    public long getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(long idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getCodArt() {
        return codArt;
    }

    public void setCodArt(String codArt) {
        this.codArt = codArt;
    }

    public String getNoCia() {
        return noCia;
    }

    public void setNoCia(String noCia) {
        this.noCia = noCia;
    }

    public long getPedido() {
        return pedido;
    }

    public void setPedido(long pedido) {
        this.pedido = pedido;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getId1() {
        return id1;
    }

    public void setId1(long id1) {
        this.id1 = id1;
    }

    public BigInteger getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigInteger cantidad) {
        this.cantidad = cantidad;
    }

    public String getCodAlm() {
        return codAlm;
    }

    public void setCodAlm(String codAlm) {
        this.codAlm = codAlm;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public BigInteger getReposicion() {
        return reposicion;
    }

    public void setReposicion(BigInteger reposicion) {
        this.reposicion = reposicion;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public BigInteger getPromocion() {
        return promocion;
    }

    public void setPromocion(BigInteger promocion) {
        this.promocion = promocion;
    }

    public BigInteger getCaja() {
        return caja;
    }

    public void setCaja(BigInteger caja) {
        this.caja = caja;
    }

    public BigInteger getPrecioInv() {
        return precioInv;
    }

    public void setPrecioInv(BigInteger precioInv) {
        this.precioInv = precioInv;
    }

    public BigInteger getTotalInv() {
        return totalInv;
    }

    public void setTotalInv(BigInteger totalInv) {
        this.totalInv = totalInv;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idarticulospedido != null ? idarticulospedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArticulosPedido)) {
            return false;
        }
        ArticulosPedido other = (ArticulosPedido) object;
        if ((this.idarticulospedido == null && other.idarticulospedido != null) || (this.idarticulospedido != null && !this.idarticulospedido.equals(other.idarticulospedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.encens.khipus.model.ArticulosPedido[ idarticulospedido=" + idarticulospedido + " ]";
    }
    
}
