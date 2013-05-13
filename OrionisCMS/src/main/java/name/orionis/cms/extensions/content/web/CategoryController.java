package name.orionis.cms.extensions.content.web;

import javax.annotation.Resource;

import name.orionis.cms.core.base.BaseController;
import name.orionis.cms.extensions.content.service.CategoryService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class CategoryController extends BaseController {
	
	@Resource
	private CategoryService categoryService;
	
	
	@Override
	protected String _viewBase() {
		return null;
	}
	
}
