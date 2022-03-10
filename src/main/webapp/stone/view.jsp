<%--
  Created by IntelliJ IDEA.
  User: This
  Date: 3/7/2022
  Time: 6:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <%--    <meta charset="UTF-8">--%>
    <%--    <meta name="description" content="Ogani Template">--%>
    <%--    <meta name="keywords" content="Ogani, unica, creative, html">--%>
    <%--    <meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
    <%--    <meta http-equiv="X-UA-Compatible" content="ie=edge">--%>
    <%--    <title>Ogani | Template</title>--%>
    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">
    <!-- Css Styles -->
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="css/style.css" type="text/css">

    <title>Title</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
          integrity="sha512-9usAa10IRO0HhonpyAIVpjrylPvoDwiPUiKdWk5t3PyolY1cOd4DSE0Ga+ri4AuTroPR5aQvXU9xC6qOPnzFeg=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1 style="text-align: center ">${stone.name}</h1>

    <table class="table">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Name</th>
            <th scope="col">Price</th>
            <th scope="col">Description</th>
            <th scope="col">Category</th>
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${stone.id}</td>
            <td>${stone.name}</td>
            <td>${stone.price}</td>
            <td>${stone.description}</td>
            <td>${stone.category_id}</td>
            <td><a href="/stones?action=edit&id=${stone.id}" class="btn btn-primary"><i class="fas fa-edit"></i></a>
            </td>
            <td><a href="/stones?action=delete&id=${stone.id}" class="btn btn-danger"><i class="fas fa-trash"></i></a>
            </td>
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
                             style="width: 250px; height: 250px">
                        </div>
                    </div>

                    <c:forEach var="image" items="${images}">
                        <div class="col-lg-3">
                            <div class="categories__item set-bg" data-setbg="${image.link}"
                                 style="width: 250px; height: 250px">
                            </div>

                        </div>
                    </c:forEach>

                </div>
            </div>
        </div>
    </section>

</div>
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.nice-select.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/jquery.slicknav.js"></script>
<script src="js/mixitup.min.js"></script>
<script src="js/owl.carousel.min.js"></script>
<script src="js/main.js"></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>
