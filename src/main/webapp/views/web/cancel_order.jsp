<%@ page import="model.Introduce" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Introduce intro = (Introduce) request.getAttribute("info");%>
<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if IE 9 ]><html class="ie ie9" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<!--<![endif]-->
<html lang="en">
<head>
    <!-- Basic Page Needs -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Huỷ Đơn Hàng</title>
    <link rel="icon" type="image/x-icon" href="/Template/web/img/home/Logo-happyhome-removebg-preview.png">
    <meta name="keywords" content="Furniture, Decor, Interior">
    <meta name="description" content="Furnitica - Minimalist Furniture HTML Template">
    <meta name="author" content="tivatheme">

    <!-- Mobile Meta -->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <jsp:include page="/common/web/css.jsp"></jsp:include>
</head>

<body id="contact" class="blog">
<jsp:include page="/common/web/header.jsp"></jsp:include>

<!-- main content -->
<div class="main-content">
    <div id="wrapper-site">
        <div id="content-wrapper">

            <!-- breadcrumb -->
            <nav class="breadcrumb-bg">
                <div class="container no-index">
                    <div class="breadcrumb">
                        <ol>
                            <li>
                                <a href="home">
                                    <span>Trang chủ</span>
                                </a>
                            </li>
                            <li>
                                <a href="contact">
                                    <span>Huỷ đơn hàng</span>
                                </a>
                            </li>
                        </ol>
                    </div>
                </div>
            </nav>
            <div id="main">
                <div class="page-home">
                    <div class="container">
                        <h1 class="text-center title-page">Xác nhận hủy đơn hàng</h1>
                        <h1 class="text-center title-page" style="font-size: 18px;">Chỉ có thể hủy đơn hàng trước khi đơn hàng được xác nhận!</h1>
                        <div class="row-inhert">
                            <div class="input-contact">
                                <div class="d-flex justify-content-center">
                                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                                        <form action="/cancel_order" method="post">
                                            <div class="form-group">
                                                <label for="order_id_display">Mã Đơn Hàng:</label>
                                                <% int orderId = (Integer) request.getAttribute("orderId"); %>
                                                <input type="text" id="order_id_display" class="form-control" value="<%= orderId %>" disabled>
                                                <input type="hidden" id="order_id" name="orderId" value="<%= orderId %>">
                                            </div>
                                            <div class="form-group" style="text-transform: none;">
                                                <label for="reason">Lý Do Hủy Đơn:</label>
                                                <select id="reason" name="reason" class="form-control" required style="font-size: 15px; text-transform: none;">
                                                    <option value="Thay đổi ý định mua hàng">Thay đổi ý định mua hàng</option>
                                                    <option value="Tìm thấy giá rẻ hơn">Tìm thấy giá rẻ hơn</option>
                                                    <option value="Thời gian giao hàng quá lâu">Thời gian giao hàng quá lâu</option>
                                                    <option value="Lý do khác">Lý do khác</option>
                                                </select>
                                            </div>
                                            <div class="form-group text-center">
                                                <button type="reset" class="btn btn-secondary" style="font-size: 14px; background: green; font-weight: bold;">Hủy</button>
                                                <button type="submit" class="btn btn-primary" style="font-size: 14px; background: red; font-weight: bold;">Xác Nhận</button>
                                            </div>
                                        </form>
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
<!-- contact11:10-->
</html>