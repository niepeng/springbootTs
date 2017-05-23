package com.niepeng.springbootts.annotation;
/**
 * Created by lsb on 17/5/22.
 */


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 聂鹏
 * @version 1.0
 * @date 17/5/22
 */

// 说明该注解将被包含在javadoc中
@Documented
// 这个注解可以是类注解，也可以是方法的注解
/**
 *
 @Target：定义注解的作用目标
 @Target(ElementType.TYPE)   //接口、类、枚举、注解
 @Target(ElementType.FIELD) //字段、枚举的常量
 @Target(ElementType.METHOD) //方法
 @Target(ElementType.PARAMETER) //方法参数
 @Target(ElementType.CONSTRUCTOR)  //构造函数
 @Target(ElementType.LOCAL_VARIABLE)//局部变量
 @Target(ElementType.ANNOTATION_TYPE)//注解
 @Target(ElementType.PACKAGE) ///包
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
// 定义的这个注解是注解会在class字节码文件中存在，在运行时可以通过反射获取到。

/**
 * @Retention: 定义注解的保留策略
 @Retention(RetentionPolicy.SOURCE)   //注解仅存在于源码中，在class字节码文件中不包含
 @Retention(RetentionPolicy.CLASS)    // 默认的保留策略，注解会在class字节码文件中存在，但运行时无法获得，
 @Retention(RetentionPolicy.RUNTIME)  // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
 */
@Retention(RetentionPolicy.RUNTIME)
// 子类可以继承父类中的该注解
@Inherited
public @interface MyAnnotation {
  public String name() default "MyAnnotationNameHelloWorld";
}