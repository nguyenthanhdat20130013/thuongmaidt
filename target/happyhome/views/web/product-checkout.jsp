<%@ page import="model.UserModel" %>
<% UserModel user = (UserModel)request.getAttribute("user"); %>
<%@ page import="model.Product" %>
<%@ page import="java.util.Collection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="cart" class="beans.Cart" scope="session"/>

<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if IE 9 ]><html class="ie ie9" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<!--<![endif]-->
<html lang="en">


<!-- product-checkout07:12-->
<head>
    <!-- Basic Page Needs -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Thanh toán</title>

    <meta name="keywords" content="Furniture, Decor, Interior">
    <meta name="description" content="Furnitica - Minimalist Furniture HTML Template">
    <meta name="author" content="tivatheme">

    <!-- Mobile Meta -->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <jsp:include page="/common/web/css.jsp"></jsp:include>

    <style>

        body {
            font-size: 10.5pt !important;
        }

        .step-active {
            display: block !important;
        }

        .check-info .content {
            display: none;
        }

        .ty-payments-list .list_checkbox {
            float: left;
            margin-top: 5px;
        }

        .ty-payments-list__item-group {
            padding-left: 20px;
        }

        .ty-payments-list__item {
            padding-bottom: 10px;
        }

        .clearfix .continue {
            margin-top: 15px !important;
            margin-bottom: 25px !important;
        }

        .content {
            width: 98% !important;
        }

        .cart-summary a {
            color: #fff;
        }

    </style>

</head>

<body class="product-checkout checkout-cart">
<jsp:include page="/common/web/header.jsp"></jsp:include>

