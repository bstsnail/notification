package com.bstsnail.service;

import com.bstsnail.model.User;

import java.util.List;

/**
 * Created with Intellij IDEA.
 * User: Robin
 * Date: 12/26/16
 */
public interface UserService {

    void create(User u);

    void delete(String email);

    User getUser(String email);

    User getUser(int id);

    List<User> getAllUser();

    void updateUser(String username, String password, String email);
}
