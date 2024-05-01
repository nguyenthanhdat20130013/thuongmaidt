<%@ page import="model.UserModel" %>
<%--webapp/common/admin/chatWidgetAdmin.jsp--%>
<%--
Created by IntelliJ IDEA.
User: Truong
Date: 4/18/2024
Time: 9:32 AM
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <style>
        #chat-bubble {
            position: fixed;
            bottom: 30px;
            right: 30px;
            width: 60px;
            height: 60px;
            background-color: #007bff;
            color: white;
            border-radius: 50%;
            text-align: center;
            line-height: 60px;
            font-size: 30px;
            cursor: pointer;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            z-index: 1000;
        }

        #chat-bubble:hover {
            background-color: #0056b3;
        }

        /* Chat interface styling */
        #chat-interface, #user-list {
            display: none; /* Hidden by default */
            position: fixed;
            bottom: 100px;
            right: 30px;
            width: 350px;
            height: 500px;
            background-color: white;
            border: 1px solid #ccc;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            z-index: 1000;
        }

        #user-list {
            right: 390px; /* Adjusted to display next to the chat interface */
            width: 200px; /* Smaller width for the user list */
        }

        .user-entry {
            padding: 10px;
            border-bottom: 1px solid #ccc;
            cursor: pointer;
        }

        .user-entry:hover {
            background-color: #f1f1f1;
        }

        .active-user {
            background-color: #007bff;
            color: white;
        }


        /* Tab layout */
        .tab-header {
            display: flex;
            background-color: #007bff;
            color: white;
            padding: 10px;
        }

        .tab-header div {
            flex: 1;
            text-align: center;
            cursor: pointer;
        }

        .tab-header .active {
            background-color: #0056b3;
        }

        /* Chat content styling */
        /* Ensures the chat container is a flexbox */
        .chat-content {
            display: flex;
            flex-direction: column; /* Align children (messages) in a column */
            padding: 10px;
            height: 400px;
            overflow-y: auto;
        }

        /* General message styling */
        .chat-message {
            display: flex; /* Ensures internal items (avatar and text) are aligned */
            align-items: center;
            margin: 5px;
            padding: 10px;
            border-radius: 10px;
            width: 100%; /* Take the full width to allow flex alignment to work */
            box-sizing: border-box;
        }

        /* Admin message alignment */
        .admin-message {
            justify-content: flex-end; /* Align content to the end (right) */
            background-color: #007bff;
            color: white;
            text-align: right;
        }

        /* User message alignment */
        .user-message {
            justify-content: flex-start; /* Align content to the start (left) */
            background-color: #f1f1f1;
            color: black;
            text-align: left;
        }

        .avatar {
            width: 50px;
            height: 50px;
            border-radius: 50%;
            margin-right: 10px; /* Right margin for spacing between text */
        }

        .chat-input {
            position: absolute;
            bottom: 0;
            width: 100%;
            padding: 10px;
        }

        input[type="text"], button {
            flex-grow: 1;
            margin: 0 2px;
        }

        button {
            flex-basis: 20%;
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }

    </style>
</head>
<body>
<%
    // Define variables for avatars
    String adminAvatar = "https://e7.pngegg.com/pngimages/45/992/png-clipart-customer-service-representative-help-desk-customer-support-others-miscellaneous-service.png";
    String userAvatar = "https://w7.pngwing.com/pngs/340/946/png-transparent-avatar-user-computer-icons-software-developer-avatar-child-face-heroes.png";
    String chatbotAvatar = "https://img.freepik.com/free-vector/chatbot-chat-message-vectorart_78370-4104.jpg?size=338&ext=jpg&ga=GA1.1.553209589.1713139200&semt=ais"; // Change this to the URL of your chatbot avatar

    UserModel admin = (UserModel) session.getAttribute("auth");
    int adminId = (admin != null) ? admin.getId() : -1;  // -1 signifies no admin logged in

%>

