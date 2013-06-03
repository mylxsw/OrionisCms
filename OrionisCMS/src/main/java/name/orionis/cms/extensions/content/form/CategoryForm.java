package name.orionis.cms.extensions.content.form;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Value;

import name.orionis.cms.core.base.Form;
import name.orionis.cms.extensions.content.model.Category;
/**
 * Article Category Form
 * @author orionis
 * @2013-6-2
 * Site : http://blog.orionis.name
 *
 */
public class CategoryForm extends Form<Category> {
	@NotNull(message="Category Name must not be null!")
    @Size(min = 1, max = 50, message="Category Name must be 1-50 character length.")
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
    
	@Override
	public Category toEntity() {
		Category cat = new Category();
		cat.setCate_name(cate_name);
		cat.setDescription(description);
		cat.setList_order(list_order);
		cat.setParent_id(parent_id);
		cat.setUrl(url);
		cat.setSethtml(sethtml);
		cat.setSettings(settings);
		return cat;
	}

	public String getCate_name() {
		return cate_name;
	}

	public String getDescription() {
		return description;
	}

	public int getParent_id() {
		return parent_id;
	}

	public String getUrl() {
		return url;
	}

	public Boolean getChild() {
		return child;
	}

	public String getStyle() {
		return style;
	}

	public String getImage() {
		return image;
	}

	public Boolean getSethtml() {
		return sethtml;
	}

	public String getLetter() {
		return letter;
	}

	public String getSettings() {
		return settings;
	}

	public short getList_order() {
		return list_order;
	}

	public Boolean getIs_menu() {
		return is_menu;
	}

	public void setCate_name(String cate_name) {
		this.cate_name = cate_name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setChild(Boolean child) {
		this.child = child;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setSethtml(Boolean sethtml) {
		this.sethtml = sethtml;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public void setSettings(String settings) {
		this.settings = settings;
	}

	public void setList_order(short list_order) {
		this.list_order = list_order;
	}

	public void setIs_menu(Boolean is_menu) {
		this.is_menu = is_menu;
	}

}
