<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String error = (String) request.getAttribute("error");
%>
<html>
<!-- user-login11:10-->
<head>
    <!-- Basic Page Needs -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Đăng nhập</title>

    <meta name="keywords" content="Furniture, Decor, Interior">
    <meta name="description" content="Furnitica - Minimalist Furniture HTML Template">
    <meta name="author" content="tivatheme">

    <!-- Mobile Meta -->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <jsp:include page="/common/web/css.jsp"></jsp:include>
    <style>
        .error{
            float: left !important;
            color:#dc3545 !important;
        }
    </style>
</head>

<body class="user-login blog">
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
                            <a href="user-login.jsp">
                                <span>Đăng nhập</span>
                            </a>
                        </li>
                    </ol>
                </div>
            </div>
        </nav>

    </div>

    <!-- main -->
    <div id="wrapper-site">
        <div id="content-wrapper" class="full-width">
            <div id="main">
                <div class="container">
                    <div class="login-form">
                        <form id="customer-form" action="<c:url value="/login"/>" method="post">
                            <h1 class="text-center title-page">Đăng Nhập</h1>
                            <% if(error != null){%>
                            <div style="color:#dc3545;"><%=error%></div>
                            <%}%>
                            <div style="display: flex;flex-direction: column;">
                                <div class="form-group no-gutters">
                                    <input class="form-control" name="username" id="username" type="text" value="<%=request.getParameter("username")!=null?request.getParameter("username"):"" %>"  placeholder="Tài khoản" >
                                    <label id="username-error" class="error" for="username" style="display: inline;"></label>
                                </div>
                                <div class="form-group no-gutters">
                                    <input class="form-control js-child-focus js-visible-password" name="password" id="password" type="password" value="" placeholder="Mật khẩu">
                                    <label id="password-error" class="error" for="password" style="display: inline;"></label>
                                </div>
                                <div class="no-gutters text-center">
                                    <div class="forgot-password">
                                        <a href="<c:url value="/reset-password"></c:url>">
                                            Quên mật khẩu?
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <div class="clearfix">
                                <div class="text-center no-gutters">
                                    <button class="btn btn-primary" data-link-action="sign-in" type="submit">
                                        ĐĂNG NHẬP
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<jsp:include page="/common/web/footer.jsp"></jsp:include>

<jsp:include page="/common/web/js.jsp"></jsp:include>
<script src="<c:url value="/Template/web/libs/jquery/jquery.validate.js"/>"></script>
<script>
    $.validator.setDefaults({
        errorElement: "label",
        errorClass: "error"
    });

    +(function ($) {
        $("#customer-form").validate({
            rules: {
                username: {
                    required : true
                },
                password: {
                    required: true
                }
            },
            messages: {
                username: {
                    required : "Phải nhập tên tài khoản",
                },
                password: {
                    required: "Phải nhập mật khẩu",
                }
            }
        });
    })(jQuery);
</script>
</body>


<!-- user-login11:10-->
</html>