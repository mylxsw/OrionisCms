package name.orionis.cms.extensions.blogroll.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class BlogrollCategory {

    @Size(min = 1, max = 50)
    private String categoryName;

    private int listOrder;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="blogRollCategory")
    private Set<BlogRoll> blogROlls = new HashSet<BlogRoll>();
}
