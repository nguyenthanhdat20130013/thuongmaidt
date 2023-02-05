<%@ page import="model.UserModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<% UserModel user = (UserModel)session.getAttribute("user"); %>
<%@ page import="model.Product_type" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Product" %>
<%@ page import="beans.Cart" %>
<%@ page import="java.util.Collection" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="cart" class="beans.Cart" scope="session"/>
<style>
    .drop-menu {
        width: 235px;
        margin-left: 0px;!important;

    }

    .header-top .navbar-nav .bedroom-menu{
        width: 220px !important;

    }

    .fa-chevron-down{
        font-size: 11px;
        padding-left: 2px;
    }

    .fa-chevron-right{
        font-size: 11px;
        padding-left: 4px;
    }
    .main-menu .menu .menu-top li{
        padding-right: 10px!important;
    }
    [class~=main-menu] [class~=menu] [class~=menu-top] li {
        padding-right: 10px!important;
    }

</style>
<header>

    <!-- header desktop -->
    <div class="header-top d-xs-none ">
        <div class="container">
            <div class="row">
                <!-- logo -->
                <div class="col-sm-2 col-md-2 d-flex align-items-center">
                    <div id="logo">
                        <a href="<c:url value="/home"/>">
                        <img src="<c:url value="/Template/web/img/home/Logo-happyhome-removebg-preview.png"/>" alt="logo" class="img-fluid" style="height: 90px">
                        </a>
                    </div>
                </div>

                <!-- menu -->
                <div class="col-sm-5 col-md-5 align-items-center justify-content-center navbar-expand-md main-menu">
                    <div class="menu navbar collapse navbar-collapse">
                        <ul class="menu-top navbar-nav">
                            <li>
                                <a href="<c:url value="/list_product"/>" class="parent">Sản Phẩm<i class="fa fa-chevron-down" aria-hidden="true"></i></a>
                                <div class="dropdown-menu">
                                    <ul>
                                        <% List<Product_type> list0 = (List<Product_type>) request.getAttribute("listType");
                                            for (Product_type pty: list0) {%>
                                        <li class="item living-room-drop">
                                            <a href="productCate?cid=<%=pty.getType_id()%>" title="<%=pty.getType_name()%>"> <%=pty.getType_name()%> </i>
                                            </a>
                                        </li>
                                        <%}%>
                                    </ul>
                                </div>
                            </li>
                            <li>
                                <a href="" class="parent">Bộ Sưu Tập <i class="fa fa-chevron-down" aria-hidden="true"></i></a>
                                <div class="dropdown-menu">
                                    <ul>
                                        <li class="item">
                                            <a href="" title="Blog List (Sidebar Left)">KOLDING Collection</a>
                                        </li>
                                        <li class="item">
                                            <a href="" title="Blog List (Sidebar Left) 2">FIJI Collection</a>
                                        </li>
                                    </ul>
                                </div>
                            </li>

                            <li>
                                <a href="#" class="parent">Dịch Vụ <i class="fa fa-chevron-down" aria-hidden="true"></i></a>
                                <div class="dropdown-menu service-menu">
                                    <ul>
                                        <li class="item">
                                            <a href="" title="Blog List (Sidebar Left)"> Các Câu Hỏi Thường Gặp</a>
                                        </li>
                                        <li class="item">
                                            <a href="" title="Blog List (Sidebar Left) 2"> Hướng Dẫn Đặt Hàng</a>
                                        </li>
                                        <li class="item">
                                            <a href="" title="Category Blog (Right column)">Chính Sách Vận Chuyển</a>
                                        </li>
                                        <li class="item">
                                            <a href="" title="Blog List (No Sidebar)">Chính Sách Bảo Mật Thông Tin</a>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                            <!-- <li>
                                 <a href="~/Template/contact.jsp" class="parent">Ưu Đãi</a>
                             </li>-->
                            <li>
                                <a href="<c:url value="/list_article"/>" class="parent">Bài viết <i class="fa fa-chevron-down" aria-hidden="true"></i></a>
                                <div class="dropdown-menu">
                                    <ul>
                                        <c:forEach items="${listAr}" var="item">
                                        <li class="item">
                                            <a href="articleCate?cid=${item.getArticle_category_id()}" title="Blog List (Sidebar Left)"> ${item.getArticle_category_name()}<br></a>
                                        </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </li>
                            <li>
                                <a href="" class="parent">Liên hệ</a>
                            </li>
                        </ul>
                    </div>
                </div>

                <!-- search and acount -->
                <div class="col-sm-5 col-md-5 d-flex align-items-center justify-content-end" id="search_widget">
                    <form method="get" action="/search">

                        <span role="status" aria-live="polite" class="ui-helper-hidden-accessible"></span>
                        <input type="text" name="search" value="" required placeholder="Tìm kiếm" class="ui-autocomplete-input" autocomplete="off" >
                        <button type="submit">
                            <i class="fa fa-search"></i>
                        </button>
                    </form>

                    <div id="block_myaccount_infos" class="hidden-sm-down dropdown">
                        <div class="myaccount-title ">
                            <a href="#acount" data-toggle="collapse" class="acount">
                                <i class="fa fa-user" aria-hidden="true"></i>
                                <% if(user != null) { %>
                                <span><%=user.getUserName()%></span>
                                <% } else {%>
                                <span>Tài khoản</span>
                                <% } %>
                                <i class="fa fa-angle-down" aria-hidden="true"></i>
                            </a>
                        </div>

                        <div id="acount" class="collapse" style="left: -120px;top: 36px;">

                            <div class="account-list-content">
                                <% if(user != null) { %>
                                <div>
                                    <a class="login" href="<c:url value="/account"></c:url>" rel="nofollow" title="Your info account">
                                        <i class="fa fa-cog"></i>
                                        <span>Thông tin Tài khoản </span>
                                    </a>
                                </div>
                                <% } %>
                                <% if(user == null) {%>
                                <div>
                                    <a class="login" href="<c:url value="/login"></c:url>" rel="nofollow" title="Log in to your customer account">
                                        <i class="fa fa-sign-in"></i>
                                        <span>Đăng nhập</span>
                                    </a>
                                </div>
                                <div>
                                    <a class="register" href="<c:url value="/register"></c:url>" rel="nofollow" title="Register Account">
                                        <i class="fa fa-user"></i>
                                        <span>Đăng kí tài khoản</span>
                                    </a>
                                </div>
                                <% } %>
                                <div>
                                    <a class="check-out" href="product-checkout.html" rel="nofollow" title="Checkout">
                                        <i class="fa fa-check" aria-hidden="true"></i>
                                        <span>Thanh toán</span>
                                    </a>
                                </div>
                                <div class="link_wishlist">
                                    <a href="user-wishlist.html" title="My Wishlists">
                                        <i class="fa fa-heart"></i>
                                        <span>Danh sách mong muốn</span>
                                    </a>
                                </div>

                                <% if(user != null) {%>
                                <div class="link_wishlist">
                                    <a href="<c:url value="/logout"></c:url>" title="Logout">
                                        <i class="fa fa-sign-out"></i>
                                        <span>Đăng xuất</span>
                                    </a>
                                </div>
                                <% } %>
                            </div>
                        </div>
                    </div>
                    <div class="desktop_cart">
                        <div class="blockcart block-cart cart-preview tiva-toggle">
                            <div class="header-cart tiva-toggle-btn">
                                <span class="cart-products-count">${cart.quantity}</span>
                                <i class="fa fa-shopping-cart" aria-hidden="true"></i>
                            </div>
                            <div class="dropdown-content">
                                <div class="cart-content">
                                    <table>
                                        <tbody>
                                        <%  Collection<Product> list = cart.getListProduct();
                                            for (Product p: list) {%>
                                        <tr>
                                            <td class="product-image">
                                                <a href="product-detail.html">
                                                    <img src="<%=p.getImage(0)%>" alt="Product">
                                                </a>
                                            </td>
                                            <td>
                                                <div class="product-name">
                                                    <a href="product-detail.html"><%=p.name%></a>
                                                </div>
                                                <div>
                                                    <span class="product-price"><%=p.quantity%> * <%=p.price_sell%> vnđ</span>
                                                </div>
                                            </td>
                                            <td class="action">
                                                <a class="remove" href="#">
                                                    <i class="fa fa-trash-o" aria-hidden="true"></i>
                                                </a>
                                            </td>
                                        </tr>
                                        <%}%>
                                        <tr class="total">
                                            <td colspan="2">Tổng :</td>
                                            <td>${cart.total} vnđ</td>
                                        </tr>

                                        <tr>
                                            <td colspan="3" class="d-flex justify-content-center">
                                                <div class="cart-button">
                                                    <a href="/cart_detail" title="View Cart">Xem giỏ hàng</a>
                                                    <a href="/checkout" title="Checkout">Thanh toán</a>
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
