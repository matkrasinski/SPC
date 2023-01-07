package cloud.lambdas.service;

import cloud.lambdas.dto.CompanyServiceDto;
import cloud.lambdas.dto.Mapper;
import cloud.lambdas.pojo.Service;
import cloud.lambdas.repository.ServiceRepository;

import java.util.ArrayList;
import java.util.List;

public class ServiceService {

    private final ServiceRepository serviceRepository = new ServiceRepository();
    private final CompanyService companyService = new CompanyService();
    private final CityService cityService = new CityService();

    private final Mapper mapper = new Mapper();

    public boolean addService(String serviceName, Double price) {
        return serviceRepository.addService(serviceName, price);
    }

    public boolean deleteService(Long id) {
        return serviceRepository.deleteService(id);
    }

    public List<Service> getAllServices() {
        return serviceRepository.getAllServices();
    }

    public List<Service> getServicesInCity(Long id) {
        return serviceRepository.getServicesByCity(id);
    }
    public Service findServiceByName(String serviceName) {
        return serviceRepository.findServiceByName(serviceName);
    }

    public Service findServiceById(Long id) {
        return serviceRepository.findServiceById(id);
    }

    public List<CompanyServiceDto> getAllCompanyServices() {
        List<CompanyServiceDto> companyServices = new ArrayList<>();
        companyService.getAllCompanies().forEach(c -> {
            companyServices.add(new CompanyServiceDto(c.getName(), companyService.getCompanyServicesDto(c.getId())));
        });
        return companyServices;
    }

}
