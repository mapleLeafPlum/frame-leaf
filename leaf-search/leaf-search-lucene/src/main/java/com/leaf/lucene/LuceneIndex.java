package com.leaf.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.store.*;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LuceneIndex {

    public static void main(String[] args) {
        System.out.println("start.....");
        LuceneIndex index=new LuceneIndex();
        index.index();
        System.out.println("ending.....");
    }

    public void index(){
        try {
            //1创建路径
            //Directory directory=new RAMDirectory();
            Path path= Paths.get("D:/lucene/index1", new String[0]);
            Directory directory= FSDirectory.open(path);
            //2.创建IndexWrite
            Analyzer analyzer = new StandardAnalyzer();
            IndexWriterConfig config=new IndexWriterConfig(analyzer);
            IndexWriter writer=null;
            writer=new IndexWriter(directory,config);
            //3创建Document
            Document doc=null;
            File file=new File("D:/lucene/example");
            for(File f:file.listFiles()){
                doc=new Document();
                //4为Document 创建Field
                FieldType store=new FieldType();
                store.setIndexOptions(IndexOptions.NONE);
                Field content= new Field("content",new FileReader(f),store);
                byte[] buffer=new byte[1024];
                new FileInputStream(file).read(buffer);
                content.setBytesValue(buffer);
                doc.add(content);
                store.setStored(true);
                store.setIndexOptions(IndexOptions.DOCS);
                IndexableField filename= new Field("filename",f.getName(), TextField.TYPE_STORED);

                doc.add(filename);
                IndexableField filepath= new Field("filepath",f.getAbsolutePath(),store);
                doc.add(filepath);
                //5通过IndexWrite 添加文档
                writer.addDocument(doc);
                writer.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
