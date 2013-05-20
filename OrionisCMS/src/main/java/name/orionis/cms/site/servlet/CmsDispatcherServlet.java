package name.orionis.cms.site.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
/**
 * The Core DispatcherServlet For CMS Site
 * @author orionis
 *
 */
public class CmsDispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String suffix;
	private WebApplicationContext ctx;
	
	@Override
	public void init() throws ServletException {
		// Get the url`s suffix . such as ".html"
		suffix = getServletConfig().getInitParameter("suffix");
		// Get Spring Context
		ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Deal with URL, Generate JSP file address
		String servletPath = request.getServletPath();
		String jspFile = "/WEB-INF/templates/" + servletPath.substring(1, servletPath.length() - suffix.length()) + ".jsp";
		
		// Inject Spring Context
		request.setAttribute("ctx", ctx);
		
		// Directly Forward Request to Jsp file
		getServletContext().getRequestDispatcher(jspFile).forward(request, response);
	}
}
