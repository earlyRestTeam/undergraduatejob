package com.lyx.undergraduatejob.controlles.api;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lyx.undergraduatejob.common.ESUtil;
import com.lyx.undergraduatejob.mapper.JobMapper;
import com.lyx.undergraduatejob.pojo.Job;
import com.lyx.undergraduatejob.services.IJobServices;
import com.lyx.undergraduatejob.utils.APIResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.PrefixQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @createTime 2019.12.24.1:15
 */
@Api(value = "ES测试接口", tags = {"ES测试接口"})
@RequestMapping("/es")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RestController
public class TestEsAddDocument {
    public static final String INDEX_NAME = "59city";

    @Autowired
    IJobServices jobServices;
    @Autowired
    ESUtil esUtil;
    @Autowired
    JobMapper jobMapper;


    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient restHighLevelClient;

    @RequestMapping("createdIndex")
    public APIResult createdIndex(){
        try {
            XContentBuilder builder = XContentFactory.jsonBuilder()
                    .startObject()
                    .field("properties")
                    .startObject()
                    //工作名
                    .field("jobName").startObject().field("index", "true").field("type", "text").field("analyzer", "ik_max_word").endObject()
                    //结算方式
                    .field("closeAnAccount").startObject().field("index", "true").field("type", "integer").endObject()
                    //公司 id
                    .field("companyId").startObject().field("index", "true").field("type", "integer").endObject()
                    //薪水
                    .field("salary").startObject().field("index", "true").field("type", "integer").endObject()
                    //工作 类型
                    .field("partFull").startObject().field("index", "true").field("type", "integer").endObject()
                    //status
                    .field("status").startObject().field("index", "true").field("type", "integer").endObject()
                    //aulStatus
                    .field("aulStatus").startObject().field("index", "true").field("type", "integer").endObject()
                    //aulStatus
                    .field("needNum").startObject().field("index", "true").field("type", "integer").endObject()
                    //发布时间
                    .field("createdTime").startObject().field("index","true").field("type","date").field("format", "strict_date_optional_time||epoch_millis").endObject()
                    //工作 类型
                    .field("jobType").startObject().field("index", "true").field("type", "text").field("analyzer", "ik_max_word").endObject()
                    //workArea
                    .field("workAddress").startObject().field("index", "true").field("type", "text").field("analyzer", "ik_max_word").endObject()
                    .endObject()
                    .endObject();
            CreateIndexRequest createIndexRequest = new CreateIndexRequest(INDEX_NAME);
            createIndexRequest.source(builder);
            CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
            boolean acknowledged = createIndexResponse.isAcknowledged();
            if (acknowledged) {
                return APIResult.genSuccessApiResponse("创建成功");
            } else {
                return APIResult.genFailApiResponse500("创建失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return APIResult.genFailApiResponse500("创建失败");
    }



    @ApiOperation(value = "es测试是否存在索引接口", notes = "es测试是否存在索引接口")
    @RequestMapping(value = "/index/exists", method = RequestMethod.POST)
    public APIResult indexExists(@RequestParam String indexName) {
        boolean isExists = esUtil.isIndexExists(indexName);
        return new APIResult(200, "查询成功", isExists);
    }

    @ApiOperation(value = "es测试删除索引接口", notes = "es测试删除索引接口")
    @RequestMapping(value = "/delete/index", method = RequestMethod.POST)
    public APIResult deleteIndex(@RequestParam String indexName) {
        boolean isDelete = esUtil.deleteIndex(indexName);
        if (isDelete) {
            return new APIResult(200, "删除成功", null);
        } else {
            return new APIResult(10002, "删除失败", null);
        }
    }

    @ApiOperation(value = "es测试插入接口", notes = "es测试插入接口")
    @RequestMapping(value = "/insert/data", method = RequestMethod.POST)
    public APIResult findIndustryClassList(@RequestParam String indexName) {
        IndexRequest indexRequest = new IndexRequest(indexName);

        Job job = jobMapper.selectByPrimaryKey(1);
        String userJson = JSONObject.toJSONString(job);

        indexRequest.source(userJson, XContentType.JSON);
        try {
            IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
            if (indexResponse != null) {
                String id = indexResponse.getId();
                String index = indexResponse.getIndex();
                long version = indexResponse.getVersion();

                if (indexResponse.getResult() == DocWriteResponse.Result.CREATED) {
                    System.out.println("新增文档成功!" + index + "-" + id + "-" + version);
                    return new APIResult(200, "插入成功", id);
                } else if (indexResponse.getResult() == DocWriteResponse.Result.UPDATED) {
                    System.out.println("修改文档成功!");
                    return new APIResult(10001, "插入失败", null);
                }
                // 分片处理信息
                ReplicationResponse.ShardInfo shardInfo = indexResponse.getShardInfo();
                if (shardInfo.getTotal() != shardInfo.getSuccessful()) {
                    System.out.println("分片处理信息.....");
                }
                // 如果有分片副本失败，可以获得失败原因信息
                if (shardInfo.getFailed() > 0) {
                    for (ReplicationResponse.ShardInfo.Failure failure : shardInfo.getFailures()) {
                        String reason = failure.reason();
                        System.out.println("副本失败原因：" + reason);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @ApiOperation(value = "es测试普通查询接口", notes = "es测试普通查询接口")
    @RequestMapping(value = "/query/data", method = RequestMethod.GET)
    public APIResult testESFind() {

        SearchRequest searchRequest = new SearchRequest("test_es");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //如果用name直接查询，其实是匹配name分词过后的索引查到的记录(倒排索引)；如果用name.keyword查询则是不分词的查询，正常查询到的记录
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("birthday").from("1991-01-01").to("2010-10-10").format("yyyy-MM-dd");//范围查询
//        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name.keyword", name);//精准查询
        PrefixQueryBuilder prefixQueryBuilder = QueryBuilders.prefixQuery("name.keyword", "张");//前缀查询
//        WildcardQueryBuilder wildcardQueryBuilder = QueryBuilders.wildcardQuery("name.keyword", "*三");//通配符查询
//        FuzzyQueryBuilder fuzzyQueryBuilder = QueryBuilders.fuzzyQuery("name", "三");//模糊查询
        FieldSortBuilder fieldSortBuilder = SortBuilders.fieldSort("age");//按照年龄排序
        fieldSortBuilder.sortMode(SortMode.MIN);//从小到大排序

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(rangeQueryBuilder).should(prefixQueryBuilder);//and or  查询

        sourceBuilder.query(boolQueryBuilder).sort(fieldSortBuilder);//多条件查询
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchRequest.source(sourceBuilder);
        try {
            SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits hits = response.getHits();
            JSONArray jsonArray = new JSONArray();
            for (SearchHit hit : hits) {
                String sourceAsString = hit.getSourceAsString();
                JSONObject jsonObject = JSON.parseObject(sourceAsString);
                jsonArray.add(jsonObject);
            }
            return new APIResult(200, "查询成功", jsonArray);
        } catch (IOException e) {
            e.printStackTrace();
            return new APIResult(10001, "查询失败", null);
        }
    }


    @ApiOperation(value = "es测试更新接口", notes = "es测试更新接口")
    @RequestMapping(value = "/update/data", method = RequestMethod.GET)
    public APIResult testESUpdate(@RequestParam String id, @RequestParam Double money) {
        UpdateRequest updateRequest = new UpdateRequest("test_es", id);
        Map<String, Object> map = new HashMap<>();
        map.put("money", money);
        updateRequest.doc(map);
        try {
            UpdateResponse updateResponse = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
            if (updateResponse.getResult() == DocWriteResponse.Result.UPDATED) {
                return new APIResult(200, "更新成功", null);
            } else {
                return new APIResult(10002, "删除失败", null);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new APIResult(1003, "删除异常", null);
        }
    }

    @ApiOperation(value = "es测试删除接口", notes = "es测试删除接口")
    @RequestMapping(value = "/delete/data", method = RequestMethod.GET)
    public APIResult testESDelete(@RequestParam String id, @RequestParam String indexName) {
        DeleteRequest deleteRequest = new DeleteRequest(indexName);
        deleteRequest.id(id);
        try {
            DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
            if (deleteResponse.getResult() == DocWriteResponse.Result.NOT_FOUND) {
                return new APIResult(1001, "删除失败", null);
            } else {
                return new APIResult(200, "删除成功", null);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new APIResult(1003, "删除异常", null);
        }
    }


}
