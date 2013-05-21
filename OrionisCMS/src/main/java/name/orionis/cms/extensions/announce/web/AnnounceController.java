package name.orionis.cms.extensions.announce.web;

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
import name.orionis.cms.extensions.announce.form.AnnounceForm;
import name.orionis.cms.extensions.announce.model.Announce;
import name.orionis.cms.extensions.announce.service.AnnounceService;
import name.orionis.helper.reflection.annotation.Remark;

@Remark(value="Announce" ,group="announce")
@RequestMapping("announce")
@Controller
public class AnnounceController extends BaseController {

	@Resource
	private AnnounceService announceService;
	
	@Remark(value="Announce List" , group="announce")
	@RequestMapping("list")
	public String list(HttpServletRequest req, 
			HttpServletResponse resp,
			Model model){
		model.addAttribute("announces", 
				announceService.findAllAnnounces());
		return view("list");
	}
	
	@Remark(value="Announce Add", group="announce")
	@RequestMapping("add")
	public String add( @Valid @ModelAttribute AnnounceForm announceForm,
			BindingResult result, 
			HttpServletRequest req, 
			HttpServletResponse resp, 
			HttpSession session,
			Model model){
		
		UserInfo user = (UserInfo) session.getAttribute(AccountController.ACCOUNT_INFO);
		
		if(HTTP_GET.equals(req.getMethod())){
			return view("add");
		}
		// Check Form Information
		if(result.hasErrors() || !announceForm.validate()){
			return errors(result, announceForm, resp);
		}
		Announce entity = announceForm.toEntity();
		entity.setPublisher(user.getUsername());
		announceService.saveAnnounce(entity);
		
		return success(resp);
	}
	
	@Remark(value="Announce Edit", group="announce")
	@RequestMapping("edit")
	public String edit( @Valid @ModelAttribute AnnounceForm announceForm,
			BindingResult result, 
			HttpSession session,
			HttpServletRequest req, 
			HttpServletResponse resp, Model model){
		UserInfo user = (UserInfo) session.getAttribute(AccountController.ACCOUNT_INFO);
		
		long id = Long.parseLong(req.getParameter("id"));
		if(HTTP_GET.equals(req.getMethod())){
			model.addAttribute("announce",announceService.findAnnounce(id));
			return view("edit");
		}
		// Check Form Information
		if(result.hasErrors() || !announceForm.validate()){
			return errors(result, announceForm, resp);
		}
		Announce entity = announceForm.toEntity();
		entity.setId(id);
		entity.setUpdator(user.getUsername());
		announceService.updateAnnounce(entity);
		
		return success(resp);
	}
	
	@Remark(value="Announce delete",group="announce")
	@RequestMapping("delete")
	public String role_delete(@RequestParam("id") long id, HttpServletRequest req, 
			HttpServletResponse resp){
		announceService.deleteAnnounce(announceService.findAnnounce(id));
		return success(resp);
	}
	
	
	
	@Override
	protected String _viewBase() {
		return "extensions/announce/";
	}

}
