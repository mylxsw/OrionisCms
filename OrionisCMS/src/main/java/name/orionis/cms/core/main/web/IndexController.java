package name.orionis.cms.core.main.web;

import javax.servlet.http.HttpSession;

import name.orionis.cms.core.base.BaseController;
import name.orionis.helper.reflection.annotation.Remark;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The Cms System main controller
 * @author orionis
 * @2013-5-14
 * Site : http://blog.orionis.name
 *
 */
@Controller
@Remark(value="CMS管理主界面",group="main")
public class IndexController extends BaseController {
	
	
	/**
	 * Admin Home Page
	 * @param model
	 * @return
	 */
	@Remark(value="管理主页" , group="main")
	@RequestMapping("/index")
	public String index(Model model, HttpSession session){
		
		return view("index");
	}
	
	
	@Override
	protected String _viewBase() {
		return "main/";
	}

}
