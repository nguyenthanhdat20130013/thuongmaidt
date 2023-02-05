package controller.admin;

import model.UserModel;
import service.UserService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginControllerAdmin", value = "/admin-login")
public class LoginControllerAdmin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("views/admin/login.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserModel user = UserService.checkLogin(username, password);
        if (user != null) {
            if (user.getEnable() != 1) {
                request.setAttribute("error", "Tài khoản của bạn đã bị khoá");
                request.getRequestDispatcher("views/admin/login.jsp").forward(request, response);
                return;
            }
            if (user.checkRole(1)) {
                request.getSession().setAttribute("auth", user);
                response.sendRedirect("admin-home");
            } else {
                request.setAttribute("error", "Bạn không có quyền truy cập.");
                request.getRequestDispatcher("views/admin/login.jsp").forward(request, response);
            }
        } else{
            request.setAttribute("error", "Thông tin đăng nhập không hợp lệ.");
            request.getRequestDispatcher("views/admin/login.jsp").forward(request, response);
        }
    }
}
