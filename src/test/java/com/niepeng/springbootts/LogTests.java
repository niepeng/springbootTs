package com.niepeng.springbootts;
/**
 * Created by lsb on 17/5/19.
 */


import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author 聂鹏
 * @version 1.0
 * @date 17/5/19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= App.class)
public class LogTests {

  protected final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Test
  public void contextLoads() {
    logger.trace("I am trace log.");
    logger.debug("I am debug log.");
    logger.warn("I am warn log.");
    logger.error("I am error log.");
  }

}