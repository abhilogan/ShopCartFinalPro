<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../../../favicon.ico">

<title>Checkout Page</title>

<!-- Bootstrap core CSS -->
<link href="../../dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="form-validation.css" rel="stylesheet">
</head>

<body class="bg-light">

	<div class="container">
		<div class="py-5 text-center">
			<!-- <img class="d-block mx-auto mb-4" src="https://getbootstrap.com/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72"> -->
			<img alt=""
				src="C:\Users\lenovo\eclipse-workspace\LoginApp2\ShoppingCart1\src\main\webapp\resources\images\checkout.PNG"
				width="72" height="72">
			<h2>Checkout form</h2>
			<p class="lead">Please fill all the Checked-Out Details</p>
		</div>

		<div class="row">
			<div class="col-md-4 order-md-2 mb-4">
				<h4 class="d-flex justify-content-between align-items-center mb-3">
					<span class="text-muted">Your cart</span> <span
						class="badge badge-secondary badge-pill">${carsize}</span>
				</h4>
				<ul class="list-group mb-3">
					<c:set var="total" value="0" />
					<c:forEach items="${carts}" var="cart">
						<li
							class="list-group-item d-flex justify-content-between lh-condensed">
							<div>
								<h6 class="my-0">${cart.productName}</h6>
								<small class="text-muted">${cart.quantity}</small>
							</div> <span class="text-muted">${cart.price*cart.quantity}</span>
						</li>
						<c:set var="total" value="${total+(cart.price*cart.quantity)}" />
					</c:forEach>

					<li class="list-group-item d-flex justify-content-between"><span>Total
							(RUPEES)</span> <strong>${total}</strong></li>
				</ul>

				<form class="card p-2">
					<div class="input-group">
						<input type="text" class="form-control" placeholder="Promo code">
						<div class="input-group-append">
							<button type="submit" class="btn btn-secondary">Redeem</button>
						</div>
					</div>
				</form>
			</div>
			<div class="col-md-8 order-md-1">
				<h4 class="mb-3">Billing address</h4>
				
				
				<form:form  action="${pageContext.request.contextPath}/invoice" modelAttribute="invoice" method="post">
					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="firstName">Full name</label> 
							<form:input type="text" path="user.name" class="form-control" id="firstName" placeholder="full name" required=""/>
							<div class="invalid-feedback">Valid first name is required.
							</div>
						</div>
						
					</div>

					

					<div class="mb-3">
						<label for="email">Email <span class="text-muted">(Optional)</span></label>
						<form:input type="email" path="user.emailID" class="form-control" id="email" placeholder="you@example.com"/>
						<div class="invalid-feedback">Please enter a valid email
							address for shipping updates.</div>
					</div>

					<div class="mb-3">
						<label for="address">Billed Address</label> 
						<form:input type="text" path="billTo" class="form-control" id="address" placeholder="1234 Main St" required=""/>
						<div class="invalid-feedback">Please enter your shipping
							address.</div>
					</div>

					<div class="mb-3">
						<label for="address2">Shipping Address<span class="text-muted">(Optional)</span></label>
						<form:input type="text" path="shippedTo" class="form-control" id="address2" placeholder="Apartment or suite" required=""/>
					</div>

					<div class="row">
						<div class="col-md-5 mb-3">
							<label for="country">Country</label> <select
								class="custom-select d-block w-100" name="country" id="country" required>
								<option value="">Choose...</option>
								<option>United States</option>
								<option>India</option>
							</select>
							<div class="invalid-feedback">Please select a valid
								country.</div>
						</div>
						<div class="col-md-4 mb-3">
							<label for="state">State</label> <select
								class="custom-select d-block w-100" name="state" id="state" required>
								<option value="">Choose...</option>
								<option>Delhi</option>
								<option>Madhya Pradesh</option>
								<option>Rajasthan</option>
								<option>Maharashtra</option>
								<option>Jammu and Kashmir</option>
								<option>Clafornia</option>
							</select>
							<div class="invalid-feedback">Please provide a valid state.
							</div>
						</div>
						<div class="col-md-3 mb-3">
							<label for="zip">Zip</label> 
							<input type="text" name="pincode"	class="form-control" id="zip" placeholder="" required>
							<div class="invalid-feedback">Zip code required.</div>
						</div>
					</div>
					<h4 class="mb-3">Payment</h4>

					<div class="d-block my-3">
						<div class="custom-control custom-radio">
							<form:radiobutton path="paymentMethod" id="credit" value="credit card" name="paymentMethod" class="custom-control-input" checked="" required=""/> 
							<label class="custom-control-label" for="credit">Credit card</label>
						</div>
						<div class="custom-control custom-radio">
							<form:radiobutton path="paymentMethod" id="debit" value="debit card" name="paymentMethod" class="custom-control-input" required=""/> 
							<label class="custom-control-label" for="debit">Debit card</label>
						</div>
						<div class="custom-control custom-radio">
							<form:radiobutton path="paymentMethod" id="paypal" value="paypal" name="paymentMethod"  class="custom-control-input" required=""/> 
							<label class="custom-control-label" for="paypal">PayPal</label>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="cc-name">Name on card</label> <input type="text"
								class="form-control" id="cc-name" placeholder="" required>
							<small class="text-muted">Full name as displayed on card</small>
							<div class="invalid-feedback">Name on card is required</div>
						</div>
						<div class="col-md-6 mb-3">
							<label for="cc-number">Credit card number</label> <input
								type="text" class="form-control" id="cc-number" placeholder=""
								required>
							<div class="invalid-feedback">Credit card number is
								required</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-3 mb-3">
							<label for="cc-expiration">Expiration</label> <input type="text"
								class="form-control" id="cc-expiration" placeholder="" required>
							<div class="invalid-feedback">Expiration date required</div>
						</div>
						<div class="col-md-3 mb-3">
							<label for="cc-cvv">CVV</label> <input type="text"
								class="form-control" id="cc-cvv" placeholder="" required>
							<div class="invalid-feedback">Security code required</div>
						</div>
					</div>
					<hr class="mb-4">
					<button class="btn btn-primary btn-lg btn-block" type="submit">Continue
						to PAY</button>
				</form:form>
			</div>
		</div>

		<footer class="my-5 pt-5 text-muted text-center text-small">
		<p class="mb-1">&copy; 2017-2018 Company Name</p>
		<ul class="list-inline">
			<li class="list-inline-item"><a href="#">Privacy</a></li>
			<li class="list-inline-item"><a href="#">Terms</a></li>
			<li class="list-inline-item"><a href="#">Support</a></li>
		</ul>
		</footer>
	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script>
		window.jQuery
				|| document
						.write(
								'<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')
	</script>
	<script src="../../assets/js/vendor/popper.min.js"></script>
	<script src="../../dist/js/bootstrap.min.js"></script>
	<script src="../../assets/js/vendor/holder.min.js"></script>
	<script>
		// Example starter JavaScript for disabling form submissions if there are invalid fields
						(
								function() {
									'use strict';

									window
											.addEventListener(
													'load',
													function() {
														// Fetch all the forms we want to apply custom Bootstrap validation styles to
														var forms = document
																.getElementsByClassName('needs-validation');

														// Loop over them and prevent submission
														var validation = Array.prototype.filter
																.call(
																		forms,
																		function(
																				form) {
																			form
																					.addEventListener(
																							'submit',
																							function(
																									event) {
																								if (form
																										.checkValidity() === false) {
																									event
																											.preventDefault();
																									event
																											.stopPropagation();
																								}
																								form.classList
																										.add('was-validated');
																							},
																							false);
																		});
													}, false);
								})();
	</script>
</body>
</html>
