package controller.admin;

import model.UserModel;
import service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DataUserController", value = "/data-user")
public class DataUserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        int id = 0;
        if(request.getParameter("id") != null ) {id = Integer.parseInt(request.getParameter("id"));}
        if(action == null){
            List<UserModel> users = UserService.findAll();
            request.setAttribute("users",users);
            request.getRequestDispatcher("views/admin/user-data.jsp").forward(request, response);
            return;
        }
        if(action.equals("list") ){
            List<UserModel> users = UserService.findAll();
            request.setAttribute("users",users);
            request.getRequestDispatcher("views/admin/user-data.jsp").forward(request, response);
            return;
        }
        if(action.equals("edit")){
            UserModel user = UserService.findById(id);
            request.setAttribute("user",user);
            request.getRequestDispatcher("views/admin/edit-user.jsp").forward(request, response);
            return;
        }
        if(action.equals("add")){
            request.getRequestDispatcher("views/admin/add-user.jsp").forward(request, response);
            return;
        }
        if(action.equals("delete")){
            UserService.delete(id);
            List<UserModel> users = UserService.findAll();
            request.setAttribute("users",users);
            request.setAttribute("success","Xoá thành công");
            request.getRequestDispatcher("views/admin/user-data.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        int id = 0;
        int role = 0;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String full_name = request.getParameter("full_name");
        String enable = request.getParameter("enable");
        if(request.getParameter("id") != null ){ id = Integer.parseInt(request.getParameter("id"));}
        if(request.getParameter("role") != null ) {role = Integer.parseInt(request.getParameter("role"));}
        if(action.equals("edit")){
            UserModel oldUser = UserService.findById(id);
            UserModel newUser = new UserModel(id,oldUser.getUserName(),oldUser.getPassWord(),role,full_name,oldUser.getPhoneNum(),email,oldUser.getAddress(),0,oldUser.getGender());
            UserService.updateAdmin(newUser,enable);
            request.setAttribute("success","Cập nhật thành công");
            request.setAttribute("user",newUser);
            request.getRequestDispatcher("views/admin/edit-user.jsp").forward(request, response);
            return;
        }
        if(action.equals("add")){
            if(UserService.checkUserName(username)){
                request.setAttribute("error","Tên tài khoản đã tồn tại");
                request.getRequestDispatcher("views/admin/add-user.jsp").forward(request, response);
                return;
            }
            UserModel user = new UserModel(0,username,password,role,"","",email,"",1,"");
            UserService.save(user);
            request.setAttribute("success","Thêm thành công");
            request.getRequestDispatcher("views/admin/add-user.jsp").forward(request, response);
        }
    }
}
