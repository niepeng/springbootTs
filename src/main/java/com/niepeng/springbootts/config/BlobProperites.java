package com.niepeng.springbootts.config;
/**
 * Created by lsb on 17/5/16.
 */


import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author 聂鹏
 * @version 1.0
 * @date 17/5/16
 */
@Component
@Data
public class BlobProperites {

  @Value("${com.springbootts.blog.name}")
  private String name;

  @Value("${com.springbootts.blog.title}")
  private String title;

  @Value("${com.springbootts.blog.desc}")
  private String desc;

  @Value("${com.springbootts.blog.value}")
  private String value;

  @Value("${com.springbootts.blog.number}")
  private Integer number;

  @Value("${com.springbootts.blog.bignumber}")
  private Long bignumber;

  @Value("${com.springbootts.blog.test1}")
  private Integer test1;

  @Value("${com.springbootts.blog.test2}")
  private Integer test2;



}