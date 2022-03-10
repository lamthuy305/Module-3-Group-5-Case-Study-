<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" integrity="sha512-9usAa10IRO0HhonpyAIVpjrylPvoDwiPUiKdWk5t3PyolY1cOd4DSE0Ga+ri4AuTroPR5aQvXU9xC6qOPnzFeg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1>Danh sách người dùng</h1>
    <a class="btn btn-primary float-end" href="/users?action=create">Tạo mới</a>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th scope="col">Mã người dùng</th>
            <th scope="col">Tên người dùng</th>
            <th scope="col">Ngày tháng năm sinh</th>
            <th scope="col">Địa chỉ</th>
            <th scope="col">Email</th>
            <th scope="col">ROLE_ID</th>
            <th colspan="2"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.id}</td>
                <td><a href="/users?action=view&id=${user.id}">${user.username}</a></td>
                <td>${user.birthday}</td>
                <td>${user.address}</td>
                <td>${user.email}</td>
                <td>${user.role_id}</td>
                <td>
                    <a class="btn btn-info" href="/users?action=update&id=${user.id}">
                        <i class="fas fa-edit"></i>
                    </a>
                </td>
                <td><a class="btn btn-danger" href="/users?action=delete&id=${user.id}"><i class="fas fa-trash"></i></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</body>
</html>