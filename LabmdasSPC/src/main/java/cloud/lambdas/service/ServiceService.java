package cloud.lambdas.service;

import cloud.lambdas.pojo.Service;
import cloud.lambdas.repository.ServiceRepository;

import java.util.List;

public class ServiceService {

    ServiceRepository serviceRepository = new ServiceRepository();

    public boolean addService(String name) {
        return serviceRepository.addService(name);
    }

    public boolean deleteService(Integer id) {
        return serviceRepository.deleteService(id);
    }

    public List<Service> getAllServices() {
        return serviceRepository.getAllServices();
    }

    public List<Service> getServicesInCity(String cityName) {
        return serviceRepository.getServicesByCity(cityName);
    }
    public Service findServiceByName(String serviceName) {
        return serviceRepository.findServiceByName(serviceName);
    }

    public Service findServiceById(Integer id) {
        return serviceRepository.findServiceById(id);
    }

}
