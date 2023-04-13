package controller.admin;

import controller.admin.datatable.DataTable;
import mapper.UserMapper;
import model.Log;
import model.UserModel;
import service.LogService;
import service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "DataUserController", value = "/data-user")
public class DataUserController extends HttpServlet {
    String name="List-User";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        int id = 0;
        UserModel currentUser = (UserModel) request.getSession().getAttribute("auth");
        Log log = new Log(Log.INFO,currentUser.getId(),this.name,"",0);
        if(request.getParameter("id") != null ) {id = Integer.parseInt(request.getParameter("id"));}
        if(action == null){
            request.getRequestDispatcher("views/admin/user-data.jsp").forward(request, response);
            return;
        }
        if(action.equals("list") ){
            List<UserModel> users = UserService.findAll();
            log.setContent(users.toString());
            LogService.addLog(log);
            request.setAttribute("users",users);
            request.getRequestDispatcher("views/admin/user-data.jsp").forward(request, response);
            return;
        }
        if(action.equals("edit")){
            UserModel user = UserService.findById(id);
            log.setSrc(this.name+" VIEW ");
            log.setContent(user.toString());
            LogService.addLog(log);
            request.setAttribute("user",user);
            request.getRequestDispatcher("views/admin/edit-user.jsp").forward(request, response);
            return;
        }
        if(action.equals("add")){
            request.getRequestDispatcher("views/admin/add-user.jsp").forward(request, response);
            return;
        }
        if(action.equals("delete")){
            UserModel deleteUser = UserService.findById(id);
            UserService.delete(id);
            log.setSrc(this.name+" DELETE ");
            log.setContent(deleteUser.toString());
            log.setLevel(Log.WARNING);
            LogService.addLog(log);
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

        UserModel currentUser = (UserModel) request.getSession().getAttribute("auth");
        Log log = new Log(Log.INFO,currentUser.getId(),this.name,"",0);

        if(request.getParameter("id") != null ){ id = Integer.parseInt(request.getParameter("id"));}
        if(request.getParameter("role") != null ) {role = Integer.parseInt(request.getParameter("role"));}
        if(action.equals("edit")){
            UserModel oldUser = UserService.findById(id);
            UserModel newUser = new UserModel(id,oldUser.getUserName(),oldUser.getPassWord(),role,full_name,oldUser.getPhoneNum(),email,oldUser.getAddress(),0,oldUser.getGender());
            UserService.updateAdmin(newUser,enable);
            log.setSrc(this.name+" EDIT ");
            log.setContent(newUser.toString());
            LogService.addLog(log);
            request.setAttribute("success","Cập nhật thành công");
            request.setAttribute("user",newUser);
            request.getRequestDispatcher("views/admin/edit-user.jsp").forward(request, response);
            return;
        }
        if(action.equals("add")){
            log.setSrc(this.name+" ADD ");
            if(UserService.checkUserName(username)){
                log.setContent("ADD USER FAIL");
                LogService.addLog(log);
                request.setAttribute("error","Tên tài khoản đã tồn tại");
                request.getRequestDispatcher("views/admin/add-user.jsp").forward(request, response);
                return;
            }
            UserModel user = new UserModel(0,username,password,role,"","",email,"",1,"");
            UserService.save(user);
            log.setContent("ADD USER SUCCESS"  + user.toString());
            LogService.addLog(log);
            request.setAttribute("success","Thêm thành công");
            request.getRequestDispatcher("views/admin/add-user.jsp").forward(request, response);
        }
    }
}
