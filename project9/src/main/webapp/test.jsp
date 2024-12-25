<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Shop Page</title>
</head>
<body>

<h1>Product List</h1>

<c:if test="${not empty listPro}">
    <ul>
        <c:forEach var="product" items="${listPro}">
            <li>${product.name} - ${product.price}</li>
        </c:forEach>
    </ul>
</c:if>

</body>
</html>
