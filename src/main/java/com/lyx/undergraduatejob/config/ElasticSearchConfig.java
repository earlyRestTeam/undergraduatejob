package com.lyx.undergraduatejob.config;

//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.TransportAddress;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;


//@Configuration
public class ElasticSearchConfig {
//    @Value("${elasticsearch.host}")
//    private String esHost;
//
//    @Value("${elasticsearch.port}")
//    private int esPort;
//
//    @Value("${elasticsearch.cluster.name}")
//    private String esName;
//
//    @Value("${elasticsearch.pool}")
//    private String poolSize;
//
////    @Bean(name = "transportClient")
//    public TransportClient transportClient() {
//        TransportClient transportClient = null;
//        try {
//            // 配置信息
//            Settings esSetting = Settings.builder()
////                    .put("cluster.name", clusterName) //集群名字
//                    .put("client.transport.sniff", true)//增加嗅探机制，找到ES集群
//                    .put("thread_pool.search.size", Integer.parseInt(poolSize))//增加线程池个数，暂时设为5
//                    .build();
//            //配置信息Settings自定义
//            transportClient = new PreBuiltTransportClient(esSetting);
//            TransportAddress transportAddress = new TransportAddress(InetAddress.getByName(esHost), Integer.valueOf(esPort));
//            transportClient.addTransportAddresses(transportAddress);
//        } catch (Exception e) {
////            LOGGER.error("elasticsearch TransportClient create error!!", e);
//            e.printStackTrace();
//        }
//        return transportClient;
//    }
}
