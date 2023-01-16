package cloud.lambdas.repository;

import cloud.lambdas.dbUtils.DatabaseConnection;
import cloud.lambdas.pojo.City;
import cloud.lambdas.pojo.Company;
import cloud.lambdas.pojo.Service;

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
                cities.add(new City(rs.getLong("company_id"),
                        rs.getString("name")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cities;
    }

    public City findCityByName(String name) {
        City city = new City();
        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM City WHERE name = ?");
            selectStatement.setString(1, name);
            ResultSet rs = selectStatement.executeQuery();
            if (rs.next())
                city = new City(rs.getLong("company_id"),
                        rs.getString("name"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return city;
    }
    public City findCityById(Long id) {
        City city = new City();
        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM City WHERE id = ?");
            selectStatement.setLong(1, id);
            ResultSet rs = selectStatement.executeQuery();
            if (rs.next())
                city = new City(rs.getLong("company_id"),
                        rs.getString("name"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return city;
    }

    public City findCityByCompanyIdAndName(Long companyId, String name) {
        City city = new City();
        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement selectStatement = connection.prepareStatement(
                    "SELECT * FROM City WHERE companyId = ? and name = ?");
            selectStatement.setLong(1, companyId);
            selectStatement.setString(2,name);
            ResultSet rs = selectStatement.executeQuery();
            if (rs.next())
                city = new City(rs.getLong("company_id"),
                        rs.getString("name"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return city;
    }

    public List<City> getCitiesByCompany(Long companyId) {
        List<City> cities = new ArrayList<>();
        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement selectStatement =
                    connection.prepareStatement("SELECT * FROM City WHERE company_id = ?");
            selectStatement.setLong(1, companyId);
            ResultSet rs = selectStatement.executeQuery();
            while (rs.next()) {
                cities.add(new City(rs.getLong("company_id"),
                        rs.getString("name")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cities;
    }

    public boolean addCity(String cityName, Long companyId) {
        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement selectStatement =
                    connection.prepareStatement("INSERT INTO City(name, company_id) values (?, ?)");
            selectStatement.setString(1, cityName);
            selectStatement.setLong(2, companyId);
            return !selectStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteCity(Long cityId) {
        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement selectStatement =
                    connection.prepareStatement("DELETE from City where id = ?");
            selectStatement.setLong(1, cityId);
            return !selectStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
