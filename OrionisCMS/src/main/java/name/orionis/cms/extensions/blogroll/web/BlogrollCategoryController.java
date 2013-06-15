package name.orionis.cms.extensions.blogroll.web;

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
import name.orionis.cms.extensions.blogroll.model.BlogrollCategory;
import name.orionis.cms.extensions.blogroll.service.BlogrollCategoryService;
import name.orionis.helper.reflection.annotation.Remark;
/**
 * Blogroll Category Controller
 * @author orionis
 * @2013-5-20
 * Site : http://blog.orionis.name
 *
 */
@Remark(value="友情链接分类管理" ,group="blogroll")
@RequestMapping("blogroll/category")
@Controller
public class BlogrollCategoryController extends BaseController {
	@Resource
	private BlogrollCategoryService categoryService;
	
	/**
	 * Category List
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@Remark(value="友情链接分类管理" , group="blogroll")
	@RequestMapping("list")
	public String list(HttpServletRequest req, HttpServletResponse resp, Model model){
		model.addAttribute("category",categoryService.findAllBlogrollCategorys());
		return view("list");
	}
	
	/**
	 * Add New Category
	 * @param categoryForm
	 * @param result
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@Remark(value="添加分类 ", group="blogroll")
	@RequestMapping("add")
	public String add( @Valid @ModelAttribute BlogrollCategoryForm categoryForm,
			BindingResult result, 
			HttpServletRequest req, HttpServletResponse resp, Model model){
		if(HTTP_GET.equals(req.getMethod())){
			return view("add");
		}
		// Check Form Information
		if(result.hasErrors() || !categoryForm.validate()){
			return errors(result, categoryForm, resp);
		}
		
		try{
			categoryService.saveBlogrollCategory(categoryForm.toEntity());
		}catch(Exception e){
			return failed(resp);
		}
		return success(resp);
	}
	
	@Remark(value="修改分类", group="blogroll")
	@RequestMapping("edit")
	public String edit( @Valid @ModelAttribute BlogrollCategoryForm categoryForm,
			BindingResult result, 
			HttpServletRequest req, HttpServletResponse resp, Model model){
		long category_id = Long.parseLong(req.getParameter("id"));
		if(HTTP_GET.equals(req.getMethod())){
			model.addAttribute("category", categoryService.findBlogrollCategory(category_id));
			return view("edit");
		}
		// Check Form Information
		if(result.hasErrors() || !categoryForm.validate()){
			return errors(result, categoryForm, resp);
		}
		BlogrollCategory entity = categoryForm.toEntity();
		entity.setId(category_id);
		categoryService.updateBlogrollCategory(entity);
		
		return success(resp);
	}
	
	/**
	 * Category Delete
	 * @param id
	 * @param req
	 * @param resp
	 * @return
	 */
	@Remark(value="删除分类",group="blogroll")
	@RequestMapping("delete")
	public String role_delete(@RequestParam("id") long id, HttpServletRequest req, 
			HttpServletResponse resp){
		try{
			categoryService.deleteBlogrollCategory(BlogrollCategory.findBlogrollCategory(id));
		}catch(Exception e){
			return failed(resp);
		}
		return success(resp);
	}
	
	@Override
	protected String _viewBase() {
		return "extensions/blogroll/category/";
	}
}
