package com.leaf.test;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Date: 2018/5/3 17:33
 * @Auther: nothing
 */
public class Test {
    public static void main(String[] args) {
        try{
            // 连接到 mongodb 服务
            MongoClient mongoClient = new MongoClient( "192.168.1.89" , 27017 );

            // 连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("logs");
            System.out.println("Connect to database successfully");

            mongoDatabase.createCollection("logs");
            System.out.println("集合创建成功");

            MongoCollection<Document> collection = mongoDatabase.getCollection("logs");
            System.out.println("集合 test 选择成功");
            Document document = new Document("title", "MongoDB").
                    append("description", "database").
                    append("likes", 100).
                    append("by", "Fly");
            List<Document> documents = new ArrayList<Document>();
            documents.add(document);
            collection.insertMany(documents);
            System.out.println("文档插入成功");

            collection = mongoDatabase.getCollection("logs");


            System.out.println("集合 test 选择成功");
        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
}
