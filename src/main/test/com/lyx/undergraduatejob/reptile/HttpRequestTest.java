package com.lyx.undergraduatejob.reptile;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @createTime 2019.12.28.10:48
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class HttpRequestTest {

    @Autowired
    HttpRequest request;
    @Test
    void getData() throws IOException, URISyntaxException {
        //测试
//        String rawHtml = request.getRawHtml("https://cs.58.com/loucjl/?utm_source=market&spm=" +
//                "u-2d2yxv86y3v43nkddh1.BDPCPZ_BT&PGTID=0d000000-0000-002c-85f1-e9914b29f51e&ClickID=1");
//        request.parseJobMainPage(rawHtml);



    //


//        request.getJobListData(request.getRawHtml("https://cs.58.com/job.shtml?utm_sourc" +
//                "e=market&spm=u-2d2yxv86y3v43nkddh1.BDPCPZ_BT&PGTID=0d100000-0019-e433-ab96-00a9fc6fdaf8&ClickID=2"));
//        request.parseWel();
    }
    @Test
    public void testParseJobMainPage() throws IOException, URISyntaxException {
//        request.parseJobMainPage("https://cs.58.com/xiaoshouyuan/?utm" +
//                "_source=market&spm=u-2d2yxv86y3v43nkddh1.BDPCPZ_BT&PGTID=0d000000-0000-08c5-321c-e1664f5a849c&ClickID=2");
    }
    @Test
    public void testParseCompanyIfo() throws IOException, URISyntaxException, InterruptedException {
        request.parseCompanyInfo("https://qy.58.com/mq/64557857516299" +
                "/?PGTID=0d002408-00bc-d521-8324-c46199495158&ClickID=1");
    }
    @Test
    public void testGoJob() throws IOException, URISyntaxException {
//        request.goJob("//qy.58.com/mq/64557857516299/2/?PGTID=0d002408-00bc-d370-6e67-3b307cfb7092&ClickID=1",1);
    }
    /**
     * 测试 job页面 -- pass
     * @throws IOException
     * @throws URISyntaxException
     */
    @Test
    public void testParseJobInfo() throws IOException, URISyntaxException {
//                request.parseJobInfo("https://cs.58.com/" +
//                "yewu/40643628946718x.shtml?PGTID=0d002408-00bc-db24-42a1-f9cbf1c6d0d3&ClickID=1",1);
    }

    /**
     * 测试 跳转到 名企 -- pass
     * @throws IOException
     * @throws URISyntaxException
     */
    @Test
    public void testGoCompany() throws IOException, URISyntaxException, InterruptedException {
        request
                .toMingQiPage("https://cs.58.com/cantfwy/" +
                        "?utm_source=market&spm=u-2d2yxv86y3v43nkddh1.BDPCPZ" +
                        "_BT&PGTID=0d202408-0019-e599-bdce-55b4c0ac602c&ClickID=1");
    }
    @Autowired
    RedisTemplate redisTemplate;
    //运行 进行 爬取
    @Test
    public void test() throws IOException, InterruptedException, URISyntaxException {
//        request.start("https://cs.58.com/job.shtml?PGTID=0d202408-0019-ee54-29b2-0ff9b8a1cfc3&ClickID=1");

        request.toMingQiPage("https://sz.58.com/cantfwy/?PGTID=0d202408-0000-4311-4aeb-0908300a68d9&ClickID=1");

//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        executorService.execute(request.consumeJob);
//        executorService.execute(request.consumeJob);
//        executorService.execute(request.consumeJob);
//
//        executorService.execute(request.consumeWelfare);
//        executorService.execute(request.consumeImage);
//
//        request.latchWait();
    }
    @Test
    public void test2() throws IOException {
        request.start("https://cs.58.com/job.shtml?utm_source=market&spm=u-2d2yxv86y3v43nkddh1.BDPCPZ_BT&PGTID=0d100000-0019-ed7f-22da-0eb0a9e78896&ClickID=4");
        //redisTemplate.opsForList().leftPush("jobKey","v1");
//        Object key = redisTemplate.opsForList().rightPop("jobKey", 5000, TimeUnit.SECONDS);
//        System.out.println("key = " + key);
    }
}