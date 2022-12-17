package cloud.lambdas.pojo;


import java.sql.Date;

public class CompanyUser {

    private Long id;
    private Date orderDate;
    private String description;

    private Long companyId;
    private Long userId;

    private Long serviceId;

    public CompanyUser() {}

    public CompanyUser(Long id, Date orderDate, String description, Long companyId, Long userId, Long serviceId) {
        this.id = id;
        this.orderDate = orderDate;
        this.description = description;
        this.companyId = companyId;
        this.userId = userId;
        this.serviceId = serviceId;
    }

    public CompanyUser(Long userId, Long companyId, Long serviceId, Date orderDate, String description) {
        this.orderDate = orderDate;
        this.description = description;
        this.companyId = companyId;
        this.userId = userId;
        this.serviceId = serviceId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }
}

