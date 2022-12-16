package cloud.lambdas.dto;


import java.sql.Date;

public class ServiceDto {
    private String name;
    private Date order_date;
    private String description;

    public ServiceDto() {}

    public ServiceDto(String name, Date order_date, String description) {
        this.name = name;
        this.order_date = order_date;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
