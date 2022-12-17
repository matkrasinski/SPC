package cloud.lambdas.handlers;

import cloud.lambdas.pojo.Service;
import cloud.lambdas.service.CompanyService;
import cloud.lambdas.service.ServiceService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.List;

public class ServiceHandlers {
    ServiceService serviceService = new ServiceService();
    CompanyService companyService = new CompanyService();

    public List<Service> handleGetAllServicesRequest(Void unused, Context context) {
        return serviceService.getAllServices();
    }

    public List<Service> handleGetServicesInCity(APIGatewayProxyRequestEvent input, Context context) {
//        context.getLogger().log(String.valueOf(requestEvent.get().get("cityName")));

        return serviceService.getServicesInCity(input.getPathParameters().get("name"));
    }

    public Service handleFindServiceByNameRequest(String s, Context context) {
        return serviceService.findServiceByName(s);
    }
    public void handleFindServiceByIdRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        ServiceService serviceService = new ServiceService();

        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        JSONParser parser = new JSONParser();
        Gson gson = new Gson();
        Integer id;
        Service service = null;
        try {
            JSONObject reqObject = (JSONObject) parser.parse(reader);
            if (reqObject.get("pathParameters") != null) {
                JSONObject pps = (JSONObject) reqObject.get("pathParameters");
                if (pps.get("id") != null) {
                    id = Integer.parseInt((String) pps.get("id"));
                    service = serviceService.findServiceById(Long.valueOf(id));
                }
            } else if (reqObject.get("queryStringParameters") != null) {
                JSONObject qps = (JSONObject) reqObject.get("queryStringParameters");
                if (qps.get("id") != null) {
                    id = Integer.parseInt((String) qps.get("id"));
                    service = serviceService.findServiceById(Long.valueOf(id));
                }
            }

        } catch (ParseException e) {
            context.getLogger().log("ERROR : " + e.getMessage());
        }
        writer.write(gson.toJson(service));
        reader.close();
        writer.close();
    }

    public List<Service> handleGetCompanyServicesRequest(Long companyId, Context context) {
        return companyService.getCompanyServices(companyId);
    }

    public Boolean handleAddServiceRequest(Service service, Context context) {
        return serviceService.addService(service.getName());

    }
}
