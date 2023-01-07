package cloud.lambdas.pojo;


import java.sql.Date;

public class Day {
    private Long companyId;
    private Date forbiddenDay;

    public Day() {}

    public Day(Date forbiddenDay) {
        this.forbiddenDay = forbiddenDay;
    }

    public Day(Long companyId, Date forbiddenDay) {
        this.companyId = companyId;
        this.forbiddenDay = forbiddenDay;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long id) {
        this.companyId = id;
    }

    public Date getForbiddenDay() {
        return forbiddenDay;
    }

    public void setForbiddenDay(Date forbiddenDay) {
        this.forbiddenDay = forbiddenDay;
    }
}
