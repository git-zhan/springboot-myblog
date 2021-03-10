package com.zxx.blog.util.io;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.InputStream;
import java.io.InputStreamReader;

public class IOUtils {

    public static String readAsText(InputStream in) {
        StringBuilder res = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            char[] chars = new char[128];
            int flag = -1;
            while((flag = reader.read(chars,0,chars.length)) != -1){
                res.append(new String(chars,0,flag));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(reader,in);
        }
        return res.toString();
    }


    public static void close(Closeable... closeables) {
        try {
            for(Closeable closeable : closeables) {
                if(closeable != null) {
                    closeable.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
