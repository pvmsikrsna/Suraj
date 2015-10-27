package model;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.security.Timestamp;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "UserProfile")
public class UsersProfile {

    @Id
    private long Id;
    @OneToOne
    @JoinColumn(name = "Users_Id")
    private Users user;
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
        return Id;
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
        this.Id = id;
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
        return find.where().eq("Id", id).findUnique();
    }

    public static List<UsersProfile> findByGender(String g) {
        return find.where().eq("Gender", g).findList();
    }

    public static UsersProfile findByUser(Users user) {
        return find.where().eq("Users_Id", user.getId()).findUnique();
    }


    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
