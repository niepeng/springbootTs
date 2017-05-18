package com.niepeng.springbootts.config;
/**
 * Created by lsb on 17/5/18.
 */


import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 聂鹏
 * @version 1.0
 * @date 17/5/18
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

  private static final String GROUP_BOOK = "book";
  private static final String GROUP_PAGE = "page";


  @Bean
  public Docket createRestApi1() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName(GROUP_BOOK)
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.niepeng.springbootts.controllers"))
//        .paths(PathSelectors.any())
        .paths(regex("/book/.*"))
        .build();
  }

  @Bean
  public Docket createRestApi2() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName(GROUP_PAGE)
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.niepeng.springbootts.controllers"))
//        .paths(PathSelectors.any())
        .paths(regex("/page/.*"))
        .build();
  }



  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("Spring Boot中使用Swagger2构建RESTful APIs")
        .description("更多内容...")
        .termsOfServiceUrl("#")
        .contact(new Contact("聂鹏", "", "zjutlsb@gmail.com"))
        .version("1.0")
        .build();

  }
}