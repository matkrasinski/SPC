package cloud.lambdas.handlers;

import cloud.lambdas.dto.ServiceUserDto;
import cloud.lambdas.pojo.CompanyUser;
import cloud.lambdas.pojo.Service;
import cloud.lambdas.pojo.User;
import cloud.lambdas.service.UserService;
import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"unchecked", "Duplicates", "MismatchedQueryAndUpdateOfCollection", "unused"})
public class UserHandler {

    UserService userService = new UserService();
    Gson gson = new Gson();

    public void handleAddUserRequest(InputStream inputStream, OutputStream outputStream) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        JSONParser parser = new JSONParser();
        JSONObject responseObject = new JSONObject();
        JSONObject responseBody = new JSONObject();
        try {
            JSONObject reqObject = (JSONObject) parser.parse(reader);
            if (reqObject.get("body") != null) {
                JSONObject object = (JSONObject) parser.parse((String) reqObject.get("body"));
                if (object.get("uuid") == null ||  ((String) object.get("uuid")).isBlank()) {
                    responseObject.put("statusCode", 400);
                    responseBody.put("message", "Wrong uuid");
                } else {
                    userService.addUser((String) object.get("uuid"));
                    responseObject.put("statusCode", 200);
                    responseBody.put("message", "User registered in database");
                }
            }
            Map<String, Object> headers = new HashMap<>();
            headers.put("Context-type", "application/json");
            headers.put("Access-Control-Allow-Origin", "*");

            responseObject.put("body", responseBody.toString());
            responseObject.put("headers", headers);
        } catch (ParseException | IOException e) {
            responseObject.put("statusCode", 400);
            responseObject.put("error", e);
        }
        writer.write(responseObject.toString());
        reader.close();
        writer.close();

    }
    public Boolean handleDeleteUserRequest(Long userId, Context context) {
        return userService.deleteUser(userId);
    }

    public List<User> handleGetAllUsersRequest(Void unused, Context context) {
        return userService.getAllUsers();
    }

    public void handleFindUserByIdRequest(InputStream inputStream, OutputStream outputStream) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        JSONParser parser = new JSONParser();
        JSONObject responseObject = new JSONObject();
        JSONObject responseBody = new JSONObject();
        User user = null;
        try {
            JSONObject reqObject = (JSONObject) parser.parse(reader);
            if (reqObject.get("pathParameters") != null) {
                JSONObject qps = (JSONObject) reqObject.get("pathParameters");
                if (qps.get("uuid") != null) {
                    user = userService.findUserByUuid((String) qps.get("uuid"));
                }
            } else {
                responseBody.put("message", "Bad request");
                responseObject.put("statusCode", 400);
            }

            if (user != null) {
                responseBody.put("User", user);
                responseObject.put("statusCode", 200);
            } else {
                responseBody.put("message", "No items found");
                responseObject.put("statusCode", 404);
            }

            Map<String, Object> headers = new HashMap<>();

            headers.put("Context-type", "application/json");
            headers.put("Access-Control-Allow-Origin", "*");

            responseObject.put("body", gson.toJson(user));
            responseObject.put("headers", headers);
        } catch (ParseException | IOException e) {
            responseObject.put("statusCode", 400);
            responseObject.put("error", e);
        }
        writer.write(responseObject.toString());
        reader.close();
        writer.close();
    }

    public User handleFindUserByEmail(String s, Context context) {
        return userService.findUserByEmail(s);
    }

    public void handleAddServiceToUserRequest(InputStream inputStream, OutputStream outputStream) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        JSONParser parser = new JSONParser();
        JSONObject responseObject = new JSONObject();
        JSONObject responseBody = new JSONObject();
        try {
            JSONObject reqObject = (JSONObject) parser.parse(reader);
            if (reqObject.get("body") != null) {
                JSONObject object = (JSONObject) parser.parse((String) reqObject.get("body"));
                CompanyUser companyUser = new CompanyUser((Long) object.get("user_id"), (Long)object.get("company_id"),
                        (String) object.get("day"),
                        (Long) object.get("service_id"),
                        (String) object.get("description"),
                        (Long) object.get("city_id"));
                companyUser = userService.addServiceToUser(companyUser);
                if (companyUser == null) {
                    responseObject.put("statusCode", 400);
                    responseBody.put("message", "Company capacity is full");
                } else {
                    responseObject.put("statusCode", 200);
                    responseBody.put("message", "Service reserved to user");
                }
            }
            Map<String, Object> headers = new HashMap<>();
            headers.put("Context-type", "application/json");
            headers.put("Access-Control-Allow-Origin", "*");

            responseObject.put("body", responseBody.toString());
            responseObject.put("headers", headers);
        } catch (ParseException | IOException e) {
            responseObject.put("statusCode", 400);
            responseObject.put("error", e);
        }
        writer.write(responseObject.toString());
        reader.close();
        writer.close();
    }
    public void handleGetUserReservedServicesRequest(InputStream inputStream, OutputStream outputStream) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        JSONParser parser = new JSONParser();
        JSONObject responseObject = new JSONObject();
        JSONObject responseBody = new JSONObject();

        List<ServiceUserDto> services = null;
        try {
            JSONObject reqObject = (JSONObject) parser.parse(reader);
            if (reqObject.get("queryStringParameters") != null) {
                JSONObject qps = (JSONObject) reqObject.get("queryStringParameters");
                if (qps.get("uuid") != null) {
                    services = userService.findUserServices((String) qps.get("uuid"));
                }
            } else {
                responseBody.put("message", "Bad request");
                responseObject.put("statusCode", 400);
            }
            if (services != null) {
                responseBody.put("services", services);
                responseObject.put("statusCode", 200);
            } else {
                responseBody.put("message", "No items found");
                responseObject.put("statusCode", 404);
            }
            Map<String, Object> headers = new HashMap<>();

            headers.put("Context-type", "application/json");
            headers.put("Access-Control-Allow-Origin", "*");

            responseObject.put("headers", headers);
            responseObject.put("body", gson.toJson(services));
        } catch (ParseException | IOException e) {
            responseObject.put("statusCode", 400);
            responseObject.put("error", e);
        }
        writer.write(responseObject.toString());
        reader.close();
        writer.close();
    }


}
