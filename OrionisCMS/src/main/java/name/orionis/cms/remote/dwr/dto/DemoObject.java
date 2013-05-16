package name.orionis.cms.remote.dwr.dto;

import org.directwebremoting.annotations.DataTransferObject;
import org.directwebremoting.annotations.RemoteProperty;

/**
 * Data Transfer Object Demo
 * @author code.404
 * @2013-5-14
 * Site : http://blog.orionis.name
 *
 */
@DataTransferObject
public class DemoObject {
	@RemoteProperty
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
