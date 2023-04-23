package service.API_LOGISTIC;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;

public class GetFee {
    public static double calculateShippingFee(int fromDistrictId, int fromWardId, int toDistrictId, int toWardId, int height, int length, int width, int weight, String accessToken) throws IOException, IOException {
        // Tạo yêu cầu tính phí vận chuyển đến API
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("http://140.238.54.136/api/calculateFee");
        post.setHeader("Content-type", "application/json");
        post.setHeader("Authorization", "Bearer " + accessToken);
        JSONObject json = new JSONObject();
        json.put("from_district_id", fromDistrictId);
        json.put("from_ward_id", fromWardId);
        json.put("to_district_id", toDistrictId);
        json.put("to_ward_id", toWardId);
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
        JSONObject apiResponseJson = new JSONObject(apiResponseString);

        // Trích xuất giá trị phí vận chuyển từ phản hồi API
        double serviceFee = Double.parseDouble(String.valueOf(apiResponseJson.getJSONArray("data").getJSONObject(0).getDouble("service_fee")));
        return serviceFee;
    }

    public static void main(String[] args) {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vMTQwLjIzOC41NC4xMzYvYXBpL2F1dGgvbG9naW4iLCJpYXQiOjE2ODIxODAyMzYsImV4cCI6MTY4MjE4MDgzNiwibmJmIjoxNjgyMTgwMjM2LCJqdGkiOiJIWjhKRFpBdTlmOUdrY1l4Iiwic3ViIjoiODNjNGM1MWQ2N2Q1NGM0ODg4NWE4M2JjOGViYTJkZGMiLCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3In0.69ceGBFz9DHsDyc8RXEYd2SDpBy98SS5BBAv3bBfUHM";
        try {
            double shippingFee = calculateShippingFee(2264, 90816, 2270, 231013, 100, 100, 100, 100, token);
            System.out.println("Shipping fee: " + shippingFee);
        } catch (IOException e) {
            System.err.println("Error calculating shipping fee: " + e.getMessage());
        }

    }

}
