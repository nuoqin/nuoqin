package com;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.gc.app.dto.Book;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author cwj
 * @since
 */
public class search {

    private static RestClientBuilder restClientBuilder;

    private static RestHighLevelClient client;
    @Before
    public void connect(){
        restClientBuilder= RestClient.builder(new HttpHost("localhost",9200,"http"));

        client=new RestHighLevelClient(restClientBuilder);
    }
  

    @Test
    public void search() throws IOException {
        SearchRequest request=new SearchRequest("book");
        SearchSourceBuilder builder=new SearchSourceBuilder();
        //builder.query(QueryBuilders.matchPhrasePrefixQuery("name","j核心"));
        
        //builder.query();
//        builder.from(2);
//        builder.size(2);
        request.source(builder);
        SearchResponse res = client.search(request, RequestOptions.DEFAULT);
        SearchHits hits = res.getHits();
        SearchHit[] hitArray = hits.getHits();
        System.out.println(JSONArray.toJSONString(hitArray));

        for (SearchHit hit:hitArray) {
            String sourceAsMap = hit.getSourceAsString();

            System.out.println(sourceAsMap);

        }


    }
    
    
    
}
