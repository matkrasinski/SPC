package cloud.lambdas.service;

import cloud.lambdas.pojo.Company;
import cloud.lambdas.pojo.Day;
import cloud.lambdas.pojo.Service;
import cloud.lambdas.repository.CompanyRepository;

import java.sql.Date;
import java.util.List;

public class CompanyService {

    CompanyRepository companyRepository = new CompanyRepository();

    public List<Company> getAllCompanies() {
        return companyRepository.getAllCompanies();
    }

    public Company findCompanyByName(String name) {
        return companyRepository.findCompanyByName(name);
    }

    public Company findCompanyById(Long companyId) {
        return companyRepository.findCompanyById(companyId);
    }
    public boolean addCompany(Company company) {
        return companyRepository.addCompany(company);
    }

    public boolean deleteCompany(Long companyId) {
        return companyRepository.deleteCompany(companyId);
    }
    public List<Company> getCompaniesInCity(Long cityId) {
        return companyRepository.getCompaniesByCity(cityId);
    }
    public List<Service> getCompanyServices(Long companyId) {
        return companyRepository.getCompanyServices(companyId);
    }

    public boolean addCompanyService(Long companyId, Long serviceId) {
        return companyRepository.addCompanyService(companyId, serviceId);
    }
    public List<Day> getCompanyForbiddenDays(Long companyId) {
        return companyRepository.getCompanyForbiddenDays(companyId);
    }

    public Day addForbiddenDay(Day day) {
        return companyRepository.addCompanyForbiddenDays(day);
    }

}
