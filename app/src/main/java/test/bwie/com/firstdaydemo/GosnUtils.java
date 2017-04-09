package test.bwie.com.firstdaydemo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * date: 2017/4/8.
 * author: 王艺凯 (lenovo )
 * function:
 */

public class GosnUtils {
    public static String jiexi(InputStream inputStream) {
        byte[] b = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            while ((len = inputStream.read(b)) != -1) {

                bos.write(b, 0, len);
            }
            return bos.toString("utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
