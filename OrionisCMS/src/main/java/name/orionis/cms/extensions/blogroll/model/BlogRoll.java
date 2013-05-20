package name.orionis.cms.extensions.blogroll.model;

import java.util.Date;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class BlogRoll {

    private int siteId;

    @Size(min = 1, max = 100)
    private String linkName;

    @Size(max = 100)
    private String linkUrl;

    @Size(max = 100)
    private String logo;

    @Size(max = 200)
    private String introduce;

    private int listOrder;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date createTime;

    private int linkType;

    @ManyToOne
    private BlogrollCategory blogRollCategory;
}
