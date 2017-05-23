package com.niepeng.springbootts.validate;
/**
 * Created by lsb on 17/5/22.
 */


import com.niepeng.springbootts.common.ValidatorUtils;
import org.junit.Test;

/**
 * @author 聂鹏
 * @version 1.0
 * @date 17/5/22
 */

public class ValidateTests {

  @Test
  public void test1() {
      UserTestBean bean = new UserTestBean();
      bean.setUserId(1);
      bean.setUserName("niepeng");
      bean.setUserPwd("psw123");
      bean.setUserAge(19);
      ValidatorUtils.validate(bean);
  }

}