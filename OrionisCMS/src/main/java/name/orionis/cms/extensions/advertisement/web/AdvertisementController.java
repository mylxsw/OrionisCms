package name.orionis.cms.extensions.advertisement.web;
import java.util.Date;

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
import name.orionis.cms.extensions.advertisement.form.AdvertisementForm;
import name.orionis.cms.extensions.advertisement.model.Advertisement;
import name.orionis.cms.extensions.advertisement.service.AdvertisementService;
import name.orionis.helper.reflection.annotation.Remark;

/**
 * Advertisement Controller
 * @author orionis
 * @2013-5-22
 * Site : http://blog.orionis.name
 *
 */
@Remark(value = "Advertisement Management", group = "advertisement")
@RequestMapping("advertisement")
@Controller
public class AdvertisementController extends BaseController {

	@Resource
	private AdvertisementService advertisementService;

	@Remark(value = "AD list", group = "advertisement")
	@RequestMapping("list")
	public String list(HttpServletRequest req, HttpServletResponse resp,
			Model model) {
		model.addAttribute("advertisements", advertisementService.findAllAdvertisements());
		return view("list");
	}

	@Remark(value = "Add AD", group = "advertisement")
	@RequestMapping("add")
	public String add(@Valid @ModelAttribute AdvertisementForm advertisementForm,
			BindingResult result, HttpServletRequest req,
			HttpServletResponse resp, HttpSession session, Model model) {

		UserInfo user = (UserInfo) session
				.getAttribute(AccountController.ACCOUNT_INFO);

		if (HTTP_GET.equals(req.getMethod())) {
			return view("add");
		}
		// Check Form Information
		if (result.hasErrors() || !advertisementForm.validate()) {
			return errors(result, advertisementForm, resp);
		}
		Advertisement entity = advertisementForm.toEntity();
		entity.setPublisher(user.getUsername());
		entity.setCreateDate(new Date());
		advertisementService.saveAdvertisement(entity);

		return success(resp);
	}

	@Remark(value = "Modify AD", group = "advertisement")
	@RequestMapping("edit")
	public String edit(@Valid @ModelAttribute AdvertisementForm advertisementForm,
			BindingResult result, HttpSession session, HttpServletRequest req,
			HttpServletResponse resp, Model model) {
		UserInfo user = (UserInfo) session
				.getAttribute(AccountController.ACCOUNT_INFO);

		long id = Long.parseLong(req.getParameter("id"));
		if (HTTP_GET.equals(req.getMethod())) {
			model.addAttribute("advertisement", advertisementService.findAdvertisement(id));
			return view("edit");
		}
		// Check Form Information
		if (result.hasErrors() || !advertisementForm.validate()) {
			return errors(result, advertisementForm, resp);
		}
		Advertisement entity = advertisementForm.toEntity();
		entity.setId(id);
		entity.setUpdator(user.getUsername());
		advertisementService.updateAdvertisement(entity);

		return success(resp);
	}

	@Remark(value = "Delete AD", group = "advertisement")
	@RequestMapping("delete")
	public String delete(@RequestParam("id") long id, HttpServletRequest req,
			HttpServletResponse resp) {
		advertisementService.deleteAdvertisement(advertisementService.findAdvertisement(id));
		return success(resp);
	}

	@Override
	protected String _viewBase() {
		return "extensions/advertisement/";
	}

}

