package com.leaf.elasticsearch;


import com.leaf.elasticsearch.model.Person;
import org.elasticsearch.action.ActionFuture;
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
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.search.MatchQuery;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.search.suggest.term.TermSuggestion;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class ElasticJavaApi {
    private SearchSourceBuilder searchSourceBuilder;

    public static void main(String args[]){
        long start=System.currentTimeMillis();
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

        for (DiscoveryNode item: client.listedNodes()){
            pringValue(item.getHostAddress(),item.getHostName(),item.getName());
        }

        for (DiscoveryNode item: client.connectedNodes()){
            pringValue(item.getHostAddress(),item.getHostName(),item.getName());
        }
        System.out.println(" client time:"+(System.currentTimeMillis()-start)+" ms");

       try {
           //scroll(client);
           //scrollTest(client);
          // boolQuery(client);
           //aggregation(client);
           termSearch(client);
           //multInsert(client);

       }catch (Exception a){
          a.printStackTrace();
       }
   }

   public static void multInsert(TransportClient client) throws Exception{
       long start=System.currentTimeMillis();
       BulkRequest bulkRequest=new BulkRequest();
       for(int i=0;i<1000;i++){
           IndexRequest indexRequest=new IndexRequest(Person.INDEX,Person.TYPE);
           Map<String,Object> param=new HashMap<>();
           param.put(Person.FIRST_NAME,"client"+i);
           param.put(Person.LAST_NAME,"bulk-update"+1);
           param.put(Person.AGE,i);
           param.put(Person.COUNT,i);
           param.put(Person.ABOUNT,"my first time to go"+i);
           param.put(Person.INTERESTS,new String[]{"sport","music","online"});
           indexRequest.source(param);
           bulkRequest.add(indexRequest);
       }
       BulkResponse bulkResponse=client.bulk(bulkRequest).get();
       System.out.println("time:"+(System.currentTimeMillis()-start)+" ms");
      /* for(BulkItemResponse item: bulkResponse.getItems()){
           pringValue(item.getIndex(),item.getType(),item.getId(),item.getFailureMessage(),item.status().getStatus());
       }*/

   }

   public static void scroll(TransportClient client) throws Exception{
       SearchRequestBuilder scroll= client.prepareSearch(Person.INDEX);
       scroll.setTypes(Person.TYPE);
       scroll.setScroll(TimeValue.timeValueSeconds(1));
       scroll.setQuery(QueryBuilders.matchQuery(Person.LAST_NAME,"bulk-update1"));
       //scroll.setSize(20);
       //
       SearchResponse response=scroll.get();
       String scrollId=response.getScrollId();
       pringValue(scrollId);
       int batch=0;
       do{
           for(SearchHit item: response.getHits().getHits()){
               pringValue(item.getSourceAsString(),item.getSourceAsMap());
               batch++;
           }

           SearchScrollRequestBuilder searchScroll= client.prepareSearchScroll(scrollId);
           searchScroll.setScroll(TimeValue.timeValueSeconds(1));

           response=searchScroll.execute().actionGet();

       }while(response.getHits().getHits().length!=0);
       pringValue("total:",batch);

   }

    public static void scrollTest(TransportClient client) throws Exception{
        SearchRequestBuilder scroll= client.prepareSearch(Person.INDEX);
        scroll.setTypes(Person.TYPE);
        scroll.setScroll(TimeValue.timeValueSeconds(1));
        scroll.setQuery(QueryBuilders.termQuery(Person.LAST_NAME+".keyword","bulk-update1"));
        //scroll.setSize(20);
        //
        SearchResponse response=scroll.get();
        String scrollId=response.getScrollId();
        pringValue(scrollId);
        int batch=0;
        do{
            for(SearchHit item: response.getHits().getHits()){
                pringValue(item.getSourceAsString(),item.getSourceAsMap());
                batch++;
            }

            SearchScrollRequestBuilder searchScroll= client.prepareSearchScroll(scrollId);
            searchScroll.setScroll(TimeValue.timeValueSeconds(1));

            response=searchScroll.execute().actionGet();

        }while(response.getHits().getHits().length!=0);
        pringValue("total:",batch);

    }

    public static void boolQuery(TransportClient client) throws  Exception{
        SearchRequestBuilder scroll= client.prepareSearch(Person.INDEX);
        scroll.setTypes(Person.TYPE);
        scroll.setSize(100).addSort(Person.COUNT, SortOrder.ASC);


        QueryBuilder queryBuilder=QueryBuilders.boolQuery()
       // .must(QueryBuilders.matchQuery(Person.LAST_NAME,"bulk-update1"))
              //  .mustNot(QueryBuilders.rangeQuery(Person.COUNT).gt(20).lt(10))
       // .filter(QueryBuilders.rangeQuery(Person.AGE).gte(10).lte(20))
        .should(QueryBuilders.rangeQuery(Person.COUNT).gt(12).lt(20));

        QueryBuilders.existsQuery(Person.LAST_NAME);
        QueryBuilders.prefixQuery(Person.LAST_NAME,"dd");
        QueryBuilders.wildcardQuery(Person.LAST_NAME,"dd*");
        QueryBuilders.idsQuery().addIds("","");
        QueryBuilders.idsQuery();

        QueryBuilders.geoBoundingBoxQuery("");


        scroll.setQuery(queryBuilder);


        SearchResponse response=scroll.get();
        int batch=0;
        for(SearchHit item: response.getHits().getHits()){
            pringValue(item.getSourceAsString());
            batch++;
        }
        pringValue(response.getHits().getHits().length,response.getHits().getTotalHits());

    }

    public static void pringValue(Object ...text){
        StringBuilder sb=new StringBuilder();
        for (Object string:text){
            sb.append(string).append(",");
        }
        System.out.println(sb.toString());
    }

    public static void termSearch(TransportClient client){
        SearchRequestBuilder scroll= client.prepareSearch(Person.INDEX);
        scroll.setTypes(Person.TYPE)
       // .setQuery(QueryBuilders.termQuery(Person.LAST_NAME+".keyword","bulk-update1"))
        //.setQuery(QueryBuilders.prefixQuery(Person.LAST_NAME,"bulk"))
        //.setQuery(QueryBuilders.matchQuery(Person.LAST_NAME,"bulk spark"))
       // .setQuery(QueryBuilders.matchPhraseQuery(Person.LAST_NAME,"update1"))
         //.setQuery(QueryBuilders.matchAllQuery())
         // .setQuery(QueryBuilders.matchPhrasePrefixQuery(Person.LAST_NAME,"bulk"))
         //.setQuery(QueryBuilders.wildcardQuery(Person.LAST_NAME,"updat*"))
        .setQuery(QueryBuilders.regexpQuery(Person.LAST_NAME,"bulk"))
        ;

        scroll.addSort(Person.COUNT,SortOrder.ASC);
        scroll.setSize(2000);
        SearchResponse response=scroll.get();

        int batch=0;

        for(SearchHit item: response.getHits().getHits()){
            pringValue(item.getSourceAsString());
            batch++;
        }

        pringValue("total:",batch);
    }

    public static void aggregation(TransportClient client){
        SearchResponse response=client.prepareSearch(Person.INDEX)
                .addAggregation(AggregationBuilders.terms("lastnames").field(Person.LAST_NAME))

        .execute().actionGet();
        int batch=0;
        for(SearchHit item: response.getHits().getHits()){
            pringValue(item.getSourceAsString());
            batch++;
        }
    }
}
