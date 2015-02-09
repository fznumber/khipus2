package encens.khipus.model.purchases;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Diego on 09/02/2015.
 */
@Entity
@Table(name = "inv_articulos", schema = "", catalog = "eos")
@IdClass(InvArticulosPK.class)
public class InvArticulos {
    private String noCia;
    private String codArt;
    private String controlValorado;
    private Double cantiadEqui;
    private String codGru;
    private String codMedMay;
    private Double saldoMon;
    private Double stockmaximo;
    private Double stockminimo;
    private String descri;
    private String nombrecorto;
    private String cuentaArt;
    private String vendible;
    private String estado;
    private String codSub;
    private Double costoUni;
    private String codMed;
    private Long version;
    private InvGrupos invGrupos;
    private Collection<Ventaarticulo> ventaarticulos;

    @Id
    @Column(name = "NO_CIA", nullable = false, insertable = true, updatable = true, length = 2)
    public String getNoCia() {
        return noCia;
    }

    public void setNoCia(String noCia) {
        this.noCia = noCia;
    }

    @Id
    @Column(name = "COD_ART", nullable = false, insertable = true, updatable = true, length = 6)
    public String getCodArt() {
        return codArt;
    }

    public void setCodArt(String codArt) {
        this.codArt = codArt;
    }

    @Basic
    @Column(name = "CONTROL_VALORADO", nullable = true, insertable = true, updatable = true, length = 255)
    public String getControlValorado() {
        return controlValorado;
    }

    public void setControlValorado(String controlValorado) {
        this.controlValorado = controlValorado;
    }

    @Basic
    @Column(name = "CANTIAD_EQUI", nullable = true, insertable = true, updatable = true, precision = 2)
    public Double getCantiadEqui() {
        return cantiadEqui;
    }

    public void setCantiadEqui(Double cantiadEqui) {
        this.cantiadEqui = cantiadEqui;
    }

    @Basic
    @Column(name = "COD_GRU", nullable = true, insertable = true, updatable = true, length = 3)
    public String getCodGru() {
        return codGru;
    }

    public void setCodGru(String codGru) {
        this.codGru = codGru;
    }

    @Basic
    @Column(name = "COD_MED_MAY", nullable = true, insertable = true, updatable = true, length = 6)
    public String getCodMedMay() {
        return codMedMay;
    }

    public void setCodMedMay(String codMedMay) {
        this.codMedMay = codMedMay;
    }

    @Basic
    @Column(name = "SALDO_MON", nullable = true, insertable = true, updatable = true, precision = 6)
    public Double getSaldoMon() {
        return saldoMon;
    }

    public void setSaldoMon(Double saldoMon) {
        this.saldoMon = saldoMon;
    }

    @Basic
    @Column(name = "STOCKMAXIMO", nullable = true, insertable = true, updatable = true, precision = 6)
    public Double getStockmaximo() {
        return stockmaximo;
    }

    public void setStockmaximo(Double stockmaximo) {
        this.stockmaximo = stockmaximo;
    }

    @Basic
    @Column(name = "STOCKMINIMO", nullable = true, insertable = true, updatable = true, precision = 6)
    public Double getStockminimo() {
        return stockminimo;
    }

    public void setStockminimo(Double stockminimo) {
        this.stockminimo = stockminimo;
    }

    @Basic
    @Column(name = "DESCRI", nullable = true, insertable = true, updatable = true, length = 100)
    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    @Basic
    @Column(name = "NOMBRECORTO", nullable = true, insertable = true, updatable = true, length = 14)
    public String getNombrecorto() {
        return nombrecorto;
    }

    public void setNombrecorto(String nombrecorto) {
        this.nombrecorto = nombrecorto;
    }

    @Basic
    @Column(name = "CUENTA_ART", nullable = true, insertable = true, updatable = true, length = 31)
    public String getCuentaArt() {
        return cuentaArt;
    }

    public void setCuentaArt(String cuentaArt) {
        this.cuentaArt = cuentaArt;
    }

    @Basic
    @Column(name = "VENDIBLE", nullable = true, insertable = true, updatable = true, length = 255)
    public String getVendible() {
        return vendible;
    }

    public void setVendible(String vendible) {
        this.vendible = vendible;
    }

    @Basic
    @Column(name = "ESTADO", nullable = true, insertable = true, updatable = true, length = 3)
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Basic
    @Column(name = "COD_SUB", nullable = true, insertable = true, updatable = true, length = 3)
    public String getCodSub() {
        return codSub;
    }

    public void setCodSub(String codSub) {
        this.codSub = codSub;
    }

