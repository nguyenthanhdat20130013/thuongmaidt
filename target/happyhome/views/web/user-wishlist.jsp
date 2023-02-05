<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if IE 9 ]><html class="ie ie9" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<!--<![endif]-->
<html lang="en">


<!-- user-wishlist11:10-->
<head>
    <!-- Basic Page Needs -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Dach sách mong muốn</title>

    <meta name="keywords" content="Furniture, Decor, Interior">
    <meta name="description" content="Furnitica - Minimalist Furniture HTML Template">
    <meta name="author" content="tivatheme">

    <!-- Mobile Meta -->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <jsp:include page="/common/web/css.jsp"></jsp:include>

    <style>
        .wishlist_delete a{
            background: #343434;
            color: #ffff;
        }

        .menu-top a{
            height: 60px;
        }

    </style>
</head>

<body class="user-wishlist blog">
    <jsp:include page="/common/web/header.jsp"></jsp:include>

    <!-- main content -->
    <div class="main-content">
        <div class="wrap-banner">
            <!-- breadcrumb -->
            <nav class="breadcrumb-bg">
                <div class="container no-index">
                    <div class="breadcrumb">
                        <ol>
                            <li>
                                <a href="index-2.html">
                                    <span>Trang chủ</span>
                                </a>
                            </li>
                            <li>
                                <a href="user-wishlist.jsp">
                                    <span>Sản phẩm mong muốn</span>
                                </a>
                            </li>
                        </ol>
                    </div>
                </div>
            </nav>
        </div>

        <!-- main -->
        <div id="wrapper-site">
            <div class="container">
                <div class="row">
                    <div id="content-wrapper" class="col-xs-12 col-sm-12 col-md-12 col-lg-12 onecol">
                        <div id="main">
                            <div id="content" class="page-content">
                                <div id="mywishlist">
                                    <h1 class="title-page">Danh sách  sản phẩm</h1>
                                    <div id="block-history" class="block-center">
                                        <table class="std table">
                                            <thead>
                                                <tr>
                                                    <th class="first_item">Tên sản phẩm</th>
                                                    <th class="item mywishlist_second">Ngày tạo</th>
                                                    <th class="last_item mywishlist_first">Tác vụ</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr id="wishlist_1">
                                                    <td>
                                                        Bàn sofa mặt đá hiện đại, sang trọng TT68185
                                                    </td>
                                                    <td>10/11/2022</td>
                                                    <td class="wishlist_delete">
                                                        <a href="product-detail.jsp" class="btn btn-default" >Xem</a>
                                                        <a href="javascript:;"  class="btn btn-default" onclick="return (WishlistDelete('wishlist_1', '1', 'Do you really want to delete this wishlist ?'));">Xoá</a>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="page-home">
                                        <a class="btn btn-default" href="table.jsp">
                                            <i class="fa fa-shopping-basket" aria-hidden="true"></i>
                                            <span>Tiếp tục mua hàng</span>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="/common/web/footer.jsp"></jsp:include>

    <!-- Vendor JS -->
    <jsp:include page="/common/web/js.jsp"></jsp:include>
</body>
</html>