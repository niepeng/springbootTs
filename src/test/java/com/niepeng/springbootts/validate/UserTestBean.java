package com.niepeng.springbootts.validate;
/**
 * Created by lsb on 17/5/22.
 */


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * @author 聂鹏
 * @version 1.0
 * @date 17/5/22
 */
@Data
public class UserTestBean {

  @Min(1)
  private int userId;

  @NotNull(message = "用户名不能为空！")
  @Size(min=1,message = "用户名不能为空！")
  private String userName;

  @NotNull
  @Size(min = 6, max = 20, message = "密码长度必须在6-20之间！")
  private String userPwd;

  @Min(value = 18, message = "年龄最小值必须是18")
  @Max(value = 60, message = "年龄最大值必须是60")
  private int userAge;


}