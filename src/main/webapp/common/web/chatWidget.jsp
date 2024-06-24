<%@ page import="model.UserModel" %>
<%--webapp/common/web/chatWidget.jsp--%>
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
        /* Basic styling */
        #chat-bubble {
            position: fixed;
            bottom: 30px;
            left: 30px;
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
        #chat-interface {
            display: none; /* Hidden by default */
            position: fixed;
            bottom: 100px;
            left: 30px;
            width: 350px;
            height: 500px;
            background-color: white;
            border: 1px solid #ccc;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            z-index: 1000;
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
        .chat-content {
            padding: 10px;
            height: 400px;
            overflow-y: auto;
            display: none;
        }

        .chat-message {
            margin: 5px;
            padding: 10px;
            border-radius: 10px;
        }

        .admin-message {
            background-color: #f1f1f1;
            color: black;
            text-align: left;
        }

        .user-message {
            background-color: #007bff;
            color: white;
            text-align: right;
            direction: rtl;
        }

        .chat-input {
            position: absolute;
            bottom: 0;
            width: 100%;
            padding: 10px;
        }

        input[type="text"] {
            width: 80%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        button {
            width: 18%;
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }

        .avatar {
            vertical-align: middle;
            width: 50px;
            height: 50px;
            border-radius: 50%;
            margin-right: 10px;
        }
        /* Modal (hộp thoại xác nhận) */
        .modal {
            display: none;
            position: fixed;
            z-index: 1001;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.4);
            padding-top: 60px;
        }

        .modal-content {
            background-color: #fefefe;
            margin: 15% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 300px;
            border-radius: 8px;
            text-align: center;
        }

        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
            cursor: pointer;
        }

        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
        }

        .modal-buttons {
            display: flex;
            justify-content: space-around;
            margin-top: 20px;
        }

        .modal-buttons button {
            width: 40%;
        }
    </style>
</head>
<body>
<%
    // Define variables for avatars
    String adminAvatar = "https://e7.pngegg.com/pngimages/45/992/png-clipart-customer-service-representative-help-desk-customer-support-others-miscellaneous-service.png";
    String userAvatar = "https://w7.pngwing.com/pngs/340/946/png-transparent-avatar-user-computer-icons-software-developer-avatar-child-face-heroes.png";
    String chatbotAvatar = "https://img.freepik.com/free-vector/chatbot-chat-message-vectorart_78370-4104.jpg?size=338&ext=jpg&ga=GA1.1.553209589.1713139200&semt=ais"; // Change this to the URL of your chatbot avatar

    UserModel user = (UserModel) session.getAttribute("user");
    int userId = (user != null) ? user.getId() : -1;  // -1 signifies no user logged in

%>

<div id="chat-bubble" onclick="checkLoginAndToggleChat()">
    <i class="fa fa-comments" aria-hidden="true"></i>
</div>
<!-- Hộp thoại xác nhận -->
<div id="login-modal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeModal()">&times;</span>
        <p>Bạn phải đăng nhập mới sử dụng được chức năng này.</p>
        <div class="modal-buttons">
            <button onclick="redirectToLogin()">Đăng nhập</button>
            <button onclick="closeModal()">Hủy</button>
        </div>
    </div>
