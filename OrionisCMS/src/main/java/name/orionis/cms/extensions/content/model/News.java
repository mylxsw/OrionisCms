package name.orionis.cms.extensions.content.model;

import java.util.Date;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class News {

    @NotNull(message="The title must not be null!")
    @Size(min = 1, max = 80, message="The title length must be 1-80 character length!")
    private String title;

    @Size(max = 1000, message="The description must be less than 1000 character length!")
    private String description;

    @Size(max = 40)
    private String keywords;

    @Size(max = 40)
    private String username;

    @DecimalMax("999")
    private short list_order;

    @Size(max = 40000, message="The Content must be less than 40000 character length!")
    private String content;
    
    @Size(max = 5)
    private String style;

    @Size(max = 100)
    private String image;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date create_time;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date update_time;

    @Value("false")
    private Boolean is_link;

    @Size(max = 100)
    private String url;

    private short status;

    @ManyToOne
    private Category category;
   
}
