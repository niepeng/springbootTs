package com.niepeng.springbootts.controllers;
/**
 * Created by lsb on 17/5/19.
 */


import com.niepeng.springbootts.dao.UserDO;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 聂鹏
 * @version 1.0
 * @date 17/5/19
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

  @ApiOperation(value="获取user列表数据", notes="暂时没有任何参数,来获取userList的数据,从数据库中获取实现")
  @RequestMapping(value="/list", method= RequestMethod.GET)
  public List<UserDO> getUserList() {
    return userMapper.getUserList();
  }
}