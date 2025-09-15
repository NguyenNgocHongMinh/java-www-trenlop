<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 15/09/2025
  Time: 9:48 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Checkout</title>
  <style>
    body { font-family: Arial, sans-serif; background: #f9f9f9; }
    header, nav { background: #444; color: white; padding: 10px; text-align: center; }
    nav a { color: white; margin: 0 15px; text-decoration: none; font-weight: bold; }
    nav a:hover { text-decoration: underline; }
    .checkout-form {
      background: white; border: 1px solid #ccc; padding: 20px;
      width: 400px; margin: 20px auto;
    }
    .checkout-form input[type=text] {
      width: 100%; padding: 5px; margin: 5px 0;
      border: 1px solid #aaa;
    }
    .checkout-form label { display: block; margin-top: 10px; font-weight: bold; }
    .checkout-form .radio-group { margin: 10px 0; }
    input[type=submit], button {
      background: #444; color: white; border: none;
      padding: 6px 12px; cursor: pointer;
    }
    input[type=submit]:hover, button:hover { background: #666; }
  </style>
</head>
<body>
<header><h1>IUH BOOKSTORE</h1></header>

<div class="checkout-form">
  <h2>Checkout</h2>
  <form action="checkout" method="post">
    <label>Fullname:</label>
    <input type="text" name="fullname" required>
    <label>Shipping address:</label>
    <input type="text" name="address" required>
    <label>Total price (VND):</label>
    <input type="text" contenteditable="false" name="total" value="${cart.total}" readonly>
    <label>Payment method:</label>
    <div class="radio-group">
      <input type="radio" name="payment" value="Paypal" required> Paypal
      <input type="radio" name="payment" value="ATM"> ATM Debit
      <input type="radio" name="payment" value="Visa"> Visa/Master
    </div>
    <input type="submit" name="action" value="Save">
    <button type="submit" name="action" value="Cancel">Cancel</button>
  </form>
</div>
</body>
</html>
