package com.niepeng.springbootts.services;

import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lsb on 17/5/18.
 */
public interface UserService {

  @Transactional
  void create(String name, Integer age);

  void deleteByName(String name);

  Integer getAllUsers();

  void deleteAllUsers();

}
