package name.orionis.cms.core.rbac.web;

import name.orionis.cms.core.base.BaseController;
import name.orionis.helper.reflection.annotation.Remark;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Rbac Permission Controller
 * @author orionis
 * @2013-5-18
 * Site : http://blog.orionis.name
 *
 */
@Controller
@RequestMapping("/rbac/permission")
@Remark(value="Rbac Permission Controller", group="rbac")
public class RbacPermissionController extends BaseController {

	
	
	@Override
	protected String _viewBase() {
		return null;
	}

}
