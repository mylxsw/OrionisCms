package name.orionis.cms.core.exception;
/**
 * Service Layer Action Failed Exception
 * @author orionis
 * @2013-5-17
 * Site : http://blog.orionis.name
 *
 */
public class ActionFailedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message;
	private Exception e;
	
	public ActionFailedException(String message , Exception e){
		this.message = message;
		this.e = e;
	}
	
	public ActionFailedException(String message){
		this.message = message;
	}
	
	public ActionFailedException(Exception e){
		this.e = e;
	}
	public ActionFailedException(){
	}

	
	@Override
	public String getMessage() {
		return message;
	}

	public Exception getE() {
		return e;
	}

	public void setE(Exception e) {
		this.e = e;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
