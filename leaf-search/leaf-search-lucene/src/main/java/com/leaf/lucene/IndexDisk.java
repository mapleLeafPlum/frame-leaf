package com.leaf.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IndexDisk {
    public static void main(String[] args) {
        IndexDisk indexDisk = new IndexDisk();
        indexDisk.index();
    }

    public void index() {
        try {
            //在运行是要添加参数如：-docs （你文件的路径）
            String usage = "java com.bingo.backstage.IndexFiles [-index INDEX_PATH] [-docs DOCS_PATH] [-update]\n\n" +
                    "This indexes the documents in DOCS_PATH, creating a Lucene indexin INDEX_PATH that can be searched with SearchFiles";
            String indexPath = "index";
            //1创建路径
            Path path = Paths.get("D:/lucene/index2", new String[0]);
            Directory directory = FSDirectory.open(path);
            //分词器
            Analyzer analyzer = new StandardAnalyzer();
            //2.创建IndexWrite
            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            //设置为创建或附加
            config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
            IndexWriter writer = new IndexWriter(directory, config);

            indexDocs(writer, new File("D:/lucene/example"));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void indexDocs(IndexWriter writer, File file) {
        InputStream stream = null;
        try {
            for (File f:file.listFiles()){
                stream = new FileInputStream(f);
                Document doc = new Document();
                StringField pathField = new StringField("path", file.toString(), Field.Store.YES);
                doc.add(pathField);
                doc.add(new TextField("contents", new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))));
                writer.addDocument(doc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
