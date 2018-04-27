package com.leaf.elasticsearch;

import com.leaf.elasticsearch.model.Person;

import com.sun.jmx.remote.internal.ClientNotifForwarder;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.get.*;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.*;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElasticTest {
    private SearchSourceBuilder searchSourceBuilder;

    public static void main(String args[]){
        //集群设置
        Settings settings = Settings.builder()
                //通过 clustername 发现集群
                //.put("cluster.name", "elasticsearch")
                //自动发现集群
                .put("client.transport.sniff",true)
                .build();

        TransportClient client =new PreBuiltTransportClient(settings);
        client.addTransportAddress(new TransportAddress(new InetSocketAddress("no250",9300)));
      //  client.addTransportAddress(new TransportAddress(new InetSocketAddress("no251",9300)));
      //  client.addTransportAddress(new TransportAddress(new InetSocketAddress("no252",9300)));
        pringText("listedNOdes");
        for (DiscoveryNode item: client.listedNodes()){
            pringValue(item.getHostAddress(),item.getHostName(),item.getName());
        }
        pringText("connectedNodes");
        for (DiscoveryNode item: client.connectedNodes()){
            pringValue(item.getHostAddress(),item.getHostName(),item.getName());
        }
        pringText("filteredNodes");
        for (DiscoveryNode item: client.filteredNodes()){
            pringValue(item.getHostAddress(),item.getHostName(),item.getName());
        }
        pringText("setting keyset");
        for(String key: client.settings().keySet()){
            pringValue(key,client.settings().get(key));
        }
        pringText("setting names");
        for(String key: client.settings().names()){
            pringValue(key,client.settings().get(key));
        }
        pringText("setting getAsGroups");
        for(String key: client.settings().getAsGroups().keySet()){
            pringValue(key,client.settings().getAsGroups().get(key));
        }

       try {
           getCount(client);
           //bulk(client);
           //searchTest(client);
           //multiGet(client);
           //getPerson(client);
       // test();
        //create(client);
        //prepareDelete(client);
        //prepareCreateTest(client);
        //delete(client);
        //prepareUpdate(client);
        //update(client);
       }catch (Exception a){
          a.printStackTrace();
       }
   }

   public static void getCount(TransportClient client){


   }

   public static void bulk(TransportClient client) throws  Exception{
       // index ---------------------------------------
       IndexRequest indexRequest=new IndexRequest(Person.INDEX,Person.TYPE);
       Map<String,Object> param=new HashMap<>();
       param.put(Person.FIRST_NAME,"client");
       param.put(Person.LAST_NAME,"bulk-update");
       param.put(Person.AGE,30);
       param.put(Person.COUNT,100);
       param.put(Person.ABOUNT,"my first time to go");
       param.put(Person.INTERESTS,new String[]{"sport","music","online"});
       indexRequest.source(param);

       IndexRequestBuilder indexRequestBuilder=client.prepareIndex("customer","person");
       Map<String,Object> prepareParam=new HashMap<>();
       prepareParam.put(Person.FIRST_NAME,"client");
       prepareParam.put(Person.LAST_NAME,"prepareBulk-update");
       prepareParam.put(Person.AGE,30);
       prepareParam.put(Person.COUNT,100);
       prepareParam.put(Person.ABOUNT,"my first time to go");
       prepareParam.put(Person.INTERESTS,new String[]{"sport","music","online"});
       indexRequestBuilder.setSource(prepareParam);

       //update----------------------------------------------------
       UpdateRequest updateRequest=new UpdateRequest(Person.INDEX,Person.TYPE,"KMQOm2EB95_bPyvAmVTE");
       updateRequest.doc(param);

       UpdateRequestBuilder updateRequestBuilder=client.prepareUpdate(Person.INDEX,Person.TYPE,"KcQOm2EB95_bPyvAr1TB");
       updateRequestBuilder.setDoc(prepareParam);


       //delete----------------------------------------------------
       DeleteRequest deleteRequest=new DeleteRequest(Person.INDEX,Person.TYPE,"KMQOm2EB95_bPyvAmVTE");
       DeleteRequestBuilder deleteRequestBuilder=client.prepareDelete(Person.INDEX,Person.TYPE,"KcQOm2EB95_bPyvAr1TB");
       //-------------------------------------------------------------------------------------------------------

       pringText("client.bulk()");
       BulkRequest bulkRequest=new BulkRequest();
       //bulkRequest.add(indexRequest);
       //bulkRequest.add(updateRequest);
       bulkRequest.add(deleteRequest);
       BulkResponse bulkResponse= client.bulk(bulkRequest).get();
       for(BulkItemResponse item: bulkResponse.getItems()){
           pringValue(item.getIndex(),item.getType(),item.getId(),item.getFailureMessage(),item.status().getStatus());
       }

       ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
       pringText("client.prepareBulk()");
       BulkRequestBuilder bulkRequestBuilder=client.prepareBulk();
       //bulkRequestBuilder.add(indexRequestBuilder);
      // bulkRequestBuilder.add(indexRequest);
      // bulkRequestBuilder.add(updateRequestBuilder);
       bulkRequestBuilder.add(deleteRequestBuilder);
       bulkResponse=bulkRequestBuilder.get();
       for(BulkItemResponse item: bulkResponse.getItems()){
           pringValue(item.getIndex(),item.getType(),item.getId(),item.getFailureMessage(),item.status().getStatus());
       }

   }

    public static void pringValue(Object ...text){
        StringBuilder sb=new StringBuilder();
        for (Object string:text){
            sb.append(string).append(",");
        }
        System.out.println(sb.toString());
    }

   public static void multiGet(TransportClient client) throws  Exception{
       MultiGetRequest multiGetRequest=new MultiGetRequest();
       multiGetRequest.add(Person.INDEX,Person.TYPE,"6");
       MultiGetResponse multiGetResponse=client.multiGet(multiGetRequest).get();
      for (MultiGetItemResponse item:multiGetResponse.getResponses()){
          System.out.println(item.getResponse().getSource());
      }
       System.out.println(multiGetResponse);


       MultiGetRequestBuilder multiGetRequestBuilder=client.prepareMultiGet();

       multiGetResponse =multiGetRequestBuilder.add(Person.INDEX,Person.TYPE,new String[]{"6","7","8"}).get();
       for (MultiGetItemResponse item:multiGetResponse.getResponses()){
           System.out.println(item.getResponse().getIndex()+","+item.getResponse().getType());
           for(String key:item.getResponse().getFields().keySet()){
               System.out.println(key+"=="+item.getResponse().getFields().get(key).toString());
           }
           System.out.println(item.getResponse().getSource());
       }
       System.out.println(multiGetResponse);

   }

   public static void searchs(TransportClient client){
       SearchRequest request=new SearchRequest();
       ActionFuture<SearchResponse> response=client.search(request);

   }

   public static  void getPerson(TransportClient client) throws  Exception{
      GetResponse response= client.prepareGet(Person.INDEX,Person.TYPE,"MdgximEBGpOIYPg2OkNG").get();
      Map<String,Object> result= response.getSource();
      System.out.println(response.getSource());
      ///
       GetRequest request=new GetRequest(Person.INDEX,Person.TYPE,"6");
       GetResponse getResponse=client.get(request).get();
       System.out.println(getResponse.getSource());

   }

   public static void searchTest(TransportClient client) throws  Exception{
       ///-------------------------------------------------------/////////////
       ///---------------------------client.prepareSearch()
       pringText("client.prepareSearch()");
       SearchRequestBuilder searchRequestBuilder=client.prepareSearch(Person.INDEX);
       searchRequestBuilder.setTypes(Person.TYPE)
               .setQuery(QueryBuilders.matchQuery(Person.FIRST_NAME, "John"))                 // Query
               .setPostFilter(QueryBuilders.rangeQuery("age").from(10).to(100))     // Filter
               .setFrom(0).setSize(60).setExplain(true)
               .get();
       SearchResponse response =searchRequestBuilder.get();

       for (SearchHit h:response.getHits().getHits()) {
           System.out.println(h.getSourceAsMap());
       }
       System.out.println(response.getHits().getHits());
       ///---------------------------client.search()
       pringText("client.search()");
       //-----------------------------------------------------------------
       SearchRequest searchRequest=new SearchRequest(Person.INDEX);
       searchRequest.types(Person.TYPE);
       SearchSourceBuilder builder=new SearchSourceBuilder();
       builder.query(QueryBuilders.queryStringQuery("first_name:John AND age:22"));
       builder.from(0).size(100);

       searchRequest.source(builder);
       SearchResponse searchResponse=client.search(searchRequest).get();
       System.out.println(searchResponse.getHits().getHits());
       for (SearchHit h:searchResponse.getHits().getHits()) {
           System.out.println(h.getSourceAsMap());
       }
       ///-------------------------------------------------------/////////////
       ///---------------------------------------------------------/////////////
       ///---------------------------client.multiSearch()
       pringText("client.multiSearch()");
       MultiSearchRequest multiSearchRequest=new MultiSearchRequest();
       multiSearchRequest.add(searchRequest);
       multiSearchRequest.add(searchRequestBuilder);
       MultiSearchResponse multiSearchResponse=client.multiSearch(multiSearchRequest).get();
       for(MultiSearchResponse.Item items: multiSearchResponse.getResponses()){
           for (SearchHit h:items.getResponse().getHits().getHits()) {
               System.out.println(h.getSourceAsMap());
           }
       };

       ///--------------------------client.prepareMultiSearch()
       pringText("client.prepareMultiSearch()");
       MultiSearchRequestBuilder multiSearchRequestBuilder=client.prepareMultiSearch();
       multiSearchRequestBuilder.add(searchRequest);
       multiSearchRequestBuilder.add(searchRequestBuilder);
       multiSearchResponse=multiSearchRequestBuilder.get();
       for(MultiSearchResponse.Item items: multiSearchResponse.getResponses()){
           for (SearchHit h:items.getResponse().getHits().getHits()) {
               System.out.println(h.getSourceAsMap());
           }
       };

       ///---------------------------------------------------------/////////////

   }

   public static void pringText(String text){
       System.out.println("-----------------------"+text+"---------------------");
   }

   public static  void transportSearch(){
       Settings settings = Settings.builder()
               .put("cluster.name", "elasticsearch").build();
       TransportClient client =new PreBuiltTransportClient(settings);
       client.addTransportAddress(new TransportAddress(new InetSocketAddress("no250",9300)));
       SearchRequestBuilder search=client.prepareSearch("customer");
       SearchResponse response = search
               .setTypes("person")
               .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
               .setQuery(QueryBuilders.termQuery("multi", "test"))                 // Query
               .setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))     // Filter
               .setFrom(0).setSize(60).setExplain(true)
               .get();

   }

    public static void search(){
        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch").build();
        TransportClient client =new PreBuiltTransportClient(settings);
        client.addTransportAddress(new TransportAddress(new InetSocketAddress("no250",9300)));
        SearchRequestBuilder search=client.prepareSearch("customer");

        SearchResponse response = search
                .setTypes("person")
                .setQuery(QueryBuilders.termQuery("first_name", "John"))                 // Query
                .setFrom(0).setSize(60).setExplain(true)
                .get();
        System.out.println(response);

    }

    public static void delete(TransportClient client){
        DeleteRequest deleteRequest=new DeleteRequest(Person.INDEX,Person.TYPE,"13");
        client.delete(deleteRequest);
        System.out.println(90);
    }

    public static void prepareDelete(TransportClient client){
        DeleteRequestBuilder delete=client.prepareDelete(Person.INDEX,Person.TYPE,"3");
        delete.execute();
        System.out.println(90);
    }

    public static void update(TransportClient client){
        UpdateRequest updateRequest=new UpdateRequest(Person.INDEX,Person.TYPE,"14");
        Map<String,Object> para=new HashMap<>();
        para.put(Person.FIRST_NAME,"cao3");
        para.put(Person.LAST_NAME,"xu3");
        para.put(Person.AGE,100);
        para.put(Person.COUNT,100);
        para.put(Person.ABOUNT,"my first time to go");
        para.put(Person.INTERESTS,new String[]{"sport","music","online"});
        updateRequest.doc(para);
        client.update(updateRequest);
        System.out.println(90);
    }

    public static void prepareUpdate(TransportClient client){
        UpdateRequestBuilder update=client.prepareUpdate(Person.INDEX,Person.TYPE,"12");
        Map<String,Object> para=new HashMap<>();
        para.put(Person.FIRST_NAME,"cao2");
        para.put(Person.LAST_NAME,"xu2");
        para.put(Person.AGE,100);
        para.put(Person.COUNT,100);
        para.put(Person.ABOUNT,"my first time to go");
        para.put(Person.INTERESTS,new String[]{"sport","music","online"});
        update.setDoc(para).get();
        System.out.println(90);
    }

    public static void prepareCreate(TransportClient client){
        IndexRequestBuilder create=client.prepareIndex("customer","person");
        Map<String,Object> para=new HashMap<>();
        para.put(Person.FIRST_NAME,"cao1");
        para.put(Person.LAST_NAME,"xu1");
        para.put(Person.AGE,30);
        para.put(Person.COUNT,100);
        para.put(Person.ABOUNT,"my first time to go");
        para.put(Person.INTERESTS,new String[]{"sport","music","online"});
        create.setSource(para).get();
        System.out.println(90);
    }

    public static void prepareCreateTest(TransportClient client) throws  Exception{
        IndexRequestBuilder create=client.prepareIndex("customer","person");
        Map<String,Object> para=new HashMap<>();
        para.put(Person.FIRST_NAME,"cao11111");
        para.put(Person.LAST_NAME,"xu111111");
        para.put(Person.AGE,30);
        para.put(Person.COUNT,100);
        para.put(Person.ABOUNT,"my first time to go");
        para.put(Person.INTERESTS,new String[]{"sport","music","online"});
        XContentBuilder builder=  XContentFactory.jsonBuilder()
                 .startObject()
                 .field(Person.FIRST_NAME,"dogdogdog")
                 .field(Person.LAST_NAME,"dogdogdog")
                 .field(Person.AGE,30)
                 .field(Person.COUNT,100)
                 .field(Person.ABOUNT,"my first time to go")
                 .field(Person.INTERESTS,new String[]{"sport","music","online"})
                 .endObject();
        IndexResponse bus=create.setSource(builder).get();
        IndexResponse response=create.setSource(para).get();

        System.out.println(response);
    }

    public static void create(TransportClient client){
        IndexRequest indexRequest=new IndexRequest(Person.INDEX,Person.TYPE);
        Map<String,Object> para=new HashMap<>();
        para.put(Person.FIRST_NAME,"qqqqq");
        para.put(Person.LAST_NAME,"qqqqqq");
        para.put(Person.AGE,30);
        para.put(Person.COUNT,100);
        para.put(Person.ABOUNT,"my first time to go");
        para.put(Person.INTERESTS,new String[]{"sport","music","online"});
        indexRequest.source(para);
        client.index(indexRequest);
        System.out.println(90);
    }

}
