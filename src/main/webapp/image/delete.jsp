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
    <h1 style="text-align: center " >Image</h1>
    <a href="/image" class="btn btn-primary">Image List</a>


    <form action="/image?action=delete&id=${image.id}" method="post">
        <table class="table">
            <thead>
            <tr>
                <th scope="col" style="width: 20%">Image ID</th>
                <th scope="col" style="width: 20%">Stone ID</th>
                <th scope="col" style="width: 40%">Image</th>
                <th style="width: 19%"></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${image.id}</td>
                <td>${image.stone_id}</td>
                <td><img src="${image.link}" alt="" width="500px" height="500px"></td>
                <td><td><button type="submit" class="btn btn-danger">Xóa</button></td></td>
            </tr>
            </tbody>
        </table>

    </form>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>
