package name.orionis.cms.core.rbac.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class RbacRole  implements Serializable  {

    @Column(unique = true)
    @Size(min = 2, max = 20)
    private String roleName;

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "rbacRole")
    private Set<RbacUser> rbacUser = new HashSet<RbacUser>();

    @Enumerated
    private Status status;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rbacRole")
    private Set<RbacPermission> rbacPermissions = new HashSet<RbacPermission>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rbacRole")
    private Set<RbacMenu> rbacMenus = new HashSet<RbacMenu>();
}
