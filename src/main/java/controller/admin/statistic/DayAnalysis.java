package controller.admin.statistic;

import model.Order;
import model.Statics.ProductSellNum;
import org.json.JSONArray;
import org.json.JSONObject;
import service.ServiceStatistics;
import service.ServiceStatisticsDay;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "DayAnalysis", value = "/day_analysis")
public class DayAnalysis extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy dữ liệu từ request
        String selectedMonth = request.getParameter("selectedDate");
        try{
            // Tách chuỗi thành tháng và năm
            String[] parts = selectedMonth.split("/");
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);
            System.out.println(selectedMonth);

            //Tạo đối tượng ServiceStatisticsDay
            ServiceStatisticsDay serviceStatistics = new ServiceStatisticsDay();
            //lấy doanh thu theo tháng
            long total = serviceStatistics.getRevenueByMonthYear(day, month, year);
            //lấy số đơn hàng theo tháng
            int orderNum = serviceStatistics.getOrderNumByMonthYear(day, month, year);
            //lấy số sản phẩm đã bán theo tháng
            int productNum = serviceStatistics.getNumProductByMonthYear(day, month, year);
            //lấy đơn hàng theo tháng đang vận chuyển
            int orderNumShipping = serviceStatistics.getTransByMonthYear(day, month, year);
            //Chuyển sang kiểu String
            String totalString = String.valueOf(total);
            String orderNumString = String.valueOf(orderNum);
            String productNumString = String.valueOf(productNum);
            String orderNumShippingString = String.valueOf(orderNumShipping);
            //Lấy ra sp bán chạy
            ArrayList<ProductSellNum> productSellNums = (ArrayList<ProductSellNum>) serviceStatistics.getTopSellingProducts(day, month, year);
            // Tạo một đối tượng JSONArray để lưu trữ danh sách sản phẩm
            JSONArray jsonArray = new JSONArray();

            // Duyệt qua danh sách và tạo JSON object cho mỗi sản phẩm
            if(!productSellNums.isEmpty()){
                for (ProductSellNum product : productSellNums) {
                    JSONObject productJson = new JSONObject();
                    String productID = String.valueOf(product.getId());
                    String productURL = "/view_product?pid="+productID;
                    productJson.put("id", product.getId());
                    productJson.put("name", product.getName());
                    productJson.put("price", product.getPrice());
                    productJson.put("image", product.getImage());
                    productJson.put("amountSell", product.getAmountSell());
                    productJson.put("url", productURL);
                    // Thêm JSON object sản phẩm vào mảng
                    jsonArray.put(productJson);
                }
            }else {
                JSONObject productJson = new JSONObject();
                productJson.put("id", 0);
                productJson.put("name", "Không có sản phẩm nào");
                productJson.put("price", 0);
                productJson.put("image", "https://i.imgur.com/c20RsyT.jpeg");
                productJson.put("amountSell", 0);
                productJson.put("url", "productURL");
                // Thêm JSON object sản phẩm vào mảng
                jsonArray.put(productJson);
            }
            //Lấy ra đơn hàng theo tháng
            List<Order> orders = serviceStatistics.getOrdersByMonth(day, month, year);
            // Tạo một đối tượng JSONArray để lưu trữ danh sách sản phẩm
            JSONArray jsonArrayOrder = new JSONArray();
            if(!orders.isEmpty()){
                for (Order order : orders) {
                    JSONObject orderJson = new JSONObject();
                    String date = String.valueOf(order.getDate_order());
                    String convertDate = order.convertDate(date);
                    long totalMoney = order.getTotal_money();
                    int fee = order.getFee();
                    String formatTotalMoney = order.formatCurrency(totalMoney + fee);
                    orderJson.put("id", order.getOder_id());
                    orderJson.put("name", order.getFullName(order.getUser_name()));
                    orderJson.put("phone", order.getPhoneNum());
                    orderJson.put("date", convertDate);
                    orderJson.put("totalMoney", formatTotalMoney);
                    // Thêm JSON object sản phẩm vào mảng
                    jsonArrayOrder.put(orderJson);
                }
            } else {
                JSONObject orderJson = new JSONObject();
                orderJson.put("id", 0);
                orderJson.put("name", "Không có đơn hàng nào");
                orderJson.put("phone", "0");
                orderJson.put("date", "dd/MM/yyyy");
                orderJson.put("totalMoney", "0");
                // Thêm JSON object sản phẩm vào mảng
                jsonArrayOrder.put(orderJson);
            }

            //Nhập sản phẩm
            ArrayList<ProductSellNum> importProduct = (ArrayList<ProductSellNum>) serviceStatistics.getTopImportProducts(day, month, year);
            // Tạo một đối tượng JSONArray để lưu trữ danh sách sản phẩm
            JSONArray jsonArrayImport = new JSONArray();

            // Duyệt qua danh sách và tạo JSON object cho mỗi sản phẩm
            if(!importProduct.isEmpty()){
                for (ProductSellNum product : importProduct) {
                    JSONObject productJson = new JSONObject();
                    productJson.put("id", product.getId());
                    productJson.put("name", product.getName());
                    productJson.put("image", product.getImage());
                    productJson.put("amountSell", product.getAmountSell());
                    // Thêm JSON object sản phẩm vào mảng
                    jsonArrayImport.put(productJson);
                }
            }else {
                JSONObject productJson = new JSONObject();
                productJson.put("id", 0);
                productJson.put("name", "Không có sản phẩm nào");
                productJson.put("image", "https://i.imgur.com/c20RsyT.jpeg");
                productJson.put("amountSell", 0);
                // Thêm JSON object sản phẩm vào mảng
                jsonArrayImport.put(productJson);
            }


            // Tạo đối tượng JSON chứa kết quả
            JSONObject jsonObjectDay = new JSONObject();
            jsonObjectDay.put("resultDay", totalString);
            jsonObjectDay.put("orderNumDay", orderNumString);
            jsonObjectDay.put("productNumDay", productNumString);
            jsonObjectDay.put("orderNumShippingDay", orderNumShippingString);
            jsonObjectDay.put("productsDay", jsonArray);
            jsonObjectDay.put("ordersDay", jsonArrayOrder);
            jsonObjectDay.put("importProductDay", jsonArrayImport);
            response.getWriter().write(jsonObjectDay.toString());
        } catch (Exception e){
            e.printStackTrace();
        }


        // Gửi dữ liệu JSON về phía client
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
