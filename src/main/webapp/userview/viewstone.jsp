<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Ogani Template">
    <meta name="keywords" content="Ogani, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>VicoStone | View</title>
    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">
    <!-- Css Styles -->
    <link rel="stylesheet" href="/file/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="/file/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="/file/css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="/file/css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="/file/css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="/file/css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="/file/css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="/file/css/style.css" type="text/css">
</head>

<body>


<%--SỬA TỪ ĐÂY--%>

<!-- Header Section Begin -->
<header class="header">
    <div class="header__top">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-6">
                    <div class="header__top__left">
                        <ul>
                            <li><i class="fa fa-envelope"></i>Codegym@gmail.com</li>
                            <%--                            <li>Free Shipping for all Order of $99</li>--%>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6">
                    <div class="header__top__right">
                        <div class="header__top__right__social">
                            <a href="https://www.facebook.com/"><i class="fa fa-facebook"></i></a>
                        </div>
                        <div class="header__top__right__language">
                            <img src="../img/vietnam.png" alt="" height="15" width="20">
                            <div>Viet Nam</div>
                        </div>
                        <div class="header__top__right__auth">
                            <a href="/login"><i class="fa fa-user"></i> Login</a>
                        </div>
                        <div class="header__top__right__auth">
                            <a href="/register">/Register</a>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-lg-3">
                <div class="header__logo">
                    <img src="../img/logovico.jpg" alt="" width="100" height="100">
                </div>
            </div>
            <div class="col-lg-6">
                <nav class="header__menu">
                    <ul>
                        <li class="active"><a href="/home">Home</a></li>
                        <li><a href="/orders?action=create">Shopping</a></li>
                        <li><a href="#">WARRANTY</a>
                        <li><a href="#">Customer Service</a>

                        </li>
                    </ul>
                </nav>
            </div>
        </div>
        <div class="humberger__open">
            <i class="fa fa-bars"></i>
        </div>
    </div>
</header>
<!-- Header Section End -->

<!-- Hero Section Begin -->
<section class="hero">
    <div class="container">
        <div class="row">
            <div class="col-lg-3">
                <div class="hero__categories">
                    <div class="hero__categories__all">
                        <i class="fa fa-bars"></i>
                        <span>All Category</span>
                    </div>
                    <ul>
                        <c:forEach var="category" items="${categories}">
                            <li><a href="/home?action=viewcategory&id=${category.id}">${category.name}</a></li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
            <div class="col-lg-9">
                <div class="hero__search">
                    <div class="hero__search__form">
                        <form action="/home?action=seachstone" method="get">
                            <div class="hero__search__categories">
                                All Product
                                <span class="arrow_carrot-down"></span>
                            </div>
                            <input type="hidden" name="action" value="seachstone">
                            <input type="text" placeholder="What do you need?" name="q">
                            <button type="submit" class="site-btn">SEARCH</button>
                        </form>
                    </div>
                    <div class="hero__search__phone">
                        <div class="hero__search__phone__icon">
                            <i class="fa fa-phone"></i>
                        </div>
                        <div class="hero__search__phone__text">
                            <h5>0989.999.999</h5>
                            <span>support 24/7 time</span>
                        </div>
                    </div>
                </div>
                <div>
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Name</th>
                                <th scope="col">Price</th>
                                <th scope="col">Description</th>
                                <th scope="col">Category</th>
                                <th scope="col">Image</th>
                            </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>${stone.id}</td>
                                    <td>${stone.name}</td>
                                    <td>${stone.price}</td>
                                    <td>${stone.description}</td>
                                    <td>${stone.category_id}</td>
                                    <td><a href="/home?action=viewstone&id=${stone.id}"><img src="${stone.image}" alt="" width="100" height="100"></a></td>
                                </tr>
                            </tbody>
                        </table>

                    <div style="height: 50px"></div>
                    <section class="categories">
                        <div class="container">
                            <div class="row">
                                <div class="categories__slider owl-carousel">
                                    <div class="col-lg-3">
                                        <div class="categories__item set-bg" data-setbg="${stone.image}"
                                             style="width: 200px; height: 200px">
                                        </div>
                                    </div>

                                    <c:forEach var="image" items="${images}">
                                        <div class="col-lg-3">
                                            <div class="categories__item set-bg" data-setbg="${image.link}"
                                                 style="width: 200px; height: 200px">
                                            </div>

                                        </div>
                                    </c:forEach>

                                </div>
                            </div>
                        </div>
                    </section>

                </div>
            </div>
        </div>
    </div>
</section>
<!-- Hero Section End -->



<!-- Footer Section Begin -->
<footer class="footer spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-3 col-md-6 col-sm-6">
                <div class="footer__about">
                    <div class="footer__about__logo">
                        <img src="img/logovico.jpg" alt="" width="200px" height=" 200 px">
                    </div>
                </div>
            </div>
            <div class="col-lg-3 col-md-6 col-sm-6">
                <div class="footer__about">
                    <ul>
                        <li>Address: Ha Noi</li>
                        <li>Phone: 0989.999.999</li>
                        <li>Email: hello@colorlib.com</li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="footer__copyright">
                    <div class="footer__copyright__text">
                        <p>Copyright &copy; 2022 All rights reserved | This template is made by Group 5 - C1121G1 -
                            CodeGym</p>
                    </div>
                    <div class="footer__copyright__payment"><img src="img/payment-item.png" alt=""></div>
                </div>
            </div>
        </div>
    </div>
</footer>
<!-- Footer Section End -->

<!-- Js Plugins -->
<script src="/file/js/jquery-3.3.1.min.js"></script>
<script src="/file/js/bootstrap.min.js"></script>
<script src="/file/js/jquery.nice-select.min.js"></script>
<script src="/file/js/jquery-ui.min.js"></script>
<script src="/file/js/jquery.slicknav.js"></script>
<script src="/file/js/mixitup.min.js"></script>
<script src="/file/js/owl.carousel.min.js"></script>
<script src="/file/js/main.js"></script>


</body>

</html>

