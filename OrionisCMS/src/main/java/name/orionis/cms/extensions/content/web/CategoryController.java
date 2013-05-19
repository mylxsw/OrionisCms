package name.orionis.cms.extensions.content.web;

import javax.servlet.http.HttpServletRequest;

import name.orionis.cms.core.base.BaseController;
import name.orionis.helper.reflection.annotation.Remark;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/category")
@Remark(value="Category" , group="content")
public class CategoryController extends BaseController {
	
	@RequestMapping("index")
	@Remark(value="index", group="content")
	public String index(HttpServletRequest req, ModelAndView model){
		return view("index");
	}
	
	
	@Override
	protected String _viewBase() {
		return "category/";
	}
	
}
