<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%-- <form action="product/cart/add" method="post">
<img alt="" src="${selectedProductImage}"><br>
${selectedProduct.id}
<img alt="" src="resources/images/${selectedProduct.id}.PNG">
<img alt="" src="resources/images/${selectedProduct.id}.PNG">
Product name :<input type="text" readonly="readonly" name="productName" value="${selectedProduct.name}"><br>
price :<input type="text" disabled="disabled" name="price" value="${selectedProduct.price}"><br>
Quantity :<input type="text" name="quantity" ><br>

Description : ${selectedProduct.description}<br>
<input type="submit" value="Add To Cart">

</form> --%>


	<%-- <img alt="" src="${selectedProductImage}"> <br>
PRODUCT-ID:${selectedProduct.id}<br>
<img alt="" src="${selectedProductId}"><br>
PRODUCT NAME  : ${selectedProduct.name} <br>
PRODUCT PRICE : ${selectedProduct.price} <br>
DESCRIPTION   : ${selectedProduct.description} <br> --%>


	<table>
		<tr>
			<td>PRODUCT-ID :</td>
			<td>${selectedProduct.name}</td>
		</tr>

		<tr>
			<td><img alt="" src="${selectedProductId}"></td>
		</tr>

		<tr>
			<td>PRODUCT NAME :</td>
			<td>${selectedProduct.name}</td>
		</tr>


		<tr>
			<td>PRODUCT PRICE :</td>
			<td>${selectedProduct.price}</td>
		</tr>

		<tr>
			<td>DESCRIPTION :</td>
			<td>${selectedProduct.description}</td>
		</tr>

	</table>


	<a href="product/cart/add/${selectedProduct.id}">Add to Cart</a>


	<%-- <c:forEach items="${products}" var="product">

${product.name}

${product.description}

${product.price}

<img alt="" src="resources/images/${product.id}.PNG">

	</c:forEach> --%>


</body>
</html>