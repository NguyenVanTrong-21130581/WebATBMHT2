<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
                    <form action="add" method="post"  enctype="multipart/form-data" >
                        <div class="modal-header">						
                            <h4 class="modal-title">Add Product</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">
                         					
                            <div class="form-group">
                                <label>Name</label>
                                <input name="name" type="text" class="form-control" required>                            
                            </div>
                            
                            <div class="form-group">
                                <label>Image</label>
                                <input name="image" type="file" class="form-control" >
                            </div>
                              <div class="form-group">
                                <label>Image 2</label>
                                <input name="image2" type="text" class="form-control" >
                            </div>
                              <div class="form-group">
                                <label>Image 3</label>
                                <input name="image3" type="text" class="form-control" >
                            </div>
                              <div class="form-group">
                                <label>Image 4</label>
                                <input name="image4" type="text" class="form-control" >
                            </div>
                            <div class="form-group">
                                <label>Price</label>
                                <input name="price" type="text" class="form-control" >
                            </div>
                            <div class="form-group">
                                <label>Title</label>
                                <textarea name="title" class="form-control" required></textarea>
                            </div>
                             <div class="form-group">
                                <label>Model</label>
                                <input name="model" type="text" class="form-control" >
                            </div>
                             <div class="form-group">
                                <label>Color</label>
                                <input name="color" type="text" class="form-control" >
                            </div>
                             <div class="form-group">
                                <label>Delivery</label>
                                <input name="delivery" type="text" class="form-control" >
                            </div>
                            <div class="form-group">
                                <label>Description</label>
                                <textarea name="description" class="form-control" ></textarea>
                            </div>
                            <div class="form-group">
                                <label>Category</label>
                                <select name="category" class="form-select" aria-label="Default select example">
                                    <c:forEach items="${listCate}" var="o">
                                        <option value="${o.cate_id}">${o.cate_name}</option>
                                    </c:forEach>
                                </select>
                            </div>

                        </div>
                        <div class="modal-footer">  
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-success" value="Add">
                        </div>
                    </form>
               
</body>
</html>