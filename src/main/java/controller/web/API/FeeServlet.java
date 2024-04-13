package controller.web.API;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import service.API_LOGISTIC.GetFee;
import service.API_LOGISTIC.Login_API;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

import static service.API_LOGISTIC.GetFee.calculateShippingFee;

@WebServlet(name = "FeeServlet", urlPatterns = "/FeeServlet")
public class FeeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String provinceId = request.getParameter("province");
        String districtId = request.getParameter("district");
        String wardId = request.getParameter("ward");
        // Xử lý dữ liệu ở đây
        String token = Login_API.login();
        int fromDistrictId = 3695;
        String fromWardCode = "90737";

        int toDistrictId = Integer.parseInt(districtId);
        String toWardCode = wardId;
        int serviceId = 53320;
        Integer serviceTypeId = null;
        int height = 50;
        int length = 20;
        int width = 20;
        int weight = 200;
        int insuranceValue = 10000;
        int codFailedAmount = 2000;
        String coupon = null;

       double shippingFee = 0;
        try {
             shippingFee = calculateShippingFee(token, fromDistrictId, fromWardCode, toDistrictId, toWardCode, serviceId, serviceTypeId, height, length, width, weight, insuranceValue, codFailedAmount, coupon
            );

          //  System.out.println("Shipping Fee: " + shippingFee);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        // Trả về phí chuyển hàng dưới dạng chuỗi
        String shippingFeeStr = String.valueOf(shippingFee);
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(shippingFeeStr);
    }


}
