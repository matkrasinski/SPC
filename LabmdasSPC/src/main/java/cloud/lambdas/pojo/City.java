package cloud.lambdas.pojo;

public class City {
    private Long companyId;
    private String name;


    public City(Long companyId, String name) {
        this.companyId = companyId;
        this.name = name;
    }

    public City() {}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
