package com.niepeng.springbootts.services.impl;
/**
 * Created by lsb on 17/5/18.
 */


import com.niepeng.springbootts.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @author 聂鹏
 * @version 1.0
 * @date 17/5/18
 */
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public void create(String name, Integer age) {
    jdbcTemplate.update("insert into springbootts_user(NAME, AGE) values(?, ?)", name, age);
  }

  @Override
  public void deleteByName(String name) {
    jdbcTemplate.update("delete from springbootts_user where NAME = ?", name);
  }

  @Override
  public Integer getAllUsers() {
    return jdbcTemplate.queryForObject("select count(1) from springbootts_user", Integer.class);
  }

  @Override
  public void deleteAllUsers() {
    jdbcTemplate.update("delete from springbootts_user");
  }

}