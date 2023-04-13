package dao;

import model.Log;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogDAO {

    public static void addLog(Log log){
        ResultSet rs;
        PreparedStatement pst;
        String sql;
        try {
            sql = "INSERT INTO log(`level`, `user`, src, content, createAt, `status`)  VALUES(?,?,?,?,NOW(),?)";
            pst = DBConnection.getConnection().prepareStatement(sql);
            pst.setInt(1, log.getLevel());
            pst.setInt(2, log.getUserId());
            pst.setString(3, log.getSrc());
            pst.setString(4, log.getContent());
            pst.setInt(5, log.getStatus());
            pst.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
