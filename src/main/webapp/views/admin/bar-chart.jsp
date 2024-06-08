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
            display: none;
        }

        #pickerContainer>form>label {
            margin-right: 5px;
        }
        #pickerContainer>form>input {
            margin-right: 20px;
        }
        #pickerContainer>div {
            width: fit-content;
        }
        #nodata {
            font-size: 50px;
            width: fit-content;
            margin: 10% auto;
        }
        #btn-loadData{
            text-wrap: nowrap;
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
                            <label>Bạn muốn thống kê theo:</label>
                            <div id="pickerContainer">
                                <form>
                                    <label for="yearRadio">Năm</label>
                                    <input type="radio" id="yearRadio" name="pickerType" value="year">
                                    <label for="monthRadio">Tháng</label>
                                    <input type="radio" id="monthRadio" name="pickerType" value="month" checked>
                                    <label for="dateRadio">Ngày</label>
                                    <input type="radio" id="dateRadio" name="pickerType" value="date">
                                </form>

                                <div id="yearPicker" class="picker">
                                    <select id="yearInput" class="form-control" >
                                    </select>
                                </div>
                                <div id="monthPicker" class="picker">
                                    <input type="month" id="monthInput" class="form-control">
                                </div>
                                <div id="datePicker" class="picker">
                                    <input type="date" id="startDate" class="form-control" style="margin-right: 30px">
                                    <input type="date" id="endDate" class="form-control" style="margin-right: 30px">
                                    <button class="btn btn-primary" id="btn-loadData">Thống kê</button>
                                </div>
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
    // đặt tháng mặc định cho lựa chọn tháng là tháng hiện tại
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
    setDefaultMonth();

    // đặt mặt định cho lựa chọn khoảng là ngày hiện tại và 10 ngày trước
    function setDefaultDates() {
        const startDateInput = document.getElementById("startDate");
        const endDateInput = document.getElementById("endDate");

        const today = new Date();
        const tenDaysAgo = new Date();
        tenDaysAgo.setDate(today.getDate() - 10);

        const formatDate = (date) => {
            const year = date.getFullYear();
            const month = (date.getMonth() + 1).toString().padStart(2, '0');
            const day = date.getDate().toString().padStart(2, '0');
            return year+"-"+month+"-"+day;
        };
        startDateInput.value = formatDate(tenDaysAgo);
        endDateInput.value = formatDate(today);
    }
    setDefaultDates();

    // đặt năm cho lựa chọn năm
    function setYears(){
        const xhr = new XMLHttpRequest();
        xhr.open("GET", "admin-order-date-years", true)
        xhr.onreadystatechange = function (){
            if(xhr.readyState === 4 && xhr.status === 200){
                const responseData = JSON.parse(xhr.responseText);
                const yearSelect = document.getElementById('yearInput');
                yearSelect.innerHTML = '<option value="0">Tất cả các năm</option>'; // Xóa các tùy chọn hiện tại
                responseData.years.forEach(year => {
                    const option = document.createElement('option');
                    option.value = year+"";
                    option.textContent = year;
                    yearSelect.appendChild(option);
                });
            }
        }
        xhr.send();

    }
    setYears();

    // ẩn tất cả các chọn thời gian
    function hideAllPicker() {
        var pickers = document.getElementsByClassName("picker");
        for(var i=0; i < pickers.length; i++){
            pickers[i].style.display = 'none';
        }
    }

    // hiện chọn tháng là mặc định
    document.getElementById("monthPicker").style.display='block';
    // Lắng nghe sự kiện khi người dùng thay đổi tháng trong hộp chọn ngày tháng, set lại số liệu thống kê
    document.addEventListener("DOMContentLoaded", function() {
        var radioButton = document.getElementsByName("pickerType");
        for(var i=0; i < radioButton.length; i++){
            radioButton[i].addEventListener('change', function (){
                var type = this.value;
                hideAllPicker();
                switch (type){
                    case "date":
                        document.getElementById("datePicker").style.display='flex';
                        const startDate = document.getElementById("startDate").value;
                        const endDate = document.getElementById("endDate").value;
                        setDataWithDate(startDate, endDate);
                        break;
                    case "month":
                        document.getElementById("monthPicker").style.display='block';
                        setDataWithMonth(document.getElementById("monthInput").value);
                        break;
                    case "year":
                        document.getElementById("yearPicker").style.display='block';
                        setDataWithYear(document.getElementById("yearInput").value);
                        break;
                }
            });
        }

    });


    // biểu đồ
    var currentChart;
    function setDataBarChart(labels, data, type){
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
                            text: type // Đơn vị cho trục x
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
    // cập nhật dữ liệu biểu đồ cột
    function updateChartData(labels, data, type) {
        notifyNoData(data);
        if (currentChart) {
            currentChart.data.labels = labels;
            currentChart.data.datasets.forEach((dataset) => {
                dataset.data = data;
            });
            currentChart.options.scales = {
                x: {
                    title: {
                        display: true,
                        text: type // Đơn vị cho trục x
                    }
                }
            };

            currentChart.update();
        }
    }

    // hiển thị không có dữ liệu
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

    // dữ liệu mặc định
    const barLabels = <%= new Gson().toJson(labels) %>;
    const barData = <%= new Gson().toJson(data) %>;
    setDataBarChart(barLabels, barData, 'Ngày');

    // gửi request để lấy dữ liệu
    function setDataWithMonth(yearMonth){
        const xhr = new XMLHttpRequest();
        xhr.open("POST", "admin-total-revenue", true)
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function (){
            if(xhr.readyState === 4 && xhr.status === 200){
                const responseData = JSON.parse(xhr.responseText);
                updateChartData(responseData.labels, responseData.data, "Ngày");
            }
        }
        xhr.send("type=month&month=" + yearMonth);
    }
    function setDataWithDate(startDate, endDate){
        const xhr = new XMLHttpRequest();
        xhr.open("POST", "admin-total-revenue", true)
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function (){
            if(xhr.readyState === 4 && xhr.status === 200){
                const responseData = JSON.parse(xhr.responseText);
                updateChartData(responseData.labels, responseData.data, "Ngày");
            }
        }
        xhr.send("type=date&startDate=" + startDate +"&endDate=" + endDate);
    }
    function setDataWithYear(year){
        const xhr = new XMLHttpRequest();
        xhr.open("POST", "admin-total-revenue", true)
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function (){
            if(xhr.readyState === 4 && xhr.status === 200){
                const responseData = JSON.parse(xhr.responseText);
                if(year=='0'){
                    updateChartData(responseData.labels, responseData.data, "Năm");
                }else{
                    updateChartData(responseData.labels, responseData.data, "Tháng");
                }
            }
        }
        xhr.send("type=year&year=" + year);
    }

    // cập nhật dữ liệu khi nguời dùng chọn tháng
    document.addEventListener("DOMContentLoaded", function() {
        const monthPicker = document.getElementById("monthInput");
        monthPicker.addEventListener("change", function() {
            // lay ngay da chon
            const selectedDate = monthPicker.value;
            setDataWithMonth(selectedDate);
        });
    });
    // cập nhật dữ liệu khi người dùng chọn khoảng ngày
    document.addEventListener("DOMContentLoaded", function() {
        const btnLaodData = document.getElementById("btn-loadData");
        btnLaodData.addEventListener("click", function() {
            // lay ngay da chon
            const startDate = document.getElementById("startDate").value;
            const endDate = document.getElementById("endDate").value;
            setDataWithDate(startDate, endDate);
        });
    });
    // cập nhật dữ liệu khi người dùng chọn năm
    document.addEventListener("DOMContentLoaded", function() {
        const selectYear = document.getElementById("yearInput");
        selectYear.addEventListener("change", function() {
            setDataWithYear(this.value);
        });
    });

</script>
</body>
</html>