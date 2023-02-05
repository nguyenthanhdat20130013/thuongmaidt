<%@ page import="model.UserModel" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  List<UserModel> users = (List<UserModel>) request.getAttribute("users");
%>
<c:url var="UrlAction" value="/data-user?action=delete"/>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Danh sách user</title>
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
            <h1>Danh sách người dùng</h1>
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">Danh sách người dùng</li>
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
                  <select class="form-control " >
                    <option selected="selected" disabled="disabled">Tìm kiếm theo</option>
                    <option>Sản Phẩm mới</option>
                    <option>Sản Phẩm hot</option>
                    <option>Hết hàng</option>
                    <option>Texas</option>
                    <option>Washington</option>
                  </select>
                </div>
                <button class="btn btn-primary" style="float: right;"><a href="<c:url value="/data-user?action=add"></c:url>" style="color: white">Thêm mới</a></button>
              </div>

              <!-- /.card-header -->
              <div class="card-body">
                <c:if test="${success != null}">
                  <div class="alert-success" style="width: 36%;">${success}</div>
                </c:if>
                <table id="example1" class="table table-bordered table-striped">
                  <thead>
                  <tr>
                    <th>Tên tài khoản</th>
                    <th>Email</th>
                    <th>Quyền</th>
                    <th>Tình trạng</th>
                    <th>Tác vụ</th>
                  </tr>
                  </thead>
                  <tbody>
                  <% for (UserModel user : users) {%>
                  <tr>
                    <td><%=user.getUserName()==null?"Dữ liệu đang cập nhật":user.getUserName()%></td>
                    <td><%=user.getEmail()==null?"Dữ liệu đang cập nhật":user.getEmail()%></td>
                    <% if(user.getRole() == 0)  {%>
                    <td>user</td>
                    <% } else if(user.getRole() == 1){%>
                    <td> mod</td>
                    <% } else {%>
                    <td> admin </td>
                    <% } %>
                    <% if(user.getEnable() == 1)  {%>
                    <td> mở</td>
                    <% } else {%>
                    <td> khoá</td>
                    <% } %>
                    <td>
                      <a class="btn btn-danger" id="delete" href="data-user?action=delete&id=<%=user.getId()%>">Xoá </a>
                      <a class="btn btn-success"  href="data-user?action=edit&id=<%=user.getId()%>">Sửa </a>
                    </td>
                  </tr>
                  <% } %>
                  </tbody>
                  <tfoot>
                  <tr>
                    <th>Tên tài khoản</th>
                    <th>Email</th>
                    <th>Quyền</th>
                    <th>Tình trạng</th>
                    <th>Tác vụ</th>
                  </tr>
                  </tfoot>
                </table>
              </div>
              <!-- /.card-body -->
            </div>
            <!-- /.card -->
          </div>
          <!-- /.col -->
        </div>
        <!-- /.row -->
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
  /* $('#delete').click(function (e) {
     e.preventDefault();
     $.ajax({
       type: "POST",
       url: '${UrlAction}',
      data: form.serialize(),
      success: function () {
        alert( "Xoá thành công" );
      },
      error: function (error){
        console.log(error);
      }
    });
  });*/
</script>
</body>
</html>