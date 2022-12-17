package cloud.lambdas.handlers;

import cloud.lambdas.dto.CompanyServiceDto;
import cloud.lambdas.pojo.Company;
import cloud.lambdas.service.CompanyService;
import cloud.lambdas.service.ServiceService;
import com.amazonaws.services.lambda.runtime.Context;

import java.util.List;

public class CompanyHandler {

    CompanyService companyService = new CompanyService();

    public Boolean handleAddCompanyRequest(Company company, Context context) {
        return companyService.addCompany(company.getName());
    }

    public Boolean handleDeleteRequest(Long companyId, Context context) {
        return companyService.deleteCompany(companyId);
    }

    public List<Company> handleGetCompaniesInCityRequest(Long cityId, Context context) {
        return companyService.getCompaniesInCity(cityId);
    }

    public Company handleFindCompanyByIdRequest(Long id, Context context) {
        return companyService.findCompanyById(id);
    }

    public Company handleFindCompanyByNameRequest(String s, Context context) {
        return companyService.findCompanyByName(s);
    }
    public List<Company> handleGetAllCompaniesRequest(Context context) {
        return companyService.getAllCompanies();
    }

    public List<CompanyServiceDto> handleGetAllCompanyServicesRequest(Context context) {
        ServiceService serviceService = new ServiceService();
        return serviceService.getAllCompanyServices();
    }

}
