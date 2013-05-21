package name.orionis.cms.extensions.announce.form;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import name.orionis.cms.core.base.Form;
import name.orionis.cms.extensions.announce.model.Announce;
/**
 * Announce Form
 * @author orionis
 * @2013-5-22
 * Site : http://blog.orionis.name
 *
 */
public class AnnounceForm extends Form<Announce> {
    @NotNull
    @Size(min = 1)
    private String title;

    @Size(min = 0, max = 1000)
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date publishTime;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date endTime;

	@Override
	public Announce toEntity() {
		Announce ann = new Announce();
		ann.setTitle(title);
		ann.setContent(content);
		ann.setCreateDate(new Date());
		ann.setStartTime(startTime);
		ann.setEndTime(endTime);
		ann.setPublishTime(publishTime);
		
		return ann;
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

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
