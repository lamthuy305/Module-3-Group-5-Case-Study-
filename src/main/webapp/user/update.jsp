<%--
  Created by IntelliJ IDEA.
  User: Bach
  Date: 3/8/2022
  Time: 3:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <a href="/users">Danh sách người dùng</a>
    <h1>Cập nhật sản phẩm</h1>
    <form action="/users?action=update&id=${user.id}" method="post">
        <div class="mb-3">
            <label for="userName" class="form-label">Tên người dùng:</label>
            <input type="text" class="form-control" id="userName" name="name" value="${user.username}" >
        </div>
        <div class="mb-3">
            <label for="userPassword" class="form-label">Mật khẩu:</label>
            <input type="text" class="form-control" id="userPassword" name="password" value="${user.password}" >
        </div>
        <div class="mb-3">
            <label for="userBirthday" class="form-label">Ngày tháng năm sinh(YYYY/MM/DD):</label>
            <input type="text" class="form-control" id="userBirthday" name="birthday" value="${user.birthday}" >
        </div>
        <div class="mb-3">
            <label for="userAddress" class="form-label">Địa chỉ:</label>
            <input type="text" class="form-control" id="userAddress" name="address" value="${user.address}" >
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email:</label>
            <input type="text" class="form-control" id="email" name="email" value="${user.email}" >
        </div>
        <div class="mb-3">
            <label for="role_id" class="form-label">ROLE_ID:</label>
            <input type="text" class="form-control" id="role_id" name="role_id" value="${user.role_id}" >
        </div>
        <button type="submit" class="btn btn-primary">Cập nhật</button>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>