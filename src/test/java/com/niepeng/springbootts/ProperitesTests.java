package com.niepeng.springbootts;
/**
 * Created by lsb on 17/5/17.
 */



import com.niepeng.springbootts.config.BlobProperites;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author 聂鹏
 * @version 1.0
 * @date 17/5/17
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= App.class)
public class ProperitesTests {

    private static final Log log = LogFactory.getLog(ProperitesTests.class);

    @Autowired
    private BlobProperites blobProperites;


	  @Test
    public void test1() {
        Assert.assertEquals("Springboot-中文，总算搞定！", blobProperites.getName());
        log.info("随机数测试输出：");
        log.info("随机字符串 : " + blobProperites.getValue());
        log.info("随机int : " + blobProperites.getNumber());
        log.info("随机long : " + blobProperites.getBignumber());
        log.info("随机10以下 : " + blobProperites.getTest1());
        log.info("随机10-20 : " + blobProperites.getTest2());
    }

}