package com.niepeng.springbootts.controllers;
/**
 * Created by lsb on 17/5/17.
 */


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 聂鹏
 * @version 1.0
 * @date 17/5/17
 */

@Controller
@RequestMapping("/page")
public class PageController {

  @RequestMapping("/hello")
  @ResponseBody
  public String hello() {
    return "Hello World,rr12345";
  }

  @RequestMapping("/index")
  public String index(Model map) {
    map.addAttribute("host", "hostname我自己定义");
    return "index";
  }

  @RequestMapping("/helloEx")
  public String helloEx() throws Exception {
    throw new Exception("发生错误");
  }


}