 

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Ecommerce Site</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	 >
</head>
<body style="background-image: url(https://source.unsplash.com/E8Ufcyxz514/2400x1823); background-size: cover; background-repeat: no-repeat;
  background-size: cover; align-items: center;">

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color:#a770ef">
			<div>
				<a href=" " class="navbar-brand"> Ecommerce Site </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Products List</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${product != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${product == null}">
					<form action="insert" method="post">
				</c:if>

				<caption style="background-color:#a770ef">
					<h2>
						<c:if test="${product != null}">
            			Edit Product
            		</c:if>
						<c:if test="${product == null}">
            			Add New Product
            		</c:if>
					</h2>
				</caption>

				<c:if test="${product != null}">
					<input type="hidden" name="id" value="<c:out value='${product.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Product Name</label> <input type="text"
						value="<c:out value='${product.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Product Description</label> <input type="text"
						value="<c:out value='${product.description}' />" class="form-control"
						name="description">
				</fieldset>

				<fieldset class="form-group">
					<label>Product Price</label> <input type="text"
						value="<c:out value='${product.price}' />" class="form-control"
						name="price">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Quantity</label> <input type="text"
						value="<c:out value='${product.quantity}' />" class="form-control"
						name="quantity">
				</fieldset>
				

				<button type="submit" class="btn" style="background-color:#a770ef">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>