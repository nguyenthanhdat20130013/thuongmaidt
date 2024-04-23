<%@ page import="java.util.List" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="APIurl" value="/api-admin-product"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Danh sách sản phẩm</title>
    <jsp:include page="/common/admin/css.jsp"></jsp:include>
    <style>
        input[type='checkbox']{
            width: 18px;
            height: 18px;
        }
        #pieChart {
            margin: 30px auto;
        }
    </style>
</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">
    <!-- Navbar -->
    <jsp:include page="/common/admin/header.jsp"></jsp:include>
    <!-- /.navbar -->

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1>Doanh thu cửa hàng theo thời gian</h1>
                    </div>
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">Trang chủ</a></li>
                            <li class="breadcrumb-item active">Danh sách sản phẩm</li>
                        </ol>
                    </div>
                </div>
            </div><!-- /.container-fluid -->
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-12">
                        <div>
                            <label for="monthPicker">Chọn tháng:</label>
                            <div style="display: flex;width: fit-content">
                                <input type="month" id="monthPicker" class="form-control">
                                <button id="reloadButton" class="btn btn-primary" style="margin-left: 10px">Reload</button>
                            </div>
                        </div>
                        <!-- Phần tử cho biểu đồ tròn -->
                        <canvas id="pieChart" width="700" height="750"></canvas>

                    </div>
                    <!-- /.col -->
                </div>
                <!-- modal -->
            </div>
            <!-- /.container-fluid -->
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
    <jsp:include page="/common/admin/footer.jsp"></jsp:include>
</div>
<!-- ./wrapper -->
<jsp:include page="/common/admin/js.jsp"></jsp:include>
<!-- Thư viện Chart.js -->
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<%
    // Lấy dữ liệu từ request
    List<String> labels = (List<String>) request.getAttribute("Label");
    List<Double> data = (List<Double>) request.getAttribute("Data");
%>
<script>
    var currentChart;
    function setDataPieChart(labels, data){
        // Dữ liệu cho biểu đồ tròn
        var pieData = {
            labels: labels,
            datasets: [{
                data: data,
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)'
                ],
                borderWidth: 1
            }]
        };

        // Vẽ biểu đồ tròn
        var pieChartCanvas = document.getElementById("pieChart").getContext("2d");
        currentChart = new Chart(pieChartCanvas, {
            type: 'pie',
            data: pieData
        });
    }
    function setDefaultMonth() {
        const monthPicker = document.getElementById("monthPicker");
        // Lấy ngày và tháng hiện tại
        const currentDate = new Date();
        const currentYear = currentDate.getFullYear();
        const currentMonth = (currentDate.getMonth() + 1).toString().padStart(2, '0');
        // Đặt giá trị mặc định cho input
        <%--const defaultValue = `${currentYear}-${currentMonth}`;--%>
        const defaultValue = currentYear + '-' + currentMonth;
        monthPicker.value = defaultValue;
    }
    function updateChartData(labels, data) {
        if (currentChart) {
            currentChart.data.labels = labels;
            currentChart.data.datasets.forEach((dataset) => {
                dataset.data = data;
            });
            currentChart.update();
        }
    }

    // Sự kiện khi nhấn nút reload
    document.addEventListener("DOMContentLoaded", function() {
        // Lắng nghe sự kiện khi nhấn nút Reload
        const reloadButton = document.getElementById("reloadButton");
        reloadButton.addEventListener("click", function() {
            const monthPicker = document.getElementById("monthPicker");
            const selectedDate = monthPicker.value;
            // Gửi yêu cầu AJAX tới Servlet
            const xhr = new XMLHttpRequest();
            xhr.open("POST", "admin-revenue-by-category", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    // Nhận kết quả từ Servlet
                    const responseData = JSON.parse(xhr.responseText);

                    // Cập nhật dữ liệu cho biểu đồ tròn
                    updateChartData(responseData.labels, responseData.data);
                }
            };
            // Gửi dữ liệu với phương thức POST
            xhr.send("month=" + selectedDate);
        });
    });


    const pieLabels = <%= new Gson().toJson(labels) %>;
    const pieData = <%= new Gson().toJson(data) %>;
    setDataPieChart(pieLabels, pieData);
    setDefaultMonth();
</script>
</body>
</html>