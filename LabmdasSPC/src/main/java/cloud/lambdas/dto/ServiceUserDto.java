package cloud.lambdas.dto;

import java.sql.Date;
import java.time.LocalDate;

public class ServiceUserDto {

    private Double price;
    private String serviceName;
    private Date orderDate;
    private String companyName;
    private String description;

    private String cityName;


    public ServiceUserDto(Double price, String serviceName, Date orderDate, String companyName, String description, String cityName) {
        this.price = price;
        this.serviceName = serviceName;
        this.orderDate = orderDate;
        this.companyName = companyName;
        this.description = description;
        this.cityName = cityName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
