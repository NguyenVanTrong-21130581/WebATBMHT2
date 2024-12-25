<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Nhập Key</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h2 class="mt-5">Nhập Key để ký dữ liệu</h2>
        <form action="signData" method="post">
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" class="form-control" id="name" name="name" value="${name}" readonly>
            </div>
            <div class="form-group">
                <label for="sdt">Phone Number:</label>
                <input type="text" class="form-control" id="sdt" name="sdt" value="${sdt}" readonly>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="text" class="form-control" id="email" name="email" value="${email}" readonly>
            </div>
            <div class="form-group">
                <label for="address">Address:</label>
                <input type="text" class="form-control" id="address" name="address" value="${address}" readonly>
            </div>
            <div class="form-group">
                <label for="content">Content:</label>
                <textarea class="form-control" id="content" name="content" readonly>${content}</textarea>
            </div>
            <div class="form-group">
                <label for="key">Key:</label>
                <input type="text" class="form-control" id="key" name="key" placeholder="Nhập key của bạn">
            </div>
            <button type="submit" class="btn btn-primary btn-block">Ký Dữ Liệu</button>
        </form>
    </div>
</body>
</html>
