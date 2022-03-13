<%--
  Created by IntelliJ IDEA.
  User: This
  Date: 3/7/2022
  Time: 11:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

</head>
<body>
<div class="container">
    <a href="/orders" class="btn btn-primary">Order List</a>
    <h1>Update Order</h1>
    <form action="/orders?action=edit&id=${order.id}" method="post">
        <div class="mb-3">
            <label for="order_id" class="form-label">Order Id</label>
            <input type="text" class="form-control" id="order_id" name="order_id" value="${order.id}" disabled>
        </div>
        <div class="mb-3">
            <label for="user_id" class="form-label">User Id</label>
            <input type="text" class="form-control" id="user_id" name="user_id" value="${order.user_id}">
        </div>
        <div class="mb-3">
            <label for="date" class="form-label">Create Date</label>
            <input type="text" class="form-control" id="date" name="date" value="${order.date}">
        </div>
        <button type="submit" class="btn btn-primary">Edit</button>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>
