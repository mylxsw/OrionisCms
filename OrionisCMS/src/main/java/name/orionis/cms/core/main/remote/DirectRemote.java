package name.orionis.cms.core.main.remote;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Component;

/**
 * Dwr Access Service
 * @author code.404
 * @2013-5-14
 * Site : http://blog.orionis.name
 *
 */
@Component
@RemoteProxy
public class DirectRemote {

	@RemoteMethod
	public String demo(String id){
		return "hello, " + id;
	}
}
