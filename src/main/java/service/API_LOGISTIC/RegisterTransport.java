package service.API_LOGISTIC;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dao.DBConnection;
import model.Order;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public class RegisterTransport {

    public static Transport registerTransport(String token, Order order,String from_district_id, String from_ward_id, String to_district_id, String to_ward_id) throws IOException {
        String height = "100";
        String length = "100";
        String width = "100";
        String weight = "100";
        // Tạo yêu cầu tính phí vận chuyển đến API
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("http://140.238.54.136/api/registerTransport");
        post.setHeader("Content-type", "application/json");
        post.setHeader("Authorization", "Bearer " + token);
        JSONObject json = new JSONObject();
        json.put("from_district_id", from_district_id);
        json.put("from_ward_id", from_ward_id);
        json.put("to_district_id", to_district_id);
        json.put("to_ward_id", to_ward_id);
        json.put("height", height);
        json.put("length", length);
        json.put("width", width);
        json.put("weight", weight);
        StringEntity entity = new StringEntity(json.toString());
        post.setEntity(entity);

        // Gửi yêu cầu và nhận phản hồi từ API
        HttpResponse apiResponse = client.execute(post);
        HttpEntity apiResponseEntity = apiResponse.getEntity();
        String apiResponseString = EntityUtils.toString(apiResponseEntity);
        // Trích xuất giá trị phí vận chuyển từ phản hồi API
        String val = apiResponseString;
        // Chuyển chuỗi JSON thành đối tượng JSONObject
        JSONObject jsonObject = new JSONObject(val);

        // Lấy ra giá trị của thuộc tính "id"
        String id = jsonObject.getJSONObject("Transport").getString("id");


        // Lấy ra giá trị của thuộc tính "created_at"
        String createdAt = jsonObject.getJSONObject("Transport").getString("created_at");

        // Lấy ra giá trị của thuộc tính "leadTime"
        long leadTime = jsonObject.getJSONObject("Transport").getLong("leadTime");
        Transport transport = new Transport(id, order,convertCreatedAt(createdAt), convertLeadTime(leadTime));
        return transport;

    }
    // Hàm chuyển đổi "leadTime" sang dd/mm/yyyy
    public static String convertLeadTime(long leadTime) {
        Date date = new Date(leadTime * 1000L); // chuyển giây thành mili giây
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC")); // set timezone
        return dateFormat.format(date);
    }

    // Hàm chuyển đổi "created_at" sang dd/mm/yyyy
    public static String convertCreatedAt(String createdAt) {
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'");
        inputDateFormat.setTimeZone(TimeZone.getTimeZone("UTC")); // set timezone
        try {
            Date date = inputDateFormat.parse(createdAt);
            SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return outputDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String convertUnixTime(long unixSeconds) {
        // Thêm số giây vào Unix Epoch và áp dụng chênh lệch múi giờ
        LocalDateTime dateTime = LocalDateTime.ofEpochSecond(unixSeconds, 0, ZoneOffset.UTC)
                .plusHours(7);

        // Định dạng ngày tháng năm và giờ
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        // Trả về ngày tháng năm và giờ dưới dạng chuỗi
        return dateTime.format(formatter);
    }
    public static void compareDateTime(String dateTimeStr1, String dateTimeStr2) {
        // Định dạng để chuyển đổi chuỗi thành LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        // Chuyển đổi chuỗi thành LocalDateTime
        LocalDateTime dateTime1 = LocalDateTime.parse(dateTimeStr1, formatter);
        LocalDateTime dateTime2 = LocalDateTime.parse(dateTimeStr2, formatter);

        // So sánh hai LocalDateTime
        int comparisonResult = dateTime1.compareTo(dateTime2);

        // In kết quả
        if (comparisonResult < 0) {
            System.out.println(dateTimeStr1 + " đến trước " + dateTimeStr2);
        } else if (comparisonResult > 0) {
            System.out.println(dateTimeStr1 + " đến sau " + dateTimeStr2);
        } else {
            System.out.println(dateTimeStr1 + " và " + dateTimeStr2 + " bằng nhau");
        }
    }
    public static String getCurrentDateTime(){
        // Lấy ngày giờ hiện tại
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Định dạng ngày tháng năm và giờ
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        // Chuyển đổi và in kết quả
        return currentDateTime.format(formatter);
    }


    public static String getDeliveryDate(String token, int fromDistrictId, String fromWardCode,
                                       int toDistrictId, String toWardCode, int serviceId) throws IOException {
        String urlAPI = "https://dev-online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/leadtime";

        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(urlAPI);
        post.setHeader("Content-type", "application/json");
        post.setHeader("Token", token);

        JSONObject json = new JSONObject();
        json.put("from_district_id", fromDistrictId);
        json.put("from_ward_code", fromWardCode);
        json.put("to_district_id", toDistrictId);
        json.put("to_ward_code", toWardCode);
        json.put("service_id", serviceId);

        StringEntity entity = new StringEntity(json.toString());
        post.setEntity(entity);

        HttpResponse apiResponse = client.execute(post);
        HttpEntity apiResponseEntity = apiResponse.getEntity();
        String apiResponseString = EntityUtils.toString(apiResponseEntity);

        JSONObject apiResponseJson = new JSONObject(apiResponseString);
        long leadTime = apiResponseJson.getJSONObject("data").getLong("leadtime");

        return convertUnixTime(leadTime);
    }

    public static void updateOrderStatusByTransportLeadTime(int idOrder, String createdAt, String leadTime) {
        String sql = "UPDATE `transports` SET `created_at` = ?, `leadTime` = ? WHERE `id_order` = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, createdAt);
            ps.setString(2, leadTime);
            ps.setInt(3, idOrder);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    public static Transport registerShipment(String token, Order order,String from_district_id, String from_ward_id, String to_district_id, String to_ward_id) {
        String apiUrl = "https://dev-online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/create";
        String orderCode = null;
        try {
            int district_id = Integer.parseInt(to_district_id);
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Token", token);
            connection.setDoOutput(true);

            JsonObject jsonData = new JsonObject();
            jsonData.addProperty("payment_type_id", 2);
            jsonData.addProperty("note", "HappyHome 123");
            jsonData.addProperty("required_note", "KHONGCHOXEMHANG");
            jsonData.addProperty("from_name", "HappyHome");
            jsonData.addProperty("from_phone", "0987654321");
            jsonData.addProperty("from_address", "Khu phố 6, Phường Linh Trung, Thành phố Thủ Đức, Hồ Chí Minh, Vietnam");
            jsonData.addProperty("from_ward_name", "Phường Linh Trung");
            jsonData.addProperty("from_district_name", "Thành phố Thủ Đức");
            jsonData.addProperty("from_province_name", "HCM");
            jsonData.addProperty("to_name", "Khách hàng");
            jsonData.addProperty("to_phone", "0987654321");
            jsonData.addProperty("to_address", "72 Thành Thái, Phường 14, Quận 10, Hồ Chí Minh, Vietnam");
            jsonData.addProperty("to_ward_code", to_ward_id);
            jsonData.addProperty("to_district_id", district_id);
            jsonData.addProperty("weight", 200);
            jsonData.addProperty("length", 1);
            jsonData.addProperty("width", 19);
            jsonData.addProperty("height", 10);
            jsonData.addProperty("deliver_station_id", (String) null);
            jsonData.addProperty("service_id", 0);
            jsonData.addProperty("service_type_id", 2);

            JsonArray itemsArray = new JsonArray();
            JsonObject itemObject = new JsonObject();
            itemObject.addProperty("name", "Áo Polo");
            itemObject.addProperty("code", "Polo123");
            itemObject.addProperty("quantity", 1);
            itemObject.addProperty("weight", 1200);
            itemsArray.add(itemObject);

            jsonData.add("items", itemsArray);

            OutputStream os = connection.getOutputStream();
            os.write(jsonData.toString().getBytes());
            os.flush();
            os.close();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JsonObject responseJson = JsonParser.parseString(response.toString()).getAsJsonObject();
                if (responseJson.get("code").getAsInt() == 200) {
                    orderCode = responseJson.getAsJsonObject("data").get("order_code").getAsString();
                } else {
                    System.out.println("Error: " + responseJson.get("message").getAsString());
                }
            } else {
                BufferedReader errorReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                String errorLine;
                StringBuilder errorResponse = new StringBuilder();
                while ((errorLine = errorReader.readLine()) != null) {
                    errorResponse.append(errorLine);
                }
                errorReader.close();
                System.out.println("Error Response: " + errorResponse.toString());
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        Transport transport = new Transport(orderCode, order,"Chờ vận chuyển", "Chờ vận chuyển");
        return transport;
    }

    public static void main(String[] args) throws IOException, JSONException, ParseException {
//        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vMTQwLjIzOC41NC4xMzYvYXBpL2F1dGgvbG9naW4iLCJpYXQiOjE2ODIyMjk4MTYsImV4cCI6MTY4MjIzMDQxNiwibmJmIjoxNjgyMjI5ODE2LCJqdGkiOiJuZEtUU3pRZ1JacUtNa0R1Iiwic3ViIjoiODNjNGM1MWQ2N2Q1NGM0ODg4NWE4M2JjOGViYTJkZGMiLCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3In0.4Szj5kC8JjsHAEh4GPiEJtTC7cEWwrssofLb51fWiDE";
//        try {
//            String from_district_id = "2264";
//            String from_ward_id = "90816";
//            String to_district_id = "2270";
//            String to_ward_id = "231013";
//            Order order = new Order();
//            Transport transport = registerTransport(token,order, from_district_id, from_ward_id, to_district_id, to_ward_id);
//            System.out.println("Transport: " + transport.toString());
//        } catch (IOException e) {
//            System.err.println("Error calculating transport: " + e.getMessage());
//        }
        System.out.println(getCurrentDateTime());
        System.out.println(convertLeadTime(1713916799));
        System.out.println(convertUnixTime(1714089599));
//        compareDateTime(getCurrentDateTime(), convertUnixTime(1714089599));

//        System.out.println(registerShipment("1ec8d4c1-f724-11ee-a6e6-e60958111f48"));

            String token = "1ec8d4c1-f724-11ee-a6e6-e60958111f48";
            int fromDistrictId = 3695;
            String fromWardCode = "90758";
            int toDistrictId = 3440;
            String toWardCode = "907557";
            int serviceId = 53320;

            String deliveryDate = getDeliveryDate(token, fromDistrictId, fromWardCode, toDistrictId, toWardCode, serviceId);
            System.out.println("Estimated Delivery Date: " + deliveryDate);

        updateOrderStatusByTransportLeadTime(36, getCurrentDateTime(),deliveryDate );
    }
}