<div id="chat-bubble" onclick="toggleChat()">
    <i class="fa fa-comments" aria-hidden="true"></i>
</div>

<div id="chat-interface">
    <div class="tab-header">
        <div onclick="switchTab('admin-chat')" class="active">Admin</div>
        <div onclick="switchTab('ai-chat')">AI Chatbot</div>
    </div>
    <div id="admin-chat" class="chat-content">
        <!-- Chat messages and input for admin chat -->
        <div id="admin-chat-messages" class="chat-messages">
            <div class="chat-message admin-message">
                <img src="<%= adminAvatar %>" alt="Admin" class="avatar">
                <%--                Hello! How can I help you today?--%>
            </div>
            <!-- User's message -->
            <div class="chat-message user-message">
                <img src="<%= userAvatar %>" alt="User" class="avatar">
                <%--                I need help with my order.--%>
            </div>
        </div>
        <div class="chat-input">
            <input id="admin-chat-input" type="text" placeholder="Type your message...">
            <button onclick="sendMessage('admin-chat')">Send</button>
        </div>
    </div>

    <div id="ai-chat" class="chat-content">
        <!-- Chat messages and input for AI chatbot -->
        <div class="chat-message admin-message">
            <img src="<%= chatbotAvatar %>" alt="AI" class="avatar">
            Welcome to our AI assistant!
        </div>
        <div class="chat-input">
            <input type="text" placeholder="Type your message...">
            <button onclick="sendMessage('ai-chat')">Send</button>
        </div>
    </div>
</div>

