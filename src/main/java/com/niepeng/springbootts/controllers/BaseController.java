package com.niepeng.springbootts.controllers;
/**
 * Created by lsb on 17/5/16.
 */


import com.niepeng.springbootts.config.BlobProperites;
import com.niepeng.springbootts.dao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 聂鹏
 * @version 1.0
 * @date 17/5/16
 */
@Component
public class BaseController {

  @Autowired
  protected BlobProperites blobProperites;

  @Autowired
  protected UserMapper userMapper;

}