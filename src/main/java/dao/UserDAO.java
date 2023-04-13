package dao;

import model.UserModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

public class UserDAO {

    public static UserModel findLogin(String username, String password) {
        UserModel user = null;
        ResultSet rs;
        PreparedStatement pst;
        String sql;
        try {
            sql = "select uid,user_name,password,role,enable from USERS where user_name = ? and password = ?";
            pst = DBConnection.getConnection().prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            rs = pst.executeQuery();
            while (rs.next()) {
                user = new UserModel(rs.getInt("uid"), rs.getString("user_name"), rs.getString("password"), rs.getInt("role"), rs.getInt("enable"));
            }
            return user;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static UserModel findById(int id) {
        UserModel user = null;
        ResultSet rs;
        PreparedStatement pst;
        String sql;
        try {
            sql = "select * from USERS where uid = ?";
            pst = DBConnection.getConnection().prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                user = new UserModel(rs.getInt("uid"), rs.getString("user_name"),rs.getString("password"), rs.getInt("role"),
                        rs.getString("full_name"), rs.getString("phone_num"), rs.getString("email"), rs.getString("address"), rs.getInt("enable"), rs.getString("gender"));
            }
            return user;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static UserModel findByUserAndEmail(String username, String email) {
        UserModel user = null;
        ResultSet rs;
        PreparedStatement pst;
        String sql;
        try {
            sql = "select * from USERS where user_name = ? and email = ?";
            pst = DBConnection.getConnection().prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, email);
            rs = pst.executeQuery();
            while (rs.next()) {
                user = new UserModel(rs.getInt("uid"), rs.getString("user_name"),rs.getString("password"), rs.getInt("role"),
                        rs.getString("full_name"), rs.getString("phone_num"), rs.getString("email"), rs.getString("address"), rs.getInt("enable"),rs.getString("gender"));
            }
            return user;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void changePassword(int id, String newPassword){
        PreparedStatement pst;
        String sql;
        try {
            sql = "update USERS set password = ? where uid = ?";
            pst = DBConnection.getConnection().prepareStatement(sql);
            pst.setString(1, newPassword);
            pst.setInt(2, id);
            pst.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateUserWeb(int id, String full_name, String phone_num, String email, String address, String gender) {
        PreparedStatement pst;
        String sql;
        try {
            sql = "update USERS set full_name = ?, phone_num = ?, email = ?, address = ?, gender = ? where uid = ?";
            pst = DBConnection.getConnection().prepareStatement(sql);
            pst.setString(1, full_name);
            pst.setString(2, phone_num);
            pst.setString(3, email);
            pst.setString(4, address);
            pst.setString(5, gender);
            pst.setInt(6, id);
            pst.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addUser(String user_name, String password, String full_name, String email, String gender) {
        PreparedStatement pst;
        String sql;
        try {
            sql = "insert into USERS (user_name,password,full_name,email,gender,role,enable) values(?,?,?,?,?,?,?)";
            pst = DBConnection.getConnection().prepareStatement(sql);
            pst.setString(1, user_name);
            pst.setString(2, password);
            pst.setString(3, full_name);
            pst.setString(4, email);
            pst.setString(5, gender);
            pst.setInt(6, 0);
            pst.setInt(7, 1);
            pst.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkUserName(String user_name){
        return check("user_name",user_name);
    }

    public static boolean checkEmail(String email){
        return check("user_name",email);
    }

    private static boolean check(String col, String value) {
        PreparedStatement pst;
        String sql;
        ResultSet rs;
        try {
            sql = "select * from USERS where " + col + " = ?";
            pst = DBConnection.getConnection().prepareStatement(sql);
            pst.setString(1, value);
            rs = pst.executeQuery();
            if(rs.next()) return true;
            return false;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<UserModel> findAll() {
        List<UserModel> users = null;
        ResultSet rs;
        PreparedStatement pst;
        String sql;
        try {
            users = new ArrayList<>();
            sql = "select * from USERS";
            pst = DBConnection.getConnection().prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                users.add(new UserModel(rs.getInt("uid"), rs.getString("user_name"),rs.getString("password"), rs.getInt("role"),
                        rs.getString("full_name"), rs.getString("phone_num"), rs.getString("email"), rs.getString("address"), rs.getInt("enable"), rs.getString("gender")));
            }
            return users;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void save(UserModel user) {
        PreparedStatement pst;
        String sql;
        try {
            sql = "insert into USERS (user_name,password,full_name,role,email,enable) values(?,?,?,?,?,?)";
            pst = DBConnection.getConnection().prepareStatement(sql);
            pst.setString(1, user.getUserName());
            pst.setString(2, user.getPassWord());
            pst.setString(3, user.getFullName());
            pst.setInt(4, user.getRole());
            pst.setString(5, user.getEmail());
            pst.setInt(6, 1);
            pst.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateUserAdmin(UserModel user) {
        PreparedStatement pst;
        String sql;
        try {
            sql = "update USERS set full_name = ?, email = ?,role = ?, enable = ? where uid = ?";
            pst = DBConnection.getConnection().prepareStatement(sql);
            pst.setString(1, user.getFullName());
            pst.setString(2, user.getEmail());
            pst.setInt(3, user.getRole());
            pst.setInt(4, user.getEnable());
            pst.setInt(5, user.getId());
            pst.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void detele(int id) {
        PreparedStatement pst;
        String sql;
        try {
            deleteUserForgetPassword(id);
            sql = "delete from USERS where uid = ?";
            pst = DBConnection.getConnection().prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

  /*  private static void deteleUserOrder(String username){

    }*/

    /*private static int selectIdOrderByUser(String username){
        int result;
        ResultSet rs;
        PreparedStatement pst;
        String sql;
        try {
            sql = "SELECT order_id from `order` where username = ?";
            pst = DBConnection.getConnection().prepareStatement(sql);
            pst.setString(1,username);
            rs = pst.executeQuery();
            while (rs.next()) {

            }
            return users;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }*/

    public static void updateToken(int id, String token) {
        PreparedStatement pst;
        String sql;
        try {
            sql = "update forget_password set token = ? where user_id = ?";
            pst = DBConnection.getConnection().prepareStatement(sql);
            pst.setString(1,token);
            pst.setInt(2,id);
            pst.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkToken(String token){
        PreparedStatement pst;
        String sql;
        ResultSet rs;
        try {
            sql = "select * from forget_password where token = ?";
            pst = DBConnection.getConnection().prepareStatement(sql);
            pst.setString(1,token);
            rs = pst.executeQuery();
            if(rs.next()) return true;
            return false;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void addToken(int id, String token, Timestamp create_date, Timestamp token_expiry) {
        PreparedStatement pst;
        String sql;
        try {
            sql = "insert  into forget_password (uid,token,token_expiry,created_date) values (?,?,?,?)";
            pst = DBConnection.getConnection().prepareStatement(sql);
            pst.setInt(1,id);
            pst.setString(2,token);
            pst.setTimestamp(3,token_expiry);
            pst.setTimestamp(4,create_date);
            pst.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkTokenExpiry(int id, String token) {
        PreparedStatement pst;
        ResultSet rs;
        String sql;
        try {
            sql = "SELECT * from forget_password where token_expiry >= now() and uid = ? and token = ? ";
            pst = DBConnection.getConnection().prepareStatement(sql);
            pst.setInt(1,id);
            pst.setString(2,token);
            rs = pst.executeQuery();
            if(rs.next()) return true;
            return false;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static UserModel findByToken(String token) {
        UserModel user = null;
        ResultSet rs;
        PreparedStatement pst;
        String sql;
        try {
            sql = "select * from forget_password where token = ?";
            pst = DBConnection.getConnection().prepareStatement(sql);
            pst.setString(1, token);
            rs = pst.executeQuery();
            while (rs.next()) {
                user = new UserModel(rs.getInt("uid"),"","",0,
                        1);
            }
            return user;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void deleteToken(int id, String token) {
        PreparedStatement pst;
        String sql;
        try {
            sql = "delete from forget_password where  uid = ? and token = ? ";
            pst = DBConnection.getConnection().prepareStatement(sql);
            pst.setInt(1,id);
            pst.setString(2,token);
            pst.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteUserForgetPassword(int id) {
        PreparedStatement pst;
        String sql;
        try {
            sql = "delete from forget_password where  uid = ? ";
            pst = DBConnection.getConnection().prepareStatement(sql);
            pst.setInt(1,id);
            pst.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
