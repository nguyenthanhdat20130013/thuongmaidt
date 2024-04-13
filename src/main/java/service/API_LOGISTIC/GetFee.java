package service.API_LOGISTIC;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class GetFee {
//    public static Double calculateShippingFee(String token, String from_district_id, String from_ward_id, String to_district_id, String to_ward_id) throws IOException, IOException {
//
//        // Trích xuất giá trị phí vận chuyển từ phản hồi API
//        // double serviceFee = Double.parseDouble(String.valueOf(apiResponseJson.getJSONArray("data").getJSONObject(0).getDouble("service_fee")));
//        return 0.0;
//    }
public static double calculateShippingFee(
        String token,
        int fromDistrictId,
        String fromWardCode,
        int toDistrictId,
        String toWardCode,
        int serviceId,
        Integer serviceTypeId,
        int height,
        int length,
        int width,
        int weight,
        int insuranceValue,
        int codFailedAmount,
        String coupon
) throws IOException {
    String urlAPI = "https://dev-online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/fee";

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
    if (serviceTypeId != null) {
        json.put("service_type_id", serviceTypeId);
    } else {
        json.put("service_type_id", JSONObject.NULL);
    }
    json.put("height", height);
    json.put("length", length);
    json.put("width", width);
    json.put("weight", weight);
    json.put("insurance_value", insuranceValue);
    json.put("cod_failed_amount", codFailedAmount);
    json.put("coupon", coupon == null ? JSONObject.NULL : coupon);

    StringEntity entity = new StringEntity(json.toString());
    post.setEntity(entity);

    HttpResponse apiResponse = client.execute(post);
    HttpEntity apiResponseEntity = apiResponse.getEntity();
    String apiResponseString = EntityUtils.toString(apiResponseEntity);

    JSONObject apiResponseJson = new JSONObject(apiResponseString);
    double total = apiResponseJson.getJSONObject("data").getDouble("total");

    return 0;
}
    public static void main(String[] args) throws IOException {
        String token = Login_API.login();
        int fromDistrictId = 1454;
        String fromWardCode = "21211";
        int toDistrictId = 1452;
        String toWardCode = "21012";
        int serviceId = 53320;
        Integer serviceTypeId = null;
        int height = 50;
        int length = 20;
        int width = 20;
        int weight = 200;
        int insuranceValue = 10000;
        int codFailedAmount = 2000;
        String coupon = null;

        try {
            double shippingFee = calculateShippingFee(
                    token,
                    fromDistrictId,
                    fromWardCode,
                    toDistrictId,
                    toWardCode,
                    serviceId,
                    serviceTypeId,
                    height,
                    length,
                    width,
                    weight,
                    insuranceValue,
                    codFailedAmount,
                    coupon
            );

        //    System.out.println("Shipping Fee: " + shippingFee);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}