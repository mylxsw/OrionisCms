package name.orionis.cms.extensions.content.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Category {

    @NotNull
    @Value("0")
    private short site_id;

    @NotNull
    @Size(min = 1, max = 50)
    private String cate_name;

    @Size(max = 500)
    private String description;

    @Value("0")
    private int parent_id;

    @Size(max = 100)
    private String url;

    @Value("false")
    private Boolean child;

    @Size(max = 5)
    private String style;

    @Size(max = 100)
    private String image;

    @Value("false")
    private Boolean sethtml;

    @Size(max = 50)
    private String letter;

    @Size(max = 1000)
    private String settings;

    @DecimalMax("999")
    private short list_order;

    @Value("true")
    private Boolean is_menu;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private Set<News> contents = new HashSet<News>();
}
