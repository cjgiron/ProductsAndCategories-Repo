<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Page</title>
</head>
<body>
	<h1 style="text-align: center"><c:out value="${product.name}"></c:out></h1>
	<div style="display: flex; justify-content: center;">
		<div style="margin-right: 100px;">
			<h3>Categories</h3>
			<ul>
				<c:forEach items="${currentCategories}" var="category">
					<li><c:out value="${category.name}"></c:out></li>
				</c:forEach>
			</ul>
		</div>
		<div>
			<form action="/products/${product.id}/category" method="post">
				<p>
					<label for="category_id">Add Category: </label>
					<select name="category_id">
						<c:forEach items="${nonCurrentCategories}" var="category">
							<option value="${category.id}"><c:out value="${category.name}"></c:out></option>
						</c:forEach>
					</select>
				</p>
				<input type="submit" value="Add"/>
			</form>
		</div>
	</div>
</body>
</html>