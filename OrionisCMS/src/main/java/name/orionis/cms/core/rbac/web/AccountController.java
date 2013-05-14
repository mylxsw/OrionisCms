package name.orionis.cms.core.rbac.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import name.orionis.cms.core.base.BaseController;

@Controller
@RequestMapping("/account")
public class AccountController extends BaseController {

	@RequestMapping("login")
	public String login(Model model){
		return view("login");
	}
	
	@RequestMapping("timeout")
	public String timeout(Model model){
		return view("timeout");
	}
	
	@Override
	protected String _viewBase() {
		return "account/";
	}

}
