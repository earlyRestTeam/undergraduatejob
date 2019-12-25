package com.lyx.undergraduatejob.services.es.impl;


import com.lyx.undergraduatejob.pojo.Job;
import com.lyx.undergraduatejob.services.es.IJobSearchService;
import org.springframework.stereotype.Service;

/**
 * @createTime 2019.12.23.19:56
 */
@Service
public class JobSearchService implements IJobSearchService {
    @Override
    public boolean updateJob(Job job) {
        return false;
    }

    @Override
    public boolean addJob(Job job) {
        return false;
    }

    @Override
    public boolean deleteJob(int id) {
        return false;
    }
//    @Autowired
//    JobDAO jobDAO;
//
//    @Override
//    public SearchQuery selectJobByJobSearchEntityWithOutCompany(Integer start, Integer pageSize, JobSearchEntity jobSearchEntity) {
//
//        Map<String, Object> map = Maps.newHashMap();
//        Pageable pageable = PageRequest.of(start, pageSize);
//        //检索条件
//        BoolQueryBuilder bqb = QueryBuilders.boolQuery();
//
//        if (jobSearchEntity.getStatus() != null && jobSearchEntity.getStatus() > 0)
//            bqb.must(QueryBuilders.matchPhraseQuery("status", jobSearchEntity.getStatus()));
//
//        if (jobSearchEntity.getAulStatus() != null)
//            bqb.must(QueryBuilders.matchPhraseQuery("aulStatus", jobSearchEntity.getAulStatus()));
//
//        String key;
//        if (!StringUtils.isEmpty((key = jobSearchEntity.getKeyWord())))
//            bqb.must(QueryBuilders.multiMatchQuery(key, "jobTitle", "companyName", "jobDesc"));
//        Integer companyId;
//        if ((companyId = jobSearchEntity.getCompanyId()) != null && companyId > 0)
//            bqb.must(QueryBuilders.matchPhraseQuery("companyId", companyId));
//        String workArea;
//        if (!StringUtils.isEmpty((workArea = jobSearchEntity.getWorkArea())))
//            bqb.must(QueryBuilders.matchPhraseQuery("workArea", workArea));
//        if (!StringUtils.isEmpty(jobSearchEntity.getJobType()))
//            bqb.must(QueryBuilders.matchPhraseQuery("jobType", jobSearchEntity.getJobType()));
//        //结算类型
//        Integer closeAnAccount;
//        if ((closeAnAccount = jobSearchEntity.getCloseAnAccount()) != null && jobSearchEntity.getCloseAnAccount() > 0)
//            bqb.must(QueryBuilders.matchPhraseQuery("closeAnAccount", closeAnAccount));
//        //兼职 全职
//        if (jobSearchEntity.getPartFull() != null && jobSearchEntity.getPartFull() > 0)
//            bqb.must(QueryBuilders.matchPhraseQuery("partFull", jobSearchEntity.getPartFull()));
//
//        RentValueBlock rentValueBlock = RentValueBlock.getRentValueBlock(jobSearchEntity.getSalaryArea());
//
//        RangeQueryBuilder salaryRangQuery = null;
//        if (rentValueBlock.getMin() > 0) {
//            salaryRangQuery = QueryBuilders.rangeQuery("salary").gt(rentValueBlock.getMin());
//        }
//
//        if (rentValueBlock.getMax() > 0) {
//            if (salaryRangQuery != null)
//                salaryRangQuery = QueryBuilders.rangeQuery("salary").lt(rentValueBlock.getMax());
//            else
//                salaryRangQuery.lt(rentValueBlock.getMax());
//        }
//        if (salaryRangQuery != null)
//            bqb.must(salaryRangQuery);
//
////
////        example.setOrderByClause(jobSearchEntity.getOrderExample()+jobSearchEntity.getOrder()
////                + StaticPool.JOB_VIP_SORT);
//
//
//        //排序条件
//        FieldSortBuilder fsb = null;
//        fsb = SortBuilders.fieldSort(jobSearchEntity.getOrderExample()).order(SortOrder.valueOf(jobSearchEntity.getOrder()));
//        fsb = SortBuilders.fieldSort("jobVip").order(SortOrder.DESC);
//
//        //聚合条件
////        TermsAggregationBuilder builder1 = AggregationBuilders.terms("taxonomy").field("taxonomy.keyword");
////        TermsAggregationBuilder builder2 = AggregationBuilders.terms("year").field("year.keyword");
////        TermsAggregationBuilder builder = builder1.subAggregation(builder2);
//        //构建查询
//        SearchQuery query = new NativeSearchQueryBuilder()
//                .withQuery(bqb)
//                .withSort(fsb)
//                .withPageable(pageable)
//                .build();
//        System.out.println("query = " + query);
//        return query;
//    }
//
//    @Override
//    public boolean updateJob(Job job) {
//        jobDAO.save(job);
//        return true;
//    }
//
//    @Override
//    public boolean addJob(Job job) {
//        jobDAO.save(job);
//        return true;
//    }
//
//    @Override
//    public boolean deleteJob(int id) {
//        jobDAO.deleteById(id);
//        return true;
//    }
}
