<%@page import="java.nio.file.Paths"%>
<%@page import="java.nio.file.Path"%>
<%@page import="model.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	Object obj = session.getAttribute("customer");
	Customer customer = null;
	if (obj != null) {
		customer = (Customer)obj;
	}
	
	String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>

<!-- Navbar -->
  <nav class="navbar navbar-expand-lg navbar-light" style="background-color: white">
    <div class="container text-center" style="background-color: white">
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0 p-2 flex-fill">
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              Tops
            </a>
            <ul class="dropdown-menu">
              <li><a class="dropdown-item" href="#">T-shirts</a></li>
              <li><a class="dropdown-item" href="#">Shirts</a></li>
              <li><a class="dropdown-item" href="#">Jackets</a></li>
              <li><a class="dropdown-item" href="#">Hoodies</a></li>
            </ul>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              Bottoms
            </a>
            <ul class="dropdown-menu">
              <li><a class="dropdown-item" href="#">Pants</a></li>
              <li><a class="dropdown-item" href="#">Shorts</a></li>
              <li><a class="dropdown-item" href="#">Skirts</a></li>
            </ul>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              Accessories
            </a>
            <ul class="dropdown-menu">
              <li><a class="dropdown-item" href="#">Bags</a></li>
              <li><a class="dropdown-item" href="#">Headwears</a></li>
            </ul>
          </li>
          <% if (customer == null) { %>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              Join us
            </a>
            <ul class="dropdown-menu">
              <li><a class="dropdown-item text-body-secondary" href="<%=url %>/customer-page/sign-in.jsp">Sign in</a></li>
              <li><a class="dropdown-item text-body-secondary" href="<%=url %>/customer-page/sign-up.jsp">Sign up</a></li>
            </ul>
          </li>
          <% } %>
        </ul>
        <a class="navbar-brand p-2 flex-fill" href="<%=url %>/index.jsp">
          <img src="<%=url %>/img/logo/espada.png" alt="Espada-logo" height="50">
        </a>
        <form class="d-flex p-2 flex-fill" role="search">
          <input class="form-control me-2 p-2" type="search" placeholder="Search" aria-label="Search">
          <button class="btn btn-outline-secondary" type="submit">Search</button>
        </form>
        <% if (customer != null) { %>
        <div class="nav-item dropdown ms-3 dropstart">
            <a class="nav-link dropdown-toggle text-body-secondary" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              <img src="<%=url %>/img/logo/user-logo.jpeg" style="border-radius: 50%;" alt="user-logo" height="50">
            </a>
            <ul class="dropdown-menu">
              <li><a class="dropdown-item text-body-secondary" href="#">Cart</a></li>
              <li><a class="dropdown-item text-body-secondary" href="<%=url %>/customer-page/change-password.jsp">Change password</a></li>
              <li><a class="dropdown-item text-body-secondary" href="<%=url %>/customer-page/update-info.jsp">Update information</a></li>
              <li><a class="dropdown-item text-body-secondary" href="<%=url %>/customer?action=sign-out">Sign out</a></li>
        	</ul>
        </div>	
          <% } %>
      </div>
    </div>
  </nav>
<!-- End Navbar -->