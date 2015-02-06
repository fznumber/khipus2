package encens.khipus.model.admin;




import encens.khipus.model.employees.Employee;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Class that represents the user model entity
 *
 * @author
 * @version 1.0
 */


@Entity
@GenericGenerator(name = "foreign", strategy = "foreign", parameters = {
        @Parameter(name = "property", value = "employee")})

@Table(schema = encens.khipus.util.Constants.KHIPUS_SCHEMA, name = "usuario", uniqueConstraints = @UniqueConstraint(columnNames = {"idcompania", "usuario"}))
@Filter(name = encens.khipus.util.Constants.COMPANY_FILTER_NAME)
public class User implements java.io.Serializable {

    @Id
    @GeneratedValue(generator = "foreign")
    @Column(name = "idusuario")
    private Long id;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "idusuario")
    private Employee employee;

    @Column(name = "usuario", length = 50)
    private String username;

    @Column(name = "clave", nullable = false, length = 200)
    private String password;

    @Transient
    private String confirmPassword;

    @Transient
    private String previousPassword;

    @Column(name = "email")
    private String email;

    @Column(name = "fechacreacion", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate = new Date();


    @Column(name = "numerousuario", length = 4)
    private String financesCode;

    @Column(name = "usuariofinanzas")
    private Boolean financesUser;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "usuariorol",
            joinColumns = @JoinColumn(name = "idusuario"),
            inverseJoinColumns = @JoinColumn(name = "idrol"),
            schema = encens.khipus.util.Constants.KHIPUS_SCHEMA
    )
    @Filter(name = encens.khipus.util.Constants.COMPANY_FILTER_NAME)
    @OrderBy("name asc")
    private List<Role> roles;

/*    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @Filter(name = encens.khipus.util.Constants.COMPANY_FILTER_NAME)
    private List<UserCashBox> userCashBoxList = new ArrayList<UserCashBox>(0);
*/
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserBusinessUnit> businessUnits;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "idcompania", nullable = false, updatable = false, insertable = true)
    private Company company;


    @Version
    @Column(name = "version", nullable = false)
    private long version;


    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

/*    public List<UserCashBox> getUserCashBoxList() {
        return userCashBoxList;
    }

    public void setUserCashBoxList(List<UserCashBox> userCashBoxList) {
        this.userCashBoxList = userCashBoxList;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getFinancesCode() {
        return financesCode;
    }

    public void setFinancesCode(String financesCode) {
        this.financesCode = financesCode;
    }

    public Boolean getFinancesUser() {
        return financesUser;
    }

    public void setFinancesUser(Boolean financesUser) {
        this.financesUser = financesUser;
    }

    public String getPreviousPassword() {
        return previousPassword;
    }

    public void setPreviousPassword(String previousPassword) {
        this.previousPassword = previousPassword;
    }

    public List<UserBusinessUnit> getBusinessUnits() {
        return businessUnits;
    }

    public void setBusinessUnits(List<UserBusinessUnit> businessUnits) {
        this.businessUnits = businessUnits;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof User && this.getId().equals(((User) obj).getId());
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
