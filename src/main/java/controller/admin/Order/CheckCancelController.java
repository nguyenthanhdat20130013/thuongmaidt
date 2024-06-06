package controller.admin.Order;

import controller.admin.Access;
import controller.admin.IpAddress;
import dao.LogDAO;
import dao.RoleDAO;
import model.CancelOrder;
import model.Log;
import model.Role;
import model.UserModel;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CheckCancelController", value = "/admin_cancel_order")
public class CheckCancelController extends HttpServlet {
    String name = "List-Check-Order";
    String listAccess = "xem danh sách duyệt đơn hàng";

    String checkAccess = "duyệt đơn hàng";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserModel currentUser = (UserModel) request.getSession().getAttribute("auth");
        Role roleUser = RoleDAO.findById(currentUser.getRole());
        boolean access = Access.checkAccess(roleUser.getPermission(),RoleDAO.findIdPermissionByName(listAccess));
        if(!access){
            request.getRequestDispatcher("views/admin/no-permission.jsp").forward(request, response);
            return;
        }
        Log log = new Log(Log.INFO,currentUser.getId(),this.name,"",0, IpAddress.getClientIpAddr(request));
        boolean checkPm = Access.checkAccess(roleUser.getPermission(),RoleDAO.findIdPermissionByName(checkAccess));
        OrderService orderService = new OrderService();
        orderService.updateOrderStatusByTransportLeadTime();
        List<CancelOrder> listOrders = orderService.getAllOderCancel();
        log.setContent(listOrders.toString());
        LogDAO.addLog(log);
        request.setAttribute("checkPm",checkPm);
        request.setAttribute("listOrders",listOrders);
        request.getRequestDispatcher("/views/admin/checkcancel-data.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
