package encens.khipus.model.contacts;


import encens.khipus.model.admin.Company;
import org.hibernate.annotations.Filter;

import javax.persistence.*;

/**
 * Entity for Salutations
 *
 * @author:
 */

@TableGenerator(schema = encens.khipus.util.Constants.KHIPUS_SCHEMA, name = "Saludation.tableGenerator",
        table = encens.khipus.util.Constants.SEQUENCE_TABLE_NAME,
        pkColumnName = encens.khipus.util.Constants.SEQUENCE_TABLE_PK_COLUMN_NAME,
        valueColumnName = encens.khipus.util.Constants.SEQUENCE_TABLE_VALUE_COLUMN_NAME,
        pkColumnValue = "saludo",
        allocationSize = encens.khipus.util.Constants.SEQUENCE_ALLOCATION_SIZE)

@NamedQueries(
        {
                @NamedQuery(name = "Salutation.findById", query = "select o from Salutation o where o.id=:id")
        }
)
@javax.persistence.Entity
@Filter(name = encens.khipus.util.Constants.COMPANY_FILTER_NAME)
@Table(schema = encens.khipus.util.Constants.KHIPUS_SCHEMA, name = "saludo", uniqueConstraints = @UniqueConstraint(columnNames = {"idcompania", "nombre"}))
public class Salutation implements java.io.Serializable {

    @Id
    @Column(name = "idsaludo", nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Saludation.tableGenerator")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String name;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "idcompania", nullable = false, updatable = false, insertable = true)
    private Company company;

    @Version
    @Column(name = "version", nullable = false)
    private long version;

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
