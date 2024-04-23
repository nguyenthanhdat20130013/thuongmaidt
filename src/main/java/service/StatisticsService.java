package service;

import dao.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
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
            "WHERE MONTH(o.date_order) = "+month+" AND YEAR(o.date_order) = "+year+" "+
            "GROUP BY pt.type_id";

            ps = DBConnection.getConnection().prepareStatement(sql);
//            ps.setInt(1, month);
//            ps.setInt(2, year);
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

    public static void main(String[] args) {
        LocalDate currentDate = LocalDate.now();
        Map<String, Double> result = statisticsByCategory(currentDate.getMonthValue(), currentDate.getYear());
        for (String s : result.keySet()) {
            System.out.println(s + "_____" + result.get(s));
        }
    }
}
