<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <a href="/users">Danh sách người dùng</a>
    <h1>Chi tiết tài khoản</h1>
    <form>
        <div class="mb-3">
            <label for="userId" class="form-label">Mã người dùng:</label>
            <input type="text" class="form-control" id="userId" name="id" value="${user.id}" disabled>
        </div>
        <div class="mb-3">
            <label for="userName" class="form-label">Tên người dùng:</label>
            <input type="text" class="form-control" id="userName" name="name" value="${user.username}" disabled>
        </div>
        <div class="mb-3">
            <label for="userPassword" class="form-label">Mật khẩu:</label>
            <input type="text" class="form-control" id="userPassword" name="password" value="${user.password}" disabled>
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
            <label for="email" class="form-label">Email:</label>
            <input type="text" class="form-control" id="email" name="email" value="${user.email}" disabled>
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">ROLE_ID:</label>
            <input type="text" class="form-control" id="roleId" name="role_id" value="${user.role_id}" disabled>
        </div>
        <a href="/users" class="btn btn-secondary">Quay lại trang danh sách người dùng</a>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>