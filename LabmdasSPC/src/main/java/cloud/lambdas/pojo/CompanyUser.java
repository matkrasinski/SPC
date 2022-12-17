package cloud.lambdas.pojo;


import java.sql.Date;

public class CompanyUser {

    private Integer id;
    private Date orderDate;
    private String description;

    private Integer companyId;
    private Integer userId;

    private Integer serviceId;

    public CompanyUser() {}

    public CompanyUser(Integer id, Date orderDate, String description, Integer companyId, Integer userId, Integer serviceId) {
        this.id = id;
        this.orderDate = orderDate;
        this.description = description;
        this.companyId = companyId;
        this.userId = userId;
        this.serviceId = serviceId;
    }

    public CompanyUser(Integer userId, Integer companyId, Integer serviceId, Date orderDate, String description) {
        this.orderDate = orderDate;
        this.description = description;
        this.companyId = companyId;
        this.userId = userId;
        this.serviceId = serviceId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }
}

