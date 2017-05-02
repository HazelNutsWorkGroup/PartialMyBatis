package com.taiji.controller.manage;

import com.taiji.domain.manage.UserDomain;
import com.taiji.service.manage.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by Sleeb on 2017/5/2.
 */
@RestController
@RequestMapping("/v0.1/User")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @RequestMapping(value = "",method = RequestMethod.POST)
    public ResponseEntity<UserDomain> addUser(@RequestBody UserDomain userDomain, UriComponentsBuilder ucBuilder){

        return new ResponseEntity<UserDomain>(userDomain, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDomain> GetUser(@PathVariable Long id) {
        logger.warn("UserId:"+id.toString());
        UserDomain userDomain = userService.getUser(id);

        return new ResponseEntity<UserDomain>(userDomain, HttpStatus.OK);
    }

}
