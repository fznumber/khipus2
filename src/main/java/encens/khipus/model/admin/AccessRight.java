package encens.khipus.model.admin;

import org.hibernate.annotations.Filter;

import javax.persistence.*;

/**
 * Entity that relates Function and Role
 *
 * @author:
 */

@Entity
@Filter(name = encens.khipus.util.Constants.COMPANY_FILTER_NAME)
@Table(schema = encens.khipus.util.Constants.KHIPUS_SCHEMA, name = "derechoacceso")
public class AccessRight implements java.io.Serializable {

    @EmbeddedId
    private AccessRightPk id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idfuncionalidad", referencedColumnName = "idfuncionalidad", nullable = false, insertable = false, updatable = false)
    private SystemFunction function;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idrol", referencedColumnName = "idrol", nullable = false, insertable = false, updatable = false)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "idcompania", referencedColumnName = "idcompania", nullable = false, updatable = false, insertable = true),
            @JoinColumn(name = "idmodulo", referencedColumnName = "idmodulo", nullable = false, updatable = false, insertable = true)
    })
    private CompanyModule companyModule;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "idcompania", nullable = false, updatable = false, insertable = false)
    private Company company;

    @Column(name = "permiso", nullable = false)
    private Byte permission;

    public AccessRight() {
    }

//    public AccessRight(SystemFunction function, Role role) {
//        this.function = function;
//        this.role = role;
//        this.id = new AccessRightPk(function.getId(), role.getId());
//    }

    public AccessRight(SystemFunction function, Role role, CompanyModule companyModule) {
        this.function = function;
        this.role = role;
        this.id = new AccessRightPk(function.getId(), role.getId());
        this.companyModule = companyModule;
//        this.company = companyModule.getId().getCompany();
    }

    public AccessRightPk getId() {
        return id;
    }

    public void setId(AccessRightPk id) {
        this.id = id;
    }

    public SystemFunction getFunction() {
        return function;
    }

    public void setFunction(SystemFunction function) {
        this.function = function;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Byte getPermission() {
        return permission;
    }

    public void setPermission(Byte permission) {
        this.permission = permission;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public CompanyModule getCompanyModule() {
        return companyModule;
    }

    public void setCompanyModule(CompanyModule companyModule) {
        this.companyModule = companyModule;
    }
}

