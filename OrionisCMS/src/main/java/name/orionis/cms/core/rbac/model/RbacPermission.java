package name.orionis.cms.core.rbac.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class RbacPermission {

    @NotNull
    @Size(max = 40)
    private String permissionName;

    @NotNull
    @Size(max = 255)
    private String permissionValue;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "rbacPermissions")
    private Set<RbacRole> rbacRoles = new HashSet<RbacRole>();
}
