package name.orionis.cms.utils;

import java.io.IOException;
import java.util.Properties;

import org.springframework.stereotype.Component;

/**
 * Some System Configuration Information
 * @author orionis
 * @2013-5-17
 * Site : http://blog.orionis.name
 *
 */
@Component
public class Configuration extends Properties {
	private static final long serialVersionUID = 1L;
	private String PATH = "/META-INF/spring/configuration.properties";
	
	public Configuration() throws IOException{
		this.load(this.getClass().getResourceAsStream(PATH));
	}
	
	public int getInt(String key){
		return Integer.parseInt(getProperty(key));
	}
	public long getLong(String key){
		return Long.parseLong(getProperty(key));
	}
	public short getShort(String key){
		return Short.parseShort(getProperty(key));
	}
	public boolean getBool(String key){
		return getProperty(key).equalsIgnoreCase("true");
	}
	public String getString(String key){
		return getProperty(key);
	}
}
