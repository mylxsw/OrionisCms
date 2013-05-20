package name.orionis.cms.core.rbac.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findRbacUsersByUserNameEquals", "findRbacUsersByUserNameEqualsAndPasswordEquals" })
public class RbacUser implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
    @Column(unique = true)
    @Size(min = 3, max = 50)
    private String userName;

    @Size(min = 0, max = 256)
    private String password;

    @Size(max = 15)
    private String lastLoginIP;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date lastLoginTime;

    @Size(max = 40)
    private String email;

    @Size(max = 50)
    private String realName;

    @Enumerated
    private Status status;

    @ManyToOne
    private RbacRole rbacRole;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createData;
}
