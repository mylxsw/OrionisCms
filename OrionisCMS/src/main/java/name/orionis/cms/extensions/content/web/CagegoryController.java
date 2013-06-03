package name.orionis.cms.extensions.content.web;
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
import name.orionis.cms.extensions.content.form.CategoryForm;
import name.orionis.cms.extensions.content.model.Category;
import name.orionis.cms.extensions.content.service.CategoryService;
import name.orionis.helper.reflection.annotation.Remark;

/**
 * Content Category
 * @author orionis
 * @2013-5-22
 * Site : http://blog.orionis.name
 *
 */
@Remark(value = "Article Category", group = "content")
@RequestMapping("category")
@Controller
public class CagegoryController extends BaseController {

	@Resource
	private CategoryService categoryService;

	@Remark(value = "Article Category list", group = "category")
	@RequestMapping("list")
	public String list(HttpServletRequest req, HttpServletResponse resp,
			Model model) {
		model.addAttribute("categorys", categoryService.findAllCategorys());
		return view("list");
	}

	@Remark(value = "Add Article Category", group = "category")
	@RequestMapping("add")
	public String add(@Valid @ModelAttribute CategoryForm categoryForm,
			BindingResult result, HttpServletRequest req,
			HttpServletResponse resp, HttpSession session, Model model) {

		UserInfo user = (UserInfo) session
				.getAttribute(AccountController.ACCOUNT_INFO);

		if (HTTP_GET.equals(req.getMethod())) {
			return view("add");
		}
		// Check Form Information
		if (result.hasErrors() || !categoryForm.validate()) {
			return errors(result, categoryForm, resp);
		}
		Category entity = categoryForm.toEntity();

		categoryService.saveCategory(entity);

		return success(resp);
	}

	@Remark(value = "Modify Article Category", group = "category")
	@RequestMapping("edit")
	public String edit(@Valid @ModelAttribute CategoryForm categoryForm,
			BindingResult result, HttpSession session, HttpServletRequest req,
			HttpServletResponse resp, Model model) {
		UserInfo user = (UserInfo) session
				.getAttribute(AccountController.ACCOUNT_INFO);

		long id = Long.parseLong(req.getParameter("id"));
		if (HTTP_GET.equals(req.getMethod())) {
			model.addAttribute("category", categoryService.findCategory(id));
			return view("edit");
		}
		// Check Form Information
		if (result.hasErrors() || !categoryForm.validate()) {
			return errors(result, categoryForm, resp);
		}
		Category entity = categoryForm.toEntity();
		entity.setId(id);
		categoryService.updateCategory(entity);

		return success(resp);
	}

	@Remark(value = "Delete Article Category", group = "category")
	@RequestMapping("delete")
	public String delete(@RequestParam("id") long id, HttpServletRequest req,
			HttpServletResponse resp) {
		categoryService.deleteCategory(categoryService.findCategory(id));
		return success(resp);
	}

	@Override
	protected String _viewBase() {
		return "extensions/category/";
	}

}

