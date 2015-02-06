package encens.khipus.model.contacts;


import encens.khipus.model.admin.Company;
import org.hibernate.annotations.Filter;

import javax.persistence.*;

/**
 * Entity for Street
 *
 * @author:
 */
@TableGenerator(schema = encens.khipus.util.Constants.KHIPUS_SCHEMA, name = "Street.tableGenerator",
        table = encens.khipus.util.Constants.SEQUENCE_TABLE_NAME,
        pkColumnName = encens.khipus.util.Constants.SEQUENCE_TABLE_PK_COLUMN_NAME,
        valueColumnName = encens.khipus.util.Constants.SEQUENCE_TABLE_VALUE_COLUMN_NAME,
        pkColumnValue = "calle",
        allocationSize = encens.khipus.util.Constants.SEQUENCE_ALLOCATION_SIZE)

@javax.persistence.Entity
@Filter(name = encens.khipus.util.Constants.COMPANY_FILTER_NAME)
@Table(schema = encens.khipus.util.Constants.KHIPUS_SCHEMA, name = "calle", uniqueConstraints = {@UniqueConstraint(columnNames = {"idcompania", "idbarrio", "nombre"})})
public class Street implements java.io.Serializable {

    @Id
    @Column(name = "idcalle", nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Street.tableGenerator")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 200)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idbarrio", nullable = false)
    private District district;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "idcompania", nullable = false, updatable = false, insertable = true)
    private Company company;

    @Version
    @Column(name = "version")
    private long version;

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
