package model;

import java.sql.Timestamp;

@Entity
@Table(name = "Users")
public class Users {

    private Long id;
    private String emailId;
    private String password;
    private String auth_token;
    private Timestamp createdTstamp;
    private Timestamp lastUpdatedTstamp;

    public long getId() {
        return id;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getAuth_token() {
        return auth_token;
    }

    public String getPassword() {
        return password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuth_token(String auth_token) {
        this.auth_token = auth_token;
    }
    public static Finder<Long, Users> find = new Finder<Long, Users>(Long.class,
            Users.class);
}
