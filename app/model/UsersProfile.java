package model;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by zemoso014 on 27/10/15.
 */
public class UsersProfile {

    private long id;
    private String firstName;
    private String lastName;
    private Date DOB;
    private String gender;
    private Timestamp createdTstamp;
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

}
