package cloud.lambdas.pojo;

public class CompanyService {
    private Long companyId;
    private Long serviceId;

    public CompanyService(Long companyId, Long serviceId) {
        this.companyId = companyId;
        this.serviceId = serviceId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }
}