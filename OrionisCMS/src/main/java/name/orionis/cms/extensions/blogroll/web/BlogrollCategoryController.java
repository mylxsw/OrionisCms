package name.orionis.cms.extensions.blogroll.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import name.orionis.cms.core.base.BaseController;
import name.orionis.cms.extensions.blogroll.service.BlogrollCategoryService;
import name.orionis.cms.extensions.blogroll.service.BlogrollService;
import name.orionis.helper.reflection.annotation.Remark;
/**
 * Blogroll Category Controller
 * @author orionis
 * @2013-5-20
 * Site : http://blog.orionis.name
 *
 */
@Remark(value="Blogroll Category" ,group="blogroll")
@RequestMapping("blogroll/category")
@Controller
public class BlogrollCategoryController extends BaseController {
	@Resource
	private BlogrollService blogrollService;
	@Resource
	private BlogrollCategoryService categoryService;
	
	@Remark(value="Blogroll Category List" , group="blogroll")
	@RequestMapping("list")
	public String list(HttpServletRequest req, HttpServletResponse resp, Model model){
		model.addAttribute("category",categoryService.findAllBlogrollCategorys());
		return view("list");
	}
	
	@Remark(value="Blogroll Category Add", group="blogroll")
	@RequestMapping("add")
	public String add(HttpServletRequest req, HttpServletResponse resp){
		return view("add");
	}
	
	
	@Override
	protected String _viewBase() {
		return "extensions/blogroll/category/";
	}
}
