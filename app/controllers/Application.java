package controllers;

import Utilities.Encryption;
import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import model.Users;
import model.UsersProfile;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.index;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Application extends Controller {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result register() {

        ObjectNode result = Json.newObject();
        JsonNode json = request().body().asJson();
        System.out.println("Input to register method :" + json.toString());

        String emailId = json.findPath("emailId").textValue();
        String passwd = json.findPath("password").textValue();


        if (emailId == null || "".equals(emailId.trim())) {
            result.put("Error Message", "Please enter your email");
            return badRequest(result);
        }
        if (passwd == null || "".equals(passwd.trim())) {
            result.put("Error Message", "Please enter your password");
            return badRequest(result);
        }
        emailId = emailId.trim();
        passwd = passwd.trim();

        Users user = null;
        user = Users.findByEmail(emailId);
        if (user != null) {
            result.put("Error Message", "Sorry another user exists by this emailId.Please choose some other emailId");
            return badRequest(result);
        }
        String password = Encryption.calculateHash(passwd);
        String authToken = UUID.randomUUID().toString();
        user = new Users();
        user.setEmailId(emailId);
        user.setPassword(password);
        user.setAuth_token(authToken);
        Ebean.save(user);
        UsersProfile usersProfile = new UsersProfile();
        usersProfile.setUser(user);
        Ebean.save(usersProfile);
        result.put("Success Message", "your registration was successfull");
        return ok(result);
    }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result login() {
        ObjectNode result = Json.newObject();
        JsonNode json = request().body().asJson();
        System.out.println("Input to login method :" + json.toString());
        String emailId = json.findPath("emailId").textValue();
        String passwd = json.findPath("password").textValue();

        if (emailId == null || "".equals(emailId.trim())) {
            result.put("Error Message", "Please enter email first");
            return badRequest(result);
        }
        if (passwd == null || "".equals(passwd.trim())) {
            result.put("Error Message", "Please enter your password");
            return badRequest(result);
        }
        emailId = emailId.trim();
        passwd = passwd.trim();

        Users user = Users.findByEmail(emailId);

        if (user == null) {
            result.put("Error Message", "Please register yourself");
            return unauthorized(result);
        } else {
            String passwordDatabase = user.getPassword();
            if (Encryption.matchHash(passwd, passwordDatabase)) {
                result.put("Success", user.getEmailId());
                result.put("Details", "You can view your details and update your profile");
                return ok(result);
            } else {
                result.put("Unauthorised Access", "Please enter correct credentials");
                return unauthorized(result);
            }

        }
    }


    @Security.Authenticated(Secured.class)
    @BodyParser.Of(BodyParser.Json.class)
    public static Result update() throws ParseException {
        ObjectNode result = Json.newObject();
        JsonNode json = request().body().asJson();
        System.out.println("Input to update method :" + json.toString());

        Users user = Users.findByAuthToken(request().getHeader("AUTH_TOKEN"));
        String fname = json.findPath("firstName").textValue();
        String lname = json.findPath("lastName").textValue();
        String gender = json.findPath("gender").textValue();
        String dob = json.findPath("DOB").textValue();
        Date DOB = null;
        DOB = sdf.parse(dob);
        System.out.println("DOB is" + DOB.toString());
        if (fname == null || "".equals(fname.trim()) || lname == null || "".equals(lname.trim()) || gender == null
                || "".equals(gender.trim()) || dob == null || "".equals(dob.trim())) {
            result.put("ErrorMessage", "Please give all the details.Recheck again");
            return badRequest(result);
        }
        fname = fname.trim();
        lname = lname.trim();
        gender = gender.trim();
        dob = dob.trim();

        if (!(gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F"))) {
            result.put("ErrorMessage", "Please specify gender as m/f only");
            return badRequest(result);
        }

        UsersProfile usersProfile = UsersProfile.findByUser(user);
        usersProfile.setUser(user);
        usersProfile.setFirstName(fname);
        usersProfile.setLastName(lname);
        usersProfile.setGender(gender);
        usersProfile.setDOB(DOB);
        Ebean.update(usersProfile);

        System.out.println("User_Profile firstname is " + usersProfile.getFirstName());

        result.put("SuccessMessage", "Congrats!!!! your profile has been updated");
        return ok(result);
    }


    @Security.Authenticated(Secured.class)
    public static Result view() {
        ObjectNode result = Json.newObject();
        Users user = Users.findByAuthToken(request().getHeader("AUTH_TOKEN"));
        UsersProfile usersProfile = UsersProfile.findByUser(user);
        result.put("firstName", usersProfile.getFirstName());
        result.put("lastName", usersProfile.getLastName());
        result.put("gender", usersProfile.getGender());
        result.put("DOB", sdf.format(usersProfile.getDOB()));
        return ok(result);

    }


}
