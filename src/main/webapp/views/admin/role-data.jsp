<%@ page import="java.util.List" %>
<%@ page import="model.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String message = request.getParameter("messageResponse");
    List<Role> roles = (List<Role>) request.getAttribute("roles");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Danh sách quyền</title>
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
                        <h1>Danh sách các quyền</h1>
                    </div>
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">Trang chủ</a></li>
                            <li class="breadcrumb-item active">Danh sách danh mục</li>
                        </ol>
                    </div>
                </div>
            </div><!-- /.container-fluid -->
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card ">
                            <div class="card-header">
                                    <button class="btn btn-primary" style="float: right;"><a href="<c:url value="/role-data?action=add"></c:url>" style="color: white">Thêm mới</a></button>
                            </div>
                            <div class="card-body" style="display: block; padding:0px ;">
                                <c:if test="${ messageResponse != null}">
                                    <div class="alert-${alert}" style="width: 36%;">${messageResponse}</div>
                                </c:if>
                                <form style="padding:10px">
                                    <table id="example1" class="table table-bordered table-striped" >
                                        <thead>
                                        <tr>
                                            <th>Mã quyền </th>
                                            <th>Tên quyền</th>
                                            <th>Số tài khoản</th>
                                            <th>Tác vụ</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                            <% int id; for (Role role : roles) { id = role.getId();%>
                                        <tr>
                                            <td><%=role.getId()%></td>
                                            <td><%=role.getName()%></td>
                                            <td><%=role.getNumUser()%></td>
                                            <td>
                                                <a class="btn btn-danger" href="role-data?action=delete&id=<%=id%>">Xoá</a>
                                                <a class="btn btn-success" href="role-data?action=edit&id=<%=id%>">Sửa</a>
                                            </td>
                                        </tr>
                                            <%}%>
                                        </tfoot>
                                    </table>
                                </form>
                            </div>
                            <!-- /.card-body -->
                        </div>
                    </div>
                </div>
                <!-- /.row -->
            </div><!-- /.container-fluid -->
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