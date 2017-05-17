package com.niepeng.springbootts.controllers;
/**
 * Created by lsb on 17/5/16.
 */


import com.niepeng.springbootts.bean.Book;
import com.niepeng.springbootts.config.BlobProperites;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 聂鹏
 * @version 1.0
 * @date 17/5/16
 */
@RestController
@RequestMapping("/book")
public class BookController extends BaseController {


  @Autowired
  private BlobProperites blobProperites;


  @RequestMapping("/index")
  public String index() {
    return "Hello World 你好," + blobProperites.getNumber() + ", name=" + blobProperites.getName();
  }

  /**
   * 增删改查询测试
   */
  static Map<Long, Book> books = Collections.synchronizedMap(new HashMap<Long, Book>());

  @RequestMapping(value="/", method= RequestMethod.GET)
  public List<Book> getBookList() {
    List<Book> r = new ArrayList<Book>(books.values());
    return r;
  }

  @RequestMapping(value="/", method=RequestMethod.POST)
  public String postUser(@ModelAttribute Book user) {
    books.put(user.getId(), user);
    return "success";
  }

  @RequestMapping(value="/{id}", method=RequestMethod.GET)
  public Book getBook(@PathVariable Long id) {
    return books.get(id);
  }

  @RequestMapping(value="/{id}", method=RequestMethod.PUT)
  public String putBook(@PathVariable Long id, @ModelAttribute Book user) {
    Book u = books.get(id);
    u.setName(user.getName());
    u.setAge(user.getAge());
    books.put(id, u);
    return "success";
  }

  @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
  public String deleteUser(@PathVariable Long id) {
    books.remove(id);
    return "success";
  }


}