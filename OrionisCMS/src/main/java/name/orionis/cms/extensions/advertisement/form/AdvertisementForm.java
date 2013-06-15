package name.orionis.cms.extensions.advertisement.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import name.orionis.cms.core.base.Form;
import name.orionis.cms.extensions.advertisement.model.Advertisement;
/**
 * Advertisement Form
 * @author orionis
 * @2013-5-22
 * Site : http://blog.orionis.name
 *
 */
public class AdvertisementForm extends Form<Advertisement> {

	@NotNull(message="标题不能为空!")
    @Size(min=1, max = 50, message="标题长度为1-50字符之间!")
    private String title;
    

    @Size(min = 0, max = 500, message="内容不能多有500个字符长度!")
    private String content;
	
	@Override
	public Advertisement toEntity() {
		Advertisement adv = new Advertisement();
		adv.setTitle(title);
		adv.setContent(content);
		return adv;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
