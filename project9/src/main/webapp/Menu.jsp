<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<header class="site-navbar" role="banner">
			<div class="site-navbar-top">
				<div class="container">
					<div class="row align-items-center">

						<div
							class="col-6 col-md-4 order-2 order-md-1 site-search-icon text-left">
							<form action="search" method="post" class="site-block-top-search">
								<span class="icon icon-search2"></span> <input value="${txtS }"
									name="txt" type="text" class="form-control border-0"
									placeholder="Search">
							</form>
						</div>

						<div
							class="col-12 mb-3 mb-md-0 col-md-4 order-1 order-md-2 text-center">
							<div class="site-logo">
								<a href="home" class="js-logo-clone">SHOES</a>
							</div>
						</div>

						<div class="col-6 col-md-4 order-3 order-md-3 text-right">
							<div class="site-top-icons">
								<ul>
									<li><a href="login"><span class="icon icon-person"></span></a></li>
									<li><a href="#"><span class="icon icon-heart-o"></span></a></li>
									<li><a href="managerCart" class="site-cart"> <span
											class="icon icon-shopping_cart"></span> <span class="count">${totalAmount }</span>
									</a></li>
									<li class="d-inline-block d-md-none ml-md-0"><a href="#"
										class="site-menu-toggle js-menu-toggle"><span
											class="icon-menu"></span></a></li>
								</ul>
							</div>
						</div>

					</div>
				</div>
			</div>
			<nav class="site-navigation text-right text-md-center"
				role="navigation">
				<div class="container">
					<ul class="site-menu js-clone-nav d-none d-md-block">
						<li ><a href="home">Home</a>
							
						<li ><a href="about">About</a>
							

						<c:if test="${sessionScope.acc.isSell == 1 }">
							<li><a href="manager">Manager Product</a></li>
						</c:if>

						<c:if test="${sessionScope.acc.isAdmin == 1 }">
							<li><a href="managerAccount">Manager Account</a></li>
						</c:if>

						<li ><a href="shop">Shop</a></li>
						<c:if test="${sessionScope.acc == null }">
							<li><a href="login">Login</a></li>
						</c:if>

						<c:if test="${sessionScope.acc != null }">
							<li><a href="logout">Logout</a></li>

							<li><a href="orders">Hello ${sessionScope.acc.user }</a></li>
						</c:if>

						
					</ul>
				</div>
			</nav>
		</header>