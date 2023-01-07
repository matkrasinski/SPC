package cloud.lambdas.service;

import cloud.lambdas.dto.CityDto;
import cloud.lambdas.dto.Mapper;
import cloud.lambdas.dto.ServiceDto;
import cloud.lambdas.pojo.Company;
import cloud.lambdas.pojo.Day;
import cloud.lambdas.pojo.Service;
import cloud.lambdas.repository.CompanyRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CompanyService {

    Mapper mapper = new Mapper();
    private final CompanyRepository companyRepository = new CompanyRepository();

    public List<Company> getAllCompanies() {
        return companyRepository.getAllCompanies();
    }

    public Company findCompanyByName(String name) {
        return companyRepository.findCompanyByName(name);
    }

    public Company findCompanyById(Long companyId) {
        return companyRepository.findCompanyById(companyId);
    }
    public boolean addCompany(String companyName) {
        return companyRepository.addCompany(companyName);
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

    public List<ServiceDto> getCompanyServicesDto(Long companyId) {
        return companyRepository.getCompanyServicesDto(companyId);
    }
    public boolean addCompanyService(Long companyId, Long serviceId, Long amount) {
        return companyRepository.addCompanyService(companyId, serviceId, amount);
    }

    public List<Day> getCompanyForbiddenDays(Long companyId) {
        return companyRepository.getCompanyForbiddenDays(companyId);
    }

    public Day addForbiddenDay(Day day) {
        return companyRepository.addCompanyForbiddenDays(day);
    }

    public boolean deleteForbiddenDay(Long id) {
        return companyRepository.deleteCompanyForbiddenDay(id);
    }

    public List<CityDto> getCompanyCities(Long id) {
        return companyRepository.getCompanyCities(id).stream().map(mapper::mapCity).collect(Collectors.toList());
    }

}
