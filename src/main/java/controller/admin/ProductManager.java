package controller.admin;

import model.Log;
import model.Product;

import model.Product_type;
import model.UserModel;
import service.LogService;
import service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet(name = "ProductManager", value = "/product_manager")
public class ProductManager extends HttpServlet {
    String name = "List-Product";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserModel currentUser = (UserModel) request.getSession().getAttribute("auth");
        Log log = new Log(Log.INFO,currentUser.getId(),this.name,"",0,IpAddress.getClientIpAddr(request));
        LogService.addLog(log);
        ProductService service = new ProductService();
        List<Product> pro = service.getAllProduct();
        request.setAttribute("listProduct", pro);
        request.getRequestDispatcher("views/admin/data-product.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
