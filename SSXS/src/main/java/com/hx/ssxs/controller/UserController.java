package com.hx.ssxs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hx.ssxs.entity.User;
import com.hx.ssxs.service.UserService;

/**
 * @author zhouli
 */
@RequestMapping("/user")
@RestController
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping("/findAll")
  public List<User> findAll(HttpServletRequest request){
      return userService.findAll();
  }
}
