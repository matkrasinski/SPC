package cloud.lambdas.repository;

import cloud.lambdas.dbUtils.DatabaseConnection;
import cloud.lambdas.pojo.Company;
import cloud.lambdas.pojo.Day;
import cloud.lambdas.pojo.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyRepository {
    private final DatabaseConnection databaseConnection = new DatabaseConnection();

    public List<Company> getAllCompanies() {
        List<Company> companies = new ArrayList<>();
        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement selectStatement =
                    connection.prepareStatement("SELECT * FROM Company");
            ResultSet rs = selectStatement.executeQuery();
            while (rs.next()) {
                companies.add(new Company(rs.getLong("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return companies;
    }

    public Company findCompanyByName(String name) {
        Company company = new Company();
        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM Company WHERE name = ?");
            selectStatement.setString(1, name);
            ResultSet rs = selectStatement.executeQuery();
            if (rs.next())
                company = new Company(rs.getLong("id"), rs.getString("name"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return company;
    }
    public Company findCompanyById(Long id) {
        Company company = new Company();
        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM Company WHERE id = ?");
            selectStatement.setLong(1, id);
            ResultSet rs = selectStatement.executeQuery();
            if (rs.next())
                company = new Company(rs.getLong("id"), rs.getString("name"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return company;
    }
    public boolean deleteCompany(Long id) {
        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement selectStatement =
                    connection.prepareStatement("DELETE FROM Company WHERE id = ?");
            selectStatement.setLong(1, id);
            return !selectStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean addCompany(String companyName) {
        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement selectStatement =
                    connection.prepareStatement("INSERT INTO Company(name) VALUES (?)");
            selectStatement.setString(1, companyName);
            return !selectStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Service> getCompanyServices(Long id) {
        List<Service> services = new ArrayList<>();
        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement selectStatement = connection.prepareStatement("SELECT s.id, name " +
                    "FROM CompanyService cs " +
                    "JOIN Service s on s.id = cs.service_id " +
                    "WHERE cs.company_id = ?");
            selectStatement.setLong(1, id);
            ResultSet rs = selectStatement.executeQuery();
            while (rs.next()) {
                services.add(new Service(
                        rs.getLong("id"),
                        rs.getString("name")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return services;
    }

    public boolean addCompanyService(Long companyId, Long serviceId) {
        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement selectStatement =
                    connection.prepareStatement("INSERT INTO " +
                            "CompanyService(company_id, service_id)" +
                            " values (?, ?)");
            selectStatement.setLong(1, companyId);
            selectStatement.setLong(2, serviceId);
            return !selectStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Day addCompanyForbiddenDays(Day day) {
        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement selectStatement =
                    connection.prepareStatement("INSERT INTO " +
                            "Day(company_id, forbidden_day)" +
                            " values (?, ?)");
            selectStatement.setLong(1, day.getCompanyId());
            selectStatement.setDate(2, day.getForbiddenDay());
            selectStatement.execute();
            return new Day(day.getCompanyId(), day.getForbiddenDay());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Day> getCompanyForbiddenDays(Long companyId) {
        List<Day> days = new ArrayList<>();
        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement selectStatement =
                    connection.prepareStatement("SELECT forbidden_day FROM Day WHERE company_id = ?");
            selectStatement.setLong(1, companyId);
            ResultSet rs = selectStatement.executeQuery();
            while (rs.next()) {
                days.add(new Day(rs.getDate("forbidden_day")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return days;
    }

    public List<Company> getCompaniesByCity(Long cityId) {
        List<Company> companies = new ArrayList<>();
        try(Connection connection = this.databaseConnection.createConnection()) {
            PreparedStatement selectStatement =
                    connection.prepareStatement("SELECT * FROM Company co " +
                            "JOIN City ci on co.id = ci.company_id WHERE ci.city_id = ?");
            selectStatement.setLong(1, cityId);
            ResultSet rs = selectStatement.executeQuery();
            while (rs.next()) {
                companies.add(new Company(rs.getLong("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return companies;
    }
}
