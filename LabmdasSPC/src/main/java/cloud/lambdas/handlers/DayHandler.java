package cloud.lambdas.handlers;

import cloud.lambdas.pojo.Day;
import cloud.lambdas.service.CompanyService;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.sql.Date;
import java.util.List;


@SuppressWarnings({"unchecked", "Duplicates", "MismatchedQueryAndUpdateOfCollection", "unused"})
public class DayHandler {
    CompanyService companyService = new CompanyService();
    Gson gson = new Gson();
    public void handleAddForbiddenDayRequest(InputStream inputStream, OutputStream outputStream) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        JSONParser parser = new JSONParser();
        JSONObject responseObject = new JSONObject();
        JSONObject responseBody = new JSONObject();
        try {
            JSONObject reqObject = (JSONObject) parser.parse(reader);
            if (reqObject.get("body") != null) {
                JSONObject object = (JSONObject) parser.parse((String) reqObject.get("body"));

                companyService.addForbiddenDay(new Day((Long) object.get("companyId"), (Date) object.get("day")));
            }
            responseBody.put("message", "Forbidden day added");
            responseObject.put("statusCode", 200);
            responseObject.put("body", responseBody.toString());
        } catch (ParseException | IOException e) {
            responseObject.put("statusCode", 400);
            responseObject.put("error", e);
        }
        writer.write(responseObject.toString());
        reader.close();
        writer.close();
    }
    public void handleDeleteForbiddenDayRequest(InputStream inputStream, OutputStream outputStream) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        JSONParser parser = new JSONParser();
        JSONObject responseObject = new JSONObject();
        JSONObject responseBody = new JSONObject();
        Long id = null;
        try {
            JSONObject reqObject = (JSONObject) parser.parse(reader);
            if (reqObject.get("pathParameters") != null) {
                JSONObject qps = (JSONObject) reqObject.get("pathParameters");
                if (qps.get("id") != null) {
                    id = (long) Integer.parseInt((String) qps.get("id"));
                    companyService.deleteForbiddenDay(id);
                }
            }
            responseBody.put("message", "Forbidden day deleted");
            responseObject.put("statusCode", 200);
            responseObject.put("body", responseBody.toString());
        } catch (ParseException | IOException e) {
            responseObject.put("statusCode", 400);
            responseObject.put("error", e);
        }
        writer.write(responseObject.toString());
        reader.close();
        writer.close();
    }
    public void handleGetCompanyForbiddenDays(InputStream inputStream, OutputStream outputStream) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        JSONParser parser = new JSONParser();
        JSONObject responseObject = new JSONObject();
        JSONObject responseBody = new JSONObject();
        Long id = null;
        List<Day> days = null;
        try {
            JSONObject reqObject = (JSONObject) parser.parse(reader);
            if (reqObject.get("queryStringParameters") != null) {
                JSONObject qps = (JSONObject) reqObject.get("queryStringParameters");
                if (qps.get("companyId") != null) {
                    id  = (long) Integer.parseInt((String) qps.get("companyId"));
                    days = companyService.getCompanyForbiddenDays(id);
                }
            } else {
                responseBody.put("message", "Bad request");
                responseObject.put("statusCode", 400);
            }
            if (days != null) {
                responseBody.put("services", days);
                responseObject.put("statusCode", 200);
            } else {
                responseBody.put("message", "No items found");
                responseObject.put("statusCode", 404);
            }
            responseObject.put("body", gson.toJson(days));
        } catch (ParseException | IOException e) {
            responseObject.put("statusCode", 400);
            responseObject.put("error", e);
        }
        writer.write(responseObject.toString());
        reader.close();
        writer.close();
    }

}
