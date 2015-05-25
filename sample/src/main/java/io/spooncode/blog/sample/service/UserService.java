package io.spooncode.blog.sample.service;

import io.spooncode.blog.sample.domain.User;

/**
 * Created by woniper on 15. 5. 22..
 */
public interface UserService {

    User createUser(User user);

    User getUser(int userId);
}
