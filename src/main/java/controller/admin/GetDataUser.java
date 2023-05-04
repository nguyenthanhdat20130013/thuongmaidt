package controller.admin;

import controller.admin.datatable.DataTable;
import mapper.UserMapper;
import model.UserModel;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "GetDataUser", value = "/GetDataUser")
public class GetDataUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Map<String, String> parameterMap = getParameterMap(request);
        String users;
        try {
            long start = Long.parseLong(parameterMap.get("start"));
            int length = Integer.parseInt(parameterMap.get("length"));
            int draw = Integer.parseInt(parameterMap.get("draw"));
            users = new DataTable<UserModel>().table("users",draw ,start, length).build(UserModel.class, new UserMapper(),"uid");
            out.println(users);
            out.flush();
        } catch (NumberFormatException e){
            out.println("Error");
            out.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private Map<String, String> getParameterMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String value = request.getParameter(name);
            map.put(name, value);
        }
        return map;
    }
}
