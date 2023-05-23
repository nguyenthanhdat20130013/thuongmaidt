<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Trang chủ</title>
    <jsp:include page="/common/admin/css.jsp"></jsp:include>
    <!-- CSS Files -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-datepicker@1.9.0/dist/css/bootstrap-datepicker.min.css">
    <style>
        .active{
            background-color: rgba(255,255,255,.1)!important;
            color: #fff!important;
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
        <div class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1 class="m-0" id="result">Bảng thống kế tình hình bán hàng</h1>
                    </div><!-- /.col -->
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">Trang chủ</a></li>
                        </ol>
                    </div><!-- /.col -->
                </div><!-- /.row -->
                <div class="row">
                    <div class="col-sm-12">
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="thongke-option" id="radio-thang" checked>
                            <label class="form-check-label" for="radio-thang">Thống kê theo tháng</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="thongke-option" id="radio-ngay" >
                            <label class="form-check-label" for="radio-ngay">Thống kê theo ngày</label>
                        </div>

                    </div>
                </div>

                <div class="col-sm-2" id="datepicker-container-m" style="display: block;">
                    <label for="datepicker">Chọn tháng và năm:</label>
                    <div class="input-group date">
                        <input type="text" id="datepicker-month" class="form-control">
                        <div class="input-group-append">
                            <button id="btn-xem-thang" class="btn btn-primary">Xem</button>
                        </div>
                    </div>
                </div>

                <div id="result-container-m" style="display: none;">
                    <div class="row">
                        <div class="col-sm-12">
                        </div>
                    </div>
                </div>


                <div id="datepicker-container" style="display: none;">
                    <div class="row">
                        <div class="col-sm-2">
                            <div class="form-group d-sm-inline-block">
                                <label for="datepicker">Chọn ngày:</label>
                                <div class="input-group date">
                                    <input type="text" id="datepicker" class="form-control">
                                    <div class="input-group-append">
                                        <button id="btn-xem" class="btn btn-primary">Xem</button>
                                    </div>
                                </div>
                            </div>

                        </div>

                    </div>
                </div>
                <div id="result-container" style="display: none;">
                    <div class="row">
                        <div class="col-sm-12">

                        </div>
                    </div>
                </div>
            </div><!-- /.container-fluid -->
        </div>

        <!-- /.content-header -->

        <!-- Main content -->
        <div class="content">
            <h3 class="text-center">Thống kê tổng quan</h3>
            <div class="container-fluid py-4">
                <div class="row">
                    <div class="col-xl-3 col-sm-6 mb-xl-0 mb-">
                        <div class="card">
                            <div class="card-header p-3 pt-2">
                                <h2>Doanh thu</h2>
                                <div class="text-end pt-1">
                                    <h4 class="mb-0" id="doanhthuthang"> </h4>
                                    <p class="text-danger">Tính theo giá trị đơn hàng</p>
                                </div>
                            </div>
                            <hr class="dark horizontal my-0">
                            <div class="card-footer p-3">
                                <p class="mb-0"><span class="text-success text-sm font-weight-bolder">Xem chi tiết</span></p>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-sm-6 mb-xl-0 mb-4">
                        <div class="card">
                            <div class="card-header p-3 pt-2">
                                <h2>Số lượng đơn hàng</h2>
                                <div class="text-end pt-1">
                                    <h4 class="mb-0" id="donhangthang"></h4>
                                    <p class="text-danger">Dựa vào số đơn hàng đã đặt</p>
                                </div>
                            </div>
                            <hr class="dark horizontal my-0">
                            <div class="card-footer p-3">
                                <p class="mb-0"><span class="text-success text-sm font-weight-bolder">Xem chi tiết</span></p>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-sm-6 mb-xl-0 mb-4">
                        <div class="card">
                            <div class="card-header p-3 pt-2">
                                <h2>Số lượng sản phẩm đã bán</h2>
                                <div class="text-end pt-1">
                                    <h4 class="mb-0" id="sanphamthang"></h4>
                                    <p class="text-danger">Theo số lượng sản phẩm của đơn hàng</p>
                                </div>
                            </div>
                            <hr class="dark horizontal my-0">
                            <div class="card-footer p-3">
                                <p class="mb-0"><span class="text-success text-sm font-weight-bolder">Xem chi tiết</span></p>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-sm-6">
                        <div class="card">
                            <div class="card-header p-3 pt-2">
                                <h2>Đơn hàng đang vận chuyển</h2>
                                <div class="text-end pt-1">
                                    <h4 class="mb-0" id="vanchuyenthang"></h4>
                                    <p class="text-danger">Theo trạng thái vận chuyển</p>
                                </div>
                            </div>
                            <hr class="dark horizontal my-0">
                            <div class="card-footer p-3">
                                <p class="mb-0"><span class="text-success text-sm font-weight-bolder">Xem chi tiết</span></p>
                            </div>
                        </div>
                    </div>
                </div>
                <h3 class="text-center">Biểu đồ thống kê</h3>
                <div class="row mt-4">
                    <div class="col-6 mt-4 mb-4">
                        <div class="card z-index-2 ">
                            <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2 bg-transparent">
                                <div class="bg-gradient-primary shadow-primary border-radius-lg py-3 pe-1">
                                    <div class="chart">
                                        <canvas id="chart-bars" class="chart-canvas" height="170"></canvas>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <h6 class="mb-0 ">Website Views</h6>
                                <p class="text-sm ">Last Campaign Performance</p>
                                <hr class="dark horizontal">
                                <div class="d-flex ">
                                    <i class="material-icons text-sm my-auto me-1">schedule</i>
                                    <p class="mb-0 text-sm"> campaign sent 2 days ago </p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-6 mt-4 mb-4">
                        <div class="card z-index-2  ">
                            <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2 bg-transparent">
                                <div class="bg-gradient-success shadow-success border-radius-lg py-3 pe-1">
                                    <div class="chart">
                                        <canvas id="chart-line" class="chart-canvas" height="170"></canvas>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <h6 class="mb-0 "> Daily Sales </h6>
                                <p class="text-sm "> (<span class="font-weight-bolder">+15%</span>) increase in today sales. </p>
                                <hr class="dark horizontal">
                                <div class="d-flex ">
                                    <i class="material-icons text-sm my-auto me-1">schedule</i>
                                    <p class="mb-0 text-sm"> updated 4 min ago </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <h3 class="text-center">Chi tiết</h3>
                <div class="row mb-3">
                    <div class="col-lg-8 col-md-6 mb-md-0 mb-4">
                        <div class="card">
                        <div class="card-header pb-0">
                            <div class="row">
                                <div class="col-lg-6 col-7">
                                    <h6>Đơn hàng gần đây</h6>
                                    <p class="text-sm mb-0">
                                        <i class="fa fa-check text-info" aria-hidden="true"></i>
                                        <span class="font-weight-bold ms-1">30 done</span> this month
                                    </p>
                                </div>
                                <div class="col-lg-6 col-5 my-auto text-end">
                                    <div class="dropdown float-lg-end pe-4">
                                        <a class="cursor-pointer" id="dropdownTable" data-bs-toggle="dropdown" aria-expanded="false">
                                            <i class="fa fa-ellipsis-v text-secondary"></i>
                                        </a>
                                        <ul class="dropdown-menu px-2 py-3 ms-sm-n4 ms-n5" aria-labelledby="dropdownTable">
                                            <li><a class="dropdown-item border-radius-md" href="javascript:;">Action</a></li>
                                            <li><a class="dropdown-item border-radius-md" href="javascript:;">Another action</a></li>
                                            <li><a class="dropdown-item border-radius-md" href="javascript:;">Something else here</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card-body px-0 pb-2">
                            <div class="table-responsive">
                                <table class="table align-items-center mb-0">
                                    <thead>
                                    <tr>
                                        <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Companies</th>
                                        <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Members</th>
                                        <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Budget</th>
                                        <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Completion</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td>
                                            <div class="d-flex px-2 py-1">
                                                <div>
                                                    <img src="../assets/img/small-logos/logo-xd.svg" class="avatar avatar-sm me-3" alt="xd">
                                                </div>
                                                <div class="d-flex flex-column justify-content-center">
                                                    <h6 class="mb-0 text-sm">Material XD Version</h6>
                                                </div>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="avatar-group mt-2">
                                                <a href="javascript:;" class="avatar avatar-xs rounded-circle" data-bs-toggle="tooltip" data-bs-placement="bottom" title="Ryan Tompson">
                                                    <img src="../assets/img/team-1.jpg" alt="team1">
                                                </a>
                                                <a href="javascript:;" class="avatar avatar-xs rounded-circle" data-bs-toggle="tooltip" data-bs-placement="bottom" title="Romina Hadid">
                                                    <img src="../assets/img/team-2.jpg" alt="team2">
                                                </a>
                                                <a href="javascript:;" class="avatar avatar-xs rounded-circle" data-bs-toggle="tooltip" data-bs-placement="bottom" title="Alexander Smith">
                                                    <img src="../assets/img/team-3.jpg" alt="team3">
                                                </a>
                                                <a href="javascript:;" class="avatar avatar-xs rounded-circle" data-bs-toggle="tooltip" data-bs-placement="bottom" title="Jessica Doe">
                                                    <img src="../assets/img/team-4.jpg" alt="team4">
                                                </a>
                                            </div>
                                        </td>
                                        <td class="align-middle text-center text-sm">
                                            <span class="text-xs font-weight-bold"> $14,000 </span>
                                        </td>
                                        <td class="align-middle">
                                            <div class="progress-wrapper w-75 mx-auto">
                                                <div class="progress-info">
                                                    <div class="progress-percentage">
                                                        <span class="text-xs font-weight-bold">60%</span>
                                                    </div>
                                                </div>
                                                <div class="progress">
                                                    <div class="progress-bar bg-gradient-info w-60" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                        <div class="card">
                        <div class="card-header pb-0">
                            <div class="row">
                                <div class="col-lg-6 col-5">
                                    <h5>Sản phẩm bán chạy</h5>
                                </div>
                            </div>
                        </div>
                        <div class="table">
                            <div class="table-responsive">
                                <table class="table align-items-center mb-0">
                                    <thead>
                                    <tr>
                                        <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Tên sản phẩm</th>
                                        <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Giá bán ra</th>
                                        <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 opacity-7">Số lượng đã bán</th>
                                        <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 opacity-7">Chi tiết</th>
                                    </tr>
                                    </thead>
                                    <tbody id="productTableBody">
                                    <!-- Các sản phẩm sẽ được thêm vào đây -->
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6">
                    <div class="card h-80">
                        <div class="card-header pb-0">
                            <h6>Orders overview</h6>
                            <p class="text-sm">
                                <i class="fa fa-arrow-up text-success" aria-hidden="true"></i>
                                <span class="font-weight-bold">24%</span> this month
                            </p>
                        </div>
                        <div class="card-body p-3">
                            <div class="timeline-one-side">
                                <div class="timeline-block mb-3">
                    <span class="timeline-step">
                      <i class="material-icons text-success text-gradient">notifications</i>
                    </span>
                                    <div class="timeline-content">
                                        <h6 class="text-dark text-sm font-weight-bold mb-0">$2400, Design changes</h6>
                                        <p class="text-secondary font-weight-bold text-xs mt-1 mb-0">22 DEC 7:20 PM</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            </div>
            <!-- /.col-md-6 -->

            <!-- /.row -->

            <!-- /.container-fluid -->
        </div>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- Main Footer -->
    <jsp:include page="/common/admin/footer.jsp"></jsp:include>
</div>
<!-- ./wrapper -->

<!-- REQUIRED SCRIPTS -->
<jsp:include page="/common/admin/js.jsp"></jsp:include>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap-datepicker@1.9.0/dist/js/bootstrap-datepicker.min.js"></script>
<script>
    $(document).ready(function() {
        $('input[name="thongke-option"]').change(function() {
            if ($(this).attr('id') === 'radio-ngay') {
                $('#datepicker-container').show();
                $('#result-container').hide();
                // Đặt giá trị mặc định cho result-thang
                var defaultResult = 'Bạn đang xem tháng';
                $('#result').text(defaultResult);
            } else {
                $('#datepicker-container').hide();
                $('#result-container').hide();
            }
        });

        $('#datepicker').datepicker({
            format: 'dd/mm/yyyy',
            autoclose: true,
            todayHighlight: true
        });

        $('#btn-xem').click(function() {
            var selectedDate = $('#datepicker').val();
            // Simulate fetching data from server based on selected date
            var result = 'Thông tin thống kê cho ngày ' + selectedDate;
            $('#result').text(result);
            $('#result-container').show();
        });
    });
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap-datepicker@1.9.0/dist/js/bootstrap-datepicker.min.js"></script>
<script>
    $(document).ready(function() {
        $("#datepicker").datepicker({
            format: "dd/mm/yyyy", // Định dạng hiển thị ngày tháng
            autoclose: true, // Tự động đóng datepicker khi chọn ngày
            todayBtn: "linked", // Nút "Today" liên kết với ngày hôm nay
            clearBtn: true, // Hiển thị nút "Clear" để xóa ngày đã chọn
            language: "vi", // Ngôn ngữ hiển thị (có thể thay đổi thành mã ngôn ngữ khác)
            container: "#datepicker-container", // Định vị datepicker trong container có ID "datepicker-container"
        });
    });

</script>
<script>
    $(document).ready(function() {
        // Đặt radio-thang là checked mặc định
        $('#radio-thang').prop('checked', true);
        // Lấy tháng và năm hiện tại
        var currentDate = new Date();
        var currentMonth = currentDate.getMonth() + 1;
        var currentYear = currentDate.getFullYear();

        // Đặt giá trị mặc định cho datepicker
        $('#datepicker-month').val(currentMonth + '/' + currentYear);

        // Đặt giá trị mặc định cho result-thang
        var defaultResult = 'Thông tin thống kê tháng ' + currentMonth + '/' + currentYear;
        $('#result').text(defaultResult);

        $('input[name="thongke-option"]').change(function() {
            if ($(this).attr('id') === 'radio-thang') {
                $('#datepicker-container-m').show();
                $('#result-container-m').hide();
                $('#result').text(defaultResult);
            } else {
                $('#datepicker-container-m').hide();
                $('#result-container-m').hide();
            }
        });

        $('#datepicker-month').datepicker({
            format: 'mm/yyyy',
            autoclose: true,
            minViewMode: 'months'
        });
        // Tạo sự kiện onchange cho select month
        $('#datepicker-month').change(function() {
            var selectedMonth = $(this).val();
            $('#result').text('Thông tin thống kê tháng ' + selectedMonth);

            // Gửi giá trị selectedMonth đến servlet bằng Ajax
            $.ajax({
                type: 'GET',
                url: 'month_analysis',
                data: { selectedMonth: selectedMonth },
                dataType: 'json',
                success: function(response) {
                    // Xử lý dữ liệu doanh thu
                    var result = response.result;
                    var formattedResult = Number(result).toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
                    $('#doanhthuthang').text(formattedResult);
                    //xử lý dữ liệu đơn hàng
                    var order = response.orderNum;
                    $('#donhangthang').text(order);
                    //xử lý dữ liệu sản phẩm
                    var product = response.productNum;
                    $('#sanphamthang').text(product);
                    //xử lý dữ liệu vận chuyển
                    var ship = response.orderNumShipping;
                    $('#vanchuyenthang').text(ship);
                    //xử lý sản phẩm bán chạy
                    var productTableBody = $('#productTableBody');

                    // Xóa bỏ các sản phẩm hiện có trong <tbody>
                    productTableBody.empty();

                    // Lặp qua danh sách sản phẩm
                    for (var i = 0; i < response.products.length; i++) {
                        var product = response.products[i];
                        var productName = product.name;
                        var productImageSrc = product.image;
                        var productPrice = product.price;
                        var productQuantitySold = product.amountSell;

                        // Tạo các thành phần HTML cho mỗi sản phẩm
                        var productRow = $('<tr>');
                        var productImage = $('<img>').attr('src', productImageSrc).addClass('avatar avatar-sm me-3').css({
                            height: '50px',
                            width: '50px',
                            borderRadius: '50%'
                        });
                        var productNameElement = $('<h6>').addClass('mb-xl-0').css('padding-left', '10px').text(productName);
                        var productInfoDiv = $('<div>').addClass('d-flex flex-column justify-content-center').append(productNameElement);
                        var productDetailsDiv = $('<div>').addClass('d-flex px-2 py-1').append($('<div>').append(productImage), productInfoDiv);
                        var productDetailsColumn = $('<td>').append(productDetailsDiv);
                        var productPriceColumn = $('<td>').addClass('align-middle text-sm').append($('<h6>').addClass('mb-xl-0').text(productPrice));
                        var productQuantitySoldColumn = $('<td>').addClass('align-middle text-sm').append($('<h6>').addClass('mb-xl-0').text(productQuantitySold));
                        var productDetailLinkColumn = $('<td>').addClass('align-middle text-sm').append($('<h6>').addClass('mb-xl-0').append($('<i>').addClass('fa fa-eye').attr('aria-hidden', 'true')));

                        // Thêm các thành phần vào hàng sản phẩm
                        productRow.append(productDetailsColumn, productPriceColumn, productQuantitySoldColumn, productDetailLinkColumn);

                        // Thêm hàng sản phẩm vào <tbody>
                        productTableBody.append(productRow);
                    }
                },
                error: function(xhr, status, error) {
                    // Xử lý lỗi (nếu có)
                }
            });
        });

// Tự động truyền giá trị ban đầu của select month khi trang được tải
        $(document).ready(function() {
            var selectedMonth = $('#datepicker-month').val();
            $('#result').text('Thông tin thống kê tháng ' + selectedMonth);

            // Gửi giá trị selectedMonth đến servlet bằng Ajax
            $.ajax({
                type: 'GET',
                url: 'month_analysis',
                data: { selectedMonth: selectedMonth },
                dataType: 'json',
                success: function(response) {
                    // Xử lý dữ liệu doanh thu
                    var result = response.result;
                    var formattedResult = Number(result).toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
                    $('#doanhthuthang').text(formattedResult);
                    //xử lý dữ liệu đơn hàng
                    var order = response.orderNum;
                    $('#donhangthang').text(order);
                    //xử lý dữ liệu sản phẩm
                    var product = response.productNum;
                    $('#sanphamthang').text(product);
                    //xử lý dữ liệu vận chuyển
                    var ship = response.orderNumShipping;
                    $('#vanchuyenthang').text(ship);
                    //xử lý sản phẩm bán chạy
                    var productTableBody = $('#productTableBody');

                    // Xóa bỏ các sản phẩm hiện có trong <tbody>
                    productTableBody.empty();

                    // Lặp qua danh sách sản phẩm
                    for (var i = 0; i < response.products.length; i++) {
                        var product = response.products[i];
                        var productName = product.name;
                        var productImageSrc = product.image;
                        var productPrice = product.price;
                        var productQuantitySold = product.amountSell;
                        var productURL = product.url;

                        // Tạo các thành phần HTML cho mỗi sản phẩm
                        var productRow = $('<tr>');
                        var productImage = $('<img>').attr('src', productImageSrc).addClass('avatar avatar-sm me-3').css({
                            height: '50px',
                            width: '50px',
                            borderRadius: '50%'
                        });
                        var productNameElement = $('<h6>').addClass('mb-xl-0').css('padding-left', '10px').text(productName);
                        var productInfoDiv = $('<div>').addClass('d-flex flex-column justify-content-center').append(productNameElement);
                        var productDetailsDiv = $('<div>').addClass('d-flex px-2 py-1').append($('<div>').append(productImage), productInfoDiv);
                        var productDetailsColumn = $('<td>').append(productDetailsDiv);
                        var productPriceColumn = $('<td>').addClass('align-middle text-sm').append($('<h6>').addClass('mb-xl-0').text(productPrice));
                        var productQuantitySoldColumn = $('<td>').addClass('align-middle text-sm').append($('<h6>').addClass('mb-xl-0').text(productQuantitySold));
                        var productDetailLinkColumn = $('<td>').addClass('align-middle text-sm').append(
                            $('<h6>').addClass('mb-xl-0').append(
                                $('<a>').attr('href', productURL).append(
                                    $('<i>').addClass('fa fa-eye').attr('aria-hidden', 'true')
                                )
                            )
                        );


                        // Thêm các thành phần vào hàng sản phẩm
                        productRow.append(productDetailsColumn, productPriceColumn, productQuantitySoldColumn, productDetailLinkColumn);

                        // Thêm hàng sản phẩm vào <tbody>
                        productTableBody.append(productRow);
                    }
                },
                error: function(xhr, status, error) {
                    // Xử lý lỗi (nếu có)
                }
            });
        });


    });

</script>
</body>
</html>

