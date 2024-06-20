<%@page import="model.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<title>Espada</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
</head>
<body>
<!-- Header -->
<jsp:include page="header.jsp" />
<!-- End Header -->

<!-- Slider -->
<div class = "row">
  <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-indicators">
      <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
      <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
      <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
    </div>
    <div class="carousel-inner">
      <div class="carousel-item active" data-bs-interval="10000">
        <img src="img/slider/s1.jpeg" class="d-block w-100" alt="...">
      </div>
      <div class="carousel-item" data-bs-interval="10000">
        <img src="img/slider/s2.jpeg" class="d-block w-100" alt="...">
      </div>
      <div class="carousel-item" data-bs-interval="10000">
        <img src="img/slider/s3.jpeg" class="d-block w-100" alt="...">
      </div>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Next</span>
    </button>
  </div>
</div>
</div>
<!-- End Slider -->

<!-- Page Container -->
  <div class="container mt-5">
    <!-- Product -->
    <div class="row">
      <div class="card p-2 flex-fill" style="width: 18rem;">
        <a href="#"><img src="img/product/p1.jpeg" class="card-img-top" alt="p1"></a>
        <div class="card-body">
          <a href="#" style="color: black; text-decoration: none;"><h5 class="card-title text-body-secondary">1729 Tee</h5></a>
          <p class="card-text text-body-secondary" style="font-size: 20px;">20$</p>
        </div>
      </div>
      <div class="card p-2 flex-fill" style="width: 18rem;">
        <a href="#"><img src="img/product/p2.jpeg" class="card-img-top" alt="p2"></a>
        <div class="card-body">
          <a href="#" style="color: black; text-decoration: none;"><h5 class="card-title text-body-secondary">Cargo Pants</h5></a>
          <p class="card-text text-body-secondary" style="font-size: 20px;">60$</p>
        </div>
      </div>
      <div class="card p-2 flex-fill" style="width: 18rem;">
        <a href="#"><img src="img/product/p3.jpeg" class="card-img-top" alt="p3" height="390px"></a>
        <div class="card-body">
          <a href="#" style="text-decoration: none;"><h5 class="card-title text-body-secondary">MadeByIlsn Tee</h5></a>
          <p class="card-text text-body-secondary" style="font-size: 20px;">30$</p>
        </div>
      </div>
      <div class="card p-2 flex-fill" style="width: 18rem;">
        <a href="#"><img src="img/product/p4.jpeg" class="card-img-top" alt="p4" height="390px"></a>
        <div class="card-body">
          <a href="#" style="color: black; text-decoration: none;"><h5 class="card-title text-body-secondary">Cargo Shorts</h5></a>
          <p class="card-text text-body-secondary" style="font-size: 20px;">40$</p>
        </div>
      </div>
    </div>
    <!-- End Product -->
  </div>
<!-- End Page Container -->
<!-- Footer -->
<jsp:include page="footer.jsp"></jsp:include>
<!-- End Footer -->
</body>
</html>