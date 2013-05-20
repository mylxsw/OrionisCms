package name.orionis.cms.core.rbac.authentication;
/**
 * Access Deny Exception
 * @author code.404
 *
 */
public class PermissionDenyException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message;
	private Exception e;
	private String redirect;
	
	public PermissionDenyException(){}
	
	public PermissionDenyException(String message, Exception e){
		this.message = message;
		this.e = e;
	}
	public PermissionDenyException(String message){
		this.message = message;
	}
	
	public PermissionDenyException(String message, String redirect){
		this.message = message;
		this.redirect = redirect;
	}
	
	public PermissionDenyException(String message, String redirect, Exception e){
		this.message = message;
		this.redirect = redirect;
		this.e = e;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Exception getE() {
		return e;
	}
	public void setE(Exception e) {
		this.e = e;
	}
	public String getRedirect() {
		return redirect;
	}
	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}
	
}
