package name.orionis.cms.extensions.content.web;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import name.orionis.cms.extensions.content.form.NewsForm;
import name.orionis.cms.extensions.content.model.Category;
import name.orionis.cms.extensions.content.model.News;
import name.orionis.cms.extensions.content.service.CategoryService;
import name.orionis.cms.extensions.content.service.NewsService;
import name.orionis.helper.reflection.annotation.Remark;

@Remark(value = "Article Controller", group = "content")
@RequestMapping("news")
@Controller
public class NewsController extends BaseController {

	@Resource
	private NewsService newsService;
	@Resource
	private CategoryService cateService;

	@Remark(value = "Article List", group = "news")
	@RequestMapping("list")
	public String list(HttpServletRequest req, HttpServletResponse resp,
			Model model) {
		model.addAttribute("news", newsService.findAllNews());
		return view("list");
	}

	@Remark(value = "Add Article", group = "news")
	@RequestMapping("add")
	public String add(@Valid @ModelAttribute NewsForm newsForm,
			BindingResult result, HttpServletRequest req,
			HttpServletResponse resp, HttpSession session, Model model) {

		UserInfo user = (UserInfo) session
				.getAttribute(AccountController.ACCOUNT_INFO);

		if (HTTP_GET.equals(req.getMethod())) {
			Map<String,String> map = new HashMap<String, String>();
			List<Category> cateList = cateService.findAllCategorys();
			for(Category c : cateList){
				map.put(c.getId() + "", c.getCate_name());
			}
			model.addAttribute("cates", map);
			return view("add");
		}
		// Check Form Information
		if (result.hasErrors() || !newsForm.validate()) {
			return errors(result, newsForm, resp);
		}

		newsService.addNews(newsForm);

		return success(resp);
	}

	@Remark(value = "Modify Article", group = "news")
	@RequestMapping("edit")
	public String edit(@Valid @ModelAttribute NewsForm newsForm,
			BindingResult result, HttpSession session, HttpServletRequest req,
			HttpServletResponse resp, Model model) {
		UserInfo user = (UserInfo) session
				.getAttribute(AccountController.ACCOUNT_INFO);

		long id = Long.parseLong(req.getParameter("id"));
		if (HTTP_GET.equals(req.getMethod())) {
			model.addAttribute("news", newsService.findNews(id));
			return view("edit");
		}
		// Check Form Information
		if (result.hasErrors() || !newsForm.validate()) {
			return errors(result, newsForm, resp);
		}
		News entity = newsForm.toEntity();
		entity.setId(id);
		newsService.updateNews(entity);

		return success(resp);
	}

	@Remark(value = "Delete Article", group = "news")
	@RequestMapping("delete")
	public String delete(@RequestParam("id") long id, HttpServletRequest req,
			HttpServletResponse resp) {
		newsService.deleteNews(newsService.findNews(id));
		return success(resp);
	}

	@Override
	protected String _viewBase() {
		return "extensions/news/";
	}

}

