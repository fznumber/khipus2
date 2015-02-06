package encens.khipus.model.admin;




import javax.persistence.*;

/**
 * @author
 * @version 2.22
 */

@TableGenerator(schema = encens.khipus.util.Constants.KHIPUS_SCHEMA, name = "UserBusinessUnit.tableGenerator",
        table = encens.khipus.util.Constants.SEQUENCE_TABLE_NAME,
        pkColumnName = encens.khipus.util.Constants.SEQUENCE_TABLE_PK_COLUMN_NAME,
        valueColumnName = encens.khipus.util.Constants.SEQUENCE_TABLE_VALUE_COLUMN_NAME,
        pkColumnValue = "usuariounidadnegocio",
        allocationSize = encens.khipus.util.Constants.SEQUENCE_ALLOCATION_SIZE)

@NamedQueries({
        @NamedQuery(name = "UserBusinessUnit.findByUser",
                query = "select userBusinessUnit from UserBusinessUnit userBusinessUnit where userBusinessUnit.user =:user")
})

@Entity
@Table(schema = encens.khipus.util.Constants.KHIPUS_SCHEMA, name = "USUARIOUNIDADNEGOCIO")
public class UserBusinessUnit implements java.io.Serializable {
    @Id
    @Column(name = "IDUSUARIOUNIDADNEGOCIO", nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "UserBusinessUnit.tableGenerator")
    private Long id;

    @Column(name = "IDUNIDADNEGOCIO", nullable = false, insertable = false, updatable = false)
    private Long businessUnitId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "IDCOMPANIA", nullable = false, updatable = false, insertable = true)
    private Company company;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "IDUSUARIO", nullable = false, updatable = false, insertable = true)
    private User user;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "IDUNIDADNEGOCIO", nullable = false, updatable = false, insertable = true)
    private BusinessUnit businessUnit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BusinessUnit getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(BusinessUnit businessUnit) {
        this.businessUnit = businessUnit;
    }

    public Long getBusinessUnitId() {
        return businessUnitId;
    }

    public void setBusinessUnitId(Long businessUnitId) {
        this.businessUnitId = businessUnitId;
    }
}
