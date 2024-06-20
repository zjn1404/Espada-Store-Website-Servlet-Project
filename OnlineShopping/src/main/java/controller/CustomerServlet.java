package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.CustomerDAO;
import database.VerifyCodeDAO;
import model.Customer;
import model.VerifyCode;
import util.Email;
import util.EmailContentCreator;
import util.Encode;
import util.VerifyCodeUtil;

/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String action = request.getParameter("action");
		
		if (action.equals("sign-up")) {
			signUp(request, response);
		} else if (action.equals("sign-in")) {
			signIn(request, response);
		} else if (action.equals("sign-out")) {
			signOut(request, response);
		} else if (action.equals("change-password")) {
			changePassword(request, response);
		} else if (action.equals("update-information")) {
			updateInformation(request, response);
		} else if (action.equals("verify")) {
			verify(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void signUp(HttpServletRequest request, HttpServletResponse response) {
		final String LINK = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		final String SUBJECT = "Verify email address";
		
		try {
			
			String url = "/customer-page/sign-up.jsp";
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String rePassword = request.getParameter("rePassword");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			String dob = request.getParameter("dob");
			String customerAddress = request.getParameter("customerAddress");
			String orderAddress = request.getParameter("orderAddress");
			String deliveryAddress = request.getParameter("deliveryAddress");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String getMail = request.getParameter("getMail");
			
			request.setAttribute("username", username);
			request.setAttribute("name", name);
			request.setAttribute("gender", gender);
			request.setAttribute("dob", dob);
			request.setAttribute("customerAddress", customerAddress);
			request.setAttribute("orderAddress", orderAddress);
			request.setAttribute("deliveryAddress", deliveryAddress);
			request.setAttribute("phone", phone);
			request.setAttribute("email", email);
			request.setAttribute("getMail", getMail);
			
			Customer customer = CustomerDAO.getInstance().selectByUsername(username);
			StringBuilder error = new StringBuilder();
			
			if (!rePassword.equals(password)) {
				error.append("Passwords do not match!");
			} else {
				password = Encode.toSHA1(password);
			}
			
			if (customer != null) {
				if (!error.isEmpty()) {
					error.append("<br>");
				}
				error.append("Username has been registered!");
			}else {
				
				if (!phone.isBlank() && phone.length() != 10) {
					if (!error.isEmpty()) {
						error.append("<br>");
					}
					error.append("Phone number should contain 10 digits!");
				} else if (email == null || email.isBlank()) {
					error.append("Email shouldn't be empty!");
				} else {
				
					LocalDate dobDate = null;
					if (!dob.isBlank() && dob != null && !dob.equals("null")) {
						dobDate = LocalDate.parse(dob);
					}
					Customer registeredCustomer = new Customer(UUID.randomUUID().toString(), name , gender, customerAddress, deliveryAddress, dobDate, phone, email, username, password, getMail != null);
					request.setAttribute("customerId", registeredCustomer.getCustomerId());
					if (CustomerDAO.getInstance().insert(registeredCustomer)) {
						VerifyCode vc = VerifyCodeUtil.createVerifyCode(registeredCustomer.getCustomerId());
						VerifyCodeDAO.getInstance().insert(vc);
						String verifyMailContent = EmailContentCreator.createVerifyMailContent(registeredCustomer.getCustomerId(), registeredCustomer.getName(), vc.getVerifyCode(), LINK);
						Email.getInstance().sendMail(email, SUBJECT, verifyMailContent);
						url = "/customer-page/verify-mail.jsp";
					}
				}
			}
			
			request.setAttribute("error", error.toString());
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			
			rd.forward(request, response);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void signIn(HttpServletRequest request, HttpServletResponse response) {
		final String LINK = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		final String SUBJECT = "Verify email address";
		
		try {
			String userName = request.getParameter("username");
			String password = request.getParameter("password");
			String url = "/index.jsp";
			String error = "";
			
			password = Encode.toSHA1(password);
			
			Customer customer = CustomerDAO.getInstance().selectByUsername(userName);
			
			if (customer == null || !customer.getPassword().equals(password)) {
				url = "/customer-page/sign-in.jsp";
				error = "Your password is incorrect or this account doesn't exist. Please try again!";
			} else {
				
				VerifyCode codeTmp = new VerifyCode();
				codeTmp.setCustomerId(customer.getCustomerId());
				VerifyCode verifyCode = VerifyCodeDAO.getInstance().selectById(codeTmp);
				
				if (verifyCode == null | !verifyCode.isVerified()) {
					VerifyCodeUtil.updateVerifyCode(verifyCode);
					String verifyMailContent = EmailContentCreator.createVerifyMailContent(verifyCode.getCustomerId(), customer.getName(), verifyCode.getVerifyCode(), LINK);
					Email.getInstance().sendMail(customer.getEmail(), SUBJECT, verifyMailContent);
					url = "/customer-page/sign-in.jsp";
					error = "Please verify account!";
				} else {
					HttpSession session = request.getSession();
					session.setAttribute("customer", customer);
				}
			}
			
			request.setAttribute("error", error);
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void signOut(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			HttpSession session = request.getSession();
			String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
			
			session.invalidate();
			
			response.sendRedirect(url + "/index.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void changePassword(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			final String CHANGESUCCESSMSG = "Password changed";
			
			String currentPassword = request.getParameter("currentPassword");
			String newPassword = request.getParameter("newPassword");
			String confirmPassword = request.getParameter("confirmPassword");
			
			HttpSession session = request.getSession();
			Object obj = session.getAttribute("customer");
			Customer customer = null;
			StringBuilder error = new StringBuilder();
			String url = "/customer-page/change-password.jsp";
			
			if (obj != null) {
				customer = (Customer)obj;
			}
			
			if (customer == null) {
				
				error.append("You are not signed in!");
			
			} else {
				
				currentPassword = Encode.toSHA1(currentPassword);
				
				if (!currentPassword.equals(customer.getPassword())) {
					
					error.append("Password is incorrect!");
				
				} else {
					
					newPassword = Encode.toSHA1(newPassword);
					
					if (newPassword.equals(currentPassword)) {
						
						error.append("New password is the same as current password!");
						
					} else {
						
						confirmPassword = Encode.toSHA1(confirmPassword);
						
						if (!newPassword.equals(confirmPassword)) {
							
							error.append("Passwords do not match!");
							
						} else {
							
							customer.setPassword(newPassword);
							
							if (!CustomerDAO.getInstance().changePassword(customer.getCustomerId(), newPassword)) {
								error.append("Can't change password!");
							} else {
								request.setAttribute("success", CHANGESUCCESSMSG);
							}
							
						}
						
					}
					
				}
			}
			
			request.setAttribute("error", error.toString());
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void updateInformation(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			String url = "/customer-page/update-info.jsp";
			StringBuilder error = new StringBuilder();
			String success = "Information is updated!";
			HttpSession session = request.getSession();
			Object obj = session.getAttribute("customer");
			Customer customer = null;
			
			if (obj != null) {
				customer = (Customer)obj;	
			}
			
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			String dob = request.getParameter("dob");
			String customerAddress = request.getParameter("customerAddress");
			String deliveryAddress = request.getParameter("deliveryAddress");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String getMail = request.getParameter("getMail");
			
			if (name != null && !name.isBlank()) {
				customer.setName(name);
			}
			
			if (gender != null && !gender.isBlank()) {
				customer.setGender(gender);
			}
			
			if (dob != null && !dob.isBlank()) {
				customer.setDob(LocalDate.parse(dob));
			}
			
			if (customerAddress != null && !customerAddress.isBlank()) {
				customer.setAddress(customerAddress);
			}
			
			if (deliveryAddress != null && !deliveryAddress.isBlank()) {
				customer.setDeliveryAddress(deliveryAddress);
			}
			
			if (phone != null && !phone.isBlank()) {
				if (phone.length() != 10) {
					error.append("Phone number should contain 10 digits!");
					success = "";
				} else {
					customer.setPhoneNumber(phone);
				}
			}
			
			if (email != null && !email.isBlank()) {
				customer.setEmail(email);
			}
			
			customer.setRegisterToGetMail(getMail != null);
			
			if (!CustomerDAO.getInstance().update(customer)) {
				if (!error.isEmpty()) {
					error.append("<br>");
				}
				error.append("Can't update information!");
				success = "";
			}
			
			session.setAttribute("customer", customer);
			request.setAttribute("error", error.toString());
			request.setAttribute("success", success);
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void verify(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			final String LINK = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
			final String SUBJECT = "Verify email address";
			
			String customerId = request.getParameter("customerId");
			String verifyCode = request.getParameter("verifyCode");
			String url = "/customer-page/sign-up-state.jsp";
			String error = "";
			VerifyCode vc = new VerifyCode(customerId, verifyCode, null, true);
			Customer customer = CustomerDAO.getInstance().selectById(customerId);
			
			vc = VerifyCodeDAO.getInstance().selectById(vc);
			
			if (vc == null || LocalDateTime.now().isAfter(vc.getExpiryDate())) {
				VerifyCodeUtil.updateVerifyCode(vc);
				String verifyMailContent = EmailContentCreator.createVerifyMailContent(vc.getCustomerId(), customer.getName(), vc.getVerifyCode(), LINK);
				Email.getInstance().sendMail(customer.getEmail(), SUBJECT, verifyMailContent);
				error = "Verify code is invalid!";
			} else {
				vc.setVerified(true);
				VerifyCodeDAO.getInstance().update(vc);
			}
			
			request.setAttribute("error", error);
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}