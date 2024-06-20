package util;

import java.security.MessageDigest;

import org.apache.tomcat.util.codec.binary.Base64;

public class Encode {
	
	public static String toSHA1(String str) {
		final String SALT = "nqtptpnmklhpntnldkvhhnqtlmqntnnmtnklvtbtlnynhvlntvhqknnlpdt";
		String newStr = str + SALT;
		String encodedStr = "";
		
		try {
			
			byte[] dataBytes = newStr.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("sha-1");
			encodedStr = Base64.encodeBase64String(md.digest(dataBytes));
			
		} catch (Exception e) {
			
			e.printStackTrace();
		
		}
		
		return encodedStr;
	}
	
}
