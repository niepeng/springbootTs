package com.niepeng.springbootts;
/**
 * Created by lsb on 17/5/19.
 */


import com.niepeng.springbootts.common.AsyncTask;
import java.util.concurrent.Future;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * @author 聂鹏
 * @version 1.0
 * @date 17/5/19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= App.class)
public class AsyncTaskTests {

  @Autowired
  private AsyncTask task;

  @Test
  public void test() throws Exception {

    long start = System.currentTimeMillis();

    Future<String> task1 = task.doTaskOne();
    Future<String> task2 = task.doTaskTwo();
    Future<String> task3 = task.doTaskThree();

    while (true) {
      if (task1.isDone() && task2.isDone() && task3.isDone()) {
        // 三个任务都调用完成，退出循环等待
        break;
      }
      Thread.sleep(1000);
    }

    long end = System.currentTimeMillis();

    System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");

  }

}