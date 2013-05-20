package name.orionis.cms.extensions.blogroll.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import name.orionis.cms.core.base.BaseController;
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
@Remark(value="Blogroll" ,group="blogroll")
@RequestMapping("blogroll")
@Controller
public class BlogrollController extends BaseController {
	
	@Resource
	private BlogrollService blogrollService;
	@Resource
	private BlogrollCategoryService categoryService;
	
	@Remark(value="Blogroll List" , group="blogroll")
	@RequestMapping("list")
	public String list(HttpServletRequest req, HttpServletResponse resp){
		
		return view("list");
	}
	
	@Override
	protected String _viewBase() {
		return "extensions/blogroll/";
	}

}
