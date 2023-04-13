package controller.admin.datatable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.DBConnection;
import mapper.RowMapper;
import mapper.UserMapper;
import model.UserModel;
import java.lang.reflect.Modifier;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataTable<T extends Item> {
    int draw = 1;
    int recordsTotal = 0;
    int recordsFiltered = 0;
    List<T> data;
    private static Gson GSON = new GsonBuilder()
            .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
            .serializeNulls()
            .create();
    String table;
    private long start;
    private int length;

    public DataTable() {
    }

    public static <T extends Item> DataTable<T> create() {
        return new DataTable<>();
    }

    public DataTable<T> table(String name,int draw,long start, int length) {
        this.table = name;
        this.start= start;
        this.length= length;
        this.draw = draw;
        return this;
    }

    private <T>List queryLists(String sql,Class<T> type,RowMapper<T> rowMapper){
        List<T> lists = new ArrayList<T>();
        ResultSet rs;
        PreparedStatement pst;
        try {
            pst = DBConnection.getConnection().prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                lists.add(rowMapper.mapRow(rs));
            }
            return lists;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private int queryCounts(String sql){
        int count = 0;
        ResultSet rs;
        PreparedStatement pst;
        try {
            pst = DBConnection.getConnection().prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                count = rs.getInt(1);
            }
            return count;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String build(Class<T> type,RowMapper<T> rowMapper) {
        String selectSql = "SELECT * FROM " + table +" LIMIT "+start+","+length+"";
        String countSql = "SELECT COUNT(uid) FROM "+table+"";
        List<T> ts = queryLists(selectSql,type,rowMapper);
        recordsTotal = queryCounts(countSql);
        this.data= (List<T>) ts;
        this.recordsFiltered = ts.size();
        DataTableOut<T> out = new DataTableOut<T>(this.draw+1, recordsTotal, recordsTotal, ts);
        return GSON.toJson(out);
    }

    @Override
    public String toString() {
        return "DataTable{" +
                "draw=" + draw +
                ", recordsTotal=" + recordsTotal +
                ", recordsFiltered=" + recordsFiltered +
                ", data=" + data +
                ", table='" + table + '\'' +
                '}';
    }

    public static void main(String[] args) {
        String users = new DataTable<UserModel>().table("users", 1, 0, 2).build(UserModel.class, new UserMapper());
        System.out.println(users);
    }
}
