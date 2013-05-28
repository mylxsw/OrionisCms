package name.orionis.cms.core.rbac.authentication;

import javax.servlet.ServletContext;

import name.orionis.cms.core.rbac.web.AccountController;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.ServletContextAware;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.annotations.RemoteMethod;

/**
 * Security for Remote Service
 * 
 * @author orionis
 * @2013-5-28 Site : http://blog.orionis.name
 * 
 */
public aspect SecurityAspectRemote {

	/**
	 * Interface Method default implementation Holder
	 * 
	 * @author orionis
	 * @2013-5-29 Site : http://blog.orionis.name
	 * 
	 */
	interface Holder {
		/**
		 * Get Spring ApplicationContext
		 * 
		 * @return
		 */
		public ApplicationContext getApplicationContext();

		/**
		 * Get Spring ServletContext
		 * 
		 * @return
		 */
		public ServletContext getServletContext();
	}

	/**
	 * Declare the dwr service class implements some interface
	 */
	declare parents :  @RemoteProxy name.orionis.cms.remote.dwr..* 
				implements ServletContextAware, ApplicationContextAware, Holder;

	/**
	 * Holder Implementation
	 * 
	 * @return
	 */
	private ApplicationContext Holder.ctx;

	public void Holder.setApplicationContext(ApplicationContext ctx) {
		this.ctx = ctx;
	}

	public ApplicationContext Holder.getApplicationContext() {
		return this.ctx;
	}

	private ServletContext Holder.servletContext;

	public void Holder.setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public ServletContext Holder.getServletContext() {
		return this.servletContext;
	}

	/**
	 * Dwr Service Pointcut
	 */
	pointcut openServicePointcut() : 
			execution(public * name.orionis.cms.remote..*.*(..));

	pointcut dwrService() : @within(RemoteProxy) 
					&& @annotation(RemoteMethod);

	/**
	 * Before Check
	 * 
	 * @param holder
	 */
	before(Holder holder) : openServicePointcut() && dwrService() && this(holder) {
		WebContext webContext = WebContextFactory.get();
		UserInfo user = (UserInfo) webContext.getSession().getAttribute(
				AccountController.ACCOUNT_INFO);
		System.out.println(user.getToken());
	}
}
