package cloud.lambdas.service;

import cloud.lambdas.dto.CompanyServiceDto;
import cloud.lambdas.pojo.Company;
import cloud.lambdas.pojo.Service;
import cloud.lambdas.repository.ServiceRepository;

import java.util.ArrayList;
import java.util.List;

public class ServiceService {

    ServiceRepository serviceRepository = new ServiceRepository();
    CompanyService companyService = new CompanyService();

    public boolean addService(String serviceName) {
        return serviceRepository.addService(serviceName);
    }

    public boolean deleteService(Long id) {
        return serviceRepository.deleteService(id);
    }

    public List<Service> getAllServices() {
        return serviceRepository.getAllServices();
    }

    public List<Service> getServicesInCity(String name) {
        return serviceRepository.getServicesByCity(name);
    }
    public Service findServiceByName(String serviceName) {
        return serviceRepository.findServiceByName(serviceName);
    }

    public Service findServiceById(Long id) {
        return serviceRepository.findServiceById(id);
    }

    public List<CompanyServiceDto> getAllCompanyServices() {
        List<CompanyServiceDto> companyServices = new ArrayList<>();
        for (Company c : companyService.getAllCompanies())
            companyServices.add(new CompanyServiceDto(c.getId(), c.getName(), companyService.getCompanyServices(c.getId())));
        return companyServices;
    }

}
