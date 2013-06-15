package name.orionis.cms.service.uploads;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ServletContextAware;

import name.orionis.cms.core.base.BaseController;
import name.orionis.helper.reflection.annotation.Remark;
import name.orionis.helper.ueditor.Uploader;
/**
 * UEDITOR Upload Controller
 * @author orionis
 * @2013-6-2
 * Site : http://blog.orionis.name
 *
 */
@Controller
@Remark(value="Ueditor文件上传控制", group="upload")
@RequestMapping("ueditor")
public class UeditorUploadController extends BaseController implements ServletContextAware {
	final private String uploadPath = "uploads/ueditor";// Save path
	final private String [] uploadTypes = {".rar" , ".doc" , ".docx" , 
			".zip" , ".pdf" , ".txt" , ".swf", ".wmv",
			".jpg", ".bmp", ".jpeg", ".png", ".gif", ".psd"};
	final private int maxUploadSize = 10000;// KB
	private ServletContext servletContext;
	/**
	 * File upload
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException 
	 */
	@Remark("File upload")
	@RequestMapping("fileupload")
	public String uploadFile(HttpServletRequest req, HttpServletResponse resp) throws IOException{
	    Uploader up = new Uploader(req);
	    up.setSavePath(servletContext.getRealPath("/")+ uploadPath); 
	    up.setAllowFiles(uploadTypes);
	    up.setMaxSize(maxUploadSize);        
	    try {
			up.upload();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    Map<String, String> map = new HashMap<String, String>();
	    map.put("url", up.getUrl());
	    map.put("fileType", up.getTitle());
	    map.put("state", up.getState());
	    map.put("original", up.getOriginalName());
	    return ajax(map, resp);
	}
	
	@Remark("Get Remote Image")
	@RequestMapping("getRemoteImage")
	public String getRemoteImage(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		String url = req.getParameter("upfile");
    	String state = "Image Get Successfully";
    	
    	String filePath = "upload";
    	String[] arr = url.split("ue_separate_ue");
    	String[] outSrc = new String[arr.length];
    	for(int i=0;i<arr.length;i++){
    		// Save upload path
			File f = new File(servletContext.getRealPath("/")+ uploadPath);
			String savePath = f.getParent() + "/"+filePath;
    		// format validation
    		String type = getFileType(arr[i]);
			if(type.equals("")){
				state = "Image type not allowed!";
				continue;
			}
    		String saveName = Long.toString(new Date().getTime())+type;
    		HttpURLConnection.setFollowRedirects(false); 
		    HttpURLConnection   conn   = (HttpURLConnection) new URL(arr[i]).openConnection(); 
		    if(conn.getContentType().indexOf("image")==-1){
		    	state = "Request header error!";
		    	continue;
		    }
		    if(conn.getResponseCode() != 200){
		    	state = "Request path not allowed";
		    	continue;
		    }
            File dir = new File(savePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
    		File savetoFile = new File(savePath +"/"+ saveName);
    		outSrc[i] = filePath +"/"+ saveName;
    		try {
    			InputStream is = conn.getInputStream();
    			OutputStream os = new FileOutputStream(savetoFile);
    			int b;
    			while ((b = is.read()) != -1) {
    				os.write(b);
    			}
    			os.close();
    			is.close();
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
	   	String outstr = "";
	   	for(int i=0;i<outSrc.length;i++){
	   		outstr+=outSrc[i]+"ue_separate_ue";
	   	}
	    outstr = outstr.substring(0,outstr.lastIndexOf("ue_separate_ue"));
	    
	    Map<String, String> map = new HashMap<String, String>();
	    map.put("url", outstr);
	    map.put("tip", state);
	    map.put("srcUrl", url);
	    return ajax(map, resp);
	}
	@Remark("Scrawl")
	@RequestMapping("scrawlUp")
	public String scrawlUp(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		String param = req.getParameter("action");
	    Uploader up = new Uploader(req);
	    up.setSavePath(servletContext.getRealPath("/")+ uploadPath);
	    String[] fileType = {".gif" , ".png" , ".jpg" , ".jpeg" , ".bmp"};
	    up.setAllowFiles(fileType);
	    up.setMaxSize(maxUploadSize); //KB
	    
	    if(param!=null && param.equals("tmpImg")){
	    	up.upload();
	    	resp.getOutputStream().print("<script>parent.ue_callback('" + up.getUrl() + "','" + up.getState() + "')</script>");
	    }else{
	    	up.uploadBase64("content");
	    	resp.getWriter().print("{'url':'" + up.getUrl()+"',state:'"+up.getState()+"'}");
	    }
	    return null;
	}
	
	@Override
	protected String _viewBase() {
		return null;
	}
	private String getFileType(String fileName){
    	String[] fileType = {".gif" , ".png" , ".jpg" , ".jpeg" , ".bmp"};
    	Iterator<String> type = Arrays.asList(fileType).iterator();
    	while(type.hasNext()){
    		String t = type.next();
    		if(fileName.endsWith(t)){
    			return t;
    		}
    	}
    	return "";
    }

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}
