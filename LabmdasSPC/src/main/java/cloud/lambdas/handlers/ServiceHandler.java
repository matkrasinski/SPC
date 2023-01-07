package cloud.lambdas.handlers;

import cloud.lambdas.dto.CompanyServiceDto;
import cloud.lambdas.dto.Mapper;
import cloud.lambdas.dto.ServiceDto;
import cloud.lambdas.pojo.Service;
import cloud.lambdas.service.CityService;
import cloud.lambdas.service.CompanyService;
import cloud.lambdas.service.ServiceService;
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
public class ServiceHandler {
    private final ServiceService serviceService = new ServiceService();
    private final CompanyService companyService = new CompanyService();

    private final CityService cityService = new CityService();
    private final Gson gson = new Gson();

    private final Mapper mapper = new Mapper();

    public List<Service> handleGetAllServicesRequest(Void unused, Context context) {
        return serviceService.getAllServices();
    }

    public Service handleFindServiceByNameRequest(String s, Context context) {
        return serviceService.findServiceByName(s);
    }

    public void handleGetServicesInCity(InputStream inputStream, OutputStream outputStream) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        JSONParser parser = new JSONParser();
        JSONObject responseObject = new JSONObject();
        JSONObject responseBody = new JSONObject();

        List<Service> services = null;
        try {
            JSONObject reqObject = (JSONObject) parser.parse(reader);
            if (reqObject.get("queryStringParameters") != null) {
                JSONObject qps = (JSONObject) reqObject.get("queryStringParameters");
                if (qps.get("id") != null) {
                    Long id = (long) Integer.parseInt((String) qps.get("id"));
                    services = serviceService.getServicesInCity(id);
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

            responseObject.put("body", gson.toJson(services));
        } catch (ParseException | IOException e) {
            responseObject.put("statusCode", 400);
            responseObject.put("error", e);
        }
        writer.write(responseObject.toString());
        reader.close();
        writer.close();
    }

    public void handleFindServiceById(InputStream inputStream, OutputStream outputStream) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        JSONParser parser = new JSONParser();
        JSONObject responseObject = new JSONObject();
        JSONObject responseBody = new JSONObject();

        Service service = null;
        try {
            JSONObject reqObject = (JSONObject) parser.parse(reader);
            if (reqObject.get("pathParameters") != null) {
                JSONObject qps = (JSONObject) reqObject.get("pathParameters");
                if (qps.get("id") != null) {
                    Long id = (long) Integer.parseInt((String) qps.get("id"));
                    service = serviceService.findServiceById(id);
                }
            } else {
                responseBody.put("message", "Bad request");
                responseObject.put("statusCode", 400);
            }

            if (service != null) {
                responseBody.put("service", service);
                responseObject.put("statusCode", 200);
            } else {
                responseBody.put("message", "No items found");
                responseObject.put("statusCode", 404);
            }

            Map<String, Object> headers = new HashMap<>();

            headers.put("Context-type", "application/json");
            headers.put("Access-Control-Allow-Origin", "*");

            responseObject.put("body", gson.toJson(service));
            responseObject.put("headers", headers);
        } catch (ParseException e) {
            responseObject.put("statusCode", 400);
            responseObject.put("error", e);
        }
        writer.write(responseObject.toString());
        reader.close();
        writer.close();
    }

    public void handleGetCompanyServicesRequest(InputStream inputStream, OutputStream outputStream) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        JSONParser parser = new JSONParser();
        JSONObject responseObject = new JSONObject();
        JSONObject responseBody = new JSONObject();
        Long id = null;
        List<ServiceDto> services = null;
        CompanyServiceDto companyServiceDto = new CompanyServiceDto();
        try {
            JSONObject reqObject = (JSONObject) parser.parse(reader);
             if (reqObject.get("pathParameters") != null) {
                JSONObject qps = (JSONObject) reqObject.get("pathParameters");
                if (qps.get("id") != null) {
                    id  = (long) Integer.parseInt((String) qps.get("id"));
                    services = companyService.getCompanyServicesDto(id);
                }
            } else {
                 responseBody.put("message", "Bad request");
                 responseObject.put("statusCode", 400);
             }

            if (services != null) {
                responseBody.put("services", services);
                responseObject.put("statusCode", 200);
                companyServiceDto.setCompanyName(companyService.findCompanyById(id).getName());
                companyServiceDto.setServices(services);
            } else {
                responseBody.put("message", "No items found");
                responseObject.put("statusCode", 404);
            }
            Map<String, Object> headers = new HashMap<>();

            headers.put("Context-type", "application/json");
            headers.put("Access-Control-Allow-Origin", "*");

            responseObject.put("body", gson.toJson(companyServiceDto));
            responseObject.put("headers", headers);
        } catch (ParseException | IOException e) {
            responseObject.put("statusCode", 400);
            responseObject.put("error", e);
        }
        writer.write(responseObject.toString());
        reader.close();
        writer.close();
    }

    public List<CompanyServiceDto> handleGetAllCompaniesServicesRequest() {
        return serviceService.getAllCompanyServices();
    }
    public void handleAddServiceRequest(InputStream inputStream, OutputStream outputStream) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        JSONParser parser = new JSONParser();
        JSONObject responseObject = new JSONObject();
        JSONObject responseBody = new JSONObject();
        try {
            JSONObject reqObject = (JSONObject) parser.parse(reader);
            if (reqObject.get("body") != null) {
                JSONObject object = (JSONObject) parser.parse((String) reqObject.get("body"));
                serviceService.addService((String) object.get("name"),
                        (Double) object.get("price"));
            }
            responseBody.put("message", "Service added");
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

    public void handleDeleteServiceRequest(InputStream inputStream, OutputStream outputStream) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        JSONParser parser = new JSONParser();
        JSONObject responseObject = new JSONObject();
        JSONObject responseBody = new JSONObject();
        try {
            JSONObject reqObject = (JSONObject) parser.parse(reader);
            if (reqObject.get("pathParameters") != null) {
                JSONObject qps = (JSONObject) reqObject.get("pathParameters");
                if (qps.get("id") != null) {
                    serviceService.deleteService((long) Integer.parseInt((String) qps.get("id")));
                }
            }
            responseBody.put("message", "Service deleted");
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

    // TO DO
//    public void handleUpdateServiceRequest(InputStream inputStream, OutputStream outputStream) throws IOException {
//        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
//        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//        JSONParser parser = new JSONParser();
//        JSONObject responseObject = new JSONObject();
//        JSONObject responseBody = new JSONObject();
//        try {
//            JSONObject reqObject = (JSONObject) parser.parse(reader);
//            if (reqObject.get("body") != null) {
//                Service service = new Service((String) ((JSONObject) parser.parse(
//                        (String) reqObject.get("body"))).get("name"));
//                serviceService.addService(service.getName());
//            }
//            responseBody.put("message", "Service added");
//            responseObject.put("statusCode", 200);
//            responseObject.put("body", responseBody.toString());
//        } catch (ParseException | IOException e) {
//            responseObject.put("statusCode", 400);
//            responseObject.put("error", e);
//        }
//        writer.write(responseObject.toString());
//        reader.close();
//        writer.close();
//    }

}
