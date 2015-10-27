package model;

import com.avaje.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;



@Entity
@Table(name = "UserProfile")
public class UsersProfile {

    @Id
    @Column(name = "user_profile_id")
    private long id;
    @Column(name = "FirstName")
    private String firstName;
    @Column(name = "LastName")
    private String lastName;
    @Column(name = "DOB")
    private Date DOB;
    @Column(name = "Gender")
    private String gender;
    @Column(name = "Current_Timestamp")
    private Timestamp createdTstamp;
    @Column(name = "Updated_Timestamp")
    private Timestamp lastUpdatedTstamp;

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDOB() {
        return DOB;
    }

    public String getGender() {
        return gender;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public static Model.Finder<Long, UsersProfile> find = new Model.Finder<Long, UsersProfile>(Long.class,
            UsersProfile.class);

    public static UsersProfile findByFirstName(String fn) {
        return find.where().eq("FirstName", fn).findUnique();
    }

    public static UsersProfile findByLastName(String ln) {
        return find.where().eq("LastName", ln).findUnique();
    }

    public static UsersProfile findById(Long id) {
        return find.where().eq("user_profile_id", id).findUnique();
    }

    public static List<UsersProfile> findByGender(String g) {
        return find.where().eq("Gender", g).findList();
    }

}
