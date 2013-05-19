package name.orionis.cms.core.rbac.model;

import java.io.Serializable;

import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findRbacMenusByRbacRoleAndParentIdEquals", "findRbacMenusByParentIdEquals" })
public class RbacMenu implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
    @Size(max = 40)
    private String menuName;

    @Size(max = 100)
    private String url;

    @Enumerated
    private Status status;

    @Value("0")
    private Long parentId;

    @ManyToOne
    private RbacRole rbacRole;
}
