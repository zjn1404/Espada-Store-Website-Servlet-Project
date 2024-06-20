<%@page import="model.Customer"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Change password</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>

<style>
	.require-area {
		color: red;
	}
</style>
<% 	
	String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String error = request.getAttribute("error") + "";
	
	if (error.equals("null")) {
		error = "";
	}
	
	String success = request.getAttribute("success") + "";
	
	if (success.equals("null")) {
		success = "";
	}
	
%>
<link href="<%=url%>/css/sign-in.css" rel="stylesheet">
</head>
<body>

<%
	Object obj = session.getAttribute("customer");
	Customer customer = null;
	if (obj != null) {
		customer = (Customer)obj;
	}
%>

<%
	if (customer == null) {
%>
	<a href="sign-in.jsp"><img src="<%=url %>/img/error/e1.jpeg" alt="" width="100%"></a>
<%
	} else {
%>

<div class="text-center navbar-light">
	<a href="<%=url %>/index.jsp"><img class="mb-2 mt-3 text-center" src="<%=url %>/img/logo/espada.png" alt="" width="150"></a>
	<hr>
</div>

<!-- Login Form -->
	<main class="form-signin w-100 m-auto">
	  <form class="mt-5" action="<%=url %>/customer" method="post">
	  	<input type="hidden" name="action" value="change-password">
	    <h1 class="h3 mb-3 fw-normal text-body-secondary">Change Password</h1>
	
	    <div class="form-floating text-body-secondary">
	      <input type="password" class="form-control" id="current-password" placeholder="current-password" name="currentPassword" required>
	      <label for="current-password">Current Password</label>
	    </div>
	    <div class="form-floating text-body-secondary">
	      <input type="password" class="form-control" id="new-password" placeholder="new-password" name="newPassword" onkeyup="passwordError()" required>
	      <label for="new-password">New Password</label>
	      <p id="new-password-error" style="color: red"></p>
	    </div>
	    <div class="form-floating text-body-secondary">
	      <input type="password" class="form-control" id="confirm-password" placeholder="confirm-password" name="confirmPassword" onkeyup="rePasswordError()" required>
	      <label for="confirm-password">Confirm Password</label>
	      <span id="confirm-password-error" style="color: red"></span>
	    </div>
	
	    <% if (!error.isEmpty() && !error.equals("null")) { %>
		    <div class="alert alert-danger" role="alert">
	  			<%= error %>
			</div>
		<% } %>
		<% if (!success.equals("null") && !success.isEmpty()) { %>
			<div class="alert alert-success" role="alert">
  			<%= success %>
			</div>
		<% } %>
	    <button class="btn btn-primary w-100 py-2 btn-secondary mb-2" type="submit">Change Password</button>
	    <%if(!success.equals("null") && !success.isEmpty()) {%>
	    	<a class="btn btn-success w-100 py-2 btn-secondary mb-2" type="button" href="index.jsp">Go To Home</a>
	    <%} %>
	  </form>
	</main>
<!-- End Login Form -->	
	
<!-- Footer -->
	<jsp:include page="../footer.jsp"></jsp:include>
<!-- End Footer -->

<!-- Script -->
	<script type="text/javascript">
	
		function passwordError() {
			const lowerCaseLetters = /[a-z]/g;
			const upperCaseLetters = /[A-Z]/g;
			const numbers = /[0-9]/g;
			const password = document.getElementById("new-password").value;
			var error = "";
		
			var hasError = false;
			
			if (password.length < 8) {
				error = "Password should be minimum 8 characters!";
				hasError = true;
			}
		
			if (!password.match(lowerCaseLetters)) {
				if (hasError) {
					error += "<br>";
				}
				error += "Password should contain lowercase letter!";
				hasError = true;
			}
			
			if (!password.match(upperCaseLetters)) {
				if (hasError) {
					error += "<br>";
				}
				error += "Password should contain uppercase letter!";
				hasError = true;
			}
		
			if (!password.match(numbers)) {
				if (hasError) {
					error += "<br>";
				}
				error += "Password should contain number!";
				hasError = true;
			}
		
			if (hasError) {
				document.getElementById("new-password-error").innerHTML = error;
				return;
			}
		
			document.getElementById("new-password-error").innerHTML = "";
		}
		
		function rePasswordError() {
			const password = document.getElementById("new-password").value;
			const rePassword = document.getElementById("confirm-password").value;
		
			if (password != rePassword) {
				document.getElementById("confirm-password-error").innerHTML = "Passwords do not match!";
				return;
			}
		
			document.getElementById("confirm-password-error").innerHTML = "";
		}
	
	</script>
<!-- End Script -->	
<% } %>
</body>
</html>