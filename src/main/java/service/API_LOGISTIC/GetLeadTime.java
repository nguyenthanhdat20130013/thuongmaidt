package service.API_LOGISTIC;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;

public class GetLeadTime {
    public static String getLeadTime(String token, int from_district_id, String from_ward_code, int to_district_id, String to_ward_code, int service_id) throws IOException {
        String urlAPI = "https://dev-online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/leadtime";

        // Tạo yêu cầu tính thời gian vận chuyển đến API
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(urlAPI);
        post.setHeader("Content-type", "application/json");
        post.setHeader("token", token);

        JSONObject json = new JSONObject();
        json.put("from_district_id", from_district_id);
        json.put("from_ward_code", from_ward_code);
        json.put("to_district_id", to_district_id);
        json.put("to_ward_code", to_ward_code);
        json.put("service_id", service_id);

        StringEntity entity = new StringEntity(json.toString());
        post.setEntity(entity);

        // Gửi yêu cầu và nhận phản hồi từ API
        HttpResponse apiResponse = client.execute(post);
        HttpEntity apiResponseEntity = apiResponse.getEntity();
        String apiResponseString = EntityUtils.toString(apiResponseEntity);

        // Xử lý phản hồi từ API
        JSONObject jsonResponse = new JSONObject(apiResponseString);
        int code = jsonResponse.getInt("code");
        String message = jsonResponse.getString("message");
        if (code == 200 && message.equals("Success")) {
            JSONObject data = jsonResponse.getJSONObject("data");
            long leadtime = data.getLong("leadtime");
            // Đổi đơn vị giây sang định dạng phù hợp ở đây nếu cần
            return String.valueOf(leadtime);
        } else {
            return "Error: " + message;
        }
    }


//    public static String getFormattedDateFromJson(String jsonString) throws JSONException {
//        JSONObject json = new JSONObject(jsonString);
//        JSONArray data = json.getJSONArray("data");
//        JSONObject dataObject = data.getJSONObject(0);
//        String formattedDate = dataObject.getString("formattedDate");
//        return formattedDate;
//    }


    public static void main(String[] args) throws IOException, JSONException, ParseException {
        String token = "YOUR_TOKEN_HERE";
        int from_district_id = 3695;
        String from_ward_code = "90758";
        int to_district_id = 3440;
        String to_ward_code = "907557";
        int service_id = 53320;

        String leadTime = getLeadTime(token, from_district_id, from_ward_code, to_district_id, to_ward_code, service_id);
        System.out.println("Lead time: " + leadTime);



}
}
