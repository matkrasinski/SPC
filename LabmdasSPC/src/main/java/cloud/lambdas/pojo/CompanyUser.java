package cloud.lambdas.pojo;


import java.sql.Date;

public class CompanyUser {

    private Long id;
    private String description;

    private Long companyId;
    private Long userId;

    private Long serviceId;
    private String orderDate;
    private Long cityId;

    public CompanyUser() {}

    public CompanyUser(Long id, String orderDate, String description, Long companyId, Long userId, Long serviceId, Long cityId) {
        this.id = id;
        this.orderDate = orderDate;
        this.description = description;
        this.companyId = companyId;
        this.userId = userId;
        this.serviceId = serviceId;
        this.cityId = cityId;
    }

    public CompanyUser(Long userId, Long companyId, String date, Long serviceId, String description, Long cityId) {
        this.orderDate = date;
        this.description = description;
        this.companyId = companyId;
        this.userId = userId;
        this.serviceId = serviceId;
        this.cityId = cityId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
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

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }
}

