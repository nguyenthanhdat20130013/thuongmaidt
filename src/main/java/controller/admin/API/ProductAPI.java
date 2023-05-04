package controller.admin.API;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.ProductSearchDAO;
import model.Product;
import util.HttpUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api-admin-product"})
public class ProductAPI  extends HttpServlet {
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        Product product =  HttpUtil.of(request.getReader()).toModel(Product.class);
        ProductSearchDAO.deletes(product);
        mapper.writeValue(response.getOutputStream(), "{}");
    }
}
