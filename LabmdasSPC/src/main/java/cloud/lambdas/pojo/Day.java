package cloud.lambdas.pojo;


import java.sql.Date;

public class Day {
    private Integer id;
    private Date forbiddenDay;

    public Day() {}

    public Day(Date forbiddenDay) {
        this.forbiddenDay = forbiddenDay;
    }

    public Day(Integer id, Date forbiddenDay) {
        this.id = id;
        this.forbiddenDay = forbiddenDay;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getForbiddenDay() {
        return forbiddenDay;
    }

    public void setForbiddenDay(Date forbiddenDay) {
        this.forbiddenDay = forbiddenDay;
    }
}
