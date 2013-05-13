package name.orionis.cms.utils;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Json conversion utils
 * @author code.404
 * @2013-5-13
 * Site : http://blog.orionis.name
 *
 */
public class JsonConverter {
	/**
	 * Convert an Map to json format string
	 * @param map
	 * @return
	 */
	public static String mapToJson(Map<String, String> map){
		String json = "{";

		Set<Entry<String, String>> entrySet = map.entrySet();
		Iterator<Entry<String, String>> iterator = entrySet.iterator();

		while(iterator.hasNext()){
			Entry<String, String> entry = iterator.next();
			json += "\"" + entry.getKey() + "\":\"" + entry.getValue() + "\",";
		}
		if(json.endsWith(",")){
			json = json.substring(0, json.length() - 1);
		}
		json += "}";

		return json;
	}
}