package com.hope.saas.api.util.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

/**
 * @author Maduo
 * @date 2020/3/30 16:20
 */
public class HttpPostUtil {

    public static String sendPost(String url, Map<String, String> headerMap, String body) {
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            //循环增加header
            for (Map.Entry<String, String> elem : headerMap.entrySet()) {
                post.addHeader(elem.getKey(), elem.getValue());
            }
            StringEntity params = new StringEntity(body, "UTF-8");
            post.setEntity(params);
            response = httpClient.execute(post);            //发送请求并接收返回数据
            if (response != null && response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();       //获取response的body部分
                result = EntityUtils.toString(entity);          //读取reponse的body部分并转化成字符串
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
