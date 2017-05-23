package com.niepeng.springbootts.annotation;
/**
 * Created by lsb on 17/5/22.
 */


import org.junit.Test;

/**
 * @author 聂鹏
 * @version 1.0
 * @date 17/5/22
 */

public class AnnotationTests {

  @MyAnnotation(name = "niepengUserName")
  public void say1(final String name) {
    System.out.println("  ~~~~~~~~~~~  :  welcome : " + name);
  }

  @MyAnnotation(name = "niepengUserName2222")
  public void say2(final String name) {
    System.out.println("  ~~~~~~~~~~~  :  welcome : " + name);
  }

  @MyAnnotation
  public void say3(final String name) {
    System.out.println("  ~~~~~~~~~~~  :  welcome : " + name);
  }


  @Test
  public void test1() throws Exception {
    final ParseMyAnnotation pm = new ParseMyAnnotation();
    pm.parseMethod(AnnotationTests.class);
  }
}