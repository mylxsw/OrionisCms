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
import name.orionis.cms.extensions.content.form.NewsForm;
import name.orionis.cms.extensions.content.model.News;
import name.orionis.cms.extensions.content.service.NewsService;
import name.orionis.helper.reflection.annotation.Remark;

@Remark(value = "新闻文章控制器", group = "content")
@RequestMapping("news")
@Controller
public class NewsController extends BaseController {

	@Resource
	private NewsService newsService;

	@Remark(value = "文章列表", group = "news")
	@RequestMapping("list")
	public String list(HttpServletRequest req, HttpServletResponse resp,
			Model model) {
		model.addAttribute("news", newsService.findAllNews());
		return view("list");
	}

	@Remark(value = "添加文章", group = "news")
	@RequestMapping("add")
	public String add(@Valid @ModelAttribute NewsForm newsForm,
			BindingResult result, HttpServletRequest req,
			HttpServletResponse resp, HttpSession session, Model model) {

		UserInfo user = (UserInfo) session
				.getAttribute(AccountController.ACCOUNT_INFO);

		if (HTTP_GET.equals(req.getMethod())) {
			return view("add");
		}
		// Check Form Information
		if (result.hasErrors() || !newsForm.validate()) {
			return errors(result, newsForm, resp);
		}
		News entity = newsForm.toEntity();

		newsService.saveNews(entity);

		return success(resp);
	}

	@Remark(value = "编辑文章", group = "news")
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

	@Remark(value = "删除文章", group = "news")
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

