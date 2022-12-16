package cloud.lambdas.service;

import cloud.lambdas.dto.ServiceDto;
import cloud.lambdas.model.User;
import cloud.lambdas.repository.UserRepository;

import java.sql.Date;
import java.util.List;

public class UserService {

    UserRepository userRepository = new UserRepository();

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

    public boolean addServiceToUser(String email, Integer serviceId, Integer companyId, Date orderDate, String description) {
        return userRepository.addServiceToUser(userRepository.findByEmail(email).getId(), serviceId, companyId, orderDate, description);
    }

    public List<ServiceDto> findUserServices(String email) {
        return userRepository.findUserServices(userRepository.findByEmail(email).getId());
    }

}
