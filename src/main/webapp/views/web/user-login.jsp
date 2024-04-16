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
    <link rel="icon" type="image/x-icon" href="/Template/web/img/home/Logo-happyhome-removebg-preview.png">
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
                            <a href="home">
                                <span>Trang chủ</span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
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
                            <div class="social-auth-links text-center mb-3">
                                <p>- Hoặc đăng nhập bằng -</p>
                                <a href="#" id="fbLoginBtn" class="btn btn-block " style="background-color: #4267B2;color: #ffffff;border-radius: 1.25rem;width: 240px;margin-left: auto;margin-right: auto;margin-bottom: 24px;">
                                    <i class="fab fa-facebook mr-2"></i> Đăng nhập bằng  Facebook
                                </a>
                              <%--  <a href="https://accounts.google.com/gsi/select?client_id=249998432598-rnscpsfgi8fvqnhqan6q8hv7vidpcmla.apps.googleusercontent.com&ux_mode=popup&ui_mode=card&as=HSExaEn25%2BCHLkOFN9%2BrsQ&channel_id=60793b2ca73ceb762bc65f8926883c31784b052018680741fab2f40c7c1ac3f9&origin=http%3A%2F%2Flocalhost%3A8080" class="btn btn-block btn-default">
                                    <i class="fab fa-google-plus mr-2"></i> Đăng nhập bằng  Google+
                                </a>
                                <a href="" class="btn btn-block btn-default">
                                    <i class="fab fa-google-plus mr-2"></i> Đăng nhập bằng  Google+
                                </a>--%>

                                <%--<div style="width: 50px;height: 50px">
                                    <fb:login-button
                                        scope="public_profile,email"
                                        onlogin="checkLoginState();">
                                </fb:login-button></div>--%>
                                <div id="google-btn" style="width:  240px;margin-left: auto;margin-right: auto"></div>
                                <div id="g_id_onload"
                                     data-client_id="134024970920-c7aegb1s5oofkrgv064q1ru8402qdeeo.apps.googleusercontent.com"
                                     data-callback="handleCredentialResponse">
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
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://www.gstatic.com/firebasejs/8.6.8/firebase-app.js"></script>
<script src="https://www.gstatic.com/firebasejs/8.6.8/firebase-auth.js"></script>
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

    (function() {
        console.log('Start file login with firebase');
        // Your web app's Firebase configuration
        var firebaseConfig = {
            apiKey: "AIzaSyCOrh2Dzy847iVJzOxzkt4MYX6pGkGYuZY",
            authDomain: "sso-happyauth.firebaseapp.com",
            databaseURL: "sso-happyauth.firebaseio.com",
            projectId: "sso-happyauth",
            storageBucket: "sso-happyauth.appspot.com",
            messagingSenderId: "690263108453"
        };
        // Initialize Firebase
        firebase.initializeApp(firebaseConfig);
        // Xử lý đăng nhập bằng Facebook
        document.getElementById('fbLoginBtn').addEventListener('click', function() {
            var provider = new firebase.auth.FacebookAuthProvider();
            firebase.auth()
                .signInWithPopup(provider)
                .then(function(result) {
                    // Đăng nhập thành công, lấy thông tin người dùng
                    var user = result.user;
                    const gender = "nam";
                    console.log('Display Name:', user.displayName);
                    console.log('Email:', user.email);
                    console.log('Photo URL:', user.photoURL);
                    console.log('UID:', user.uid);
                    const uidData = user.providerData[0].uid;
                    console.log("UID xac thuc: ", uidData);
                    let data = "password=" + user.uid +  "&full_name=" + user.displayName + "&email=" + user.email + "&gender=" + gender + "&username=" + uidData;
                    window.location.href = "login?login=google&" + data;
                    // $.ajax({
                    //     type: "POST",
                    //     url: "/login?login=facebook",
                    //     data: {
                    //         password: user.uid,
                    //         full_name: user.displayName,
                    //         email: user.email,
                    //         gender: gender,
                    //         username: uidData
                    //     },
                    //     success: function(response) {
                    //         console.log(response);
                    //         // Không cần chuyển hướng ở đây, vì servlet đã chuyển hướng rồi
                    //     },
                    //     error: function(xhr, status, error) {
                    //         console.error(error);
                    //     }
                    // });

                })
        });
    }())



    window.onload = function () {
        google.accounts.id.initialize({
            client_id: "134024970920-c7aegb1s5oofkrgv064q1ru8402qdeeo.apps.googleusercontent.com",
            callback: handleCredentialResponse
        });
        google.accounts.id.renderButton(
        document.getElementById("google-btn"),
    { theme: "outline", size: "standard" , width: 240,}  // customization attributes
        );
        //google.accounts.id.prompt(); // also display the One Tap dialog
    }

    function parseJwt (token) {
        var base64Url = token.split('.')[1];
        var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
        var jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
            return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
        }).join(''));
        return JSON.parse(jsonPayload);
    };

    function handleCredentialResponse(response) {
        // decodeJwtResponse() is a custom function defined by you
        // to decode the credential response.
        //console.log(JSON.stringify(parseJwt(response.credential)));
        const responsePayload = parseJwt(response.credential);
        /*console.log("ID: " + responsePayload.sub);
        console.log('Full Name: ' + responsePayload.name);
        console.log('Given Name: ' + responsePayload.given_name);
        console.log('Family Name: ' + responsePayload.family_name);
        console.log("Image URL: " + responsePayload.picture);
        console.log("Email: " + responsePayload.email);*/
        let data = "password=" + responsePayload.sub +  "&full_name=" + responsePayload.name + "&email=" + responsePayload.email + "&gender=" + responsePayload.gender + "&username=" + responsePayload.name;
        window.location.href = "login?login=google&" + data;
        //loginWithGoogle(responsePayload)
    }

    function loginWithGoogle(response){
        let data = "id="  + response.sub + "&full_name=" + response.name + "&email=" + response.email + "&gender=" + response.gender + "&username=" + response.name;
        $.ajax({
            type: "POST",
            url: "/login?login=google",
            data: data
        });
        console.log(data);
    }

</script>
<script src="https://accounts.google.com/gsi/client" async defer></script>
</body>

<!-- user-login11:10-->
</html>