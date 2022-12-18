package cloud.lambdas.handlers;

import cloud.lambdas.dto.CompanyServiceDto;
import cloud.lambdas.pojo.Company;
import cloud.lambdas.pojo.Service;
import cloud.lambdas.service.CompanyService;
import cloud.lambdas.service.ServiceService;
import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.List;

public class ServiceHandler {
    private final ServiceService serviceService = new ServiceService();
    private final CompanyService companyService = new CompanyService();

    private final Gson gson = new Gson();

    public List<Service> handleGetAllServicesRequest(Void unused, Context context) {
        return serviceService.getAllServices();
    }

    public Service handleFindServiceByNameRequest(String s, Context context) {
        return serviceService.findServiceByName(s);
    }

    public void handleGetServicesInCity(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
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
                if (qps.get("cityName") != null) {
                    String cityName  = (String) qps.get("cityName");
                    services = serviceService.getServicesInCity(cityName);
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
            context.getLogger().log("ERROR : " + e.getMessage());
        }
        writer.write(responseObject.toString());
        reader.close();
        writer.close();
    }

    public void handleFindServiceById(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        JSONParser parser = new JSONParser();
        JSONObject responseObject = new JSONObject();
        JSONObject responseBody = new JSONObject();

        Service service = null;
        try {
            JSONObject reqObject = (JSONObject) parser.parse(reader);
            if (reqObject.get("queryStringParameters") != null) {
                JSONObject qps = (JSONObject) reqObject.get("queryStringParameters");
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

            responseObject.put("body", gson.toJson(service));
        } catch (ParseException e) {
            context.getLogger().log("ERROR : " + e.getMessage());
        }
        writer.write(responseObject.toString());
        reader.close();
        writer.close();
    }

    public void handleGetCompanyServicesRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        JSONParser parser = new JSONParser();
        JSONObject responseObject = new JSONObject();
        JSONObject responseBody = new JSONObject();
        Long id = null;
        List<Service> services = null;
        CompanyServiceDto companyServiceDto = new CompanyServiceDto();
        try {
            JSONObject reqObject = (JSONObject) parser.parse(reader);
             if (reqObject.get("queryStringParameters") != null) {
                JSONObject qps = (JSONObject) reqObject.get("queryStringParameters");
                if (qps.get("companyId") != null) {
                    id  = (long) Integer.parseInt((String) qps.get("companyId"));
                    services = companyService.getCompanyServices(id);
                }
            } else {
                 responseBody.put("message", "Bad request");
                 responseObject.put("statusCode", 400);
             }

            if (services != null) {
                responseBody.put("services", services);
                responseObject.put("statusCode", 200);
                companyServiceDto.setCompanyId(id);
                companyServiceDto.setCompanyName(companyService.findCompanyById(id).getName());
                companyServiceDto.setServices(services);
            } else {
                responseBody.put("message", "No items found");
                responseObject.put("statusCode", 404);
            }

            responseObject.put("body", gson.toJson(companyServiceDto));
        } catch (ParseException | IOException e) {
            context.getLogger().log("ERROR : " + e.getMessage());
        }
        writer.write(responseObject.toString());
        reader.close();
        writer.close();
    }

    public List<CompanyServiceDto> handleGetAllCompaniesServicesRequest() {
        return serviceService.getAllCompanyServices();
    }

    public Boolean handleAddServiceRequest(Service service, Context context) {
        return serviceService.addService(service.getName());

    }
}
