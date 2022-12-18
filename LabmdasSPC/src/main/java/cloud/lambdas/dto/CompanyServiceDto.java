package cloud.lambdas.dto;

import cloud.lambdas.pojo.City;
import cloud.lambdas.pojo.Day;
import cloud.lambdas.pojo.Service;

import java.util.List;

public class CompanyServiceDto {

    private Long companyId;
    private String companyName;
    private List<Service> services;
    private List<Day> forbiddenDays;
    private List<CityDto> cities;

    public CompanyServiceDto() {}

    public CompanyServiceDto(Long companyId, String companyName, List<Service> services, List<Day> forbiddenDays, List<CityDto> cities) {
        this.companyId = companyId;
        this.services = services;
        this.companyName = companyName;
        this.forbiddenDays = forbiddenDays;
        this.cities = cities;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Day> getForbiddenDays() {
        return forbiddenDays;
    }

    public void setForbiddenDays(List<Day> forbiddenDays) {
        this.forbiddenDays = forbiddenDays;
    }

    public List<CityDto> getCities() {
        return cities;
    }

    public void setCities(List<CityDto> cities) {
        this.cities = cities;
    }
}
