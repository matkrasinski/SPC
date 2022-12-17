package cloud.lambdas.service;

import cloud.lambdas.pojo.Service;
import cloud.lambdas.repository.ServiceRepository;

import java.util.List;

public class ServiceService {

    ServiceRepository serviceRepository = new ServiceRepository();

    public boolean addService(String serviceName) {
        return serviceRepository.addService(serviceName);
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

}
