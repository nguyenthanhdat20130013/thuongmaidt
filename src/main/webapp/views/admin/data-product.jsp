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
            <h1>Danh sách sản phẩm</h1>
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
            <div class="card">
              <div class="card-header" style="margin-top: 10px;margin-bottom: -10px">
                <div class="form-group" style="width: 50%;float: left">
                  <select class="form-control ">
                    <option selected="selected" disabled="disabled">Tìm kiếm theo</option>
                    <option>Sản Phẩm mới</option>
                    <option>Sản Phẩm hot</option>
                  </select>

                </div>
                <button class="btn btn-primary" style="float: right;"><a href="<c:url value="/add_product"/>" style="color: white">Thêm mới</a></button>
                <button id="delete-btn" class="btn btn-danger" style="float: right;margin-right: 10px">Xoá</button>
              </div>

              <!-- /.card-header -->
              <div class="card-body">
                <table id="product-data" class="table table-bordered table-striped">

                  <thead>
                  <tr>
                    <th>Id sản phẩm</th>
                    <th>Tên sản phẩm </th>
                    <th>Giá nhập vào</th>
                    <th>Giá bán ra</th>
                    <th>Loại sản phẩm</th>
                    <th>Tác vụ</th>
                    <th><input type="checkbox" id="checkAll"></th>
                  </tr>
                  </thead>

                  <tbody>

                  <tr>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                  </tr>
                  </tbody>

                </table>
              </div>
              <!-- /.card-body -->
            </div>
            <!-- /.card -->
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
<script>
  var table = $('#product-data').DataTable({
    processing: true,
    serverSide: true,
    select: true,
    "buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"],
    "paging": true,
    "lengthChange": false,
    "lengthChange": false,
    "searching": true,
    "ordering": true,
    "info": true,
    "autoWidth": false,
    "responsive": true,
    ajax: '/GetDataProduct',
    columns:[
      {data: 'product_id', name: 'product_id'},
      {data: 'name', name: 'name'},
      {data: 'price', name: 'price'},
      {data: 'price_sell', name: 'price_sell'},
      {data: 'product_type', name: 'product_type'},
      {data: 'product_id', name: 'action',render: function (data) {
          return  '<button class="btn btn-success"><a href="/edit_product?pid=' + data +  '"' + 'style="color: white">Sửa sản phẩm </a></button>' +
          '<button class="btn btn-info"> <a href="/view_product?pid=' + data + '"' +  'style="color: white" ' + '> Xem sản phẩm </a></button>';
        }},
      {data: 'product_id', name: 'action',render: function (data) {
          return '<input type="checkbox" value="'+ data + '"' +'>';
        }},
    ]
  });



  $('#checkAll').click(function (e) {
    $('#product-data tbody :checkbox').prop('checked', $(this).is(':checked'));
    e.stopImmediatePropagation();
  });

  $("#delete-btn").click(function(e) {
    //table.row.delete( $('input[type=checkbox]:checked').parents('tr')).draw().show().draw(false);
    e.preventDefault(); // avoid to execute the actual submit of the form.
    var data = {};
    var ids = $('#product-data tbody input[type=checkbox]:checked').map(function () {
      return $(this).val();
    }).get();
    $('input[type=checkbox]:checked').parents('tr').remove();
    data['ids'] = ids;
    var actionUrl = '${APIurl}';
    deleteUser(data,actionUrl);
  });

  function deleteUser(data,actionUrl){
    $.ajax({
      type: "DELETE",
      url: actionUrl,
      contentType: "application/json",
      dataType: "json",
      data: JSON.stringify(data), // javacript object to json
      success: function (result){
        //window.location.href = "/data-user?message=delete_success";
        alert("xoá thành công");
      },
      error: function (error){
        console.log(error);
        alert("đã có lỗi xảy ra");
        //window.location.href = "/data-user?message=error_system";
      }
    });
  }
</script>
</body>
</html>