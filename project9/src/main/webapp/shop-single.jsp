<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Shoppers &mdash; Colorlib e-Commerce Template</title>
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
							class="text-black">${detail.name}</strong>
					</div>
				</div>
			</div>
		</div>

		<div class="site-section">
			<div class="container">
				<div class="row">
					<div class="col-md-6">
						<img src="${detail.image }" alt="Image" class="img-fluid">
					</div>
					<div class="col-md-6">
						<h2 class="text-black">${detail.name}</h2>
						<p>${detail.description }</p>

						<p>
							<strong class="text-primary h4">$ ${detail.price}</strong>
						</p>

						<div class="table-responsive">
							<table class="table table-sm table-borderless mb-0">
								<tbody>
									<tr>
										<th class="pl-0 w-25" scope="row"><strong>Model</strong></th>
										<td>${detail.model }</td>
									</tr>
									<tr>
										<th class="pl-0 w-25" scope="row"><strong>Color</strong></th>
										<td>${detail.color }</td>
									</tr>
									<tr>
										<th class="pl-0 w-25" scope="row"><strong>Delivery</strong></th>
										<td>${detail.delivery }</td>
									</tr>
								</tbody>
							</table>
						</div>
						<form action="addCart?pid=${detail.id }" method="post">
							<div class="mt-1">
								<div class="form-check form-check-inline pl-0">
									<input type="radio" class="form-check-input" id="small"
										value="small" name="size" checked> <label
										class="form-check-label small text-uppercase card-link-secondary"
										for="small">S</label>
								</div>
								<div class="form-check form-check-inline pl-0">
									<input type="radio" class="form-check-input" id="medium"
										value="medium" name="size"> <label
										class="form-check-label small text-uppercase card-link-secondary"
										for="medium">M</label>
								</div>
								<div class="form-check form-check-inline pl-0">
									<input type="radio" class="form-check-input" id="large"
										value="large" name="size"> <label
										class="form-check-label small text-uppercase card-link-secondary"
										for="large">L</label>
								</div>
							</div>


							<div class="mb-5">
								<div class="input-group mb-3" style="max-width: 120px;">
									<div class="input-group-prepend">
										<button class="btn btn-outline-primary js-btn-minus"
											type="button">&minus;</button>
									</div>
									<input name="quantity" type="text"
										class="form-control text-center" value="1" placeholder=""
										aria-label="Example text with button addon"
										aria-describedby="button-addon1">
									<div class="input-group-append">
										<button class="btn btn-outline-primary js-btn-plus"
											type="button">&plus;</button>
									</div>
								</div>

							</div>

							<div class="mt-1">
								<button type="submit" class="btn btn-primary btn-md mr-1 mb-2">Buy
									now</button>
								<button type="submit" class="btn btn-light btn-md mr-1 mb-2">
									<i class="fas fa-shopping-cart pr-2"></i>Add to cart
								</button>

							</div>
						</form>
					</div>

				</div>
				<!-- Classic tabs -->
				<div class="classic-tabs" style="padding-top: 65px">

					<ul class="nav tabs-primary nav-justified" id="advancedTab"
						role="tablist">
						<li class="nav-item"><a class="nav-link active show"
							id="description-tab" data-toggle="tab" href="#description"
							role="tab" aria-controls="description" aria-selected="true">Description</a>
						</li>
						<li class="nav-item"><a class="nav-link" id="info-tab"
							data-toggle="tab" href="#info" role="tab" aria-controls="info"
							aria-selected="false">Information</a></li>
						<!--  <li class="nav-item"><a class="nav-link" id="reviews-tab"
							data-toggle="tab" href="#reviews" role="tab"
							aria-controls="reviews" aria-selected="false">Reviews
								(${countAllReview })</a></li> -->
					</ul>
					<div class="tab-content" id="advancedTabContent">
						<div class="tab-pane fade show active" id="description"
							role="tabpanel" aria-labelledby="description-tab">
							<h5>Product Description</h5>

							<h6>$${String.format("%.02f",detail.price*0.9) }</h6>
							<p class="pt-1">${detail.description}</p>
						</div>
						<div class="tab-pane fade" id="info" role="tabpanel"
							aria-labelledby="info-tab">
							<h5>Additional Information</h5>
							<table class="table table-striped table-bordered mt-3">
								<thead>
									<tr>
										<th scope="row" class="w-150 dark-grey-text h6">Weight</th>
										<td><em>0.3 kg</em></td>
									</tr>
								</thead>
								<tbody>
									<tr>
										<th scope="row" class="w-150 dark-grey-text h6">Dimensions</th>
										<td><em>50 × 60 cm</em></td>
									</tr>
								</tbody>
							</table>
						</div>
					<!-- 	<div class="tab-pane fade" id="reviews" role="tabpanel"
							aria-labelledby="reviews-tab">
							<h5>
								<span>${countAllReview }</span> review for <span>${detail.name }</span>
							</h5>

							<h5 class="mt-4">Add a review</h5>
							<p></p>

							<div>
								
								<div class="md-form md-outline">
									<input name="contentReview" 
										></input>
									<label for="form76">Your review</label>
								</div>
								<div class="text-right pb-2">
									<a href="addReview">
										<button type="button" class="btn btn-primary">Add
											a review</button>
									</a>
								</div>
							</div>


							<c:forEach items="${listAllReview}" var="r">

								<div class="media mt-3 mb-4">
									<img class="d-flex mr-3 z-depth-1"
										src="https://mdbootstrap.com/img/Photos/Others/placeholder1.jpg"
										width="62" alt="Generic placeholder image">
									<div class="media-body">
										<div class="d-flex justify-content-between">
											<p class="mt-1 mb-2">
												<c:forEach items="${listAllAcount}" var="a">
													<c:if test="${r.accountID == a.id }">
														<strong>${a.user } </strong>
													</c:if>
												</c:forEach>
												<span>– </span><span>${r.dateReview }</span>
											</p>
										</div>
										<p class="mb-0">${r.contentReview }</p>
									</div>
								</div>
								<hr>

							</c:forEach>

						</div>
					</div> -->






				<!-- <div class="site-section block-3 site-blocks-2 bg-light">
						<div class="container">
							<div class="row justify-content-center">
								<div class="col-md-7 site-section-heading text-center pt-4">
									<h2>Featured Products</h2>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="nonloop-block-3 owl-carousel">
										<c:forEach items="${list5pro }" var="o">
											<div class="item">
												<div class="block-4 text-center">
													<figure class="block-4-image">
														<img src="${o.image }" alt="Image placeholder"
															class="img-fluid">
													</figure>
													<div class="block-4-text p-4">
														<h3>
															<a href="detail?pid=${o.getId() }">${o.name }</a>
														</h3>
														<p class="mb-0">${o.title }</p>
														<p class="text-primary font-weight-bold">$${o.price }</p>
													</div>
												</div>
											</div>
										</c:forEach>


									</div>
								</div>
							</div>
						</div>
					</div>  -->	

					<!--  Footer -->
					<jsp:include page="footer.jsp"></jsp:include>
					<!--  Footer -->
					<script>
						window
								.addEventListener(
										"load",
										function loadAmountCart() {
											$
													.ajax({
														url : "/project7/loadAllAmountCart",
														type : "get", //send it through get method
														data : {

														},
														success : function(
																responseData) {
															document
																	.getElementById("amountCart").innerHTML = responseData;
														}
													});
										}, false);

						
						}
					</script>
				</div>

				<script src="js/jquery-3.3.1.min.js"></script>
				<script src="js/jquery-ui.js"></script>
				<script src="js/popper.min.js"></script>
				<script src="js/bootstrap.min.js"></script>
				<script src="js/owl.carousel.min.js"></script>
				<script src="js/jquery.magnific-popup.min.js"></script>
				<script src="js/aos.js"></script>

				<script src="js/main.js"></script>

				<script src="../../../js/jquery-3.4.1.min.js"></script>
				<!-- Bootstrap tooltips -->
				<script type="text/javascript"
					src="https://mdbootstrap.com/previews/ecommerce-demo/js/popper.min.js"></script>
				<!-- Bootstrap core JavaScript -->
				<script type="text/javascript"
					src="https://mdbootstrap.com/previews/ecommerce-demo/js/bootstrap.js"></script>
				<!-- MDB core JavaScript -->
				<script type="text/javascript"
					src="https://mdbootstrap.com/previews/ecommerce-demo/js/mdb.min.js"></script>
				<!-- MDB Ecommerce JavaScript -->
				<script type="text/javascript"
					src="https://mdbootstrap.com/previews/ecommerce-demo/js/mdb.ecommerce.min.js"></script>
				<script>
					$(document)
							.ready(
									function() {
										// MDB Lightbox Init
										$(function() {
											$("#mdb-lightbox-ui")
													.load(
															"../../../mdb-addons/mdb-lightbox-ui.html");
										});
									});
				</script>
</body>
</html>