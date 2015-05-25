package io.spooncode.blog.sample.service;

import io.spooncode.blog.sample.domain.User;
import io.spooncode.blog.sample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by woniper on 15. 5. 25..
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUser(int userId) {
        return userRepository.findOne(userId);
    }
}
