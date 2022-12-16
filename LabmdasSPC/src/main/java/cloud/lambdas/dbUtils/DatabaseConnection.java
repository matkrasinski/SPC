package cloud.lambdas.dbUtils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private final String url;
    private final String userName;
    private final String password;

    public DatabaseConnection() {
        this.url = System.getenv("MYSQL_URL");
        this.userName = System.getenv("MYSQL_USERNAME");
        this.password = System.getenv("MYSQL_PASSWORD");
    }

    public DatabaseConnection(String url,  String userName, String password) {
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    public Connection createConnection() {
        try {
            return DriverManager.getConnection(this.getUrl(), this.getUserName(), this.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Couldn't connect to DB");
        }
        return null;
    }

    public String getUrl() {
        return url;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
