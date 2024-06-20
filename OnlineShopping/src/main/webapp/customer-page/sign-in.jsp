<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Sign in</title>
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
%>
<link href="<%=url%>/css/sign-in.css" rel="stylesheet">
</head>
<body>
<div class="text-center navbar-light">
	<a href="<%=url %>/index.jsp"><img class="mb-2 mt-3 text-center" src="<%=url %>/img/logo/espada.png" alt="" width="150"></a>
	<hr>
</div>

<!-- Login Form -->
	<main class="form-signin w-100 m-auto">
	  <form class="mt-5" action="<%=url %>/customer" method="post">
	  	<input type="hidden" name="action" value = "sign-in">
	    <h1 class="h3 mb-3 fw-normal text-body-secondary">Sign in</h1>
	
	    <div class="form-floating text-body-secondary">
	      <input type="text" class="form-control" id="username" placeholder="username" name="username">
	      <label for="username">Username</label>
	    </div>
	    <div class="form-floating text-body-secondary">
	      <input type="password" class="form-control" id="password" placeholder="password" name="password">
	      <label for="password">Password</label>
	    </div>
	
	    <div class="form-check text-start my-3">
	      <input class="form-check-input" type="checkbox" value="remember-me" id="flexCheckDefault">
	      <label class="form-check-label text-body-secondary" for="flexCheckDefault">
	        Remember me
	      </label>
	    </div>
	    <% if (!error.isEmpty() && !error.equals("null")) { %>
	    <div class="alert alert-danger" role="alert">
  			<%= error %>
		</div>
		<% } %>
	    <button class="btn btn-primary w-100 py-2 btn-secondary mb-2" type="submit">Sign in</button>
	    <div class="text-center mb-5"><a href="sign-up.jsp" class="text-body-secondary" style="text-decoration: none;">Sign up a new account</a></div>
	  </form>
	</main>
<!-- End Login Form -->	
	
<!-- Footer -->

	<jsp:include page="../footer.jsp"></jsp:include>

<!-- End Footer -->
</body>
</html>