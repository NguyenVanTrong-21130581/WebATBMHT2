<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Shop Shoes</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Mukta:300,400,700">
<link rel="stylesheet" href="fonts/icomoon/style.css">

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/magnific-popup.css">
<link rel="stylesheet" href="css/jquery-ui.css">
<link rel="stylesheet" href="css/owl.carousel.min.css">
<link rel="stylesheet" href="css/owl.theme.default.min.css">


<link rel="stylesheet" href="css/aos.css">

<link rel="stylesheet" href="css/style.css">

</head>
<body>

	<div class="site-wrap">


		<jsp:include page="Menu.jsp"></jsp:include>

		<div class="bg-light py-3">
			<div class="container">
				<div class="row">
					<div class="col-md-12 mb-0">
						<a href="home">Home</a> <span class="mx-2 mb-0">/</span> <strong
							class="text-black">Shop</strong>
					</div>
				</div>
			</div>
		</div>

		<div class="site-section">
			<div class="container">

				<div class="row mb-5">
					<div class="col-md-9 order-2">

						<div class="row">
							<div class="col-md-12 mb-5">
								<div class="float-md-left mb-4">
									<h2 class="text-black h5">Shop All</h2>
								</div>
							<!-- 	<div class="d-flex">
									<div class="dropdown mr-1 ml-md-auto">
										<button type="button"
											class="btn btn-secondary btn-sm dropdown-toggle"
											id="dropdownMenuOffset" data-toggle="dropdown"
											aria-haspopup="true" aria-expanded="false">Latest</button>
										<div class="dropdown-menu"
											aria-labelledby="dropdownMenuOffset">
											<a class="dropdown-item" href="#">Mới nhất</a> <a
												class="dropdown-item" href="#">Cũ nhất</a>
										</div>
									</div>

								</div>  -->
							</div>
						</div>
						<div class="row mb-5">
							<c:forEach items="${listPro}" var="product">
								<div class="col-sm-6 col-lg-4 mb-4" data-aos="fade-up">
									<div class="block-4 text-center border">
										<figure class="block-4-image">
											<a href="shop-single?pid=${product.getId() }"><img
												src="${product.image }" alt="Image placeholder"
												class="img-fluid"></a>
										</figure>
										<div class="block-4-text p-4">
											<h3>
												<a href="detail?pid=${product.id }">${product.name}</a>
											</h3>
											<p class="mb-0">${product.title}</p>
											<p class="text-primary font-weight-bold">${product.price }$</p>
										</div>
									</div>
								</div>
							</c:forEach>






						</div>
						<div class="row" data-aos="fade-up">
							<div class="col-md-12 text-center">
								<div class="site-block-27">
									<ul>

										<c:if test="${tag != 1}">
											<li><a href="shop?index=${tag-1 }">&lt;</a></li>
										</c:if>
										<c:forEach begin="1" end="${endPage }" var="i">
											<li class="${tag==i?"active":""}"><a
												href="shop?index=${i }"><span>${i }</span></a></li>
										</c:forEach>
										<c:if test="${tag != endPage}">
											<li><a href="shop?index=${tag+1 }">&gt;</a></li>
										</c:if>

									</ul>
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-3 order-1 mb-5 mb-md-0">
						<div class="border p-4 rounded mb-4">
							<h3 class="mb-3 h6 text-uppercase text-black d-block">Categories</h3>
							<ul class="list-unstyled mb-0">
								<c:forEach items="${listCate }" var="category">
									<li class="mb-1 active"><a
										href="category?cate_id=${category.cate_id}" class="d-flex"><span>${category.cate_name}</span>
									</a></li>

								</c:forEach>
							</ul>
						</div>

					<%--	<div class="border p-4 rounded mb-4">
							<div class="mb-4">
								<h3 class="mb-3 h6 text-uppercase text-black d-block">Filter
									by Price</h3>

							</div>

						 	<div class="mb-4">
								<div class="form-check pl-0 mb-3">
									<input onchange="searchByPriceUnder100()" type="checkbox"
										 id="under100"
										name="under100"> <label
										for="under100">Under $100</label>
								</div>
								<div class="form-check pl-0 mb-3">
									<input onchange="searchByPrice100To200()" type="checkbox"
										 id="100200"
										name=""> <label
										for="100200">$100 to $200</label>
								</div>
								<div class="form-check pl-0 mb-3">
									<input onchange="searchByPriceAbove200()" type="checkbox"
										 id="200above"
										name=""> <label
										for="200above">$200 & Above</label>
								</div>
							</div>--%>

							

						</div>
					</div>
				</div>

				<div class="site-section site-blocks-2">
					<div class="container">
						<div class="row">
							<div class="col-md-12">
								<div class="site-section site-blocks-2">
									<div class="row justify-content-center text-center mb-5">
										<div class="col-md-7 site-section-heading pt-4">
											<h2>Categories</h2>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-6 col-md-6 col-lg-4 mb-4 mb-lg-0"
											data-aos="fade" data-aos-delay="">
											<a class="block-2-item" href="category?cate_id=1">
												<figure class="image">
													<img src="images/category1.jpg" alt="" class="img-fluid">
												</figure>
												<div class="text">
													<span class="text-uppercase">Collections</span>
													<h3>Adidas</h3>
												</div>
											</a>
										</div>
										<div class="col-sm-6 col-md-6 col-lg-4 mb-5 mb-lg-0"
											data-aos="fade" data-aos-delay="100">
											<a class="block-2-item" href="category?cate_id=2">
												<figure class="image">
													<img src="images/categoryImage1.jpeg" alt=""
														class="img-fluid">
												</figure>
												<div class="text">
													<span class="text-uppercase">Collections</span>
													<h3>Nike</h3>
												</div>
											</a>
										</div>

										<div class="col-sm-6 col-md-6 col-lg-4 mb-5 mb-lg-0"
											data-aos="fade" data-aos-delay="200">
											<a class="block-2-item" href="category?cate_id=3">
												<figure class="image">
													<img src="images/categoryImage3.jpg" alt=""
														class="img-fluid">
												</figure>
												<div class="text">
													<span class="text-uppercase">Collections</span>
													<h3>Bitis</h3>
												</div>
											</a>
										</div>
									</div>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!--  Footer -->
		<jsp:include page="footer.jsp"></jsp:include>
		<!--  Footer -->
	</div>
	<!-- JQuery -->
	<script
		src="https://mdbootstrap.com/previews/ecommerce-demo/js/jquery-3.4.1.min.js"></script>

	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/jquery-ui.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/jquery.magnific-popup.min.js"></script>
	<script src="js/aos.js"></script>

	<script src="js/main.js"></script>
	<script>
		function searchByPriceUnder200() {
			$.ajax({
				url : "/project7/searchNhoHon200",
				type : "get", //send it through get method
				data : {

				},
				success : function(data) {
					var row = document.getElementById("content");
					row.innerHTML = data;
				},
				error : function(xhr) {
					//Do Something to handle error
				}
			});
		}
		function searchByPrice100To200() {
			$.ajax({
				url : "/project7/searchBy100to200",
				type : "get", //send it through get method
				data : {

				},
				success : function(data) {
					var row = document.getElementById("content");
					row.innerHTML = data;
				},
				error : function(xhr) {
					//Do Something to handle error
				}
			});
		}
		function searchByPriceAbove200() {
			$.ajax({
				url : "/project7/searchAjaxPriceAbove200Shop",
				type : "get", //send it through get method
				data : {

				},
				success : function(data) {
					var row = document.getElementById("content");
					row.innerHTML = data;
				},
				error : function(xhr) {
					//Do Something to handle error
				}
			});
		}
	</script>
</body>
</html>