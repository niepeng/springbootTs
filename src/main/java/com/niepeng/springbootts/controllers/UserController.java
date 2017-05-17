package com.niepeng.springbootts.controllers;
/**
 * Created by lsb on 17/5/16.
 */


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 聂鹏
 * @version 1.0
 * @email lisenbiao@51huadian.cn
 * @date 17/5/16
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {


  @RequestMapping("/index")
  public String index() {
    return "Hello World";
  }
}