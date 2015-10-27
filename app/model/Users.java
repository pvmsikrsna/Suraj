package model;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.CreatedTimestamp;
import com.avaje.ebean.annotation.UpdatedTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;


@Entity
@Table(name = "Users")
public class Users {

    @Column(name = "Id")
    @Id
    private Long id;

    @Column(name = "EmailID")
    private String emailId;
    @Column(name = "Password")
    private String password;
    @Column(name = "Authorization_Token")
    private String auth_token;

    @CreatedTimestamp
    @Column(name = "Current_Timestamp")
    private Timestamp createdTstamp;

    @UpdatedTimestamp
    @Column(name = "Updated_Timestamp")
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

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreatedTstamp() {
        return createdTstamp;
    }

    public void setCreatedTstamp(Timestamp createdTstamp) {
        this.createdTstamp = createdTstamp;
    }

    public Timestamp getLastUpdatedTstamp() {
        return lastUpdatedTstamp;
    }

    public void setLastUpdatedTstamp(Timestamp lastUpdatedTstamp) {
        this.lastUpdatedTstamp = lastUpdatedTstamp;
    }

    public static Model.Finder<Long, Users> find = new Model.Finder<Long, Users>(Long.class,
            Users.class);

    public static Users findByEmail(String email) {
        return find.where().eq("EmailId", email).findUnique();
    }

    public static Users findByPassword(String password) {
        return find.where().eq("Password", password).findUnique();
    }

    public static Users findByAuthToken(String auth_token) {
        return find.where().eq("Authorization_Token", auth_token).findUnique();
    }
}
