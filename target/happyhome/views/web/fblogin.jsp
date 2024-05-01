<%--<%@ page language="java" contentType="text/html; charset=UTF-8"--%>
<%--         pageEncoding="UTF-8"%>--%>
<%--<!DOCTYPE html>--%>
<%--<html lang="en">--%>
<%--<head>--%>
<%--    <title>Đăng nhập bằng mạng xã hội</title>--%>
<%--    <meta charset="utf-8">--%>
<%--    <meta name="viewport" content="width=device-width, initial-scale=1">--%>
<%--    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">--%>
<%--    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>--%>
<%--    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="form-group text-center">--%>
<%--    <button class="btn btn-block btn-social btn-google" id="btnGoogle" type="button"> <span class="fa fa-google"></span>Login with Google</button>--%>
<%--</div>--%>
<%--<div class="form-group text-center">--%>
<%--    <button class="btn btn-block btn-social btn-facebook" id="btnFacebook" type="button"> <span class="fa fa-facebook"></span>Login with Facebook</button>--%>
<%--</div>--%>
<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>--%>
<%--<script src="https://www.gstatic.com/firebasejs/5.2.0/firebase.js"></script>--%>
<%--<script>--%>
<%--    (function() {--%>
<%--        console.log('Start file login with firebase');--%>
<%--        // Initialize Firebase--%>
<%--        var config = {--%>
<%--            apiKey: "AIzaSyCOrh2Dzy847iVJzOxzkt4MYX6pGkGYuZY",--%>
<%--            authDomain: "sso-happyauth.firebaseapp.com",--%>
<%--            databaseURL: "sso-happyauth.firebaseio.com",--%>
<%--            projectId: "sso-happyauth",--%>
<%--            storageBucket: "sso-happyauth.appspot.com",--%>
<%--            messagingSenderId: "690263108453"--%>
<%--        };--%>
<%--        firebase.initializeApp(config);--%>
<%--        var database = firebase.database();--%>

<%--        //Google singin provider--%>
<%--        var ggProvider = new firebase.auth.GoogleAuthProvider();--%>
<%--        //Facebook singin provider--%>
<%--        var fbProvider = new firebase.auth.FacebookAuthProvider();--%>
<%--        //Login in variables--%>
<%--        const btnGoogle = document.getElementById('btnGoogle');--%>
<%--        const btnFaceBook = document.getElementById('btnFacebook');--%>

<%--        //Sing in with Google--%>
<%--        btnGoogle.addEventListener('click', e => {--%>
<%--            firebase.auth().signInWithPopup(ggProvider).then(function(result) {--%>
<%--                var token = result.credential.accessToken;--%>
<%--                var user = result.user;--%>
<%--                console.log('User>>Goole>>>>', user);--%>
<%--                userId = user.uid;--%>

<%--            }).catch(function(error) {--%>
<%--                console.error('Error: hande error here>>>', error.code)--%>
<%--            })--%>
<%--        }, false)--%>

<%--        //Sing in with Facebook--%>
<%--        btnFaceBook.addEventListener('click', e => {--%>
<%--            firebase.auth().signInWithPopup(fbProvider).then(function(result) {--%>
<%--                // This gives you a Facebook Access Token. You can use it to access the Facebook API.--%>
<%--                var token = result.credential.accessToken;--%>
<%--                // The signed-in user info.--%>
<%--                var user = result.user;--%>
<%--                console.log('User>>Facebook>', user);--%>
<%--                // ...--%>
<%--                userId = user.uid;--%>

<%--            }).catch(function(error) {--%>
<%--                // Handle Errors here.--%>
<%--                var errorCode = error.code;--%>
<%--                var errorMessage = error.message;--%>
<%--                // The email of the user's account used.--%>
<%--                var email = error.email;--%>
<%--                // The firebase.auth.AuthCredential type that was used.--%>
<%--                var credential = error.credential;--%>
<%--                // ...--%>
<%--                console.error('Error: hande error here>Facebook>>', error.code)--%>
<%--            });--%>
<%--        }, false)--%>
<%--        //jquery--%>
<%--    }())--%>
<%--</script>--%>
<%--</body>--%>
<%--</html>--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Đăng nhập bằng mạng xã hội</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="form-group text-center">
    <button class="btn btn-block btn-social btn-google" id="btnGoogle" type="button"> <span class="fa fa-google"></span>Login with Google</button>
</div>
<div class="form-group text-center">
    <button class="btn btn-block btn-social btn-facebook" id="btnFacebook" type="button"> <span class="fa fa-facebook"></span>Login with Facebook</button>
</div>

<button id="fbLoginBtn">Đăng nhập bằng Facebook</button>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://www.gstatic.com/firebasejs/8.6.8/firebase-app.js"></script>
<script src="https://www.gstatic.com/firebasejs/8.6.8/firebase-auth.js"></script>
<script>
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
                    // Xử lý thông tin người dùng
                    console.log(user);
                })
                .catch(function(error) {
                    // Đăng nhập thất bại, xử lý lỗi
                    console.log(error);
                });
        });


    }())
</script>
</body>
</html>
