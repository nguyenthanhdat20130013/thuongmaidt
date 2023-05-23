package service;

import dao.DBConnection;
import model.Product;
import model.Statics.ProductSellNum;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ServiceStatistics {

    public List<ProductSellNum> getTopSellingProducts(int month, int year) {
        List<ProductSellNum> productSellNums = new ArrayList<>();
        String sql = "SELECT od.id_product, SUM(od.amount) AS total_amount " +
                "FROM order_detail od " +
                "JOIN `orders` o ON od.id_oder = o.order_id " +
                "WHERE MONTH(o.date_order) = ? " +
                "AND YEAR(o.date_order) = ? " +
                "GROUP BY od.id_product " +
                "ORDER BY total_amount DESC " +
                "LIMIT 10";
        try (PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql)) {
            statement.setInt(1, month);
            statement.setInt(2, year);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int idProduct = resultSet.getInt("id_product");
                    int amountSell = resultSet.getInt("total_amount");
                    ProductSellNum productSellNum = new ProductSellNum(idProduct, amountSell);
                    productSellNums.add(productSellNum);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return productSellNums;
    }
    public long getRevenueByMonthYear(int month, int year) {
        long revenues =0;
        String sql = "SELECT SUM(total_money) AS `Revenue` " +
                "FROM orders " +
                "WHERE MONTH(date_order) = ? AND YEAR(date_order) = ?";
        try (PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql)) {
            statement.setInt(1, month);
            statement.setInt(2, year);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    revenues = resultSet.getInt("Revenue");

                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return revenues;
    }
    public int getOrderNumByMonthYear(int month, int year) {
        int revenues =0;
        String sql = "SELECT COUNT(order_id) AS `Revenue` " +
                "FROM orders " +
                "WHERE MONTH(date_order) = ? AND YEAR(date_order) = ?";
        try (PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql)) {
            statement.setInt(1, month);
            statement.setInt(2, year);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    revenues = resultSet.getInt("Revenue");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return revenues;
    }
    public int getNumProductByMonthYear(int month, int year) {
        int num =0;
        String sql = "SELECT SUM(od.amount)  FROM order_detail od JOIN orders o ON o.order_id = od.id_oder WHERE MONTH(o.date_order) = ? AND YEAR(o.date_order) = ?";
        try (PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql)) {
            statement.setInt(1, month);
            statement.setInt(2, year);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    num = resultSet.getInt(1);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return num;
    }
    public int getTransByMonthYear(int month, int year) {
        int trans =0;
        String sql = "SELECT COUNT(order_id)  FROM orders o WHERE MONTH(o.date_order) = ? AND YEAR(o.date_order) = ? AND o.status = 1;";
        try (PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql)) {
            statement.setInt(1, month);
            statement.setInt(2, year);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    trans = resultSet.getInt(1);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return trans;
    }



    public static void main(String[] args) {
        ServiceStatistics serviceStatistics = new ServiceStatistics();
//        System.out.println(serviceStatistics.getTransByMonthYear(5,2023));
        ArrayList<ProductSellNum> productSellNums = (ArrayList<ProductSellNum>) serviceStatistics.getTopSellingProducts(5, 2023);
        System.out.println(productSellNums.isEmpty());
        if(!productSellNums.isEmpty()){
            System.out.println("1");
        }else {
            System.out.println("2");
        }
//        for (ProductSellNum productSellNum : productSellNums) {
//            System.out.println(productSellNum.getId() + " " + productSellNum.getAmountSell() + " " + productSellNum.getName());
//        }
        String productID = String.valueOf(productSellNums.get(0).getId());
        String productURL = "<c:url value='/view_product?pid="+productID+"' />";
        System.out.println(productURL);
    }

}
