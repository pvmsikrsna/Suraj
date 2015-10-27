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
        String emailId = json.findPath("emailId").textValue().trim();
        String passwd = json.findPath("password").textValue().trim();


        if ("".equals(emailId)) {
            result.put("Error Message", "Please enter email first");
            return badRequest(result);
        }
        if ("".equals(passwd)) {
            result.put("Error Message", "Please enter your password");
            return badRequest(result);
        }

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
        String emailId = json.findPath("emailId").textValue().trim();
        String passwd = json.findPath("password").textValue().trim();

        if ("".equals(emailId)) {
            result.put("Error Message", "Please enter email first");
            return badRequest(result);
        }
        if ("".equals(passwd)) {
            result.put("Error Message", "Please enter your password");
            return badRequest(result);
        }
        String password = Encryption.calculateHash(passwd);
        Users user = Users.findByEmail(emailId);
        String passwordDatabase = user.getPassword();

        if (user != null) {
            if (password.equals(passwordDatabase)) {
                result.put("Success", user.getEmailId());
                return ok(result);
            } else {
                return unauthorized("Unauthorised Access", "Please enter correct credentials");
            }

        } else {
            result.put("Error Message", "Please register yourself");
            return unauthorized(result);
        }
    }

    @Security.Authenticated(Secured.class)
    @BodyParser.Of(BodyParser.Json.class)
    public static Result update() throws ParseException {
        ObjectNode result = Json.newObject();
        JsonNode json = request().body().asJson();
        System.out.println("Input to update method :" + json.toString());
        Users user = Users.findByAuthToken(request().getHeader("AUTH_TOKEN"));
        String fname = json.findPath("firstName").textValue().trim();
        String lname = json.findPath("lastName").textValue().trim();
        String gender = json.findPath("gender").textValue().trim();
        String dob = json.findPath("DOB").textValue().trim();
        Date DOB = null;
        DOB = sdf.parse(dob);
        System.out.println("DOB is" + DOB.toString());
        if ("".equals(fname) || "".equals(lname) || "".equals(gender) || "".equals(dob)) {
            result.put("ErrorMessage", "Please give all the details.Recheck again");
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
    public static Result view(){

    }


}
