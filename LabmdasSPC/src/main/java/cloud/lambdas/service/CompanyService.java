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

    public Company findCompanyById(Integer id) {
        return companyRepository.findCompanyById(id);
    }
    public boolean addCompany(String companyName) {
        return companyRepository.addCompany(companyName);
    }

    public boolean deleteCompany(String companyName) {
        return companyRepository.deleteCompany(companyRepository.findCompanyByName(companyName).getId());
    }
    public List<Company> getCompaniesInCity(String cityName) {
        return companyRepository.getCompaniesByCity(cityName);
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

    public Day addForbiddenDay(String companyName, Date date) {
        return companyRepository.addCompanyForbiddenDays(companyRepository.findCompanyByName(companyName).getId(), date);
    }

}
