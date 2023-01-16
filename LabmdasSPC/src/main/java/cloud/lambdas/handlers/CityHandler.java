package cloud.lambdas.handlers;

import cloud.lambdas.pojo.City;
import cloud.lambdas.service.CityService;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.List;

@SuppressWarnings({"unchecked", "Duplicates", "MismatchedQueryAndUpdateOfCollection", "unused"})
public class CityHandler {
    CityService cityService = new CityService();
    Gson gson = new Gson();

    public void handleAddCityRequest(InputStream inputStream, OutputStream outputStream) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        JSONParser parser = new JSONParser();
        JSONObject responseObject = new JSONObject();
        JSONObject responseBody = new JSONObject();
        try {
            JSONObject reqObject = (JSONObject) parser.parse(reader);
            if (reqObject.get("body") != null) {
                JSONObject object = (JSONObject) parser.parse((String) reqObject.get("body"));
                cityService.addCity((String) object.get("name"), (Long) object.get("companyId"));
            }
            responseBody.put("message", "City added");
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
    public void handleDeleteCityRequest(InputStream inputStream, OutputStream outputStream) throws IOException {
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
                    cityService.deleteCity(id);
                }
            }
            responseBody.put("message", "City deleted");
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
    public void handleFindByCompanyIdAndNameRequest(InputStream inputStream, OutputStream outputStream) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        JSONParser parser = new JSONParser();
        JSONObject responseObject = new JSONObject();
        JSONObject responseBody = new JSONObject();
        City city = null;
        try {
            JSONObject reqObject = (JSONObject) parser.parse(reader);
            if (reqObject.get("pathParameters") != null) {
                JSONObject qps = (JSONObject) reqObject.get("pathParameters");
                if (qps.get("id") != null && qps.get("name")!=null) {
                    Long id = (long) Integer.parseInt((String) qps.get("id"));
                    String name = (String) qps.get("name");
                    city = cityService.findCityByCompanyIdAndName(id,name);
                }
            } else {
                responseBody.put("message", "Bad request");
                responseObject.put("statusCode", 400);
            }

            if (city != null) {
                responseBody.put("city", city);
                responseObject.put("statusCode", 200);
            } else {
                responseBody.put("message", "No items found");
                responseObject.put("statusCode", 404);
            }

            responseObject.put("body", gson.toJson(city));
        } catch (ParseException | IOException e) {
            responseObject.put("statusCode", 400);
            responseObject.put("error", e);
        }
        writer.write(responseObject.toString());
        reader.close();
        writer.close();
    }

    public void handleFindByIdRequest(InputStream inputStream, OutputStream outputStream) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        JSONParser parser = new JSONParser();
        JSONObject responseObject = new JSONObject();
        JSONObject responseBody = new JSONObject();
        City city = null;
        try {
            JSONObject reqObject = (JSONObject) parser.parse(reader);
            if (reqObject.get("pathParameters") != null) {
                JSONObject qps = (JSONObject) reqObject.get("pathParameters");
                if (qps.get("id") != null) {
                    Long id = (long) Integer.parseInt((String) qps.get("id"));
                    city = cityService.findCityById(id);
                }
            } else {
                responseBody.put("message", "Bad request");
                responseObject.put("statusCode", 400);
            }

            if (city != null) {
                responseBody.put("city", city);
                responseObject.put("statusCode", 200);
            } else {
                responseBody.put("message", "No items found");
                responseObject.put("statusCode", 404);
            }

            responseObject.put("body", gson.toJson(city));
        } catch (ParseException | IOException e) {
            responseObject.put("statusCode", 400);
            responseObject.put("error", e);
        }
        writer.write(responseObject.toString());
        reader.close();
        writer.close();
    }

    public List<City> handleGetAllCitiesRequest() {
        return cityService.getAllCities();
    }
}
