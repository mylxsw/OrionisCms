package name.orionis.cms.service.uploads;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import com.google.code.kaptcha.util.Config;

import name.orionis.cms.core.base.BaseController;
import name.orionis.cms.core.rbac.authentication.UserInfo;
import name.orionis.cms.core.rbac.web.AccountController;
import name.orionis.cms.utils.Configuration;
import name.orionis.cms.utils.Constant;
import name.orionis.helper.reflection.annotation.Remark;

/**
 * File Upload Controller
 * @author code.404
 * @2013-5-20
 * Site : http://blog.orionis.name
 *
 */
@Controller
@Remark(value="文件上传控制器", group="upload")
@RequestMapping("file")
public class FileUploadController extends BaseController implements ServletContextAware {

	public ServletContext servletContext;
	@Resource
	private Configuration config;
	final public static String [] allow_ext = {
				".jpg", ".bmp", ".jpeg", ".png", ".gif", ".psd",
				".doc", ".xls", ".ppt", ".pdf", ".docx", ".wps", ".xlsx",
				".swf", ".wmv", ".avi", ".mp3" };
	
	@RequestMapping(value="/upload")
	public String handleRequest(
			@RequestParam("upload_file") MultipartFile file,
			@RequestParam("PRIVATE_KEY") String private_key,
			@RequestParam("PRIVATE_ACCESS") Boolean private_access,
			HttpServletRequest req,
			HttpServletResponse resp,
			HttpSession session){
		
		
		// Check whether upload is allowed
		try{
			UserInfo user = (UserInfo) session.getAttribute(AccountController.ACCOUNT_INFO);
			// Check whether private key is correct
			// !!!!!Attention: there still have a bug need to fix, if user get two form ,one is 
			// private ,and another is public ,then user can use it to submit 
			if(!private_key.equalsIgnoreCase(
					DigestUtils.md5Hex(user.getToken() + "{" + 
							(private_access ? "true" : "false") + "}"))){
				throw new Exception();
			}
		} catch( Exception e){
			return ajax("上传验证失败!", STATUS_FAILED, resp);
		}
		
		// Get uploaded file information
		String origin_filename = file.getOriginalFilename();
		String ext = "";
		
		// check whether filename is legal
		boolean isLegal = false;
		for(int i=0; i < allow_ext.length ; i ++){
			if(origin_filename.endsWith(allow_ext[i])){
				isLegal = true;
				ext = allow_ext[i];
			}
		}
		if(!isLegal){
			return ajax("上传文件扩展名不允许", STATUS_FAILED, resp);
		}
		
		// Move file to appropriate position
		String new_filename = DigestUtils.md5Hex(origin_filename 
				+ System.currentTimeMillis()) + ext;
		try {
			_copyFile(file, new_filename, private_access);
		} catch (IOException e) {
			e.printStackTrace();
			return ajax("文件上传错误!", STATUS_FAILED, resp);
		}
		
		// Save file information
		// when the file has been saved, the returned filename is a hash value, 
		// so, when the form submitted, you need this hash value to 
		// exchange the real filename.
		String hashValue = DigestUtils.md5Hex(origin_filename + new_filename);
		session.setAttribute(hashValue, new_filename);
		
		return ajax(hashValue, STATUS_SUCCESS, resp);
	}

	
	private void _copyFile(MultipartFile file, String new_filename , Boolean privateAccess) throws IOException{
		InputStream inputStream = null;
		FileOutputStream fos = null;
		try {
			inputStream = file.getInputStream();
			String _path = "";
			if(privateAccess){
				_path = config.getString(Constant.CMS_FILE_UPLOAD_PATH_PRIVATE); 
			}else{
				String cfg_path = config.getString(Constant.CMS_FILE_UPLOAD_PATH_PUBLIC);
				if(cfg_path.equals("")){
					_path = servletContext.getRealPath("/") + "uploads/";
				}else{
					_path = cfg_path;
				}
			}
			fos = new FileOutputStream(new File( _path + new_filename));
			byte[] buffer = new byte[1024];
			int byte_read = 0;
			while((byte_read = inputStream.read(buffer)) != -1){
				fos.write(buffer, 0, byte_read);
			}
		} catch (IOException e) {
			throw new IOException(e);
		} finally{
			if(fos != null){
				fos.close();
			}
			if(inputStream != null){
				inputStream.close();
			}
		}
	}
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	@Override
	protected String _viewBase() {
		return null;
	}

}