    @Basic
    @Column(name = "COSTO_UNI", nullable = true, insertable = true, updatable = true, precision = 6)
    public Double getCostoUni() {
        return costoUni;
    }

    public void setCostoUni(Double costoUni) {
        this.costoUni = costoUni;
    }

    @Basic
    @Column(name = "COD_MED", nullable = true, insertable = true, updatable = true, length = 6)
    public String getCodMed() {
        return codMed;
    }

    public void setCodMed(String codMed) {
        this.codMed = codMed;
    }

    @Basic
    @Column(name = "VERSION", nullable = true, insertable = true, updatable = true)
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvArticulos that = (InvArticulos) o;

        if (cantiadEqui != null ? !cantiadEqui.equals(that.cantiadEqui) : that.cantiadEqui != null) return false;
        if (codArt != null ? !codArt.equals(that.codArt) : that.codArt != null) return false;
        if (codGru != null ? !codGru.equals(that.codGru) : that.codGru != null) return false;
        if (codMed != null ? !codMed.equals(that.codMed) : that.codMed != null) return false;
        if (codMedMay != null ? !codMedMay.equals(that.codMedMay) : that.codMedMay != null) return false;
        if (codSub != null ? !codSub.equals(that.codSub) : that.codSub != null) return false;
        if (controlValorado != null ? !controlValorado.equals(that.controlValorado) : that.controlValorado != null)
            return false;
        if (costoUni != null ? !costoUni.equals(that.costoUni) : that.costoUni != null) return false;
        if (cuentaArt != null ? !cuentaArt.equals(that.cuentaArt) : that.cuentaArt != null) return false;
        if (descri != null ? !descri.equals(that.descri) : that.descri != null) return false;
        if (estado != null ? !estado.equals(that.estado) : that.estado != null) return false;
        if (noCia != null ? !noCia.equals(that.noCia) : that.noCia != null) return false;
        if (nombrecorto != null ? !nombrecorto.equals(that.nombrecorto) : that.nombrecorto != null) return false;
        if (saldoMon != null ? !saldoMon.equals(that.saldoMon) : that.saldoMon != null) return false;
        if (stockmaximo != null ? !stockmaximo.equals(that.stockmaximo) : that.stockmaximo != null) return false;
        if (stockminimo != null ? !stockminimo.equals(that.stockminimo) : that.stockminimo != null) return false;
        if (vendible != null ? !vendible.equals(that.vendible) : that.vendible != null) return false;
        if (version != null ? !version.equals(that.version) : that.version != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = noCia != null ? noCia.hashCode() : 0;
        result = 31 * result + (codArt != null ? codArt.hashCode() : 0);
        result = 31 * result + (controlValorado != null ? controlValorado.hashCode() : 0);
        result = 31 * result + (cantiadEqui != null ? cantiadEqui.hashCode() : 0);
        result = 31 * result + (codGru != null ? codGru.hashCode() : 0);
        result = 31 * result + (codMedMay != null ? codMedMay.hashCode() : 0);
        result = 31 * result + (saldoMon != null ? saldoMon.hashCode() : 0);
        result = 31 * result + (stockmaximo != null ? stockmaximo.hashCode() : 0);
        result = 31 * result + (stockminimo != null ? stockminimo.hashCode() : 0);
        result = 31 * result + (descri != null ? descri.hashCode() : 0);
        result = 31 * result + (nombrecorto != null ? nombrecorto.hashCode() : 0);
        result = 31 * result + (cuentaArt != null ? cuentaArt.hashCode() : 0);
        result = 31 * result + (vendible != null ? vendible.hashCode() : 0);
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        result = 31 * result + (codSub != null ? codSub.hashCode() : 0);
        result = 31 * result + (costoUni != null ? costoUni.hashCode() : 0);
        result = 31 * result + (codMed != null ? codMed.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumns({@JoinColumn(name = "NO_CIA", referencedColumnName = "NO_CIA", nullable = false), @JoinColumn(name = "COD_GRU", referencedColumnName = "COD_GRU")})
    public InvGrupos getInvGrupos() {
        return invGrupos;
    }

    public void setInvGrupos(InvGrupos invGrupos) {
        this.invGrupos = invGrupos;
    }

    @OneToMany(mappedBy = "invArticulos")
    public Collection<Ventaarticulo> getVentaarticulos() {
        return ventaarticulos;
    }

    public void setVentaarticulos(Collection<Ventaarticulo> ventaarticulos) {
        this.ventaarticulos = ventaarticulos;
    }
}
