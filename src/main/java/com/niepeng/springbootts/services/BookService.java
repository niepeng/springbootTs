package com.niepeng.springbootts.services;
/**
 * Created by lsb on 17/5/16.
 */


import org.springframework.stereotype.Component;

/**
 * @author 聂鹏
 * @version 1.0
 * @date 17/5/16
 */

@Component
public class BookService {

  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}