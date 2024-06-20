<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Register State</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
<%
	String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String error = request.getAttribute("error") + "";
	
	if (error.equals("null")) {
		error = "";
	}
%>
</head>
<body>
	<div class="container text-center">
		<% if (error.isEmpty()) {%>
			<h1 class="text-body-secondary">Welcome to our page!</h1>
			<h2 class="text-success">You're signed up.</h2>
		<%} else {%>
			<h1 class="text-body-secondary">Sign up failed!</h1>
			<p style="color: red;"> <%=error %></p>
			<hr>
			<p class="text-body-secondary">New verify mail was sent.</p>
		<%} %>
		<a href="<%=url %>/index.jsp"><button class="btn-secondary" >Go to home</button></a>
	</div>
</body>
</html>