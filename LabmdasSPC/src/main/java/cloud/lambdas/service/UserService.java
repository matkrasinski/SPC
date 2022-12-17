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

    public boolean addUser(String firstName, String lastName, String email, String password, Boolean isAdmin) {
        return userRepository.addUser(firstName, lastName, email, password, isAdmin);
    }

    public boolean deleteUser(Integer userId) {
        return userRepository.deleteUser(userId);
    }

    public User findById(Integer id) {
        return userRepository.findUserById(id);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public ServiceDto addServiceToUser(String email, Integer serviceId, Integer companyId, Date orderDate, String description) {
        CompanyUser companyUser =
                userRepository.addServiceToUser(findUserByEmail(email).getId(), companyId, serviceId, orderDate, description);
        return new ServiceDto(serviceService.findServiceById(serviceId).getName(),
                companyUser.getOrderDate(), companyUser.getDescription());
    }

    public List<ServiceDto> findUserServices(String email) {
        return userRepository.findUserServices(userRepository.findByEmail(email).getId());
    }

    public CompanyUser findUserCompanyUser(Integer id) {
        return userRepository.findUserCompanyUser(id);
    }

}
