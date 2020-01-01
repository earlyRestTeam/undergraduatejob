package com.lyx.undergraduatejob.reptile;

import com.google.common.collect.Lists;
import com.lyx.undergraduatejob.mapper.*;
import com.lyx.undergraduatejob.pojo.*;
import io.lettuce.core.RedisCommandTimeoutException;
import io.micrometer.core.instrument.util.StringUtils;
import io.micrometer.core.instrument.util.TimeUtils;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @createTime 2019.12.27.20:52
 */
@Component
public class HttpRequest implements InitializingBean {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    IndustriesListMapper industriesListMapper;
    @Autowired
    JobListMapper jobListMapper;
    @Autowired
    WelfareMapper welfareMapper;
    @Autowired
    CompanyMapper companyMapper;
    @Autowired
    JobMapper jobMapper;
    @Autowired
    RelationWelfareMapper relationWelfareMapper;
    @Autowired
    PictureMapper pictureMapper;


    private CountDownLatch countDownLatch = new CountDownLatch(5);

    private final HashMap<String,Integer> welIDMap = new HashMap<>();


    //          queue                  queue    queue       queue
    //行业 --》 职业 --》职位 --》查找对应公司 --》检查福利 --》插入福利 --》插入公司 --》再将职位放入队列 --》插入职位
    private static final String JOB_QUEUE_KEY = "jobKey";
    private static final String COMPANY_QUEUE_KEY = "companyKey";
    private static final String WELFARE_QUEUE_KEY = "welKey";
    private static final String IMAGE_QUEUE_KEY = "imageKey";
//    private static final String JOB_QUEUE_KEY = "jobKey";
//    private static final String JOB_QUEUE_KEY = "jobKey";

    private HttpClient httpClient;

