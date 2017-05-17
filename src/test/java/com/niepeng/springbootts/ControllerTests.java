package com.niepeng.springbootts;
/**
 * Created by lsb on 17/5/16.
 */


import com.niepeng.springbootts.controllers.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author 聂鹏
 * @version 1.0
 * @date 17/5/16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class ControllerTests {

  private MockMvc mvc;

  @Before
  public void setUp() throws Exception {
    mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
  }

  @Test
  public void getIndex() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/user/index")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
//        .andExpect(content().string(equalTo("Hello World")));
  }

}