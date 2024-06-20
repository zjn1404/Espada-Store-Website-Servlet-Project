package util;

public class EmailContentCreator {
	
	public static String createVerifyMailContent(String customerId, String customerName, String verifyCode, String baseLink) { 
		String url = String.format("%s/customer?action=verify&customerId=%s&verifyCode=%s", baseLink,customerId, verifyCode);
		return String.format("<h2>Hello %s</h2>\n"
		+ "<p>This is your verify code <strong>%s</strong>, or you can click this link below to verify email address:</p>\n"
		+ "<p><a href=\"%s\">Verify Email Address</a></p>\n"
		+ "<p>This email is valid for 5 minutes.</p>\n"
		+ "<p>This is auto generated email.</p>\n"
		+ "<p>Please don&apos;t reply to this email.</p>\n"
		+ "<p><strong>Best regards,</strong></p>\n"
		+ "<p><strong>Espada Store</strong></p>", customerName, verifyCode, url);
	}
	
}
