package com.niepeng.springbootts;
/**
 * Created by lsb on 17/5/19.
 */


import com.alibaba.fastjson.JSONObject;

/**
 * @author 聂鹏
 * @version 1.0
 * @email lisenbiao@51huadian.cn
 * @date 17/5/19
 */

public class TestMain {

  public static void main(String args[]) {
      String str = "niepeng";
      String value = JSONObject.toJSONString(str);
      System.out.println(str);
      System.out.println(value);
      System.out.println(JSONObject.parse(value));
  }
}