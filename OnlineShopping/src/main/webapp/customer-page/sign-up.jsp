<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Sign up</title>
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

	String username = request.getAttribute("username") + "";
	String name = request.getAttribute("name") + "";
	String gender = request.getAttribute("gender") + "";
	String dob = request.getAttribute("dob") + "";
	String customerAddress = request.getAttribute("customerAddress") + "";
	String orderAddress = request.getAttribute("orderAddress") + "";
	String deliveryAddress = request.getAttribute("deliveryAddress") + "";
	String phone = request.getAttribute("phone") + "";
	String email = request.getAttribute("email") + "";
	String getMail = request.getAttribute("getMail") + "";
	String error = request.getAttribute("error") + "";
	
	username = username.equals("null") ? "" : username;
	name = name.equals("null") ? "" : name;
	gender = gender.equals("null") ? "" : gender;
	dob = dob.equals("null") ? "" : dob;
	customerAddress = customerAddress.equals("null") ? "" : customerAddress;
	orderAddress = orderAddress.equals("null") ? "" : orderAddress;
	deliveryAddress = deliveryAddress.equals("null") ? "" : deliveryAddress;
	phone = phone.equals("null") ? "" : phone;
	email = email.equals("null") ? "" : email;
	getMail = getMail.equals("null") ? "" : getMail;
	error = error.equals("null") ? "" : error;
%>

</head>
<body>
	<!-- Header -->
	<jsp:include page="../header.jsp" />
	<!-- End Header -->

	<div class="container text-body-secondary">
		<h1 class="mt-3 mb-3 text-center fw-bold text-body-secondary">SIGN UP</h1>
		<p style="color: red"> <%= error %> </p>
		<form action="<%=url %>/customer" method="post">
			<input type="hidden" name="action" value = "sign-up">
			<div class="row">
				<div class="col-sm-6">
					<h3>Account</h3>
					<div class="mb-3">
					    <label for="username" class="form-label">Username<span class="require-area">*</span></label>
					    <input type="text" class="form-control text-body-secondary" id="username" name="username" value="<%=username %>" required>
				  	</div>
					<div class="mb-3">
					    <label for="password" class="form-label">Password<span class="require-area">*</span></label>
					    <input type="password" class="form-control text-body-secondary" id="password" name="password" required onkeyup="passwordError()">
						<p id="password-error" style="color: red"></p>
					</div>
					<div class="mb-3">
					    <label for="re-password" class="form-label">Confirm password<span class="require-area">*</span><span id="re-password-error" style="color: red"></span></label>
					    <input type="password" class="form-control text-body-secondary" id="re-password" name="rePassword" required onkeyup="rePasswordError()">
					</div>
					<hr/>
					<h3>Information</h3>
					<div class="mb-3">
					    <label for="name" class="form-label">Name</label>
					    <input type="text" class="form-control text-body-secondary" id="name" name="name" value="<%=name %>">
					</div>
					<div class="mb-3">
					    <label for="gender" class="form-label">Gender</label>
					    <select class="form-control text-body-secondary" id="gender" name="gender">
					    	<option value="Male">Male</option>
						    <option value="Female">Female</option>
						    <option value="Other">Other</option>
					    </select>
					</div>
					<div class="mb-3">
					    <label for="dob" class="form-label">Date of birth</label>
					    <input type="date" class="form-control text-body-secondary" id="dob" name="dob" value="<%=dob %>">
					</div>
				</div>
				<div class="col-sm-6">
					<h3>Address</h3>
					<div class="mb-3">
					    <label for="customer-address" class="form-label">Customer address</label>
					    <input type="text" class="form-control text-body-secondary" id="customer-address" name="customerAddress" value="<%=customerAddress %>">
					</div>
					<div class="mb-3">
					    <label for="order-address" class="form-label">Order address</label>
					    <input type="text" class="form-control text-body-secondary" id="order-address" name="orderAddress" value="<%=orderAddress %>">
					</div>
					<div class="mb-3">
					    <label for="delivery-address" class="form-label">Delivery address</label>
					    <input type="text" class="form-control text-body-secondary" id="delivery-address" name="deliveryAddress" value="<%=deliveryAddress%>">
					</div>
					<div class="mb-3">
					    <label for="phone" class="form-label">Phone</label>
					    <input type="text" class="form-control text-body-secondary" id="phone" name="phone" value="<%=phone%>">
					</div>
					<div class="mb-3">
					    <label for="email" class="form-label">Email<span class="require-area">*</span></label>
					    <input type="email" class="form-control text-body-secondary" id="email" name="email" value="<%=email %>" required>
					</div>
					<hr/>
					<div class="mb-3 form-check">
						<input type="checkbox" class="form-check-input" id="term-checkbox" name="readTerm" onchange="checkAcceptTerms()">
						<label class="form-check-label" for="term-checkbox">I accept all terms & conditions.<span class="require-area">*</span></label>
					</div>
					<div class="mb-3 form-check">
						<input type="checkbox" class="form-check-input" id="get-mail-checkbox" name="getMail" <% if(!getMail.equals("null") && !getMail.isEmpty()) {%> checked = "checked"<%} %>>
						<label class="form-check-label" for="get-mail-checkbox">I agree to receive general emails and product offers.</label>
					</div>
					<input id="register-btn" type="submit" class="btn btn-secondary form-control" style="visibility: hidden" value="Register" name="register">
				</div>
			</div>
		</form>
	</div>
	
<!-- Footer -->
	<jsp:include page="../footer.jsp"></jsp:include>
<!-- End Footer -->

<script>

	function passwordError() {
		const lowerCaseLetters = /[a-z]/g;
		const upperCaseLetters = /[A-Z]/g;
		const numbers = /[0-9]/g;
		const password = document.getElementById("password").value;
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
			document.getElementById("password-error").innerHTML = error;
			return;
		}

		document.getElementById("password-error").innerHTML = "";
	}

	function rePasswordError() {
		const password = document.getElementById("password").value;
		const rePassword = document.getElementById("re-password").value;

		if (password != rePassword) {
			document.getElementById("re-password-error").innerHTML = "Passwords do not match!";
			return;
		}

		document.getElementById("re-password-error").innerHTML = "";
	}

	function checkAcceptTerms() {
		const termCheckBox = document.getElementById("term-checkbox");

		if (termCheckBox.checked) {
			document.getElementById("register-btn").style.visibility = "visible"; 
		} else {
			document.getElementById("register-btn").style.visibility = "hidden";
		}
	}

</script>

</body>
</html>