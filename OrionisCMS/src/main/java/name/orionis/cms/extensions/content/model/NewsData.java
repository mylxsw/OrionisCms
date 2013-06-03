package name.orionis.cms.extensions.content.model;

import javax.persistence.Id;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class NewsData {

    @Id
    private int id;

    @Size(max = 30)
    private String template;

    @Value("true")
    private Boolean allow_comment;

    @Size(max = 100)
    private String copy_from;

    @DecimalMax("9")
    private short pagination_type;

    private int max_char_per_page;
}
