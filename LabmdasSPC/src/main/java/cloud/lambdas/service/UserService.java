package cloud.lambdas.service;

import cloud.lambdas.dto.ServiceDto;
import cloud.lambdas.dto.ServiceUserDto;
import cloud.lambdas.pojo.CompanyService;
import cloud.lambdas.pojo.CompanyUser;
import cloud.lambdas.pojo.User;
import cloud.lambdas.repository.UserRepository;

import java.util.List;

public class UserService {

    private final UserRepository userRepository = new UserRepository();

    private final ServiceService serviceService = new ServiceService();
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public boolean addUser(String uuid) {
        return userRepository.addUser(uuid);
    }

    public boolean deleteUser(Long userId) {
        return userRepository.deleteUser(userId);
    }

    public User findUserByUuid(String uuid) {
        return userRepository.findUserByUuid(uuid);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public CompanyUser addServiceToUser(CompanyUser companyUser) {
        return userRepository.addServiceToUser(companyUser);
    }

    public List<ServiceUserDto> findUserServices(String uuid) {
        return userRepository.findUserServices(uuid);
    }

    public CompanyUser findCompanyUserById(Long id) {
        return userRepository.findUserCompanyUser(id);
    }

}