    public HttpEntity getEntityByHttpGetMethod(String url) {
        httpClient = HttpClients.custom().build(); //初始化httpclient
        HttpGet httpget = new HttpGet(url); //使用的请求方法
        //请求头配置
        httpget.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        httpget.setHeader("Accept-Encoding", "gzip, deflate");
        httpget.setHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        httpget.setHeader("Cache-Control", "max-age=0");
        httpget.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36"); //这项内容很重要

        HttpResponse response = null;
        try {
            response = httpClient.execute(httpget);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity httpEntity = response.getEntity();  //获取网页内容流
        return httpEntity;
    }

    public String getHTMLContentByHttpGetMethod(String url, String code) {
        //获取Html内容
        try {
            return EntityUtils.toString(getEntityByHttpGetMethod(url), code);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getHTMLContent( String url ) throws IOException {
        //建立一个新的请求客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //使用HttpGet方式请求网址
        HttpGet httpGet = new HttpGet(url);
        //获取网址的返回结果
        CloseableHttpResponse response = httpClient.execute(httpGet);
        //获取返回结果中的实体
        HttpEntity entity = response.getEntity();
        String content = EntityUtils.toString(entity);
        //关闭HttpEntity流
        EntityUtils.consume(entity);
        return content;
    }
    //请求页面html文件
    public String getRawHtml(String url) throws URISyntaxException, ClientProtocolException, IOException {
//        String url = "https://cs.58.com/job.shtml?utm_source=market&spm=u-2d2yxv86y3v43nkddh1.BDPCPZ_BT&PGTID=0d100000-0019-e433-ab96-00a9fc6fdaf8&ClickID=2";
        HttpClientContext httpClientContext = HttpClientContext.create();
        List<Header> headerList = Lists.newArrayList(); //请求头添加
        headerList.add(new BasicHeader(HttpHeaders.ACCEPT, "text/html,application/xhtml+xml,application/xml;q=0.9," +
                "image/webp,image/apng,*/*;q=0.8"));
        headerList.add(new BasicHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36"));
        headerList.add(new BasicHeader(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate"));
        headerList.add(new BasicHeader(HttpHeaders.CACHE_CONTROL, "max-age=0"));
        headerList.add(new BasicHeader(HttpHeaders.CONNECTION, "keep-alive"));
        headerList.add(new BasicHeader(HttpHeaders.ACCEPT_LANGUAGE, "zh-CN,zh;q=0.8,en;q=0.6,zh-TW;q=0.4,ja;q=0.2," +
                "de;q=0.2"));
//        headerList.add(new BasicHeader(HttpHeaders.REFERER, "https://cs.58.com/job.shtml?utm_source=market&spm=u-2d2yxv86y3v43nkddh1.BDPCPZ_BT&PGTID=0d100000-0019-e433-ab96-00a9fc6fdaf8&ClickID=2"));
        //httpClient初始化
        HttpClient httpClient = HttpClients.custom().setDefaultHeaders(headerList).build();
        //获取响应内容
        HttpUriRequest httpUriRequest = RequestBuilder.get().setUri(url).build();
        httpClient.execute(httpUriRequest, httpClientContext);
        HttpResponse httpResponse = httpClient.execute(httpUriRequest, httpClientContext);
        //获取返回结果中的实体
        HttpEntity entity = httpResponse.getEntity();
        String html = EntityUtils.toString(entity);

        return html;
    }


    public void offer(String key,Object o){
        redisTemplate.opsForList().leftPush(key,o);
    }


    /**
     * 消费图片
     * @return
     */
    public Runnable consumeImage = () ->{
        int rTimes = 0;
            while(true){
                Object o = null;
                //消费
                try{
                    o = redisTemplate.opsForList().rightPop(IMAGE_QUEUE_KEY, 5000, TimeUnit.SECONDS);
                }catch (RedisCommandTimeoutException ex){
                    if(rTimes >= 3){
                        countDownLatch.countDown();
                        break;
                    }
                    rTimes++;
                }
                //职位的 类型 的 url
                Map<String,Object> map = (Map<String,Object>)o;
                String src = (String)map.get("src");
                Integer id = (Integer)map.get("id");
                Integer owner_type = (Integer)map.get("owner_type");
                Integer picture_type = (Integer)map.get("picture_type");
//                RelationWelfare relationWelfare = new RelationWelfare(id,wid,type);
                Picture p = new Picture(id,src,owner_type,picture_type);
                int flag = pictureMapper.insertSelective(p);
                if(flag <= 0){
                    Integer times = (Integer)map.getOrDefault("times", 0);
                    if(times >= 3)
                        continue;
                    times ++;
                    map.put("times",times);
                    offer(IMAGE_QUEUE_KEY,map);
                }
            }
        };

    /**
     * 消费 福利
     * @return
     */
    public Runnable consumeWelfare = () ->{
        int rTimes = 0;
            while(true){
                Object o = null;
                //消费
                try{
                    //消费
                    o = redisTemplate.opsForList().rightPop(WELFARE_QUEUE_KEY, 5000, TimeUnit.SECONDS);
                }catch (RedisCommandTimeoutException ex){
                    if(rTimes >= 3){
                        countDownLatch.countDown();
                        break;
                    }
                    rTimes++;
                }
                //职位的 类型 的 url
                Map<String,Integer> map = (Map<String,Integer>)o;
                Integer id = map.get("id");
                Integer wid = map.get("wId");
                Integer type = map.get("type");
                RelationWelfare relationWelfare = new RelationWelfare(id,wid,type);

                int flag = relationWelfareMapper.insertSelective(relationWelfare);
                if(flag <= 0){
                    Integer times = map.getOrDefault("times", 0);
                    if(times >= 3)
                        continue;
                    times ++;
                    map.put("times",times);
                    offer(WELFARE_QUEUE_KEY,map);
                }
            }
        };

    /**
     * 消费 工作的 线程
     * @return
     * @throws InterruptedException
     * @throws IOException
     * @throws URISyntaxException
     */
    public Runnable consumeJob = ()->{
        int rTimes = 0;
        while(true){
            //消费
            System.out.println("消费 -- job");

            Object o = null;
            //消费
            try{
                //消费
                o = redisTemplate.opsForList().rightPop(JOB_QUEUE_KEY, 5000, TimeUnit.SECONDS);
            }catch (RedisCommandTimeoutException ex){
                if(rTimes >= 3){
                    countDownLatch.countDown();
                    break;
                }
                rTimes++;
            }
            //职位的 类型 的 url
            String href = (String)o;
            System.out.println("href = " + href);
            try {
                toMingQiPage(href);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    };

    //http://www.hr0752.com/search/offer_106100000_101600.html
    public void startJobs(String url,String type) throws IOException, URISyntaxException, InterruptedException {
        String html = getHTMLContent(url);
        Document document = Jsoup.parse(html);
        Elements links = document.select("div[id=ListView]>div.V1Item.clearfix");
        for (Element ele : links){
            Elements select = ele.select("div.JobName.l.clearfix>a");
            String colseUser = ele.select("span.contact_people_text").text();
            String href = select.attr("href");
            if(!href.startsWith("/"))
                continue;
            String jobName = ele.select("div.jobName_style").text();
                    href = "http://www.hr0752.com"+href;
            Thread.currentThread().sleep(500);

            pJob(href,type,colseUser);
        }
    }

    public void pJob(String url,String jobType,String colseUser) throws IOException, InterruptedException {
        Thread.currentThread().sleep(500);
        String html = getHTMLContent(url);
        Document document = Jsoup.parse(html);

        String href = document.select("a[id=ctl00_ContentPlaceHolder1_hlEntName1]").attr("href");
        if(!href.startsWith("/"))
            return;;
        href = "http://www.hr0752.com"+href;
        Company company = pCompany(href, colseUser);
        int flag = companyMapper.insertSelective(company);
        if(flag == 0)
            return;
        String jobName = "";
        Elements select = document.select("div.info-main-left.fl>h2>span");
        if(select.size() > 0){
            jobName = select.get(0).text();
        }
        int needNum = (int)(Math.random()*20)+1;
        int partFull = (int)(Math.random()*2)+1;
        int workEx = (int)(Math.random()*5)+1;
        int education = (int)(Math.random()*5)+1;
        int salary = (int)(Math.random()*8000);
        int maxSalary = salary+(int)(Math.random()*5000);

        String address = "";
        Elements select1 = document.select("div.content-row>ul.content-info.BasicInfo>li");
        if(select1.size() > 0){
            address = select1.first().select("p").first().ownText();
        }
        String desc = document.select("div[id=ctl00_ContentPlaceHolder1_requirement]").text();
        Elements select2 = document.select("div[id=ctl00_ContentPlaceHolder1_plEntContact]>ul>li");
        String addressDetal = "";
        if(select2.size() > 0)
            addressDetal = select2.last().ownText();



        Job job = new Job(company.getId()
                , company.getCompanyName(), company.getLogo(),
                jobType, jobName, needNum, education, workEx, address
                , addressDetal, salary, maxSalary, "", desc,partFull);
        jobMapper.insertSelective(job);
    }
    public Company pCompany(String url,String colseUser) throws IOException, InterruptedException {
        Thread.currentThread().sleep(500);
        String html = getHTMLContent(url);
        Document document = Jsoup.parse(html);
        String src = document.select("img[class=logo]").attr("src");
        if(colseUser.indexOf(" | ") > 0)
            colseUser = colseUser.substring(0,colseUser.indexOf(" | "));
        String logo = "";
        if(!StringUtils.isEmpty(src))
            logo = "http:"+src;
        String name = document.select("a[id=ctl00_ContentPlaceHolder1_V3ucenttop_new_hlEntName]").text();
        CompanyExample example = new CompanyExample();
        example.createCriteria().andCompanyNameEqualTo(name);
        List<Company> companies = companyMapper.selectByExample(example);
        if(companies !=null && companies.size() > 0)
            return companies.get(0);
        String text = document.select("div[id=EntIntro]>div.content").text();
        Elements select = document.select("div.content-row>ul.content-info>li");
        String address = "";
        if(select.size() > 0){
            address = select.last().text();
        }
        return  new Company(logo, name, text,address,colseUser);
    }







    /**
     * 拼接 href
     * @param href
     * @return
     */
    public String genHref(String href){
        return "https:"+href;//"/?PGTID=0d002408-00bc-d521-8324-c46199495158&ClickID=1"
    }
    /**
     * 解析工作页面 -- 通过
     * @param href
     * @param id
     */
    public void parseJobInfo(String href,Integer id,String logo,String companyName) throws IOException, URISyntaxException, InterruptedException {
        Thread.currentThread().sleep(50);
        href = genHref(href);
        String html = getHTMLContent(href);
        Document document = Jsoup.parse(html);

        String JobType = document.select("span.pos_title").text();
        String jobName = document.select("span.pos_name").text();
        String num = document.select("span.item_condition.pad_left_none").text();
        int rNum = 10;
        if(num.length() > 0 ){
            num  = num.substring(1,num.length()-1);
            rNum = Integer.parseInt(num);
        }


//        String education = document.select("span.item_condition").get(1).text();
        int educationNum = (int)Math.random()*5;

//        if(education.equals("学历不限"))
//            educationNum = 0;


        String workYears = document.select("span.item_condition.border_right_None").text();
        int workYearsNum = (int)Math.random()*5;
        Elements address = document.select("span.pos_area_span.pos_address>span.pos_area_item");
        StringBuilder sb = new StringBuilder();
        for (Element e : address){
            sb.append(e.text()).append("-");
        }
        if(sb.length() > 0 )
            sb.deleteCharAt(sb.length()-1);
        String jobAddress = sb.toString();
        String jobAddressDetal = "";

        if(document.select("div.pos-area>span").size()>=2)
            jobAddressDetal = document.select("div.pos-area>span").get(1).text();

        String salaryArea = "";
        if(document.select("span.pos_salary").size() > 0)
            salaryArea = document.select("span.pos_salary").first().ownText();
        int salary = (int)Math.random()*8000;
        int maxSalary = salary+(int)Math.random()*5000;
        if(salaryArea.length() > 0){
            String[] split = salaryArea.split("-");
            salary = Integer.parseInt(split[0]);
            maxSalary = Integer.parseInt(split[1]);
        }

        Elements wels = document.select("div.pos_welfare>span");
        StringBuilder welsb = new StringBuilder();
        List<Integer> welIds = new ArrayList<>();
        for (Element ele : wels){
            String text = ele.text();
            if(welIDMap.containsKey(text)){
                welsb.append(text).append(",");
                welIds.add(welIDMap.get(text));
            }
        }
        if(welsb.length() > 0 )
            welsb.deleteCharAt(sb.length()-1);

        String welfares = welsb.toString();

        String jobDesc = "";
        if(document.select("div.posDes>div.des").size() > 0)
            jobDesc = document.select("div.posDes>div.des").first().text();
        Job job = new Job(id,companyName,logo,JobType,jobName,rNum,educationNum,workYearsNum,jobAddress,jobAddressDetal,salary,maxSalary,welfares,jobDesc,1);
        jobMapper.insertSelective(job);

        Integer jobId = job.getId();
        if(jobId == null)
            return;
        //加入 福利表
        for(Integer welId : welIds){
            Map<String,Integer> map = new HashMap<>();
            map.put("id",jobId);
            map.put("wId",welId);
            map.put("type",1);
            offer(WELFARE_QUEUE_KEY,map);
        }
    }

    public void start(String url) throws IOException {
        String html = getHTMLContent(url);
        List<IndustriesList> industriesLists = new ArrayList<>();
        List<JobList> jobLists = new ArrayList<>();
        //采用Jsoup解析
        Document doc = Jsoup.parse(html);
        //采取html标签中的内容
        //获取ul
        Elements elements = doc.select("div[id=sidebar-right] ul");
        for (Element ele : elements) {
            //获取行业名
            Elements li = ele.select("li");
            for (Element e : li){
                //行业名 详细
                String indu = e.select("strong").text();
                //行业实体
                IndustriesList industriesList = new IndustriesList();
                industriesList.setIndustriesName(indu);
                industriesListMapper.insertSelective(industriesList);
                int res = industriesList.getId();
                //获取职位名 详细
                Elements as = ele.select("a");
                for (Element a : as){
                    //放入 --
                    String aText = a.text();
                    JobList jobList = new JobList();
                    jobList.setJobName(aText);
                    jobList.setIndustriesId(res);
                    int jobListId = jobListMapper.insertSelective(jobList);
                    jobListId = jobList.getId();
                    String href = "https://cs.58.com"+a.attr("href");
                    //将其放入 工作的 queue
                    //href
                    //aText -- job_type
                    // -- jobListId
                    //公司id未确认
                    //status -- 》 0--待插入公司，1--待插入福利,2--插入job
//                    Map<String,String> map = new HashMap<>();

//                    map.put("href",href);
                    //放入队列
                    offer(JOB_QUEUE_KEY,href);
                }
            }
        }

    }
    /**
     * 跳转到 名企 页面 -- 所有的 开始 start
     * @param url
     * @throws IOException
     * @throws URISyntaxException
     */
    public void toMingQiPage(String url) throws IOException, URISyntaxException, InterruptedException {
        String html = getHTMLContent(url);
        //名企 页面
        String companyPage = goCompany(html);
        if(companyPage == null)
            return;

        Document doc = Jsoup.parse(companyPage);
        Elements companies = doc.select("div.companyList div.compInfo");
        for (Element company : companies){
            String href = company.select("dl a").attr("href");
            //打开链接
            parseCompanyInfo(href);
        }
    }

    /**
     * 解析公司
     * @param url
     * @throws IOException
     * @throws URISyntaxException
     */
    public void parseCompanyInfo(String url) throws IOException, URISyntaxException, InterruptedException {


        Thread.currentThread().sleep(50);
        String html = getHTMLContent(url);
        Document document = Jsoup.parse(html);
        Elements headEle = document.select("div.head_info");
        // bug
        String logo = "https:"+headEle.select("span.head_info_img img").attr("src");
        String companyName = document.select("div.intro_middle>h3").text();
        String companyDesc = document.select("div.intro_middle p.dis_non").text();
        //公司的 信息
        Elements tds = document.select("div.intro_down>table>tbody>tr>td");
        // bug
        String companyType = "";
        if(tds.size() >= 4)
            companyType = tds.get(3).select("a").text();
        String closeUser = "";
        if(tds.size() >= 5)
            closeUser = tds.get(4).text();
        String address = "";
        if(tds.size() >= 7)
            address = tds.get(6).select("span").text();

        Elements select = document.select("div.wf_std_item.clearfix>span");
        StringBuilder welfare = new StringBuilder();
        List<String> welList = new ArrayList<>(select.size());
//        &nbsp
        for (Element ele : select){
            String text = ele.ownText().trim();
            if(text.indexOf("&nbsp") >= 0){
                if(text.indexOf("&nbsp") == 0)
                    text = text.substring(5);
            }
            //String welName = text.substring(text.lastIndexOf(">")+1);
            welList.add(text);
            welfare.append(text).append(",");
        }
        if(welfare.length() > 0)
            welfare.deleteCharAt(welfare.length()-1);
        String welfares = welfare.toString();

        Company company = new Company(logo,companyName,companyDesc,companyType,closeUser,address,welfares);
        //持久化 名企
        companyMapper.insertSelective(company);

        Integer id = company.getId();
        if(id ==null)
            return;
        //加入 福利表
        for(String wn : welList){
            if(welIDMap.containsKey(wn)){
                Map<String,Integer> map = new HashMap<>();
                map.put("id",id);
                map.put("wId",welIDMap.get(wn));
                map.put("type",2);
                offer(WELFARE_QUEUE_KEY,map);
            }
        }
        //加入图片
        //bug
        Elements imgs = document.select("li.slide_item>a>img");
        for (Element ele : imgs){
            String src = ele.attr("src");
            Map<String,Object> map = new HashMap<>();
            map.put("src",src);
            map.put("owner_type",2);
            map.put("id",id);
            map.put("picture_type",3);
            offer(IMAGE_QUEUE_KEY,map);
        }
        //添加工作
        String href = document.select("div.head_nav.clearfix>ul>li>a").get(1).attr("href");
        goJob(href,id,logo,companyName);
    }

    /**
     * 跳转到 工作
     * @param href
     */
    public void goJob(String href,Integer id,String logo,String companyName) throws IOException, URISyntaxException, InterruptedException {
        href = "https://qy.58.com"+href;
        Thread.currentThread().sleep(50);
        String rawHtml = getHTMLContent(href);
        Document document = Jsoup.parse(rawHtml);
        Elements links = document.select("div.hireList>table>tbody>tr>td>a");
        //工作 链接
        for (Element ele : links){
            String href1 = ele.attr("href");
            //打开工作链接 并解析
            parseJobInfo(href1,id,logo,companyName);
        }
    }


    /**
     * 跳转到名企业
     * @param html
     * @return
     * @throws IOException
     * @throws URISyntaxException
     */
    public String goCompany(String html) throws IOException, InterruptedException {

        Document document = Jsoup.parse(html);
        Elements select = document.select("div.list_head div.list_top.clearfix span.tabs.clearfix");
        if(select.select("a").size() < 1)
            return null;
        String href = select.select("a").get(1).attr("href");
        Thread.currentThread().sleep(50);
        return getHTMLContent(href);
    }

    /**
     * 爬取 福利 只需要执行一次
     * @throws IOException
     * @throws URISyntaxException
     */
    public void parseWel() throws IOException, InterruptedException {
        Thread.currentThread().sleep(50);
        String html = getHTMLContent("https://cs.58.com/xiaoshouyuan/?utm_source=market&spm=u-2d2yxv86y3v43nkddh1.BDPCPZ_BT");
        //采用Jsoup解析
        Document doc = Jsoup.parse(html);
        Elements elements = doc.select("div[id=filterWel]>ul>li");
        for (Element e : elements){
            String text = e.select("a").text();
            Welfare welfare = new Welfare();
            welfare.setWelfareName(text);
            welfare.setCreateTime(new Date());
            welfareMapper.insert(welfare);
        }
    }
    /**
     * 加载福利
     */
    public void loadWelfare(){
        List<Welfare> welfare = welfareMapper.selectByExample(new WelfareExample());
        welfare.forEach(w -> {
            welIDMap.put(w.getWelfareName(),w.getId());
        });
    }

    /**
     * 获取 职业、行业、
     * @param html
     */
    public void getJobListData(String html) {

    }


    @Override
    public void afterPropertiesSet() throws Exception {
        loadWelfare();
//        start("https://cs.58.com/job.shtml?PGTID=0d100000-0019-e9ca-f003-d033f59e78fc&ClickID=1");

//        Runnable r = consumeJob();
//        new Thread(r).start();
//        new Thread(consumeJob()).start();
//        new Thread(consumeJob()).start();

//        new Thread(consumeJob).start();
//        new Thread(consumeJob).start();
//        new Thread(consumeJob).start();
//
//        new Thread(consumeImage).start();
//        new Thread(consumeWelfare).start();

//          此种方式 线程 不执行
//        new Thread(this::consumeWelfare).start();
//        new Thread(this::consumeImage).start();
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        executorService.exec(this::consumeJob);
//        executorService.exec(this::consumeJob);
//        executorService.exec(this::consumeJob);
//
//        executorService.exec(this::consumeWelfare);
//        executorService.exec(this::consumeImage);

    }

    public void latchWait() throws InterruptedException {
        this.countDownLatch.await();
    }

    /**
     * 从名企业 开始
     * @param url
     * @throws IOException
     * @throws InterruptedException
     */
    public void start51(String url) throws IOException, InterruptedException, URISyntaxException {
        String html = getRawHtml(url);
        html = new String(html.getBytes("gbk"),"utf8");
        Document document = Jsoup.parse(html);
        Elements links = document.select("div.dj_mq>div[id=indList]>a");
        for (Element element : links){
            String href = element.attr("href");
            href = "https:"+href;
            String companyHtml = getRawHtml(href);
            companyHtml = new String(companyHtml.getBytes("gbk"),"utf8");
            Document companyDocument = Jsoup.parse(companyHtml);
            String companyDesc = companyDocument
                    .select("div.m_describe" +
                            ">div.m_title>div.deb>div.dc.nk").text();
            Elements companyInfo = companyDocument.select("div.m_logo>div.m_msg" +
                    ">div.msnbox1>div.msn");
            String logo = companyInfo.select("img").attr("src");
            String companyName = companyInfo.select("h1>span.at").text();
            String type = companyInfo.select("span.typ").text();

            Elements jobs = companyDocument.select("div.m_tablist" +
                    ">div.e>strong.at>a");
            Company company = new Company(logo, companyName, type, companyDesc);
            int flag = companyMapper.insertSelective(company);
            if(flag < 1)
                return;
            for (Element job : jobs) {
                String jobHref = job.attr("href");
                parseJob(jobHref,company);
            }
        }
    }


    public void parseJob(String url,Company company) throws InterruptedException, IOException, URISyntaxException {
        Thread.currentThread().sleep(5000);
        String html = getRawHtml(url);
        html = new String(html.getBytes("gbk"),"utf8");
        Document document = Jsoup.parse(html);
        Elements head = document.select("div.tHeader.tHjob>div.in>div.cn");
        String jobName = head.select("h1").first().ownText();

        int salary = (int)Math.random()*8000;
        int maxSalary = salary+(int)Math.random()*5000;
        String text = head.select("strong").text();
        if(text.length() > 0 && text.indexOf("-") > 0){
            int index = text.indexOf("-");
            String s1 = text.substring(0,index);
            String s2 = text.substring(index,index+1);
            salary = ((Double) (Double.parseDouble(s1) * 1000)).intValue();
            maxSalary = Integer.parseInt(s2)*1000;
        }
        String title = head.select("p.msg.ltype").attr("title");
        String[] strs = title.split("|");
        String adress = strs[0];
        int workEx = Integer.parseInt(strs[1].substring(0,1));
        int eduction = (int)(Math.random()*5);

        int needNum = (int)(Math.random()*20)+1;
        String needNumStr = strs[3].substring(1,strs[3].length()-1);
        if(!"若干".equals(needNumStr)){
            needNum = Integer.parseInt(needNumStr);
        }
        String jobDesc = document.select("div.bmsg.job_msg inbox").text();
        String jobType = document.select("div.mt10>p.fp>a").first().text();
        Elements wels = document.select("div.jtag>div.t1>span");
        StringBuilder sb = new StringBuilder();
        List<Integer> ids = new ArrayList<>(wels.size());
        for (Element ele : wels){
            String welName = ele.text();
            if(!welIDMap.containsKey(welName)){
                Welfare welfare = new Welfare();
                welfare.setWelfareName(welName);
                welfareMapper.insertSelective(welfare);
                welIDMap.put(welName,welfare.getId());
            }
            ids.add(welIDMap.get(welName));
            sb.append(welName).append(",");
        }
        if(sb.length() > 0 )
            sb.deleteCharAt(sb.length()-1);

        Job job = new Job(company.getId()
                , company.getCompanyName(), company.getLogo(),
                jobType, jobName, needNum, eduction, workEx, adress
                , "", salary, maxSalary, sb.toString(), jobDesc,1);

        int flag = jobMapper.insertSelective(job);

        if(flag > 0){
            ids.stream().forEach(id->{
                RelationWelfare relationWelfare = new RelationWelfare();
                relationWelfare.setWelfareId(id);
                relationWelfare.setOwnerId(job.getId());
                relationWelfare.setOwnerType(1);
                relationWelfareMapper.insertSelective(relationWelfare);
            });
        }

    }


}



