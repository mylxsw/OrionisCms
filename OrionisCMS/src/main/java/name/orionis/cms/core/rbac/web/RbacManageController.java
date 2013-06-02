package name.orionis.cms.core.rbac.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import name.orionis.cms.core.base.BaseController;
import name.orionis.helper.reflection.annotation.Remark;

/**
 * Rbac System Manager
 * @author orionis
 * @2013-5-17
 * Site : http://blog.orionis.name
 *
 */
@Controller
@RequestMapping("/rbac")
@Remark(value="RBAC管理控制器", group="rbac")
public class RbacManageController extends BaseController {

	@Override
	protected String _viewBase() {
		return null;
	}

}
