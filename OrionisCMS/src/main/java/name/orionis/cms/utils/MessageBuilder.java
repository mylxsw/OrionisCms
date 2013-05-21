package name.orionis.cms.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.FieldError;

/**
 * Message Builder
 * @author orionis
 * @2013-5-17
 * Site : http://blog.orionis.name
 *
 */
public class MessageBuilder {
	
	private List<String> messages = new ArrayList<String>();
	private MessageBuilder(){}
	public static MessageBuilder init(){
		return new MessageBuilder();
	}
	public MessageBuilder addMessage(String message){
		messages.add(message);
		return this;
	}
	public MessageBuilder addMessage(List<FieldError> errors){
		for(FieldError f: errors){
			messages.add(f.getDefaultMessage());
		}
		return this;
	}
	
	public Map<String, String> buildMap(String key){
		Map<String, String> map = new HashMap<String, String>();
		map.put(key, build());
		return map;
	}
	public String build(){
		StringBuilder message = new StringBuilder();
		for(String m: messages){
			if(StringUtils.isBlank(m)){
				continue;
			}
			m = m.replaceAll("\"", "'");
			if(m.length() > 15){
				message.append("<p>").append("Some exception occur!" ).append("</p>");
				continue;
			}
			message.append("<p>").append(m ).append("</p>");
		}
		return message.toString();
	}
	
}

