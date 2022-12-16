package cloud.lambdas.model;


import java.sql.Date;

public class Day {
    private Integer id;
    private Date forbiddenDay;
    public Day(Date forbiddenDay) {
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
