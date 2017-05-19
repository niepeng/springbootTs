package com.niepeng.springbootts.dao.mapper;

import com.niepeng.springbootts.dao.UserDO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by lsb on 17/5/19.
 */

@Mapper
public interface UserMapper {


  UserDO selectUser(Long id);

  List<UserDO> getUserList();

  void create(String name, Integer age);

  void deleteByName(String name);

}
