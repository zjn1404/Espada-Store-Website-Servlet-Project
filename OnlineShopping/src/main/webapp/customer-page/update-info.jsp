<%@page import="java.time.LocalDate"%>
<%@page import="model.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Update information</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>

<%
	String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
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

	String name = customer.getName();
	String gender = customer.getGender();
	LocalDate dobDate = customer.getDob();
	String dob = dobDate == null ? "" : dobDate.toString();
	String customerAddress = customer.getAddress();
	String orderAddress = customer.getAddress();
	String deliveryAddress = customer.getDeliveryAddress();
	String phone = customer.getPhoneNumber();
	String email = customer.getEmail();
	boolean getMail = customer.isRegisterToGetMail();
	String error = request.getAttribute("error") + "";
	String success = request.getAttribute("success") + "";
	
	error = error.equals("null") ? "" : error;
	success = success.equals("null") ? "" : success;
%>

</head>
<body>
	<!-- Header -->
	<jsp:include page="../header.jsp" />
	<!-- End Header -->
	<div class="container text-body-secondary">
		<h1 class="mt-3 mb-3 text-center fw-bold text-body-secondary">UPDATE INFORMATION</h1>
		<form action="<%=url %>/customer" method="post">
			<input type="hidden" name="action" value = "update-information">
			<div class="row">
				<div class="col-sm-6">
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
					<div class="mb-3">
					    <label for="phone" class="form-label">Phone</label>
					    <input type="text" class="form-control text-body-secondary" id="phone" name="phone" value="<%=phone%>">
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
					    <label for="email" class="form-label">Email</label>
					    <input type="email" class="form-control text-body-secondary" id="email" name="email" value="<%=email %>">
					</div>
					<div class="mb-3 form-check">
						<input type="checkbox" class="form-check-input" id="get-mail-checkbox" name="getMail" <% if(getMail) {%> checked = "checked"<%} %>>
						<label class="form-check-label" for="get-mail-checkbox">I agree to receive general emails and product offers.</label>
					</div>
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
				<input id="update-btn" type="submit" class="btn btn-secondary form-control mb-2" value="Update" name="update">
				<%if(!success.equals("null") && !success.isEmpty()) {%>
			    	<a class="btn btn-success w-100 py-2 btn-secondary mb-2" type="button" href="index.jsp">Go To Home</a>
			    <%} %>
			</div>
		</form>
	</div>
<!-- Footer -->
	<jsp:include page="../footer.jsp" />
<script>

</script>

<% } %>

</body>
</html>