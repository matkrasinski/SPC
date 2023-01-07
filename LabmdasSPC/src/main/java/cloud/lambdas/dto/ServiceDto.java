package cloud.lambdas.dto;


public class ServiceDto {

    private Integer amount;

    private Long serviceId;
    private String name;
    private Double price;

    public ServiceDto(Integer amount, Long serviceId, String name, Double price) {
        this.amount = amount;
        this.serviceId = serviceId;
        this.name = name;
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
