<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>Register</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="login-form-v16/Login_v16/images/icons/favicon.ico"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="login-form-v16/Login_v16/vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="login-form-v16/Login_v16/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="login-form-v16/Login_v16/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="login-form-v16/Login_v16/vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="login-form-v16/Login_v16/vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="login-form-v16/Login_v16/vendor/animsition/css/animsition.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="login-form-v16/Login_v16/vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="login-form-v16/Login_v16/vendor/daterangepicker/daterangepicker.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="login-form-v16/Login_v16/css/util.css">
    <link rel="stylesheet" type="text/css" href="login-form-v16/Login_v16/css/main.css">
    <!--===============================================================================================-->
</head>
<body>

<div class="limiter">
    <div class="container-login100" style="background-image: url('/login-form-v16/Login_v16/images/bg-01.jpg');">
        <div class="wrap-login100 p-t-30 p-b-50">
				<span class="login100-form-title p-b-41">
					Account Register
				</span>
            <form method="post" class="login100-form validate-form p-b-33 p-t-5">
                <c:if test="${message2!=null}">
                    <p class="alert-success" style="margin-left: 30px;margin-right: 30px">${message2}</p>
                </c:if>
                <c:if test="${message1!=null}">
                    <p class="alert-danger" style="margin-left: 30px;margin-right: 30px">${message1}</p>
                </c:if>
                <p style="margin-left: 30px">Have an account?  <a href="/login" style="color: #0c84ff">Login here!</a></p>
                <div class="wrap-input100 validate-input" data-validate="Enter username">
                    <input class="input100" type="text" name="username" placeholder="User name">
                    <span class="focus-input100" data-placeholder="&#xe82a;"></span>
                </div>

                <div class="wrap-input100 validate-input" data-validate="Enter password">
                    <input class="input100" type="password" name="password" placeholder="Password">
                    <span class="focus-input100" data-placeholder="&#xe80f;"></span>
                    <i style="margin-left: 30px;color: #6A6A6A">(at least 6 character,1 number and 1 alphabet)</i>
                </div>

                <div class="wrap-input100 validate-input" data-validate="Enter birthday">
                    <input class="input100" type="date" name="birthday">
                </div>


                <div class="wrap-input100 validate-input" data-validate="Enter address">
                    <input class="input100" type="text" name="address" placeholder="Address">
                    <span class="focus-input100" data-placeholder="&#xe82a;"></span>
                </div>

                <div class="wrap-input100 validate-input" data-validate="Enter email">
                    <input class="input100" type="email" name="email" placeholder="Email">
                    <span class="focus-input100" data-placeholder="&#xe82a;"></span>
                </div>
                <div class="wrap-input100 validate-select" data-validate="Select user" style="text-align: center">
                    <select name="role_id" style="width: 300px;height: 30px">
                            <option value="1" style="text-align: center">Role Admin</option>
                            <option value="2" style="text-align: center">Role User</option>
                    </select>
                </div>
                <div class="container-login100-form-btn m-t-32">
                    <button type="submit" class="login100-form-btn">
                        Register
                    </button>
                </div>

            </form>
        </div>
    </div>
</div>


<div id="dropDownSelect1"></div>

<!--===============================================================================================-->
<script src="login-form-v16/Login_v16/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="login-form-v16/Login_v16/vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
<script src="login-form-v16/Login_v16/vendor/bootstrap/js/popper.js"></script>
<script src="login-form-v16/Login_v16/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="login-form-v16/Login_v16/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
<script src="login-form-v16/Login_v16/vendor/daterangepicker/moment.min.js"></script>
<script src="login-form-v16/Login_v16/vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
<script src="login-form-v16/Login_v16/vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
<script src="login-form-v16/Login_v16/js/main.js"></script>

</body>
</html>