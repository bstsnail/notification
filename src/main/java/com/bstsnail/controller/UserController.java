package com.bstsnail.controller;

import com.bstsnail.model.User;
import com.bstsnail.response.Response;
import com.bstsnail.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with Intellij IDEA.
 * User: Robin
 * Date: 12/26/16
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/notify/user/register", method = RequestMethod.POST)
    public Response register(@RequestBody User user) {
        System.out.println("Retrieve User - " + user);
        userService.create(user);
        Response resp = new Response();
        resp.setStatus(200);
        resp.setErrmsg("OK");
        return resp;
    }

    @RequestMapping(value = "/notify/user/login", method = RequestMethod.POST)
    public Response login(@RequestBody User user) {
        System.out.println("Login - " + user);
        Response resp = new Response();
        User dbUser = userService.getUser(user.getEmail());
        if (dbUser != null && dbUser.getEmail() != null &&
                dbUser.getEmail().equals(user.getEmail())) {
            resp.setStatus(200);
            resp.setErrmsg("OK");
        }
        else {
            resp.setStatus(401);
            resp.setErrmsg("Email or password not right");
        }
        return resp;
    }
}
