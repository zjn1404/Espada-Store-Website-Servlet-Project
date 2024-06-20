package util;

import java.time.LocalDateTime;

import database.VerifyCodeDAO;
import model.VerifyCode;

public class VerifyCodeUtil {
	private static final int VERIFYCODELEN = 6;

	public static VerifyCode createVerifyCode(String customerId) {
		String verifyCode = NumberGenerator.generate(VERIFYCODELEN);
		LocalDateTime time = LocalDateTime.now();
		time = time.plusMinutes(5);
		return new VerifyCode(customerId, verifyCode, time, false);
	}
	
	public static void updateVerifyCode(VerifyCode vc) {
		vc.setVerifyCode(NumberGenerator.generate(VERIFYCODELEN));
		vc.setExpiryDate(LocalDateTime.now().plusMinutes(5));
		VerifyCodeDAO.getInstance().update(vc);
	}
	
}
