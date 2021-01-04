package com.hx.edit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hx.edit.entity.User;
import com.hx.edit.service.UserService;

/**
 * @author zhouli
 */
@RequestMapping("/user")
@RestController
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping("/findAll")
  public List<User> findAll(){
      return userService.findAll();
  }
}
