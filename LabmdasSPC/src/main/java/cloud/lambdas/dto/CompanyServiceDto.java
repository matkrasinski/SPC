package cloud.lambdas.dto;

import java.util.List;

public class CompanyServiceDto {

    private String companyName;
    private List<ServiceDto> services;

    public CompanyServiceDto() {}

    public CompanyServiceDto(String companyName, List<ServiceDto> services) {
        this.companyName = companyName;
        this.services = services;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<ServiceDto> getServices() {
        return services;
    }

    public void setServices(List<ServiceDto> services) {
        this.services = services;
    }
}
