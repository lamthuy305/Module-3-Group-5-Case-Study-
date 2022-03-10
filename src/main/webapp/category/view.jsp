<%--
  Created by IntelliJ IDEA.
  User: This
  Date: 3/7/2022
  Time: 5:26 PM
  To change this template use File | Settings | File Templates.
--%>
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
    <h1 style="text-align: center " >Category List</h1>
    <%--    <a href="/stones?action=create" class="btn btn-primary"><b>Thêm khách hàng mới</b></a>--%>
    <%--    <a href="/stones?action=find" class="btn btn-primary"><b>Tìm kiếm khách hàng theo ID</b></a>--%>

    <table class="table">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Name</th>
            <th scope="col">Price</th>
            <th scope="col">Description</th>
            <th scope="col">Category</th>
            <th scope="col">Image</th>
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="stone" items="${stones}">
            <tr>
                <td>${stone.id}</td>
                <td>${stone.name}</td>
                <td>${stone.price}</td>
                <td>${stone.description}</td>
                <td>${stone.category_id}</td>
                <td><a href="/stones?action=view&id=${stone.id}"><img src="${stone.image}" alt="" width="100" height="100"></a></td>
                <td><a href="/stones?action=edit&id=${stone.id}" class="btn btn-primary"><i class="fas fa-edit"></i></a></td>
                <td><a href="/stones?action=delete&id=${stone.id}" class="btn btn-danger"><i class="fas fa-trash"></i></a></td>
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
