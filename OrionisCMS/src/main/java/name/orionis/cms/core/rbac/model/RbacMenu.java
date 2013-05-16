package name.orionis.cms.core.rbac.model;

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
@RooJpaActiveRecord
public class RbacMenu {

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
