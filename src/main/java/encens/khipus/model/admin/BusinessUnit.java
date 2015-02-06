package encens.khipus.model.admin;


import encens.khipus.model.contacts.Organization;
import encens.khipus.model.employees.Employee;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

/**
 * Entity for BusinessUnit
 *
 * @version 2.26
 * @author:
 */

@Entity
@Filters(
        {
                @Filter(name = encens.khipus.util.Constants.COMPANY_FILTER_NAME),
                @Filter(name = encens.khipus.util.Constants.BUSINESS_UNIT_FILTER_NAME)
        }
)

@GenericGenerator(name = "foreign", strategy = "foreign", parameters = {
        @Parameter(name = "property", value = "organization")})
@Table(schema = encens.khipus.util.Constants.KHIPUS_SCHEMA, name = "unidadnegocio", uniqueConstraints = @UniqueConstraint(columnNames = {"idcompania", "codunidadejecutora"}))
public class BusinessUnit implements java.io.Serializable {

    @Id
    @GeneratedValue(generator = "foreign")
    @Column(name = "idunidadnegocio")
    private Long id;

    @Column(name = "SIGLA", length = 10, nullable = false)
    private String acronym;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},optional = true)
    @PrimaryKeyJoinColumn(name = "IDMETAPRODUCTOPRODUCCION")
    private Organization organization;

    @Column(name = "cantidaddepartamentos", nullable = false)
    private Integer quantityDepartments;

    @Column(name = "cantidadempleados", nullable = false)
    private Integer quantityEmployees;

    @Column(name = "descripcion")
    @Lob
    private String descriptionBU;

    @ManyToOne
    @JoinColumn(name = "idtipounidadnegocio", nullable = false)
    private BusinessUnitType businessUnitType;

    @Version
    @Column(name = "version", nullable = false)
    private long version;

    @Column(name = "publicidad", length = 80)
    private String publicity;

    @Column(name = "codunidadejecutora", nullable = false, length = 10)
    private String executorUnitCode;

    @Column(name = "POSICION")
    private Integer position;

    @Column(name = "NOMATRICOMERCIAL", length = 100)
    private String commercialEnrollmentNumber;

    @Column(name = "DIRECCION", length = 250)
    private String address;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "IDRESPONSABLERH")
    private Employee humanResourcesResponsible;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "idcompania", nullable = false, updatable = false, insertable = true)
    private Company company;

    public BusinessUnit() {
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public Integer getQuantityDepartments() {
        return quantityDepartments;
    }

    public void setQuantityDepartments(Integer quantityDepartments) {
        this.quantityDepartments = quantityDepartments;
    }

    public Integer getQuantityEmployees() {
        return quantityEmployees;
    }

    public void setQuantityEmployees(Integer quantityEmployees) {
        this.quantityEmployees = quantityEmployees;
    }

    public String getDescriptionBU() {
        return descriptionBU;
    }

    public void setDescriptionBU(String descriptionBU) {
        this.descriptionBU = descriptionBU;
    }

    public BusinessUnitType getBusinessUnitType() {
        return businessUnitType;
    }

    public void setBusinessUnitType(BusinessUnitType businessUnitType) {
        this.businessUnitType = businessUnitType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getPublicity() {
        return publicity;
    }

    public void setPublicity(String publicity) {
        this.publicity = publicity;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getExecutorUnitCode() {
        return executorUnitCode;
    }

    public void setExecutorUnitCode(String executorUnitCode) {
        this.executorUnitCode = executorUnitCode;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getCommercialEnrollmentNumber() {
        return commercialEnrollmentNumber;
    }

    public void setCommercialEnrollmentNumber(String commercialEnrollmentNumber) {
        this.commercialEnrollmentNumber = commercialEnrollmentNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Employee getHumanResourcesResponsible() {
        return humanResourcesResponsible;
    }

    public void setHumanResourcesResponsible(Employee humanResourcesResponsible) {
        this.humanResourcesResponsible = humanResourcesResponsible;
    }

    public String getFullName() {
        return getExecutorUnitCode() + " - " + getOrganization().getName();
    }

    public Integer getExecutorUnitCodeAsInteger() {
        return Integer.valueOf(executorUnitCode);
    }
}
