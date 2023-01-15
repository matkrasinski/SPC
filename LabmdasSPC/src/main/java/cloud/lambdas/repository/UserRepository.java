package cloud.lambdas.repository;

import cloud.lambdas.dbUtils.DatabaseConnection;
import cloud.lambdas.dto.ServiceDto;
import cloud.lambdas.dto.ServiceUserDto;
import cloud.lambdas.pojo.CompanyUser;
import cloud.lambdas.pojo.User;
import cloud.lambdas.service.UserService;

import javax.xml.transform.Result;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private final DatabaseConnection databaseConnection = new DatabaseConnection();

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement selectStatement = connection.prepareStatement("SELECT * from User");
            ResultSet rs = selectStatement.executeQuery();
            while (rs.next()) {
                users.add(new User(rs.getLong("id"),
                        rs.getString("uuid")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public User findUserByUuid(String uuid) {
        User user = new User();
        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement selectStatement = connection.prepareStatement("SELECT * from User where uuid = ?");
            selectStatement.setString(1, uuid);
            ResultSet rs = selectStatement.executeQuery();
            if (rs.next())
                user = new User(rs.getLong("id"),
                        rs.getString("uuid"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public boolean deleteUser(Long id) {
        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement selectStatement =
                    connection.prepareStatement("DELETE from User where id = ?");
            selectStatement.setLong(1, id);
            return !selectStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean addUser(String uuid) {
        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement selectStatement =
                    connection.prepareStatement("INSERT INTO " +
                            "User(uuid)" +
                            " values (?)");
            selectStatement.setString(1, uuid);
            return !selectStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    public boolean updateUser(Long id, String firstName,
//                              String lastName, String email, String password, Boolean isAdmin) {
//        try(Connection connection = this.databaseConnection.createConnection()) {
//            PreparedStatement selectStatement =
//                    connection.prepareStatement("UPDATE User WHERE user_id = ? " +
//                            "SET " + checkIfBlank(firstName, "first_name") +
//                            checkIfBlank(lastName, "last_name") +
//                            checkIfBlank(email, "email") +
//                            checkIfBlank(password, "password_hash") +
//                            "is_admin = ?");
//            selectStatement.setString(2, firstName);
//            selectStatement.setString(3, lastName);
//            selectStatement.setString(4, email);
//            selectStatement.setString(5, password);
//            selectStatement.setBoolean(6, isAdmin);
//            selectStatement.setLong(1, id);
//            selectStatement.executeUpdate();
//            return true;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
    private String checkIfBlank(String s, String name) {
        return s.isBlank() ? name + " = ?, " : "";
    }

    public CompanyUser addServiceToUser(CompanyUser companyUser) {
        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement checkStatement = connection.prepareStatement("select c.id, count(cu.id), capacity from Company c " +
                    "join CompanyUser cu on c.id = cu.company_id " +
                    "where cu.order_day = ? and c.id = ?");
            checkStatement.setDate(1, Date.valueOf(companyUser.getOrderDate()));
            checkStatement.setLong(2, companyUser.getCompanyId());

            ResultSet rs = checkStatement.executeQuery();

            if (rs.next()) {
                int count = rs.getInt("count(cu.id)");
                int capacity = rs.getInt("capacity");
                if (count < capacity || count == 0) {
                    PreparedStatement selectStatement =
                            connection.prepareStatement("INSERT INTO " +
                                    "CompanyUser(user_id, company_id, service_id, order_day, description, city_id)" +
                                    " values (?, ?, ?, ?, ?, ?)");


                    selectStatement.setLong(1, companyUser.getUserId());
                    selectStatement.setLong(2, companyUser.getCompanyId());
                    selectStatement.setLong(3, companyUser.getServiceId());
                    selectStatement.setDate(4, Date.valueOf(companyUser.getOrderDate()));
                    selectStatement.setString(5, companyUser.getDescription());
                    selectStatement.setLong(6, companyUser.getCityId());
                    selectStatement.execute();
                } else {
                    return null;
                }
            }

            return new CompanyUser(companyUser.getUserId(), companyUser.getCompanyId(), companyUser.getOrderDate(),  companyUser.getServiceId(),
                     companyUser.getDescription(), companyUser.getCityId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<ServiceUserDto> findUserServices(String uuid) {
        List<ServiceUserDto> services = new ArrayList<>();
        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement selectStatement = connection.prepareStatement("Select price, s.name, order_day, description, c.name, ci.name from CompanyUser cu " +
                    "join Company c on c.id = cu.company_id " +
                    "join Service s on cu.service_id = s.id " +
                    "join `User` u on u.id = cu.user_id " +
                    "join City ci on ci.id = cu.city_id " +
                    "where u.uuid = ?");
            selectStatement.setString(1, uuid);
            ResultSet rs = selectStatement.executeQuery();
            while (rs.next()) {
                services.add(new ServiceUserDto(
                        rs.getDouble("price"),
                        rs.getString("s.name"),
                        rs.getDate("order_day"),
                        rs.getString("c.name"),
                        rs.getString("description"),
                        rs.getString("ci.name")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return services;
    }

    public User findByEmail(String email) {
        User user = null;
        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement selectStatement = connection.prepareStatement("SELECT * from User where email = ?");
            selectStatement.setString(1, email);
            ResultSet rs = selectStatement.executeQuery();
            if (rs.next())
                user = new User(rs.getLong("id"),
                        rs.getString("email"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public CompanyUser findUserCompanyUser(Long id) {
        CompanyUser companyUser = new CompanyUser();
        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement selectStatement = connection.prepareStatement("SELECT * from CompanyUser where user_id = ?");
            selectStatement.setLong(1, id);
            ResultSet rs = selectStatement.executeQuery();
            if (rs.next())
                companyUser = new CompanyUser(
                        rs.getLong("id"),
                        rs.getString("order_day"),
                        rs.getString("description"),
                        rs.getLong("company_od"),
                        rs.getLong("user_id"),
                        rs.getLong("service_id"),
                        rs.getLong("city_id")
                );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return companyUser;
    }

}
