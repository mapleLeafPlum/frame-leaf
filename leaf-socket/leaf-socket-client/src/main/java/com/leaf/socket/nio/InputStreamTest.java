package com.leaf.socket.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class InputStreamTest {
    public static void main(String[] args) throws Exception {

       // File file=new File("");
       // BufferedInputStream bufferedInputStream=new BufferedInputStream(new FileInputStream(file));

        test();
    }

    public static  void test() throws IOException
    {
        ByteBuffer buff = ByteBuffer.allocate(256);
        FileChannel in = null;
        FileChannel out = null;
        try
        {
            in = new FileInputStream("D:\\abc.txt").getChannel();
            out = new FileOutputStream("D:\\abcd.txt").getChannel();
            while(in.read(buff) != -1) {
                buff.flip();
                out.write(buff);
                buff.clear();
            }
        }
        catch (FileNotFoundException e)
        {
            throw e;
        } finally {
            try {
                if(in != null) {
                    in.close();
                }
                if(out != null) {
                    out.close();
                }
            } catch(IOException e) {
                throw e;
            }
        }
    }
}
