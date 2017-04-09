package test.bwie.com.firstdaydemo;

import android.support.annotation.Nullable;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * date: 2017/4/8.
 * author: 王艺凯 (lenovo )
 * function:
 */
public class UrlsUtils {

    private static HttpURLConnection mHuc;

    @Nullable
    public static String getJson(String param) {
        try {
            URL url = new URL(param);
            mHuc = (HttpURLConnection) url.openConnection();
            mHuc.setReadTimeout(5000);
            mHuc.setConnectTimeout(5000);
            mHuc.setRequestMethod("GET");
            if (mHuc.getResponseCode() == 200) {
                InputStream inputStream = mHuc.getInputStream();
                String jiexi = GosnUtils.jiexi(inputStream);
                return jiexi;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return null;
    }
}
