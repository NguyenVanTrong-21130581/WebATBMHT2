
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
						<a href="index.jsp">Home</a> <span class="mx-2 mb-0">/</span> <strong
							class="text-black">Cart</strong>
					</div>
				</div>
			</div>
		</div>

		<div class="site-section">

			<div class="container">
				<%-- <c:if test="${error!=null }">
                 <div class="alert alert-danger" role="alert">
						 ${error}
				</div>
				</c:if>
				<c:if test="${mess!=null }">
                <div class="alert alert-success" role="alert">
				  	${mess}
				</div>
				</c:if> --%>
				<div class="row mb-5">

					<form class="col-md-12" method="post">
						<div class="site-blocks-table">
							<table class="table table-bordered">
								<thead>
									<tr>
										<th class="product-thumbnail">Image</th>
										<th class="product-name">Title</th>
										<th class="product-price">Price</th>
										<th class="product-size">Size</th>
										<th class="product-quantity">Quantity</th>
										<th class="product-total">Total</th>
										<th class="product-remove">Remove</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${listCart}" var="c">
										<c:forEach items="${listPro}" var="p">
											<c:if test="${c.productID == p.id }">
												<tr>
													<td class="product-thumbnail"><img src="${p.image }"
														alt="Image" class="img-fluid"></td>
													<td class="product-name">
														<h2 class="h5 text-black">${p.name }</h2>
													</td>
													<td>$${p.price }</td>
													<td>${c.size }</td>
													<td>
														<div class="input-group mb-3" style="max-width: 120px;">
															<!-- <div class="input-group-prepend">
																<a
																	href="subAmountCart?productID=${c.productID }&amount=${c.amount}">
																	 <button class="btn btn-outline-primary js-btn-minus">&minus;</button> 
																</a> -->
															</div>
															<input type="text" class="form-control text-center"
																value="${c.amount }" placeholder=""
																aria-label="Example text with button addon"
																aria-describedby="button-addon1">
															<!--<div class="input-group-append">
																<a
																	href="addAmountCart?productID=${c.productID }&amount=${c.amount}">
																	  <button class="btn btn-outline-primary js-btn-plus">&plus;</button>
																</a>
															</div>-->
														</div> 

													</td>
													<td>$${p.price * c.amount }</td>
													<td><a href="deleteCart?productID=${c.productID }"
														class="btn btn-primary btn-sm">X</a></td>
												</tr>
											</c:if>
										</c:forEach>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</form>
				</div>

				<div class="row">
					<div class="col-md-6">
						<div class="row mb-5">
							<div class="col-md-6 mb-3 mb-md-0">
								<button class="btn btn-primary btn-sm btn-block">Update
									Cart</button>
							</div>
							<div class="col-md-6">
								<a href="shop">
									<button class="btn btn-outline-primary btn-sm btn-block">Continue
										Shopping</button>
								</a>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<label class="text-black h4" for="coupon">Coupon</label>
								<p>Enter your coupon code if you have one.</p>
							</div>
							<div class="col-md-8 mb-3 mb-md-0">
								<input type="text" class="form-control py-3" id="coupon"
									placeholder="Coupon Code">
							</div>
							<div class="col-md-4">
								<button class="btn btn-primary btn-sm">Apply Coupon</button>
							</div>
						</div>
					</div>
					<div class="col-md-6 pl-5">
						<div class="row justify-content-end">
							<div class="col-md-7">
								<div class="row">
									<div class="col-md-12 text-right border-bottom mb-5">
										<h3 class="text-black h4 text-uppercase">Cart Totals</h3>
									</div>
								</div>
								<div class="row mb-5">
									<div class="col-md-6">
										<span class="text-black">Total</span>
									</div>
									<div class="col-md-6 text-right">
										<strong class="text-black">$${totalMoney }</strong>
									</div>
								</div>

								<div class="row">
									<div class="col-md-12">
										<a href="order">
											<button class="btn btn-primary btn-lg py-3 btn-block">Proceed
												To Checkout</button>
										</a>
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

	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/jquery-ui.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/jquery.magnific-popup.min.js"></script>
	<script src="js/aos.js"></script>

	<script src="js/main.js"></script>

</body>
</html>