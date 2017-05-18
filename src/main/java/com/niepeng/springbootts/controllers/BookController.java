package com.niepeng.springbootts.controllers;
/**
 * Created by lsb on 17/5/16.
 */


import com.niepeng.springbootts.bean.Book;
import com.niepeng.springbootts.config.BlobProperites;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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

  @ApiOperation(value="测试接口", notes="返回一段文本信息")
  @RequestMapping(value="/index", method= RequestMethod.GET)
  public String index() {
    return "Hello World 你好," + blobProperites.getNumber() + ", name=" + blobProperites.getName();
  }

  /**
   * 增删改查询测试
   */
  static Map<Long, Book> books = Collections.synchronizedMap(new HashMap<Long, Book>());
 static {
   Book book = new Book();
   book.setId(1L);
   book.setName("聂鹏1");
   book.setAge(18);
   books.put(1L, book);
 }


  @ApiOperation(value="获取book列表数据", notes="暂时没有任何参数,来获取bookList的数据,存储在内存中")
  @RequestMapping(value="/", method= RequestMethod.GET)
  public List<Book> getBookList() {
    List<Book> r = new ArrayList<Book>(books.values());
    return r;
  }

  @ApiOperation(value="添加book信息", notes="添加book信息")
  @ApiImplicitParams({
      @ApiImplicitParam(paramType = "form", name = "id", value = "id", required = true, dataType = "Long"),
      @ApiImplicitParam(paramType = "form", name = "name", value = "名称", required = true, dataType = "String"),
      @ApiImplicitParam(paramType = "form", name = "age", value = "年龄", required = true, dataType = "Integer")
  })
  @RequestMapping(value="/", method=RequestMethod.POST)
  public String postBook(@ModelAttribute Book book) {
    books.put(book.getId(), book);
    return "success";
  }

  @ApiOperation(value="获取book详细信息", notes="简单获取基本信息")
  @ApiImplicitParam(paramType="path", name = "id", value = "bookId", required = true, dataType = "Long")
  @RequestMapping(value="/{id}", method=RequestMethod.GET)
  public Book getBook(@PathVariable Long id) {
    return books.get(id);
  }

  @ApiOperation(value="修改book信息", notes="根据id修改book信息")
  @ApiImplicitParams({
      @ApiImplicitParam(paramType = "path", name = "id", value = "id", required = true, dataType = "Long"),
      @ApiImplicitParam(paramType = "form", name = "name", value = "名称", required = true, dataType = "String"),
      @ApiImplicitParam(paramType = "form", name = "age", value = "年龄", required = true, dataType = "Integer")
  })
  @RequestMapping(value="/{id}", method=RequestMethod.PUT)
  public String putBook(@PathVariable Long id, @ModelAttribute Book user) {
    Book u = books.get(id);
    u.setName(user.getName());
    u.setAge(user.getAge());
    books.put(id, u);
    return "success";
  }

  @ApiOperation(value="删除book信息", notes="根据id删除book")
  @ApiImplicitParam(paramType="path", name = "id", value = "bookId", required = true, dataType = "Long")
  @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
  public String deleteUser(@PathVariable Long id) {
    books.remove(id);
    return "success";
  }


}