package cloud.lambdas.repository;

import cloud.lambdas.dbUtils.DatabaseConnection;
import cloud.lambdas.dto.ServiceDto;
import cloud.lambdas.pojo.CompanyUser;
import cloud.lambdas.pojo.User;

import java.sql.*;
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
                users.add(new User(rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("hashed_password"),
                        rs.getBoolean("is_admin")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public User findUserById(Integer id) {
        User user = new User();
        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement selectStatement = connection.prepareStatement("SELECT * from User where id = " + id);
            ResultSet rs = selectStatement.executeQuery();
            if (rs.next())
                user = new User(rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("hashed_password"),
                        rs.getBoolean("is_admin"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public boolean deleteUser(Integer id) {
        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement selectStatement =
                    connection.prepareStatement("DELETE from User where id = ?");
            selectStatement.setInt(1, id);
            return !selectStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean addUser(String firstName, String lastName, String email, String password, Boolean isAdmin) {
        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement selectStatement =
                    connection.prepareStatement("INSERT INTO " +
                            "User(first_name, last_name, email, hashed_password, is_admin)" +
                            " values (?, ?, ?, ?, ?)");
            selectStatement.setString(1, firstName);
            selectStatement.setString(2, lastName);
            selectStatement.setString(3, email);
            selectStatement.setString(4, password);
            selectStatement.setBoolean(5, isAdmin);
            return !selectStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    public boolean updateUser(Integer id, String firstName,
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
//            selectStatement.setInt(1, id);
//            selectStatement.executeUpdate();
//            return true;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    private String checkIfBlank(String s, String name) {
//        return s.isBlank() ? name + " = ?, " : "";
//    }

    public CompanyUser addServiceToUser(Integer userId, Integer serviceId, Integer companyId, Date orderDate, String description) {
        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement selectStatement =
                    connection.prepareStatement("INSERT INTO " +
                            "CompanyUser(user_id, company_id, service_id, order_day, description)" +
                            " values (?, ?, ?, ?, ?)");
            selectStatement.setInt(1, userId);
            selectStatement.setInt(2, companyId);
            selectStatement.setInt(3, serviceId);
            selectStatement.setDate(4, orderDate);
            selectStatement.setString(5, description);
            selectStatement.execute();
            return new CompanyUser(userId, companyId, serviceId, orderDate, description);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<ServiceDto> findUserServices(Integer id) {
        List<ServiceDto> services = new ArrayList<>();
        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement selectStatement = connection.prepareStatement("SELECT order_day, description, name " +
                    "FROM CompanyUser cu " +
                    "JOIN Service s on s.id = cu.service_id " +
                    "WHERE cu.user_id = ?");
            selectStatement.setInt(1, id);
            ResultSet rs = selectStatement.executeQuery();
            while (rs.next()) {
                services.add(new ServiceDto(
                        rs.getString("name"),
                        rs.getDate("order_day"),
                        rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return services;
    }

    public User findByEmail(String email) {
        User user = new User();
        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement selectStatement = connection.prepareStatement("SELECT * from User where email = ?");
            selectStatement.setString(1, email);
            ResultSet rs = selectStatement.executeQuery();
            if (rs.next())
                user = new User(rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("hashed_password"),
                        rs.getBoolean("is_admin"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public CompanyUser findUserCompanyUser(Integer id) {
        CompanyUser companyUser = new CompanyUser();
        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement selectStatement = connection.prepareStatement("SELECT * from CompanyUser where user_id = ?");
            selectStatement.setInt(1, id);
            ResultSet rs = selectStatement.executeQuery();
            if (rs.next())
                companyUser = new CompanyUser(
                        rs.getInt("id"),
                        rs.getDate("order_day"),
                        rs.getString("description"),
                        rs.getInt("company_od"),
                        rs.getInt("user_id"),
                        rs.getInt("service_id")
                );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return companyUser;
    }

}
