package cloud.lambdas.dto;

import cloud.lambdas.pojo.Service;

import java.util.List;

public class CompanyServiceDto {

    private Long companyId;
    private List<Service> services;

    public CompanyServiceDto() {}

    public CompanyServiceDto(Long companyId, List<Service> services) {
        this.companyId = companyId;
        this.services = services;
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
}
