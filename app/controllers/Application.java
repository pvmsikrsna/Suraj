package controllers;

import Utilities.Encryption;
import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import model.Users;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

import java.util.UUID;

public class Application extends Controller {


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
        user.setPassword("");
        user.setAuth_token("");
        Ebean.save(user);
        return ok();
    }

}
