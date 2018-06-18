package com.example.nic.ytuclass.util;

import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpHelper {
    /**
     * 向接口发送get请求
     * @param faculty 参数1
     * @param _class 参数2
     * @return 服务器返回数据
     * @throws Exception
     */
    public static String getResponseStr(String faculty,String _class) throws Exception {
        StringBuilder url=new StringBuilder("#####接口地址#####");
        url.append("&xy="+java.net.URLEncoder.encode(faculty,"utf-8"));
        url.append("&bj="+_class);
        URL u = new URL(url.toString());
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(u).build();
        Response response = client.newCall(request).execute();
        String responseData = response.body().string();
        return unicodeToString(responseData);
    }

    /**
     * Unicode转汉字
     * @param str 待操作字符串
     * @return 汉字字符串
     */
    public static String unicodeToString(String str) {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            String group = matcher.group(2);
            ch = (char) Integer.parseInt(group, 16);
            String group1 = matcher.group(1);
            str = str.replace(group1, ch + "");
        }
        return str;
    }

}
