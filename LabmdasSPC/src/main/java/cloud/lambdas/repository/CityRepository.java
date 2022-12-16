package cloud.lambdas.repository;

import cloud.lambdas.dbUtils.DatabaseConnection;
import cloud.lambdas.model.City;
import cloud.lambdas.model.Company;
import cloud.lambdas.model.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityRepository {

    private final DatabaseConnection databaseConnection = new DatabaseConnection();

    public List<City> getAllCities() {
        List<City> cities = new ArrayList<>();
        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement selectStatement =
                    connection.prepareStatement("SELECT * FROM City");
            ResultSet rs = selectStatement.executeQuery();
            while (rs.next()) {
                cities.add(new City(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cities;
    }

    public List<Company> getCompaniesByCity(String cityName) {
        List<Company> companies = new ArrayList<>();
        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement selectStatement =
                    connection.prepareStatement("SELECT * FROM Company co " +
                            "JOIN City ci on co.id = ci.company_id WHERE ci.name = ?");
            selectStatement.setString(1, cityName);
            ResultSet rs = selectStatement.executeQuery();
            while (rs.next()) {
                companies.add(new Company(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return companies;
    }

    public List<Service> getServicesByCity(String cityName) {
        List<Service> services = new ArrayList<>();
        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement selectStatement =
                    connection.prepareStatement("SELECT * FROM Company co " +
                            "JOIN City ci on co.city_id = ci.id WHERE ci.name = ?");
            selectStatement.setString(1, cityName);
            ResultSet rs = selectStatement.executeQuery();
            while (rs.next()) {
                services.add(new Service(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return services;
    }

    public City findByCityName(String name) {
        City city = new City();
        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement selectStatement = connection.prepareStatement("SELECT * from City where name = ?");
            selectStatement.setString(1, name);
            ResultSet rs = selectStatement.executeQuery();
            if (rs.next())
                city = new City(rs.getInt("id"), rs.getString("name"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return city;
    }

    public boolean addCity(String name, Integer companyId) {
        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement selectStatement =
                    connection.prepareStatement("INSERT INTO City(name, company_id) values (?, ?)");
            selectStatement.setString(1, name);
            selectStatement.setInt(2, companyId);
            return !selectStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteUser(Integer cityId) {
        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement selectStatement =
                    connection.prepareStatement("DELETE from City where id = ?");
            selectStatement.setInt(1, cityId);
            return !selectStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
