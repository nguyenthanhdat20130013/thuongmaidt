<%@ page import="model.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Post" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zxx">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Trang chủ</title>
    <link rel="icon" type="image/x-icon" href="/Template/web/img/home/Logo-happyhome-removebg-preview.png">
    <meta name="keywords" content="Furniture, Decor, Interior">
    <meta name="description" content="Furnitica - Minimalist Furniture HTML Template">
    <meta name="author" content="tivatheme">
    <jsp:include page="/common/web/css.jsp"></jsp:include>

</head>

<body id="home">
<jsp:include page="/common/web/header.jsp"></jsp:include>

<!-- main content -->
<div class="main-content">
    <div class="wrap-banner">
        <!-- slide show -->
        <div class="section banner">
            <div class="tiva-slideshow-wrapper">
                <div id="tiva-slideshow" class="nivoSlider">
                    <a href="#">
                        <img class="img-responsive" src="<c:url value="/Template/web/img/home/home1-banner1.jpg"/>" title="#caption1" alt="Slideshow image">
                    </a>
                    <a href="#">
                        <img class="img-responsive" src="<c:url value="/Template/web/img/home/home1-banner2.jpg"/>" title="#caption2" alt="Slideshow image">
                    </a>
                    <a href="#">
                        <img class="img-responsive" src="<c:url value="/Template/web/img/home/home1-banner3.jpg"/>" title="#caption3" alt="Slideshow image">
                    </a>
                </div>
            </div>
        </div>
    </div>

    <!-- main -->
    <div id="wrapper-site">
        <div id="content-wrapper" class="full-width">
            <div id="main">
                <section class="page-home">
                    <!-- product living room -->
                    <div class="container">

                        <!-- best seller -->


                        <!-- banner -->
                        <div class="container" style="margin-top: 140px">
                            <div class="title-block" style="padding-bottom: 150px;margin-top: -100px">Bộ sưu tập nổi bật</div>
                            <div class="section spacing-10 group-image-special col-lg-12 col-xs-12">
                                <div class="row">
                                    <div class="col-lg-6 col-md-6">
                                        <div class="effect">
                                            <a href="#">
                                                <img class="img-fluid" src="<c:url value="/Template/web/img/home/effect3.jpg"/>" alt="banner-1" title="banner-1">
                                            </a>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 col-md-6">
                                        <div class="effect">
                                            <a href="#">
                                                <img class="img-fluid" src="<c:url value="/Template/web/img/home/effect4.jpg"/>" alt="banner-2" title="banner-2">
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <br><br><br><br>
                            <div class="section spacing-10 group-image-special col-lg-12 col-xs-12">
                                <div class="row">
                                    <div class="col-lg-6 col-md-6">
                                        <div class="effect">
                                            <a href="#">
                                                <img class="img-fluid" src="<c:url value="/Template/web/img/home/effect1.jpg"/>" alt="banner-1" title="banner-1">
                                            </a>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 col-md-6">
                                        <div class="effect">
                                            <a href="#">
                                                <img class="img-fluid" src="<c:url value="/Template/web/img/home/effect2.jpg"/>" alt="banner-2" title="banner-2">
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- testimonial -->
                        </div>

                        <!-- recent post -->
                        <div class="policy-home" >
                            <div class="container">
                                <div class="row">
                                    <div class="col-lg-4 col-md-4">
                                        <div class="block">
                                            <div class="block-content" style="">
                                                <div class="policy-item">
                                                    <div class="policy-content iconpolicy1">
                                                        <img src="<c:url value="/Template/web/img/home/policy-index-removebg.png"/>" alt="img">
                                                        <div class="description">
                                                            <div class="policy-name mb-5">Giao hàng & lắp đặt</div>
                                                            <div class="policy-des">Miễn phí</div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="tiva-html col-lg-4 col-md-4">
                                        <div class="block">
                                            <div class="block-content">
                                                <div class="policy-item">
                                                    <div class="policy-content iconpolicy2">
                                                        <img src="<c:url value="/Template/web/img/home/1on1-removebg.png"/>" alt="img">
                                                        <div class="description">
                                                            <div class="policy-name mb-5">Đổi trả 1 - 1</div>
                                                            <div class="policy-des">Miễn phí</div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="tiva-html col-lg-4 col-md-4">
                                        <div class="block">
                                            <div class="block-content">
                                                <div class="policy-item">
                                                    <div class="policy-content iconpolicy3" style="text-align: center">
                                                        <img src="<c:url value="/Template/web/img/home/warranty-removebg.png"/>" alt="img">
                                                        <div class="description">
                                                            <div class="policy-name mb-5">1 năm bảo hành</div>
                                                            <div class="policy-des">Miễn phí</div>
                                                        </div>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </div>
    </div>
</div>

<!-- footer -->



<jsp:include page="/common/web/footer.jsp"></jsp:include>
<jsp:include page="/common/web/js.jsp"></jsp:include>
</body>
</html>
