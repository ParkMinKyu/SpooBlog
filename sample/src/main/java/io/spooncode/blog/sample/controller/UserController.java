package io.spooncode.blog.sample.controller;

import io.spooncode.blog.sample.domain.User;
import io.spooncode.blog.sample.service.UserService;
import io.spooncode.blog.sample.support.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Created by woniper on 15. 5. 22..
 */
@RestController
public class UserController {

    @Autowired private UserService userService;
    @Autowired private ModelMapper modelMapper;

    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public ResponseEntity getUser(@PathVariable("userId") int userId) {
        User user = userService.getUser(userId);

        if(user == null) return new ResponseEntity<> (HttpStatus.NO_CONTENT);

        return new ResponseEntity<> (createResponseUser(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createUser(@RequestBody UserDto.Request userDto, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return new ResponseEntity<> (bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        User user = userService.createUser(createUser(userDto));
        return new ResponseEntity<> (createResponseUser(user), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/users", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateUser(@RequestBody UserDto.Request userDto) {
        return null;
    }

    @RequestMapping(value = "/users/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable("userId") int userId) {
        return null;
    }

    private UserDto.Response createResponseUser(User user) {
        return modelMapper.map(user, UserDto.Response.class);
    }

    private UserDto.Request createRequestUser(User user) {
        return modelMapper.map(user, UserDto.Request.class);
    }

    private User createUser(UserDto.Request userDto) {
        return modelMapper.map(userDto, User.class);
    }


}
