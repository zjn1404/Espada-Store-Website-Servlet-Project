package util;

import java.util.Random;

public class NumberGenerator {

	public static String generate(int len) {
		
		Random ran = new Random();
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < len; i++) {
			int num = ran.nextInt(10);
			builder.append(num);
		}
		
		return builder.toString();
	}
	
}
