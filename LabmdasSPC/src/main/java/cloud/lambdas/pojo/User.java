package cloud.lambdas.pojo;

public class User {
    private Long id;


    private String uuid;

    public User() {}

    public User(Long id, String uuid) {
        this.id = id;
        this.uuid = uuid;
    }

    public User( String uuid) {
        this.uuid = uuid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
