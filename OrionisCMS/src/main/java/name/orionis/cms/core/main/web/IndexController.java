package name.orionis.cms.core.main.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import name.orionis.cms.core.base.BaseController;

/**
 * The Cms System main controller
 * @author orionis
 * @2013-5-14
 * Site : http://blog.orionis.name
 *
 */
@Controller
public class IndexController extends BaseController {
	
	/**
	 * Admin Home Page
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String index(Model model){
		return view("index");
	}
	
	@Override
	protected String _viewBase() {
		return "main/";
	}

}
