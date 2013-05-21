package name.orionis.cms.extensions.demo.web;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import name.orionis.cms.core.base.BaseController;
import name.orionis.cms.core.rbac.authentication.UserInfo;
import name.orionis.cms.core.rbac.web.AccountController;
import name.orionis.cms.extensions.demo.form.DemoForm;
import name.orionis.cms.extensions.demo.model.Demo;
import name.orionis.cms.extensions.demo.service.DemoService;
import name.orionis.helper.reflection.annotation.Remark;

@Remark(value = "Demo", group = "demo")
@RequestMapping("demo")
@Controller
public class DemoController extends BaseController {

	@Resource
	private DemoService demoService;

	@Remark(value = "Demo List", group = "demo")
	@RequestMapping("list")
	public String list(HttpServletRequest req, HttpServletResponse resp,
			Model model) {
		model.addAttribute("demo", demoService.findAllDemoes());
		return view("list");
	}

	@Remark(value = "Demo Add", group = "demo")
	@RequestMapping("add")
	public String add(@Valid @ModelAttribute DemoForm demoForm,
			BindingResult result, HttpServletRequest req,
			HttpServletResponse resp, HttpSession session, Model model) {

		UserInfo user = (UserInfo) session
				.getAttribute(AccountController.ACCOUNT_INFO);

		if (HTTP_GET.equals(req.getMethod())) {
			return view("add");
		}
		// Check Form Information
		if (result.hasErrors() || !demoForm.validate()) {
			return errors(result, demoForm, resp);
		}
		Demo entity = demoForm.toEntity();

		demoService.saveDemo(entity);

		return success(resp);
	}

	@Remark(value="Demo Edit", group="demo")
	@RequestMapping("edit")
	public String edit( @Valid @ModelAttribute DemoForm demoForm,
			BindingResult result, 
			HttpSession session,
			HttpServletRequest req, 
			HttpServletResponse resp, Model model){
		UserInfo user = (UserInfo) session.getAttribute(AccountController.ACCOUNT_INFO);
		
		long id = Long.parseLong(req.getParameter("id"));
		if(HTTP_GET.equals(req.getMethod())){
			model.addAttribute("demo",demoService.findDemo(id));
			return view("edit");
		}
		// Check Form Information
		if(result.hasErrors() || !demoForm.validate()){
			return errors(result, demoForm, resp);
		}
		Demo entity = demoForm.toEntity();
		entity.setId(id);
		demoService.updateDemo(entity);
		
		return success(resp);
	}

	@Remark(value = "Demo delete", group = "demo")
	@RequestMapping("delete")
	public String delete(@RequestParam("id") long id, HttpServletRequest req,
			HttpServletResponse resp) {
		demoService.deleteDemo(demoService.findDemo(id));
		return success(resp);
	}

	@Override
	protected String _viewBase() {
		return "extensions/demo/";
	}

}

