<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Dat Hang</title>
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
							class="text-black">Dat Hang</strong>
					</div>
				</div>
			</div>
		</div>

		<div class="site-section">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<h2 class="h3 mb-3 text-black">Get In Touch</h2>
					</div>
					<div class="col-md-7">

						<form action="order" method="post">

							<div class="p-3 p-lg-5 border">
								<div class="form-group row">
									<div class="col-md-6">
										<label for="c_fname" class="text-black"> Name <span
											class="text-danger">*</span></label> <input type="text"
											class="form-control" id="c_fname" name="name">
									</div>
									<div class="col-md-6">
										<label for="c_lname" class="text-black">Phone Number <span
											class="text-danger">*</span></label> <input type="text"
											class="form-control" id="c_lname" name="sdt">
									</div>
								</div>
								<div class="form-group row">
									<div class="col-md-12">
										<label for="c_email" class="text-black">Email <span
											class="text-danger">*</span></label> <input type="email"
											class="form-control" id="c_email" name="email"
											value="${email}" readonly>
									</div>
								</div>

								<div class="form-group row">
									<div class="col-md-12">
										<label for="c_subject" class="text-black">Address </label> <input
											type="text" class="form-control" id="c_subject"
											name="address">
									</div>
								</div>

								<div class="form-group row">
									<div class="col-md-12">
										<label for="c_message" class="text-black">Content </label>
										<textarea name="content" id="c_message" cols="30" rows="7"
											class="form-control"></textarea>
									</div>
								</div>
								<div class="form-group row">
									<div class="col-lg-12">
										<!-- Nút Nhập Key -->
										<button type="button"
											class="btn btn-secondary btn-lg btn-block" id="showKeyForm">
											Nhập Key</button>
									</div>
								</div>

								<!-- Form nhập key (ẩn ban đầu) -->
								<div class="form-group row" id="keyForm" style="display: none;">
									<div class="col-md-12">
										<label for="c_key" class="text-black">Key <span
											class="text-danger">*</span></label> <input type="text"
											class="form-control" id="c_key" name="key">
									</div>
									<div class="col-lg-12 mt-3">
										<button type="button" class="btn btn-primary btn-lg btn-block"
											id="btn-generate-key" onclick="generateKeyPair()">Tạo
											Key</button>
									</div>
								</div>
								<div class="form-group row">
									<div class="col-lg-12">
										<input type="submit" class="btn btn-primary btn-lg btn-block"
											value="Dat hang">
									</div>
								</div>
							</div>
						</form>
					</div>
					<div class="col-md-5 ml-auto">
						<div class="p-4 border mb-3">
							<span class="d-block text-primary h6 text-uppercase">New
								York</span>
							<p class="mb-0">203 Fake St. Mountain View, San Francisco,
								California, USA</p>
						</div>
						<div class="p-4 border mb-3">
							<span class="d-block text-primary h6 text-uppercase">London</span>
							<p class="mb-0">203 Fake St. Mountain View, San Francisco,
								California, USA</p>
						</div>
						<div class="p-4 border mb-3">
							<span class="d-block text-primary h6 text-uppercase">Canada</span>
							<p class="mb-0">203 Fake St. Mountain View, San Francisco,
								California, USA</p>
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
	<script>
		document
				.getElementById('showKeyForm')
				.addEventListener(
						'click',
						function() {
							// Hiển thị form nhập key
							const keyForm = document.getElementById('keyForm');
							keyForm.style.display = (keyForm.style.display === 'none' || keyForm.style.display === '') ? 'block'
									: 'none';
						});

		document.getElementById('generateKey').addEventListener('click',
				function() {
					// Xử lý tạo key ở đây (nếu cần)
					alert('Key đã được tạo!');
				});
	</script>
<script>
    function generateKeyPair() {
        // Gửi yêu cầu tạo key pair qua AJAX
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "generateKeyPair", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

        xhr.onload = function() {
            if (xhr.status === 200) {
                var response = JSON.parse(xhr.responseText);
                if (response.status === "success") {
                    alert(response.message);  // Hiển thị thông báo thành công
                    window.location.href = "downloadPrivateKey";  // Chuyển đến servlet tải private key
                } else {
                    alert("Error: " + response.message);
                }
            }
        };
        xhr.send();
    }
</script>
</body>
</html>