package controller.admin.API;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.UserDAO;
import model.UserModel;
import util.HttpUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api-admin-user"})
public class UserAPI extends HttpServlet {

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        UserModel userModel =  HttpUtil.of(request.getReader()).toModel(UserModel.class);
        UserDAO.deletes(userModel);
        mapper.writeValue(response.getOutputStream(), "{}");
    }
}
