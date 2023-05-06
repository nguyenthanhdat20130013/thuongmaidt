package controller.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import dao.ImportProductDAO;
import model.ImportProduct;
import model.UserModel;
import util.HttpUtil;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ImportProductController",value = "/import-product")
public class ImportProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("views/admin/import-product.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        UserModel currentUser = (UserModel)request.getSession().getAttribute("auth");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ImportProduct importProduct =  HttpUtil.of(request.getReader()).toModel(ImportProduct.class);
        //importProduct.setUsername();
        ImportProductDAO.importProducts(importProduct,currentUser.getUserName());
        mapper.writeValue(response.getOutputStream(), "{}");
    }
}