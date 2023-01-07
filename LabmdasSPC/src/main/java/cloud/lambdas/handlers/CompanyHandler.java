package cloud.lambdas.handlers;

import cloud.lambdas.dto.CityDto;
import cloud.lambdas.dto.Mapper;
import cloud.lambdas.pojo.Company;
import cloud.lambdas.service.CompanyService;
import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"unchecked", "Duplicates", "MismatchedQueryAndUpdateOfCollection", "unused"})
public class CompanyHandler {

    CompanyService companyService = new CompanyService();
    Gson gson = new Gson();

    Mapper mapper = new Mapper();

    public void handleAddCompanyRequest(InputStream inputStream, OutputStream outputStream) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        JSONParser parser = new JSONParser();
        JSONObject responseObject = new JSONObject();
        JSONObject responseBody = new JSONObject();
        try {
            JSONObject reqObject = (JSONObject) parser.parse(reader);
            if (reqObject.get("body") != null) {
                companyService.addCompany((String) ((JSONObject) parser.parse(
                        (String) reqObject.get("body"))).get("name"));
            }
            responseBody.put("message", "Company added");
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

    public void handleDeleteRequest(InputStream inputStream, OutputStream outputStream) throws IOException {
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
                    companyService.deleteCompany(id);
                }
            }
            responseBody.put("message", "Company deleted");
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

    public List<Company> handleGetCompaniesInCityRequest(Long cityId, Context context) {
        return companyService.getCompaniesInCity(cityId);
    }

    public void handleFindCompanyByIdRequest(InputStream inputStream, OutputStream outputStream) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        JSONParser parser = new JSONParser();
        JSONObject responseObject = new JSONObject();
        JSONObject responseBody = new JSONObject();

        Company company = null;
        try {
            JSONObject reqObject = (JSONObject) parser.parse(reader);
            if (reqObject.get("pathParameters") != null) {
                JSONObject qps = (JSONObject) reqObject.get("pathParameters");
                if (qps.get("id") != null) {
                    Long id = (long) Integer.parseInt((String) qps.get("id"));
                    company = companyService.findCompanyById(id);
                }
            } else {
                responseBody.put("message", "Bad request");
                responseObject.put("statusCode", 400);
            }

            if (company != null) {
                responseBody.put("company", company);
                responseObject.put("statusCode", 200);
            } else {
                responseBody.put("message", "No items found");
                responseObject.put("statusCode", 404);
            }

            responseObject.put("body", gson.toJson(company));
        } catch (ParseException e) {
            responseObject.put("statusCode", 400);
            responseObject.put("error", e);
        }
        writer.write(responseObject.toString());
        reader.close();
        writer.close();
    }

    public List<Company> handleGetAllCompaniesRequest(Context context) {
        return companyService.getAllCompanies();
    }

    public void handleAddServiceToCompanyRequest(InputStream inputStream, OutputStream outputStream) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        JSONParser parser = new JSONParser();
        JSONObject responseObject = new JSONObject();
        JSONObject responseBody = new JSONObject();
        try {
            JSONObject reqObject = (JSONObject) parser.parse(reader);
            if (reqObject.get("body") != null) {
                JSONObject thisBody = (JSONObject) parser.parse((String) reqObject.get("body"));
                companyService.addCompanyService((Long) thisBody.get("companyId"),
                        (Long) thisBody.get("serviceId"),
                        (Long) thisBody.get("amount"));
            }
            responseBody.put("message", "Service added to company");
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

    public void handleGetCompanyCitiesRequest(InputStream inputStream, OutputStream outputStream) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        JSONParser parser = new JSONParser();
        JSONObject responseObject = new JSONObject();
        JSONObject responseBody = new JSONObject();

        List<CityDto> cities = new ArrayList<>();

//        Company company = null;
        try {
            JSONObject reqObject = (JSONObject) parser.parse(reader);
            if (reqObject.get("pathParameters") != null) {
                JSONObject qps = (JSONObject) reqObject.get("pathParameters");
                if (qps.get("id") != null) {
                    Long id = (long) Integer.parseInt((String) qps.get("id"));
                    cities =  companyService.getCompanyCities(id);
//                    company = companyService.findCompanyById(id);
                }
            } else {
                responseBody.put("message", "Bad request");
                responseObject.put("statusCode", 400);
            }

            if (cities != null) {
                responseBody.put("cities", cities);
                responseObject.put("statusCode", 200);
            } else {
                responseBody.put("message", "No items found");
                responseObject.put("statusCode", 404);
            }
            Map<String, Object> headers = new HashMap<>();

            headers.put("Context-type", "application/json");
            headers.put("Access-Control-Allow-Origin", "*");


            responseObject.put("body", gson.toJson(cities));
            responseObject.put("headers", headers);
        } catch (ParseException | IOException e) {
            responseObject.put("statusCode", 400);
            responseObject.put("error", e);
        }
        writer.write(responseObject.toString());
        reader.close();
        writer.close();
    }
}
