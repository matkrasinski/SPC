package cloud.lambdas.service;

import cloud.lambdas.dto.ServiceDto;
import cloud.lambdas.pojo.CompanyService;
import cloud.lambdas.pojo.CompanyUser;
import cloud.lambdas.pojo.User;
import cloud.lambdas.repository.UserRepository;

import java.sql.Date;
import java.util.List;

public class UserService {

    UserRepository userRepository = new UserRepository();

    ServiceService serviceService = new ServiceService();
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public boolean addUser(User user) {
        return userRepository.addUser(user);
    }

    public boolean deleteUser(Long userId) {
        return userRepository.deleteUser(userId);
    }

    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public ServiceDto addServiceToUser(CompanyUser companyUser) {
        userRepository.addServiceToUser(companyUser);
             return new ServiceDto(serviceService.findServiceById(companyUser.getServiceId()).getName(),
                companyUser.getOrderDate(), companyUser.getDescription());
    }

    public List<ServiceDto> findUserServices(Long id) {
        return userRepository.findUserServices(id);
    }

    public CompanyUser findCompanyUserById(Long id) {
        return userRepository.findUserCompanyUser(id);
    }

}
