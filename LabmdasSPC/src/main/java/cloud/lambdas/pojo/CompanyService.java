package cloud.lambdas.pojo;

public class CompanyService {
    private Long companyId;
    private Long serviceId;
    private Integer amount;

    public CompanyService(Long companyId, Long serviceId, Integer amount) {
        this.companyId = companyId;
        this.serviceId = serviceId;
        this.amount = amount;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}