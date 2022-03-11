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
    <h1>Add Order</h1>
    <form action="/orders?action=create" method="post">
        <div class="mb-3">
            <label for="user_id" class="form-label">User ID</label>
            <select class="form-control" name="user_id" id="user_id">
                <%--                <c:forEach var="category" items="${categories}">--%>
                <option value="4">4</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <%--                </c:forEach>--%>
            </select>
        </div>
        <div class="mb-3">
            <label for="stone_id" class="form-label">Stone ID</label>
            <select class="form-control" name="stone_id" id="stone_id">
                <c:forEach var="stone" items="${stones}">
                    <option value="${stone.id}">${stone.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <label for="quantity" class="form-label">Quantity</label>
            <input type="text" class="form-control" id="quantity" name="quantity">
        </div>
        <div class="mb-3">
            <label for="date" class="form-label">Date</label>
            <input type="text" class="form-control" id="date" name="date">
        </div>
        <button type="submit" class="btn btn-primary">ADD</button>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>
