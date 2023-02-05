<%@ page import="model.Product" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Danh sách sản phẩm</title>
  <jsp:include page="/common/admin/css.jsp"></jsp:include>
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
              </div>

              <!-- /.card-header -->
              <div class="card-body">
                <table id="example1" class="table table-bordered table-striped">

                  <thead>
                  <tr>
                    <th>Id sản phẩm</th>
                    <th>Tên sản phẩm </th>
                    <th>Giá nhập vào</th>
                    <th>Giá bán ra</th>
                    <th>Loại sản phẩm</th>
                    <th>Tác vụ</th>
                  </tr>
                  </thead>

                  <tbody>
                  <% List<Product> list0 = (List<Product>) request.getAttribute("listProduct");
                    for (Product p: list0
                    ) {%>
                  <tr>
                    <td><%=p.getProduct_id()%></td>
                    <td><%=p.getName()%></td>
                    <td><%=p.getPrice()%></td>
                    <td><%=p.getPrice_sell()%></td>
                    <td><%=p.getNType()%></td>
                    <td>
                      <button class="btn btn-info"><a href="/view_product?pid=<%=p.getProduct_id()%>" style="color: white"> Xem sản phẩm </a></button>
                      <button class="btn btn-danger" href>Xoá </button>
                      <button class="btn btn-success"><a href="/edit_product?pid=<%=p.getProduct_id()%>" style="color: white">Sửa sản phẩm </a></button>
                    </td>
                  </tr>
                  <%}%>
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

</body>
</html>