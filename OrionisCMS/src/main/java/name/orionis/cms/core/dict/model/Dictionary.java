package name.orionis.cms.core.dict.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findDictionarysByCodeEquals" })
public class Dictionary {

    @NotNull
    @Size(max = 50)
    private String code;

    @Size(max = 200)
    private String value;

    @Size(max = 255)
    private String explaination;
}
