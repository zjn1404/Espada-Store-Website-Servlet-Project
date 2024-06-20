<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Verify mail</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
<%
String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
String customerId = request.getAttribute("customerId") + "";
%>
</head>
<body>
	<div class="container text-center">
		<h2 class="text-body-secondary">Verify code was sent to your email.</h2>
		<p class="text-body-secondary">Please fill in the code</p>
		<form action="<%= url%>/customer">
			<input type="hidden" name="action" value = "verify">
			<input type="hidden" name="customerId" value = "<%=customerId %>">
			<div class="row">
				<div class="mb-3">
					<label for="verify-code" class="form-label">Verify Code</label>
					<input type="text" class="form-control text-body-secondary" id="verify-code" name="verifyCode" required>
				</div>
				<input id="verify-btn" type="submit" class="btn btn-secondary form-control" value="Verify" name="verify">
			</div>
		</form>
		<a href="<%=url %>/index.jsp"><button class="btn-secondary" >Go to home</button></a>
	</div>
	
</body>
</html>