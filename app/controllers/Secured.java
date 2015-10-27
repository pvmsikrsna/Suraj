package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import model.Users;
import play.i18n.Messages;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;


public class Secured extends Security.Authenticator {
    @Override
    public String getUsername(Context ctx) {
        String token = ctx.request().getHeader("AUTH_TOKEN");
        if (token != null) {
            Users user = Users.findByAuthToken(token);
            if (user != null) {
                return user.getEmailId();
            }
        }
        return null;
    }

    @Override
    public Result onUnauthorized(Http.Context ctx) {
        ObjectNode result = Json.newObject();
        result.put("errorMessage", Messages.get("unauthorized"));
        return unauthorized(result);
    }
}
