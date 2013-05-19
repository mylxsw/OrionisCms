package name.orionis.cms.core.rbac.model;

import java.io.Serializable;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findRbacPermissionsByRbacRole" })
public class RbacPermission  implements Serializable {

    @NotNull
    @Size(max = 40)
    private String permissionName;

    @NotNull
    @Size(max = 220)
    private String controller;

    @NotNull
    @Size(max = 220)
    private String method;

    @NotNull
    @Size(max = 255)
    private String url;

    @ManyToOne
    private RbacRole rbacRole;
}