<!-- main content -->
<div id="checkout" class="main-content">
    <div class="wrap-banner">
        <!-- breadcrumb -->
        <nav class="breadcrumb-bg">
            <div class="container no-index">
                <div class="breadcrumb">
                    <ol>
                        <li>
                            <a href="#">
                                <span>Trang chủ</span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <span>Thanh toán</span>
                            </a>
                        </li>
                    </ol>
                </div>
            </div>
        </nav>

        <!-- main -->
        <div id="wrapper-site">
            <div class="container">
                <div class="row">
                    <div id="content-wrapper" class="col-xs-12 col-sm-12 col-md-12 col-lg-12 onecol">
                        <div id="main">
                            <div class="cart-grid row">
                                <div class="col-md-8 check-info">
                                    <div class="checkout-personal-step ">
                                        <h3 class="step-title h3 info" id="step-title-1">
                                            <span class="step-number">1</span>THÔNG TIN CÁ NHÂN
                                        </h3>
                                    </div>
                                    <div class="content step-active" id="step1">
                                        <ul class="nav nav-inline">
                                            <li class="nav-item">
                                                <a class="nav-link active" data-toggle="tab"
                                                   href="#checkout-guest-form">
                                                    THÔNG TIN ĐẶT HÀNG
                                                </a>
                                            </li>
                                        </ul>
                                        <div class="tab-content">
                                            <div class="tab-pane fade in active show" id="checkout-guest-form"
                                                 role="tabpanel">
                                                <form action="#" id="customer-form" class="js-customer-form"
                                                      method="post">
                                                    <div>
                                                        <input type="hidden" name="id_customer" value="">
                                                        <div class="form-group row">
                                                            <input class="form-control" name="firstname" type="text"
                                                                   placeholder="Họ và tên : <%=user.getUserName()%>" disabled="disabled">
                                                        </div>
                                                        <div class="form-group row">
                                                            <input class="form-control" name="email" type="email"
                                                                   placeholder="Email : <%=user.getEmail()%>" disabled="disabled">
                                                        </div>
                                                        <div class="form-group row">
                                                            <input class="form-control" name="email" type="email"
                                                                   placeholder="Điện thoại : <%=user.getPhoneNum()%>" >
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="checkout-personal-step">
                                        <h3 class="step-title h3 " id="step-title-2">
                                            <span class="step-number">2</span>Địa chỉ
                                        </h3>
                                    </div>
                                    <div class="content step-active" id="step2">
                                        <ul class="nav nav-inline">
                                            <li class="nav-item">
                                                <a class="nav-link active" data-toggle="tab" href="#">
                                                    Địa chỉ hoá đơn
                                                </a>
                                            </li>
                                        </ul>
                                        <div class="tab-content">
                                            <div class="tab-pane fade in active show" role="tabpanel">
                                                <form action="#" class="js-customer-form" method="post">
                                                    <div>
                                                        <div class="form-group row">
                                                            <input class="form-control" name="adress" type="text"
                                                                   placeholder="<%=user.getAddress()%>">
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                            <!--   data-link-action="sign-in" type="submit"-->
                                        </div>
                                    </div>
                                    <div class="checkout-personal-step">
                                        <h3 class="step-title h3" id="step-title-3">
                                            <span class="step-number">3</span>Thanh toán
                                        </h3>
                                    </div>
                                    <div class="content step-active" id="step3">
                                        <div class="tab-content">
                                            <div class="tab-pane fade in active show" role="tabpanel">
                                                <div class="ty-other-pay clearfix">
                                                    <select name="thanhtoan" class="ty-payments-list">
                                                        <option value="Giao hàng thu tiền tại nhà">Giao hàng thu tiền tận nhà</option>
                                                        <option value="Nhận hàng tại cửa hàng">Nhận hàng tại cửa hàng</option>
                                                        <option value="Thanh toán qua ngân hàng">Thanh toán qua ngân hàng</option>


                                                    </select>
                                                    <div>CHÍNH SÁCH THANH TOÁN</div>
                                                    <ul class="ty-payments-list">
                                                        <li class="ty-payments-list__item">
                                                            <div class="ty-payments-list__item-group">
                                                                <label
                                                                       class="ty-payments-list__item-title">
                                                                    Giao hàng thu tiền tận nhà
                                                                </label>
                                                                <div class="ty-payments-list__description">
                                                                    Miễn phí các quận nội thành HCM ( Theo quy định của
                                                                    HappyHome... vui lòng xem chi tiết chính sách vận
                                                                    chuyển)
                                                                </div>
                                                            </div>
                                                        </li>
                                                        <div>
                                                        </div>
                                                        <li class="ty-payments-list__item">

                                                            <div class="ty-payments-list__item-group">
                                                                <label
                                                                       class="ty-payments-list__item-title">
                                                                    Nhận hàng tại cửa hàng
                                                                </label>
                                                                <div class="ty-payments-list__description">
                                                                    Giảm ngay 50.000đ nếu khách hàng nhận hàng tại
                                                                    HappyHome ( Áp dụng sản phẩm từ : 1 triệu)
                                                                </div>
                                                            </div>
                                                        </li>
                                                        <li class="ty-payments-list__item">

                                                            <div class="ty-payments-list__item-group">
                                                                <label
                                                                       class="ty-payments-list__item-title">
                                                                    Thanh toán qua ngân hàng
                                                                </label>
                                                                <div class="ty-payments-list__description">
                                                                    Khách hàng chuyển khoản thanh toán vô các tài khoản
                                                                    của HappyHome
                                                                </div>
                                                            </div>
                                                        </li>
                                                    </ul>
                                                    <div class="ty-payments-list__instruction">
                                                        <p>Giao hàng <strong>từ thứ 2 đến thứ 7 ( Chủ nhật không làm
                                                            việc)</strong></p>
                                                        <p>Thời gian giao hàng từ <strong>8h --> 19h</strong></p>
                                                    </div>
                                                </div>
                                                <form action="#" class="js-customer-form" method="post">
                                                    <div>
                                                        <div class="form-group row">
                                                            <textarea class="form-control"
                                                                      placeholder="Để lại lời nhắn cho chúng tôi"
                                                                      rows="3"></textarea>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                            <div class="clearfix">
                                                <div class="row">
                                                    <a href="/order_success"
                                                       class="continue btn btn-primary pull-xs-right" value="3"
                                                       style="margin-top: 15px;margin-bottom: 25px">Hoàn tất đặt
                                                        hàng</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="cart-grid-right col-xs-12 col-lg-4">
                                    <div class="cart-summary">
                                        <div class="cart-detailed-totals">
                                            <div class="cart-summary-products">
                                                <div class="summary-label">Có ${cart.quantity} sản phẩm trong giỏ hàng của bạn</div>
                                            </div>
                                            <div class="cart-summary-line" id="cart-subtotal-products">
                                                    <span class="label js-subtotal">
                                                        Tổng Sản phẩm:
                                                    </span>
                                                <span class="value">${cart.total} vnđ</span>
                                            </div>
                                            <div class="cart-summary-line" id="cart-subtotal-shipping">
                                                    <span class="label">
                                                        Tổng phí chuyển hàng:
                                                    </span>
                                                <span class="value">Miễn phí</span>
                                                <div>
                                                    <small class="value"></small>
                                                </div>
                                            </div>
                                            <div class="cart-summary-line cart-total">
                                                <span class="label">Tổng:</span>
                                                <span class="value">${cart.total} vnđ (bao gồm thuế.)</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="cart-summary" style="margin-top: 30px">
                                        <div class="cart-detailed-totals">
                                            <div class="cart-summary-products">
                                                <div class="summary-label">Sản phẩm trong giỏ hàng của bạn :</div>
                                            </div>
                                            <%  Collection<Product> list = cart.getListProduct();
                                                for (Product p: list) {%>
                                            <div class="cart-summary-line" id="cart-products">
                                                    <span class="label js-subtotal">
                                                      <a href=""><%=p.getName()%></a>
                                                    </span>
                                                <span class="value"><%=p.getQuantity()%> x <%=p.getPrice_sell()%> vnđ</span>
                                            </div>
                                            <%}%>
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
</div>
<!-- footer -->
<jsp:include page="/common/web/footer.jsp"></jsp:include>
<!-- Vendor JS -->
<jsp:include page="/common/web/js.jsp"></jsp:include>
</body>
</html>