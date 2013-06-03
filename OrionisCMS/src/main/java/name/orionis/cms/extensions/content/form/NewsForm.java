package name.orionis.cms.extensions.content.form;

import java.util.Date;

import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import name.orionis.cms.core.base.Form;
import name.orionis.cms.extensions.content.model.Category;
import name.orionis.cms.extensions.content.model.News;
/**
 * News Form
 * @author orionis
 * @2013-6-2
 * Site : http://blog.orionis.name
 *
 */
public class NewsForm extends Form<News> {
	@NotNull
    @Size(min = 1, max = 80)
    private String title;

    @Size(max = 1000)
    private String description;

    @Size(max = 40)
    private String username;

    @DecimalMax("999")
    private short list_order;

    @Size(max = 10000)
    private String content;
    
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date create_time;

    private Long category;

	@Override
	public News toEntity() {
		News n = new News();
		n.setTitle(title);
		n.setContent(content);
		n.setCreate_time(create_time);
		n.setDescription(description);
		n.setList_order(list_order);
		n.setUsername(username);
		
		return n;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public short getList_order() {
		return list_order;
	}

	public void setList_order(short list_order) {
		this.list_order = list_order;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Long getCategory() {
		return category;
	}

	public void setCategory(Long category) {
		this.category = category;
	}
	
}
