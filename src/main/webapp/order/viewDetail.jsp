
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" integrity="sha512-9usAa10IRO0HhonpyAIVpjrylPvoDwiPUiKdWk5t3PyolY1cOd4DSE0Ga+ri4AuTroPR5aQvXU9xC6qOPnzFeg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1 style="text-align: center " >Order Detail</h1>
    <div class="col-1" style="text-align: right ; margin-bottom: 20px">
        <a class="btn btn-primary float-end" href="/orders">Quay lại</a>
    </div>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Order_ID</th>
            <th scope="col">Order Detail Id</th>
            <th scope="col">Stone Type</th>
            <th scope="col">Quantity</th>
            <th scope="col">Price</th>
            <th scope="col">Create Date</th>
            <th scope="col">Total</th>
            <th scope="col"></th>
            <th scope="col"></th>

        </tr>
        </thead>
        <tbody>
        <c:forEach var="viewOrderDetail" items="${viewOrderDetails}">
            <tr>
                <td>${viewOrderDetail.order_id}</td>
                <td>${viewOrderDetail.order_detail_id}</td>
                <td>${viewOrderDetail.stone_name}</td>
                <td>${viewOrderDetail.quantity}</td>
                <td>${viewOrderDetail.stone_price}</td>
                <td>${viewOrderDetail.order_create_date}</td>
                <td>${viewOrderDetail.quantity*viewOrderDetail.stone_price}</td>
                <td><a href="/ordersDetail?action=edit&id=${viewOrderDetail.order_detail_id}" class="btn btn-primary"><i
                        class="fas fa-edit"></i></a></td>
                <td><a href="/ordersDetail?action=delete&id=${viewOrderDetail.order_detail_id}" class="btn btn-danger"><i
                        class="fas fa-trash"></i></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>
