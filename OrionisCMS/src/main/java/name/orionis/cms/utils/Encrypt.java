package name.orionis.cms.utils;

import org.apache.commons.codec.digest.DigestUtils;
/**
 * Encrypt Tools
 * @author orionis
 * @2013-5-17
 * Site : http://blog.orionis.name
 *
 */
public class Encrypt {
	/**
	 * Encrypt User Password With Salt
	 * Sha256
	 * Format: password{salt}
	 * @param password
	 * @param salt
	 * @return
	 */
	public static String encryptPassword(String password, String salt){
		return DigestUtils.sha256Hex(password + "{" + salt + "}");
	}
	
	public static void main(String [] args){
//		System.out.println(Encrypt.encryptPassword("testtest","testtest"));
		System.out.println(Encrypt.encryptPassword("root","root"));
		System.out.println(DigestUtils.sha256Hex("root"));
	}
}
