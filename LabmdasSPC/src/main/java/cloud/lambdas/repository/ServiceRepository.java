package cloud.lambdas.repository;

import cloud.lambdas.dbUtils.DatabaseConnection;
import cloud.lambdas.pojo.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceRepository {
    private final DatabaseConnection databaseConnection = new DatabaseConnection();
    public List<Service> getAllServices() {
        List<Service> services = new ArrayList<>();
        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement selectStatement =
                    connection.prepareStatement("SELECT * FROM Service");
            ResultSet rs = selectStatement.executeQuery();
            while (rs.next()) {
                services.add(new Service(rs.getLong("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return services;
    }

    public Service findServiceById(Long id) {
        Service service = new Service();
        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement selectStatement = connection.prepareStatement("SELECT * from Service where id = " + id);
            ResultSet rs = selectStatement.executeQuery();
            if (rs.next())
                service = new Service(rs.getLong("id"), rs.getString("name"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return service;
    }

    public Service findServiceByName(String name) {
        Service service = new Service();
        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement selectStatement = connection.prepareStatement("SELECT * from Service where name = ?");
            selectStatement.setString(1, name);
            ResultSet rs = selectStatement.executeQuery();
            if (rs.next())
                service = new Service(rs.getLong("id"), rs.getString("name"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return service;
    }
    public boolean deleteService(Long id) {
        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement selectStatement =
                    connection.prepareStatement("DELETE from Service where id = ?");
            selectStatement.setLong(1, id);
            return !selectStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean addService(Service service) {
        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement selectStatement =
                    connection.prepareStatement("INSERT INTO Service(name) values (?)");
            selectStatement.setString(1, service.getName());
            return !selectStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Service> getServicesByCity(Long id) {
        List<Service> services = new ArrayList<>();
        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement selectStatement =
                    connection.prepareStatement("SELECT * FROM Company co " +
                            "JOIN City ci on co.city_id = ci.id WHERE ci.id = ?");
            selectStatement.setLong(1, id);
            ResultSet rs = selectStatement.executeQuery();
            while (rs.next()) {
                services.add(new Service(rs.getLong("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return services;
    }

}
