package controller.admin.API;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.RoleDAO;
import model.RoleModel;
import util.HttpUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api-admin-role"})
public class RoleAPI extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        RoleModel roleModel =  HttpUtil.of(request.getReader()).toModel(RoleModel.class);
        RoleDAO.addRole(roleModel);
        mapper.writeValue(response.getOutputStream(), roleModel);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        RoleModel roleModel =  HttpUtil.of(request.getReader()).toModel(RoleModel.class);
        RoleDAO.updateRole(roleModel);
        mapper.writeValue(response.getOutputStream(), roleModel);
    }
}
