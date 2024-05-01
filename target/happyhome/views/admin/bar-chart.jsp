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
        #barChart {
            margin: 30px auto;
        }
        /* Ẩn các picker mặc định */
        .picker {
            width: fit-content;
            display: block;
        }

        #pickerContainer > label {
            margin-right: 5px;
        }
        #pickerContainer > input {
            margin-right: 20px;
        }
        #pickerContainer > div {
            width: fit-content;
        }
        #nodata {
            font-size: 50px;
            width: fit-content;
            margin: 10% auto;
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
                            <label>Chọn tháng hoặc năm:</label>
                            <div id="pickerContainer">
                                <div id="monthPicker" class="picker">
                                    <input type="month" id="monthInput" class="form-control">
                                </div>
<%--                                <label for="monthRadio">Tháng</label>--%>
<%--                                <input type="radio" id="monthRadio" name="pickerType" value="month" checked>--%>
<%--                                <label for="yearRadio">Năm</label>--%>
<%--                                <input type="radio" id="yearRadio" name="pickerType" value="year">--%>
<%--                                <div id="monthPicker" class="picker">--%>
<%--                                    <input type="month" id="monthInput" class="form-control">--%>
<%--                                </div>--%>
<%--                                <div id="yearPicker" class="picker">--%>
<%--                                    <input type="" id="yearInput" class="form-control" placeholder="Nhập năm">--%>
<%--                                </div>--%>
                            </div>
                        </div>

                        <!-- Phần tử cho biểu đồ cột -->
                        <canvas id="barChart" height="700" width="1400"></canvas>
                        <div id="nodata">Chưa có dữ liệu thống kê</div>
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
    function setDataBarChart(labels, data){
        notifyNoData(data);
        // Dữ liệu cho biểu đồ cột
        var barData = {
            labels: labels,
            datasets: [{
                label: 'Doanh thu',
                data: data,
                backgroundColor: 'rgba(255, 99, 132, 0.2)',
                borderColor: 'rgba(255, 99, 132, 1)',
                borderWidth: 1
            }]
        };

        // Cấu hình biểu đồ cột
        var barOptions = {
            scales: {
                y: {
                    beginAtZero: true,
                        title: {
                        display: true,
                            text: 'VND' // Đơn vị cho trục y
                    }
                },
                x: {
                    title: {
                        display: true,
                            text: 'Day' // Đơn vị cho trục x
                    }
                }
            }
        };

        // Vẽ biểu đồ cột
        var barChartCanvas = document.getElementById("barChart").getContext("2d");
        currentChart = new Chart(barChartCanvas, {
            type: 'bar',
            data: barData,
            options: barOptions
        });
    }
    function setDefaultMonth() {
        const monthPicker = document.getElementById("monthInput");
        // Lấy ngày và tháng hiện tại
        const currentDate = new Date();
        const currentYear = currentDate.getFullYear();
        const currentMonth = (currentDate.getMonth() + 1).toString().padStart(2, '0');
        // Đặt giá trị mặc định cho input
        const defaultValue = currentYear + '-' + currentMonth;
        monthPicker.value = defaultValue;
    }
    function updateChartData(labels, data) {
        notifyNoData(data);
        if (currentChart) {
            currentChart.data.labels = labels;
            currentChart.data.datasets.forEach((dataset) => {
                dataset.data = data;
            });
            currentChart.update();
        }
    }
    function showNoData(){
        document.getElementById("barChart").style.display = "none"
        document.getElementById("nodata").style.display = "block"
    }
    function hideNoData(){
        document.getElementById("barChart").style.display = "block"
        document.getElementById("nodata").style.display = "none"
    }
    function notifyNoData(data){
        if(data.length > 0){
            hideNoData();
        }else{
            showNoData();
        }
    }


    const barLabels = <%= new Gson().toJson(labels) %>;
    const barData = <%= new Gson().toJson(data) %>;
    setDataBarChart(barLabels, barData);
    setDefaultMonth();



    // Lắng nghe sự kiện khi người dùng thay đổi tháng trong hộp chọn ngày tháng, set lại số liệu thống kê
    document.addEventListener("DOMContentLoaded", function() {
        const monthPicker = document.getElementById("monthInput");
        monthPicker.addEventListener("change", function() {
            // lay ngay da chon
            const selectedDate = monthPicker.value;
            const xhr = new XMLHttpRequest();
            xhr.open("POST", "admin-total-revenue", true)
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.onreadystatechange = function (){
                if(xhr.readyState === 4 && xhr.status === 200){
                    const responseData = JSON.parse(xhr.responseText);
                    updateChartData(responseData.labels, responseData.data);
                }
            }
            xhr.send("month=" + selectedDate);
        });
    });

    // // Lắng nghe sự kiện khi radio button được thay đổi
    // document.querySelectorAll('input[name="pickerType"]').forEach(function(radio) {
    //     radio.addEventListener('change', function() {
    //         // Ẩn tất cả các picker
    //         document.querySelectorAll('.picker').forEach(function(picker) {
    //             picker.style.display = 'none';
    //         });
    //         // Hiển thị picker tương ứng với radio button được chọn
    //         var pickerId = this.value + 'Picker';
    //         document.getElementById(pickerId).style.display = 'block';
    //     });
    // });
    // document.getElementById("yearPickerPicker").style.display = 'none';



</script>
</body>
</html>