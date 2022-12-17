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

    public boolean addService(String serviceName) {
        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement selectStatement =
                    connection.prepareStatement("INSERT INTO Service(name) values (?)");
            selectStatement.setString(1, serviceName);
            return !selectStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Service> getServicesByCity(String name) {
        List<Service> services = new ArrayList<>();
        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement selectStatement =
                    connection.prepareStatement("Select s.id, s.name from Service s " +
                            "join CompanyService cs on cs.service_id = s.id " +
                            "join Company c on c.id = cs.company_id " +
                            "join City ci " +
                            "where ci.name = ? ");
            selectStatement.setString(1, name);
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
