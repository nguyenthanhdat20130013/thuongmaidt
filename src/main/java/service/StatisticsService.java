package service;

import dao.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticsService {

    public static Map<String, Double> statisticsByCategory(int month, int year) {
        Map<String, Double> totalPriceMap = new HashMap<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql;
        try {
            sql = "SELECT pt.type_name AS type_name, SUM(od.total) AS toltal_price "+
            "FROM orders o "+
            "JOIN order_detail od ON o.order_id = od.id_oder "+
            "JOIN products p ON od.id_product = p.product_id "+
            "JOIN product_type pt ON p.product_type = pt.type_id "+
            "WHERE MONTH(o.date_order) = "+month+" AND YEAR(o.date_order) = "+year+" AND o.`status` = 2 "+
            "GROUP BY pt.type_id";

            ps = DBConnection.getConnection().prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                totalPriceMap.put(rs.getString("type_name"), rs.getDouble("toltal_price"));
            }
            return totalPriceMap;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static Map<String, Integer> statisticsRevenueByDay(int month, int year) {
        Map<String, Integer> result = new HashMap<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql;
        try {
            sql = "SELECT SUM(o.total_money) AS total_money, DATE(o.date_order) AS times FROM orders o " +
                    "WHERE MONTH(o.date_order) = "+month+" AND YEAR(o.date_order) = "+year+" AND o.`status` = 2 "+
                    "GROUP BY DATE(o.date_order)";

            ps = DBConnection.getConnection().prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                result.put(rs.getString("times"), rs.getInt("total_money"));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static Map<String, Double> statisticsRevenueByMonth(int year) {
        Map<String, Double> result = new HashMap<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql;
        try {
            sql = "SELECT SUM(o.total_money) AS total_money, MONTH(o.date_order) AS statistics_month FROM orders o " +
                    "WHERE YEAR(o.date_order) = "+year+" AND o.`status` = 2 "+
                    "GROUP BY MONTH(o.date_order) ";

            ps = DBConnection.getConnection().prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                result.put(rs.getString("statistics_month"), rs.getDouble("total_money"));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, Integer> statisticsRevenueBySpace(String startDate, String endDate) {
        Map<String, Integer> result = new HashMap<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql;
        try {
            sql = "SELECT SUM(o.total_money) AS total_money, DATE(o.date_order) AS times FROM orders o " +
                    "WHERE DATE(o.date_order) BETWEEN ? AND ? AND o.`status`=2 " +
                    "GROUP BY DATE(o.date_order)";
            ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setString(1, startDate);
            ps.setString(2, endDate);
            rs = ps.executeQuery();
            while (rs.next()) {
                result.put(rs.getString("times"), rs.getInt("total_money"));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, Integer> statisticsRevenueByYear(int year) {
        Map<String, Integer> result = new HashMap<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql;
        try {
            if(year == 0){
                sql = "SELECT SUM(o.total_money) AS total_money, YEAR(o.date_order) AS times FROM orders o " +
                        "WHERE o.`status`= 2 " +
                        "GROUP BY YEAR(o.date_order) ";
                ps = DBConnection.getConnection().prepareStatement(sql);
            }else{
                sql = "SELECT SUM(o.total_money) AS total_money, CONCAT(YEAR(o.date_order),\"-\",LPAD(MONTH(o.date_order), 2, '0')) AS times FROM orders o " +
                        "WHERE YEAR(o.date_order) = "+year+" AND o.`status`=2 " +
                        "GROUP BY MONTH(o.date_order) ";
                ps = DBConnection.getConnection().prepareStatement(sql);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                result.put(rs.getString("times"), rs.getInt("total_money"));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> getAllYears(){
        List<String> result = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql;
        try {
            sql = "SELECT YEAR(o.date_order) AS years FROM orders o " +
                    "GROUP BY YEAR(o.date_order) ";
            ps = DBConnection.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                result.add(rs.getString("years"));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
