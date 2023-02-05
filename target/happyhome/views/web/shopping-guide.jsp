<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if IE 9 ]><html class="ie ie9" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<!--<![endif]-->
<html lang="en">

<!-- contact11:09-->
<head>
    <!-- Basic Page Needs -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Hướng dẫn đặt hàng </title>

    <meta name="keywords" content="Furniture, Decor, Interior">
    <meta name="description" content="Furnitica - Minimalist Furniture HTML Template">
    <meta name="author" content="tivatheme">


    <!-- Mobile Meta -->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <jsp:include page="/common/web/css.jsp"></jsp:include>
    <style>
        p{
            line-height: 1.2;
        }

        table p{
            padding-top: 19px;
        }
    </style>

</head>

<body id="contact" class="blog">
<header>
    <jsp:include page="/common/web/header.jsp"></jsp:include>
    <!-- header desktop -->
    <div class="header-top d-xs-none ">
        <div class="container">
            <div class="row">
                <!-- logo -->
                <div class="col-sm-2 col-md-2 d-flex align-items-center">
                    <div id="logo">
                        <a href="index-2.html">
                            <img src="img/home/Logo-happyhome-removebg-preview.png" alt="logo" class="img-fluid" style="height: 90px">
                        </a>
                    </div>
                </div>

                <!-- menu -->
                <div class="col-sm-5 col-md-5 align-items-center justify-content-center navbar-expand-md main-menu">
                    <div class="menu navbar collapse navbar-collapse">
                        <ul class="menu-top navbar-nav">
                            <li>
                                <a href="#" class="parent">Sản Phẩm</a>
                                <div class="dropdown-menu">
                                    <ul>
                                        <li class="item living-room-drop">
                                            <a href="living-room.jsp" title="Home Page 2" >Phòng Khách</a>
                                            <div class="dropdown-menu living-room-menu">
                                                <ul>
                                                    <li class="item">
                                                        <a href="#" title="">Bàn</a>
                                                    </li>
                                                    <li class="item">
                                                        <a href="#" title="">Ghế</a>
                                                    </li>
                                                    <li class="item">
                                                        <a href="#" title="">Tủ Kệ</a>
                                                    </li>
                                                    <li class="item">
                                                        <a href="#" title="">Phụ Kiện</a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </li>
                                        <li class="item dinner-room-drop">
                                            <a href="home2.html" title="Home Page 2">Phòng Ăn</a>
                                            <div class="dropdown-menu dinner-room-menu">
                                                <ul>
                                                    <li class="item">
                                                        <a href="#" title="">Bàn</a>
                                                    </li>
                                                    <li class="item">
                                                        <a href="#" title="">Ghế</a>
                                                    </li>
                                                    <li class="item">
                                                        <a href="#" title="">Tủ Kệ</a>
                                                    </li>
                                                    <li class="item">
                                                        <a href="#" title="">Phụ Kiện</a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </li>
                                        <li class="item bedroom-drop">
                                            <a href="home3.html" title="Home Page 3">Phòng Ngủ</a>
                                            <div class="dropdown-menu bedroom-menu">
                                                <ul>
                                                    <li class="item">
                                                        <a href="#" title="">Bàn</a>
                                                    </li>
                                                    <li class="item">
                                                        <a href="#" title="">Ghế</a>
                                                    </li>
                                                    <li class="item">
                                                        <a href="#" title="">Tủ Kệ</a>
                                                    </li>
                                                    <li class="item">
                                                        <a href="#" title="">Phụ Kiện</a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </li>
                                        <li class="item work-room-drop">
                                            <a href="home4.html" title="Home Page 4">Phòng Làm Việc</a>
                                            <div class="dropdown-menu work-room-menu">
                                                <ul>
                                                    <li class="item">
                                                        <a href="#" title="">Bàn</a>
                                                    </li>
                                                    <li class="item">
                                                        <a href="#" title="">Ghế</a>
                                                    </li>
                                                    <li class="item">
                                                        <a href="#" title="">Tủ Kệ</a>
                                                    </li>
                                                    <li class="item">
                                                        <a href="#" title="">Phụ Kiện</a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </li>
                                        <li class="item decorations-drop">
                                            <a href="home5.html" title="Home Page 5">Đồ Trang Trí</a>
                                            <div class="dropdown-menu decorations">
                                                <ul>
                                                    <li class="item">
                                                        <a href="#" title="">Bàn</a>
                                                    </li>
                                                    <li class="item">
                                                        <a href="#" title="">Ghế</a>
                                                    </li>
                                                    <li class="item">
                                                        <a href="#" title="">Tủ Kệ</a>
                                                    </li>
                                                    <li class="item">
                                                        <a href="#" title="">Phụ Kiện</a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                            <li>
                                <a href="#" class="parent">Bộ Sưu Tập</a>
                                <div class="dropdown-menu">
                                    <ul>
                                        <li class="item">
                                            <a href="collection.jsp" title="Blog List (Sidebar Left)">KOLDING Collection</a>
                                        </li>
                                        <li class="item">
                                            <a href="collection.jsp" title="Blog List (Sidebar Left) 2">FIJI Collection</a>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                            <li>
                                <a href="#" class="parent">Dịch Vụ</a>
                                <div class="dropdown-menu service-menu">
                                    <ul>
                                        <li class="item">
                                            <a href="faqs.jsp" title="Blog List (Sidebar Left)"> Các Câu Hỏi Thường Gặp</a>
                                        </li>
                                        <li class="item">
                                            <a href="#" title="Blog List (Sidebar Left) 2"> Hướng Dẫn Đặt Hàng</a>
                                        </li>
                                        <li class="item">
                                            <a href="delivery-policy.jsp" title="Category Blog (Right column)">Chính Sách Vận Chuyển</a>
                                        </li>
                                        <li class="item">
                                            <a href="warranty-policy.jsp" title="Blog List (No Sidebar)">Chính Sách Bảo Mật Thông Tin</a>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                            <!-- <li>
                                 <a href="contact.jsp" class="parent">Ưu Đãi</a>
                             </li>-->
                            <li>
                                <a href="#" class="parent">Bài viết</a>
                                <div class="dropdown-menu">
                                    <ul>
                                        <li class="item">
                                            <a href="blog-list-sidebar-left.jsp" title="Blog List (Sidebar Left)">Tin tức</a>
                                        </li>
                                        <li class="item">
                                            <a href="blog-list-sidebar-left2.html" title="Blog List (Sidebar Left) 2">Khuyến mãi</a>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                            <li>
                                <a href="contact.jsp" class="parent">Liên hệ</a>
                            </li>
                        </ul>
                    </div>
                </div>

                <!-- search and acount -->
                <div class="col-sm-5 col-md-5 d-flex align-items-center justify-content-end" id="search_widget">
                    <form method="get" action="#">

                        <span role="status" aria-live="polite" class="ui-helper-hidden-accessible"></span>
                        <input type="text" name="s" value="" placeholder="Tìm kiếm" class="ui-autocomplete-input" autocomplete="off">
                        <button type="submit">
                            <i class="fa fa-search"></i>
                        </button>
                    </form>

                    <div id="block_myaccount_infos" class="hidden-sm-down dropdown">
                        <div class="myaccount-title ">
                            <a href="#acount" data-toggle="collapse" class="acount">
                                <i class="fa fa-user" aria-hidden="true"></i>
                                <span>Tài khoản</span>
                                <i class="fa fa-angle-down" aria-hidden="true"></i>
                            </a>

                        </div>

                        <div id="acount" class="collapse">

                            <div class="account-list-content">

                                <div>
                                    <a class="login" href="user-acount.jsp" rel="nofollow" title="Log in to your customer account">
                                        <i class="fa fa-cog"></i>
                                        <span>Tài khoản</span>
                                    </a>
                                </div>
                                <div>
                                    <a class="login" href="user-login.jsp" rel="nofollow" title="Log in to your customer account">
                                        <i class="fa fa-sign-in"></i>
                                        <span>Đăng nhập</span>
                                    </a>
                                </div>
                                <div>
                                    <a class="register" href="user-register.jsp" rel="nofollow" title="Register Account">
                                        <i class="fa fa-user"></i>
                                        <span>Đăng kí tài khoản</span>
                                    </a>
                                </div>
                                <div>
                                    <a class="check-out" href="product-checkout.jsp" rel="nofollow" title="Checkout">
                                        <i class="fa fa-check" aria-hidden="true"></i>
                                        <span>Thanh toán</span>
                                    </a>
                                </div>
                                <div class="link_wishlist">
                                    <a href="user-wishlist.jsp" title="My Wishlists">
                                        <i class="fa fa-heart"></i>
                                        <span>Danh sách mong muốn</span>
                                    </a>
                                </div>
                                <!-- <div id="desktop_currency_selector" class="currency-selector groups-selector hidden-sm-down">
                                    <ul class="list-inline">
                                        <li>
                                            <a title="Euro" rel="nofollow" href="#">VNĐ</a>
                                        </li>
                                        <li class="current list-inline-item">
                                            <a title="British Pound Sterling" rel="nofollow" href="#">VNĐ</a>
                                        </li>
                                    </ul>
                                </div>
                                <div id="desktop_language_selector" class="language-selector groups-selector hidden-sm-down">
                                    <ul class="list-inline">
                                        <li class="list-inline-itemcurrent">
                                            <a href="#">
                                                <img class="img-fluid" src="img/home/home1-flas.jpg" alt="English" width="16" height="11">
                                            </a>
                                        </li>
                                        <li class="list-inline-item">
                                            <a href="#">
                                                <img class="img-fluid" src="img/home/home1-flas2.jpg" alt="Italiano" width="16" height="11">
                                            </a>
                                        </li>
                                        <li class="list-inline-item">
                                            <a href="#">
                                                <img class="img-fluid" src="img/home/home1-flas3.jpg" alt="Français" width="16" height="11">
                                            </a>
                                        </li>
                                        <li class="list-inline-item">
                                            <a href="#">
                                                <img class="img-fluid" src="img/home/home1-flas4.jpg" alt="Español" width="16" height="11">
                                            </a>
                                        </li>
                                    </ul>
                                </div> -->
                            </div>
                        </div>
                    </div>
                    <div class="desktop_cart">
                        <div class="blockcart block-cart cart-preview tiva-toggle">
                            <div class="header-cart tiva-toggle-btn">
                                <span class="cart-products-count">1</span>
                                <i class="fa fa-shopping-cart" aria-hidden="true"></i>
                            </div>
                            <div class="dropdown-content">
                                <div class="cart-content">
                                    <table>
                                        <tbody>
                                        <tr>
                                            <td class="product-image">
                                                <a href="product-detail.jsp">
                                                    <img src="img/product/5.jpg" alt="Product">
                                                </a>
                                            </td>
                                            <td>
                                                <div class="product-name">
                                                    <a href="product-detail.jsp">Ghế thư giãn Ball Chair màu đỏ vỏ trắng</a>
                                                </div>
                                                <div>
                                                    x2
                                                    <span class="product-price">15.375.000 vnđ</span>
                                                </div>
                                            </td>
                                            <td class="action">
                                                <a class="remove" href="#">
                                                    <i class="fa fa-trash-o" aria-hidden="true"></i>
                                                </a>
                                            </td>
                                        </tr>
                                        <tr class="total">
                                            <td colspan="2">Tổng :</td>
                                            <td>15.375.000 vn₫</td>
                                        </tr>

                                        <tr>
                                            <td colspan="3" class="d-flex justify-content-center">
                                                <div class="cart-button">
                                                    <a href="product-cart.jsp" title="View Cart">Xem giỏ hàng</a>
                                                    <a href="product-checkout.jsp" title="Checkout">Thanh toán</a>
                                                </div>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>

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
                                <a href="index.html">
                                    <span>Trang chủ</span>
                                </a>
                            </li>
                            <li>
                                <a href="recruit.jsp">
                                    <span>Hướng dẫn đặt hàng </span>
                                </a>
                            </li>
                        </ol>
                    </div>
                </div>
            </nav>
            <div id="main">
                <div class="page-home">
                    <div class="container">
                        <h1 class="text-center title-page">Hướng dẫn đặt hàng </h1>
                        <div class="row-inhert">
                            <p><strong>Bạn có thể lựa chọn một trong những cách mua hàng sau</strong></p>

                                <p><strong>Cách 1:</strong>&nbsp;Gọi điện thoại đến &nbsp;<span style="color: rgb(255, 0, 0); font-family: Arial, Helvetica, sans-serif; font-size: 16px; font-weight: 600; text-align: center;">0909905138&nbsp;</span>hoặc&nbsp;<strong>(</strong><span style="color:#FF0000;"><span style="font-size: 22px;"><span style="color:#FF0000;"><span style="font-size: 22px;"><strong><span style="font-size:18px;">0903 005 138</span></strong></span></span></span></span><strong>) từ thứ 2 đến thứ 7 </strong>&nbsp; nhân viên chúng tôi luôn sẵn sàng phục vụ bạn.</p>

                                <p><strong style="line-height: 1.6;">Cách 2:</strong><span style="line-height: 1.6;">&nbsp;Đặt mua hàng trên website</span></p>

                                <p><strong style="line-height: 1.6;">Bước 1: Tìm sản phẩm cần mua</strong></p>

                                <p>Bạn có thể tìm kiếm sản phẩm theo nhiều cách<br>
                                    <br>
                                    - Click vào Bất kỳ sản phẩm nào bạn thích trên Trang Chủ hoặc Vào chọn sản phẩm trong từng Menu cụ thể của chúng tôi:</p>

                                <p align="center"><img alt="" src="img/other/hg-dan-dat-hang-1.png" style="width: 1028px; height: 567px;"></p>

                                <p>Click vào 1 trong 2 vị trí đánh đấu để vào xem “Chi tiết Sản Phẩm”</p>

                                <p align="center"><img alt="" src="img/other/hg-dan-dat-hang-7.png" style="width: 1028px; height: 567px;"></p>

                                <p><strong>Bước 2: Đặt hàng và thanh toán </strong></p>

                                <p>Sau khi xem thông tin chi tiết sản phẩm, bạn đã ưng ý sản phẩm bạn có thể mua hàng :</p>

                                <ol>
                                    <li value="2"><strong>Thêm vào giỏ (</strong><strong><em>Nếu bạn muốn chọn thêm nhiều sản phẩm khác để mua</em></strong><strong>)</strong></li>
                                </ol>

                                <p><img alt="" src="img/other/hg-dan-dat-hang-2.png" style="width: 1028px; height: 567px;"></p>

                                <p>Hệ thống hiển thị đơn hàng các bạn click nút “<strong><em>Xem giỏ hàng</em></strong>” Website sẽ chuyển sang Bước tiếp theo:</p>

                                <p><img alt="" src="img/other/hg-dan-dat-hang-3.png" style="width: 1028px; height: 567px"></p>

                              <!--  <p>Ở đây nếu có Coupon các bạn vui lòng điền vào ô “<strong><em>Mã giảm giá</em></strong>” như hình và Click vào một trong các lựa chọn:</p>-->

                                <ol>
                                    <li><strong>Đăng nhập</strong> (Nếu bạn đã có tài khoản tại HappyHome)</li>
                                    <p><img alt="" src="img/other/hg-dan-dat-hang-4.png" style="width: 1028px; height: 587px;"></p>
                                    <li><strong>Đăng ký</strong> (Nếu bạn muốn đăng ký mới tài khoản tại HappyHome)</li>
                                    <li><strong>Đặt hàng không cần đăng ký</strong> (Đây là bước đơn giản nhất)</li>
                                </ol>




                                <p><img alt="" src="img/other/hg-dan-dat-hang-8.png" style="width: 1028px; height: 567px;"></p>

                                <p>Bước tiếp theo:</p>

                                <p><img alt="" src="img/other/hg-dan-dat-hang-5.png" style="width: 1028px; height: 634px;"></p>

                                <p>&nbsp;</p>

                                <p style="margin-left:.5in;">Hoàn tất đơn hàng bạn sẽ nhận thông báo:</p>

                                <p>&nbsp;</p>

                                <p><img alt="" src="img/other/hg-dan-dat-hang-6.png" style="width: 1028px; height: 467px;"></p>

                                <p>Sau khi hoàn thành đặt hàng, tối đa 2 giờ nhân viên của chúng tôi sẽ điện thoại xác nhận đơn hàng đồng thời xác nhận lại với bạn thời gian và địa điểm giao hàng. Thời gian giao hàng không quá 24 giờ kể từ khi đơn hàng bạn được xác nhận.</p>

                                <p><strong>Lưu ý</strong></p>

                                <p>1. Chúng tôi chỉ chấp nhận những đơn đặt hàng khi cung cấp đủ thông tin chính xác về địa chỉ, số điện thoại. Sau khi bạn đặt hàng, chúng tôi sẽ liên lạc lại để kiểm tra thông tin và thỏa thuận thêm những điều có liên quan.<br>
                                    <br>
                                    2. Một số trường hợp nhạy cảm: giá trị đơn hàng quá lớn &amp; thời gian giao hàng vào buổi tối địa chỉ giao hàng trong ngõ hoặc có thể dẫn đến nguy hiểm. Chúng tôi sẽ chủ động liên lạc với quý khách để thống nhất lại thời gian giao hàng cụ thể.<br>
                                    <br>
                                    3. Trong trường hợp giao hàng chậm trễ mà không báo trước, quý khách có thể không nhận hàng và chúng tôi sẽ hoàn trả toàn bộ số tiền mà quý khách trả trước (nếu có) trong vòng 7 ngày.<br>
                                    <br>
                                    4. Công ty cam kết tất cả hàng hóa gởi đến quý khách đều là hàng mới 100% (do công ty sản xuất). Những rủi ro phát sinh trong quá trình vận chuyển (va đập, ẩm ướt, tai nạn..) có thể ảnh hưởng đến hàng hóa, vì thế xin Quý Khách vui lòng kiểm tra hàng hóa thật kỹ trước khi ký nhận. Chúng tôi sẽ không chịu trách nhiệm với những sai lệch hình thức của hàng hoá sau khi Quý khách đã ký nhận hàng.</p>

                                <p>&nbsp;</p>

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


<!-- contact11:10-->
</html>