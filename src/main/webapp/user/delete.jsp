<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <a href="/users">Danh sách Người Dùng</a>
    <h1>Xóa Tài Khoản</h1>
    <form action="/users?action=delete&id=${user.id}" method="post">
        <div class="mb-3">
            <label for="userId" class="form-label">Mã người dùng:</label>
            <input type="text" class="form-control" id="userId" name="id" value="${user.id}" disabled>
        </div>
        <div class="mb-3">
            <label for="userName" class="form-label">Tên người dùng:</label>
            <input type="text" class="form-control" id="userName" name="name" value="${user.username}" disabled>
        </div>
        <div class="mb-3">
            <label for="userBirthday" class="form-label">Ngày tháng năm sinh(YYYY/MM/DD):</label>
            <input type="text" class="form-control" id="userBirthday" name="birthday" value="${user.birthday}" disabled>
        </div>
        <div class="mb-3">
            <label for="userAddress" class="form-label">Địa chỉ:</label>
            <input type="text" class="form-control" id="userAddress" name="address" value="${user.address}" disabled>
        </div>
        <div class="mb-3">
            <label for="userEmail" class="form-label">Email:</label>
            <input type="text" class="form-control" id="userEmail" name="email" value="${user.email}" disabled>
        </div>
        <div class="mb-3">
            <label for="userEmail" class="form-label">Email:</label>
            <input type="text" class="form-control" id="roleID" name="role_id" value="${user.email}" disabled>
        </div>
        <button type="submit" class="btn btn-danger">Xóa</button>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>