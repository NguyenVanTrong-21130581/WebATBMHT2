<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách đơn hàng</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 20px;
	background-color: #f4f4f4;
}

h1 {
	color: #333;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

th, td {
	border: 1px solid #ddd;
	padding: 10px;
	text-align: left;
}

th {
	background-color: #4CAF50;
	color: white;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

button {
	background-color: #008CBA;
	color: white;
	border: none;
	padding: 8px 16px;
	cursor: pointer;
	font-size: 14px;
}

button:hover {
	background-color: #007B9D;
}

input[type="text"] {
	padding: 8px;
	font-size: 14px;
	width: 200px;
	margin-right: 10px;
}

form {
	display: none;
	margin-top: 10px;
}

form input[type="text"] {
	width: 200px;
}

form button {
	background-color: #4CAF50;
	padding: 8px 16px;
	cursor: pointer;
	color: white;
	border: none;
}

form button:hover {
	background-color: #45a049;
}
</style>
</head>
<body>
	<h1>Danh sách đơn hàng của bạn</h1>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Tên khách hàng</th>
				<th>Địa chỉ</th>
				<th>Nội dung</th>
				<th>Tổng tiền</th>
				<th>Trạng thái</th>
				<th>Hành động</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="order" items="${orders}">
				<tr id="row-${order.orderId}">
					<td>${order.orderId}</td>
					<td>${order.name}</td>
					<td>${order.address}</td>
					<td>${order.content}</td>
					<td>${order.totalMoney}</td>
					<td>${order.status}</td>
					<td>
						<!-- Nút Ký -->
						<button onclick="showSignForm(${order.orderId})">Ký</button>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<!-- Form động -->
	<form id="dynamic-sign-form" action="SignOrderControl" method="POST"
		style="display: none; margin-top: 20px;">
		<input type="hidden" id="form-order-id" name="orderId" /> <input
			type="text" name="signature" placeholder="Nhập chữ ký" required />
		<button type="submit">Xác nhận</button>
		<button type="button" onclick="hideSignForm()">Hủy</button>
	</form>

	<script>
	function showSignForm(orderId) {
	    console.log("showSignForm for orderId:", orderId); // In ra giá trị để kiểm tra

	    const form = document.getElementById("dynamic-sign-form");
	    form.style.display = "block";

	    const orderIdInput = document.getElementById("form-order-id");
	    orderIdInput.value = orderId; // Gán giá trị orderId vào input ẩn

	    form.scrollIntoView({ behavior: "smooth" });
	}


	function hideSignForm() {
	    // Ẩn form động
	    const form = document.getElementById("dynamic-sign-form");
	    form.style.display = "none";
	}

</script>

</body>
</html>
