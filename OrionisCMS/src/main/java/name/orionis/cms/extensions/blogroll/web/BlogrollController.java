package name.orionis.cms.extensions.blogroll.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import name.orionis.cms.core.base.BaseController;
import name.orionis.cms.extensions.blogroll.form.BlogrollCategoryForm;
import name.orionis.cms.extensions.blogroll.form.BlogrollForm;
import name.orionis.cms.extensions.blogroll.model.BlogRoll;
import name.orionis.cms.extensions.blogroll.model.BlogrollCategory;
import name.orionis.cms.extensions.blogroll.service.BlogrollCategoryService;
import name.orionis.cms.extensions.blogroll.service.BlogrollService;
import name.orionis.helper.reflection.annotation.Remark;

/**
 * Blogroll Controller
 * @author orionis
 * @2013-5-20
 * Site : http://blog.orionis.name
 *
 */
@Remark(value="友情链接" ,group="blogroll")
@RequestMapping("blogroll")
@Controller
public class BlogrollController extends BaseController {
	
	@Resource
	private BlogrollService blogrollService;
	@Resource
	private BlogrollCategoryService categoryService;
	
	@Remark(value="友情链接列表" , group="blogroll")
	@RequestMapping("list")
	public String list(HttpServletRequest req, 
			HttpServletResponse resp,
			Model model){
		model.addAttribute("blogrolls", 
				blogrollService.findAllBlogRolls());
		return view("list");
	}
	
	@Remark(value="添加友情链接", group="blogroll")
	@RequestMapping("add")
	public String add( @Valid @ModelAttribute BlogrollForm rollForm,
			BindingResult result, 
			HttpServletRequest req, 
			HttpServletResponse resp, 
			Model model){
		if(HTTP_GET.equals(req.getMethod())){
			model.addAttribute("category", 
					blogrollService.findAllBlogRollsMap());
			return view("add");
		}
		// Check Form Information
		if(result.hasErrors() || !rollForm.validate()){
			return errors(result, rollForm, resp);
		}
		
		blogrollService.saveBlogRoll(rollForm.toEntity());
		
		return success(resp);
	}
	
	@Remark(value="编辑友情链接", group="blogroll")
	@RequestMapping("edit")
	public String edit( @Valid @ModelAttribute BlogrollForm rollForm,
			BindingResult result, 
			HttpServletRequest req, HttpServletResponse resp, Model model){
		long id = Long.parseLong(req.getParameter("id"));
		if(HTTP_GET.equals(req.getMethod())){
			model.addAttribute("category", 
					blogrollService.findAllBlogRollsMap());
			model.addAttribute("blogroll",blogrollService.findBlogRoll(id));
			return view("edit");
		}
		// Check Form Information
		if(result.hasErrors() || !rollForm.validate()){
			return errors(result, rollForm, resp);
		}
		BlogRoll entity = rollForm.toEntity();
		entity.setId(id);
		blogrollService.updateBlogRoll(entity);
		
		return success(resp);
	}
	
	@Remark(value="删除友情链接",group="blogroll")
	@RequestMapping("delete")
	public String role_delete(@RequestParam("id") long id, HttpServletRequest req, 
			HttpServletResponse resp){
		blogrollService.deleteBlogRoll(blogrollService.findBlogRoll(id));
		return success(resp);
	}
	
	@Override
	protected String _viewBase() {
		return "extensions/blogroll/";
	}

}