</div>
<div id="chat-interface">
    <div class="tab-header">
        <div onclick="switchTab('admin-chat')" class="active">Admin</div>
        <div onclick="switchTab('ai-chat')">AI Chatbot</div>
    </div>
    <div id="admin-chat" class="chat-content">
        <div id="admin-chat-messages" class="chat-messages">
            <div class="chat-message admin-message"></div>
            <div class="chat-message user-message"></div>
        </div>
        <div class="chat-input">
            <input id="admin-chat-input" type="text" placeholder="Type your message...">
            <button onclick="sendMessage('admin-chat')">Send</button>
        </div>
    </div>

    <div id="ai-chat" class="chat-content">
        <div id="ai-chat-messages" class="chat-messages"></div>
        <div class="chat-input">
            <input id="ai-chat-input" type="text" placeholder="Type your message...">
            <button onclick="sendAIMessage()">Send</button>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script>
    var senderId = <%= userId %>;
    var receiverId = <%= 69 %>; // admin nhan tin nhan ho tro
    var webSocket;
    var aiWebSocket;
    var readyState;
    var aiMessageContainer = null; // Container for AI message

    $(document).ready(function () {
        // toggleChat();
        switchTab('admin-chat');
        initializeWebSocket();
        initializeAIWebSocket();
    });

    function toggleChat() {
        var chatInterface = document.getElementById('chat-interface');
        var isVisible = chatInterface.style.display === 'block';
        chatInterface.style.display = isVisible ? 'none' : 'block';
        if (!isVisible) {
            if (webSocket === null || readyState !== WebSocket.OPEN) initializeWebSocket();
            if (aiWebSocket === null || readyState !== WebSocket.OPEN) initializeAIWebSocket();
            fetchMessages();
            setTimeout(() => {
                var chatContents = document.querySelectorAll('.chat-content');
                chatContents.forEach(chatContent => {
                    if (chatContent.style.display === 'block') {
                        chatContent.scrollTop = chatContent.scrollHeight;
                    }
                });
            }, 100);
        }
    }

    function checkLoginAndToggleChat() {
        if (senderId === -1) {
            document.getElementById('login-modal').style.display = 'block';
        } else {
            toggleChat();
        }
    }

    function closeModal() {
        document.getElementById('login-modal').style.display = 'none';
    }

    function redirectToLogin() {
        window.location.href = 'login';
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

    function sendMessage(tabId) {
        var input = document.querySelector('#' + tabId + ' input[type="text"]');
        var message = input.value.trim();
        if (tabId === 'admin-chat' && message && receiverId !== -1 && webSocket && webSocket.readyState === WebSocket.OPEN) {
            var msgObj = {
                senderId: senderId,
                receiverId: receiverId,
                messageText: message
            };
            webSocket.send(JSON.stringify(msgObj));
            displayMessage(msgObj, true, tabId);
            input.value = ''; // Clear input field
        } else if (tabId === 'ai-chat' && message && aiWebSocket && aiWebSocket.readyState === WebSocket.OPEN) {
            aiWebSocket.send(message);
            var chatDiv = $('#ai-chat-messages');
            var msgDiv = $('<div class="chat-message user-message"><img src="<%= userAvatar %>" class="avatar" alt="User">' + message + '</div>');
            chatDiv.append(msgDiv);
            input.value = '';
            ensureScrollToBottom();
            aiMessageContainer = null;
        }
    }

    function displayMessage(message, isSender, tabId) {
        var chatDiv = (tabId === 'ai-chat') ? $('#ai-chat-messages') : $('#admin-chat-messages');
        var messageClass = isSender ? "user-message" : "admin-message";
        var avatarURL = isSender ? "<%= userAvatar %>" : (chatDiv.is('#ai-chat-messages') ? "<%= chatbotAvatar %>" : "<%= adminAvatar %>");
        var msgDiv = $('<div class="chat-message ' + messageClass + '"><img src="' + avatarURL + '" class="avatar" alt="' + (isSender ? 'User' : 'Admin') + '"><span class="message-text">' + message.messageText + '</span></div>');
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

    function fetchMessages() {
        if (senderId === -1) return;
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
                    var msgDiv = (message.senderId == senderId) ? '<div class="chat-message user-message"><img src="<%= userAvatar %>" alt="User" class="avatar">' + message.messageText + '</div>' : '<div class="chat-message admin-message"><img src="<%= adminAvatar %>" alt="Admin" class="avatar">' + message.messageText + '</div>';
                    chatDiv.append(msgDiv);
                });
                ensureScrollToBottom();
            },
            error: function (error) {
                fetchMessages();
                console.log('Error fetching messages: ', error);
            }
        });
    }

    function initializeWebSocket() {
        var protocol = window.location.protocol === "https:" ? "wss://" : "ws://";
        var wsUri = protocol + window.location.hostname + ":8887?userId=" + senderId;
        webSocket = new WebSocket(wsUri);

        webSocket.onopen = function(evt) {
            console.log("WebSocket connection opened.");
            readyState = webSocket.readyState;
        };

        webSocket.onmessage = function(evt) {
            var message = JSON.parse(evt.data);
            if (message.receiverId === senderId && message.senderId === receiverId) {
                displayMessage(message, false, 'admin-chat');
            }
        };

        webSocket.onerror = function(evt) {
            console.error("WebSocket error observed:", evt);
        };

        webSocket.onclose = function(evt) {
            console.log("WebSocket connection closed.");
        };
    }

    function initializeAIWebSocket() {
        var protocol = window.location.protocol === "https:" ? "wss://" : "ws://";
        var wsUri = protocol + window.location.hostname + ":8888";
        aiWebSocket = new WebSocket(wsUri);

        aiWebSocket.onopen = function(evt) {
            console.log("AI WebSocket connection opened.");
            readyState = aiWebSocket.readyState;
        };

        aiWebSocket.onmessage = function(evt) {
            displayAIMessage(evt.data);
        };

        aiWebSocket.onerror = function(evt) {
            console.error("AI WebSocket error observed:", evt);
        };

        aiWebSocket.onclose = function(evt) {
            console.log("AI WebSocket connection closed.");
        };
    }

    function displayAIMessage(character) {
        if (!aiMessageContainer) {
            aiMessageContainer = $('<div class="chat-message admin-message"><img src="<%= chatbotAvatar %>" class="avatar" alt="AI"><span class="message-text"></span></div>');
            $('#ai-chat-messages').append(aiMessageContainer);
        }
        var messageTextSpan = aiMessageContainer.find('.message-text');
        messageTextSpan.append(character);
        ensureScrollToBottom();
    }

    function sendAIMessage() {
        var input = document.querySelector('#ai-chat-input');
        var message = input.value.trim();
        if (message) {
            aiWebSocket.send(message);
            var chatDiv = $('#ai-chat-messages');
            var msgDiv = $('<div class="chat-message user-message"><img src="<%= userAvatar %>" class="avatar" alt="User">' + message + '</div>');
            chatDiv.append(msgDiv);
            input.value = '';
            ensureScrollToBottom();
            aiMessageContainer = null;
        }
    }
</script>
</body>
</html>