<div id="user-list">
    <%--    <div class="user-entry active-user">User 1</div>--%>
    <%--    <div class="user-entry">User 2</div>--%>
    <%--    <div class="user-entry">User 3</div>--%>
    <!-- Additional users can be added here -->
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script>
    var senderId = <%= adminId %>;
    var receiverId = -1;
    var webSocket;
    var readyState;
    $(document).ready(function () {
        initializeWebSocket();
        switchTab('admin-chat');
    });

    function toggleChat() {
        var chatInterface = document.getElementById('chat-interface');
        var userList = document.getElementById('user-list');
        var isVisible = chatInterface.style.display === 'block';
        chatInterface.style.display = isVisible ? 'none' : 'block';
        userList.style.display = isVisible ? 'none' : 'block';

        if (!isVisible) {
            fetchUserList();
            if (webSocket === null || readyState !== WebSocket.OPEN) // Khởi tạo WebSocket nếu đã đóng
                initializeWebSocket();

            fetchMessages();

        }
    }

    function switchTab(tabName) {
        var tabs = document.getElementsByClassName('chat-content');
        var headers = document.getElementsByClassName('tab-header')[0].children;
        for (var i = 0; i < tabs.length; i++) {
            tabs[i].style.display = 'none';
            headers[i].classList.remove('active');
        }
        document.getElementById(tabName).style.display = 'block';
        headers[Array.from(headers).findIndex(header => header.textContent.includes(tabName.split('-')[0]))].classList.add('active');
    }

    function debounce(func, wait, immediate) {
        var timeout;
        return function() {
            var context = this, args = arguments;
            var later = function() {
                timeout = null;
                if (!immediate) func.apply(context, args);
            };
            var callNow = immediate && !timeout;
            clearTimeout(timeout);
            timeout = setTimeout(later, wait);
            if (callNow) func.apply(context, args);
        };
    }

    // tao ham debounce de khong bi goi nhieu lan
    var debouncedFetchUserList = debounce(function() {
        fetchUserList();
    }, 1000, false); // chac chan rang chi goi ham fetchUserList sau 1s

    function sendMessage(tabId) {
        var input = document.querySelector('#' + tabId + ' input[type="text"]');
        var message = input.value.trim();
        if (message && receiverId !== -1 && webSocket && webSocket.readyState === WebSocket.OPEN) {
            var msgObj = {
                senderId: senderId,
                receiverId: receiverId,
                messageText: message
            };
            webSocket.send(JSON.stringify(msgObj));
            displayMessage(msgObj, true);
            input.value = '';
        }
        debouncedFetchUserList(); // dung de cap nhat lai danh sach user sau khi gui tin nhan
    }


    function displayMessage(message, isSender) {
        var chatDiv = $('#admin-chat-messages');
        var messageClass = isSender ? "admin-message" : "user-message";
        var avatarURL = isSender ? "<%= adminAvatar %>" : "<%= userAvatar %>";
        var msgDiv = $('<div class="chat-message ' + messageClass + '"><img src="' + avatarURL + '" alt="User" class="avatar">' + message.messageText + '</div>');
        chatDiv.append(msgDiv);
        ensureScrollToBottom();
    }

    function ensureScrollToBottom() {
        setTimeout(() => {
            var chatContents = document.querySelectorAll('.chat-content');
            chatContents.forEach(chatContent => {
                if (chatContent.style.display === 'block') {
                    chatContent.scrollTop = chatContent.scrollHeight;
                }
            });
        }, 100);
    }

    function selectUser(userId) {
        receiverId = userId;
        $('#user-list .user-entry').removeClass('active-user');
        $('#user-' + userId).addClass('active-user');
        fetchMessages();
    }

    function fetchUserList() {
        console.log("fetchUserList");
        $.ajax({
            url: 'messages?action=listUsers&adminId=' + senderId,
            type: 'GET',
            dataType: 'json',
            success: function (users) {
                var userListDiv = $('#user-list');
                userListDiv.empty();
                var userSelected = false;  // danh dau de biet user da duoc chon truoc do

                if (users.length > 0) {
                    users.forEach(function (user, index) {
                        var userEntry = $('<div id="user-' + user.id + '" class="user-entry" onclick="selectUser(' + user.id + ')">' + user.fullName + '</div>');
                        userListDiv.append(userEntry);

                        // tu dong chon user dau tien neu chua co user nao duoc chon
                        if (index === 0 && receiverId === 70) {  // kiem tra xem co user nao duoc chon truoc do chua
                            selectUser(user.id);
                            userSelected = true;
                        }
                    });

                    // neu chua co user nao duoc chon truoc do thi chon user dau tien
                    if (!userSelected && receiverId !== 70) {
                        $('#user-' + receiverId).addClass('active-user');
                    }
                } else {
                    console.log('No users to display');
                }
                console.log('success fetching user list');
            },
            error: function () {
                console.log('Error fetching user list');
            }
        });
    }


    function fetchMessages() {
        if (senderId === -1 || receiverId === -1) return;
        $.ajax({
            url: 'messages?action=getMessages',
            type: 'GET',
            data: {
                senderId: senderId,
                receiverId: receiverId
            },
            success: function (messages) {
                var chatDiv = $('#admin-chat-messages');
                chatDiv.empty();
                $.each(messages, function (index, message) {
                    displayMessage(message, message.senderId === senderId);
                });

                ensureScrollToBottom();

            },
            error: function (error) {
                console.log('Error fetching messages: ', error);
            }
        });
    }

    function initializeWebSocket() {
        var protocol = window.location.protocol === "https:" ? "wss://" : "ws://";
        var wsUri = protocol + window.location.hostname + ":8887?userId=" + senderId;
        webSocket = new WebSocket(wsUri);

        webSocket.onopen = function (evt) {
            console.log("WebSocket connection opened.");
            readyState = webSocket.readyState;
        };

        webSocket.onmessage = function (evt) {
            fetchUserList();

            var message = JSON.parse(evt.data);
            if (message.receiverId === senderId && message.senderId === receiverId) {
                displayMessage(message, false);
            }
        };

        webSocket.onerror = function (evt) {
            console.error("WebSocket error observed:", evt);
        };

        webSocket.onclose = function (evt) {
            console.log("WebSocket connection closed.");
        };
    }
</script>


</body>
</html>