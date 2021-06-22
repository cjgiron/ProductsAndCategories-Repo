<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Category Page</title>
</head>
<body>
	<h1 style="text-align: center"><c:out value="${category.name}"></c:out></h1>
	<div style="display: flex; justify-content: center;">
		<div style="margin-right: 100px;">
			<h3>Products</h3>
			<ul>
				<c:forEach items="${currentProducts}" var="product">
					<li><c:out value="${product.name}"></c:out></li>
				</c:forEach>
			</ul>
		</div>
		<div>
			<form action="/categories/${category.id}/product" method="post">
				<p>
					<label for="product_id">Add Product: </label>
					<select name="product_id">
						<c:forEach items="${nonCurrentProducts}" var="product">
							<option value="${product.id}"><c:out value="${product.name}"></c:out></option>
						</c:forEach>
					</select>
				</p>
				<input type="submit" value="Add"/>
			</form>
		</div>
	</div>
</body>
</html>