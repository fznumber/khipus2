/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encens.khipus.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Diego
 */
@Entity
@Table(name = "sf_tmpdet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SfTmpdet.findAll", query = "SELECT s FROM SfTmpdet s"),
    @NamedQuery(name = "SfTmpdet.findByNoCia", query = "SELECT s FROM SfTmpdet s WHERE s.sfTmpdetPK.noCia = :noCia"),
    @NamedQuery(name = "SfTmpdet.findByNoTrans", query = "SELECT s FROM SfTmpdet s WHERE s.sfTmpdetPK.noTrans = :noTrans"),
    @NamedQuery(name = "SfTmpdet.findByTimemillis", query = "SELECT s FROM SfTmpdet s WHERE s.timemillis = :timemillis"),
    @NamedQuery(name = "SfTmpdet.findByCuenta", query = "SELECT s FROM SfTmpdet s WHERE s.sfTmpdetPK.cuenta = :cuenta"),
    @NamedQuery(name = "SfTmpdet.findByCodUni", query = "SELECT s FROM SfTmpdet s WHERE s.codUni = :codUni"),
    @NamedQuery(name = "SfTmpdet.findByCodCc", query = "SELECT s FROM SfTmpdet s WHERE s.codCc = :codCc"),
    @NamedQuery(name = "SfTmpdet.findByHaber", query = "SELECT s FROM SfTmpdet s WHERE s.haber = :haber"),
    @NamedQuery(name = "SfTmpdet.findByMoneda", query = "SELECT s FROM SfTmpdet s WHERE s.moneda = :moneda"),
    @NamedQuery(name = "SfTmpdet.findByDebe", query = "SELECT s FROM SfTmpdet s WHERE s.debe = :debe"),
    @NamedQuery(name = "SfTmpdet.findByTc", query = "SELECT s FROM SfTmpdet s WHERE s.tc = :tc")})
public class SfTmpdet implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SfTmpdetPK sfTmpdetPK;
    @Size(max = 255)
    @Column(name = "timemillis")
    private String timemillis;
    @Size(max = 255)
    @Column(name = "cod_uni")
    private String codUni;
    @Size(max = 255)
    @Column(name = "cod_cc")
    private String codCc;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "haber")
    private BigDecimal haber;
    @Size(max = 255)
    @Column(name = "moneda")
    private String moneda;
    @Column(name = "debe")
    private BigDecimal debe;
    @Column(name = "tc")
    private BigDecimal tc;

    public SfTmpdet() {
    }

    public SfTmpdet(SfTmpdetPK sfTmpdetPK) {
        this.sfTmpdetPK = sfTmpdetPK;
    }

    public SfTmpdet(String noCia, String noTrans, String cuenta) {
        this.sfTmpdetPK = new SfTmpdetPK(noCia, noTrans, cuenta);
    }

    public SfTmpdetPK getSfTmpdetPK() {
        return sfTmpdetPK;
    }

    public void setSfTmpdetPK(SfTmpdetPK sfTmpdetPK) {
        this.sfTmpdetPK = sfTmpdetPK;
    }

    public String getTimemillis() {
        return timemillis;
    }

    public void setTimemillis(String timemillis) {
        this.timemillis = timemillis;
    }

    public String getCodUni() {
        return codUni;
    }

    public void setCodUni(String codUni) {
        this.codUni = codUni;
    }

    public String getCodCc() {
        return codCc;
    }

    public void setCodCc(String codCc) {
        this.codCc = codCc;
    }

    public BigDecimal getHaber() {
        return haber;
    }

    public void setHaber(BigDecimal haber) {
        this.haber = haber;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public BigDecimal getDebe() {
        return debe;
    }

    public void setDebe(BigDecimal debe) {
        this.debe = debe;
    }

    public BigDecimal getTc() {
        return tc;
    }

    public void setTc(BigDecimal tc) {
        this.tc = tc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sfTmpdetPK != null ? sfTmpdetPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SfTmpdet)) {
            return false;
        }
        SfTmpdet other = (SfTmpdet) object;
        if ((this.sfTmpdetPK == null && other.sfTmpdetPK != null) || (this.sfTmpdetPK != null && !this.sfTmpdetPK.equals(other.sfTmpdetPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.encens.khipus.model.SfTmpdet[ sfTmpdetPK=" + sfTmpdetPK + " ]";
    }
    
}
