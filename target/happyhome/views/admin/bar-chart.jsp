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
                        <!-- Phần tử cho biểu đồ cột -->
                        <canvas id="barChart" height="700" width="1400"></canvas>
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
<script>
    // Dữ liệu cho biểu đồ cột
    var barData = {
        labels: ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"],
        datasets: [{
            label: 'Biểu đồ cột',
            data: [12, 19, 3, 5, 2, 5, 8, 7, 9, 2, 15],
            backgroundColor: 'rgba(255, 99, 132, 0.2)',
            borderColor: 'rgba(255, 99, 132, 1)',
            borderWidth: 1
        }]
    };

    // Cấu hình biểu đồ cột
    var barOptions = {
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero:true
                }
            }]
        }
    };

    // Vẽ biểu đồ cột
    var barChartCanvas = document.getElementById("barChart").getContext("2d");
    new Chart(barChartCanvas, {
        type: 'bar',
        data: barData,
        options: barOptions
    });

</script>
</body>
</html>