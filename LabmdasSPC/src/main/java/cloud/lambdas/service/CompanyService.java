package cloud.lambdas.service;

import cloud.lambdas.model.Company;
import cloud.lambdas.model.Day;
import cloud.lambdas.model.Service;
import cloud.lambdas.repository.CompanyRepository;

import java.sql.Date;
import java.util.List;

public class CompanyService {

    CompanyRepository companyRepository = new CompanyRepository();

    public List<Company> getAllCompanies() {
        return companyRepository.getAllCompanies();
    }

    public boolean addCompany(String companyName) {
        return companyRepository.addCompany(companyName);
    }

    public boolean deleteCompany(String companyName) {
        return companyRepository.deleteCompany(companyRepository.findCompanyByName(companyName).getId());
    }

    public List<Service> getCompanyServices(String companyName) {
        return companyRepository.getCompanyServices(companyRepository.findCompanyByName(companyName).getId());
    }

    public boolean addCompanyService(String companyName, Integer serviceId) {
        return companyRepository.addCompanyService(companyRepository.findCompanyByName(companyName).getId(), serviceId);
    }
    public List<Day> getCompanyForbiddenDays(String companyName) {
        return companyRepository.getCompanyForbiddenDays(companyRepository.findCompanyByName(companyName).getId());
    }

    public boolean addForbiddenDay(String companyName, Date date) {
        return companyRepository.addCompanyForbiddenDays(companyRepository.findCompanyByName(companyName).getId(), date);
    }

}
