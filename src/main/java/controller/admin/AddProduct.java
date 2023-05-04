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
import java.util.List;

@WebServlet(name = "AddProduct", value = "/add_product")
public class AddProduct extends HttpServlet {
    String name ="Add-Product";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService service = new ProductService();
        List<Product_type> listType = service.getAllProduct_type();
        request.setAttribute("listType", listType);
        request.getRequestDispatcher("/views/admin/add-product.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserModel currentUser = (UserModel) request.getSession().getAttribute("auth");
        Log log = new Log(Log.INFO,currentUser.getId(),this.name,"",0,IpAddress.getClientIpAddr(request));
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        //cai nay la lay du lieu tu form gui len
        int pid = Integer.parseInt(request.getParameter("id"));
        String pcode = request.getParameter("code");
        String pname = request.getParameter("ten");
        String pprice = request.getParameter("gianhap");
        String pprice_sell = request.getParameter("giaban");
        String ptype = request.getParameter("famille");
        String pbrand = request.getParameter("hangsx");
        String pcolor = request.getParameter("mausac");
        String psize = request.getParameter("kichthuoc");
        String pinsurance = request.getParameter("baohanh");
        String pattribute = request.getParameter("thuoctinh");
        String pstatus = request.getParameter("trangthai");
        String pinfo = request.getParameter("mota");
        //su ly de add product
        Product p = new Product(pid, pname, Integer.parseInt(pprice), Integer.parseInt(pprice_sell), pinfo, pcode, pbrand, pcolor, psize, pattribute, Integer.parseInt(pstatus), Integer.parseInt(ptype), pinsurance);
        ProductService ser = new ProductService();
        ser.addProduct(p);
        log.setContent(p.toString());
        log.setLevel(Log.ALERT);
        LogService.addLog(log);
        response.sendRedirect("/product_manager");
    }
}
