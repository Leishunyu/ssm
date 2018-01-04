package com.ssm.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;



public class HttpClientUtil {

    public static String post(String url, Map<String, String> params,boolean isHttps) {
        
        DefaultHttpClient httpclient ;
        if (isHttps) {
            httpclient = (DefaultHttpClient) HttpsClient.newHttpsClient();
        } else {
            httpclient = new DefaultHttpClient();
        }
        // 读取超时
        httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 900000000);
        // 请求超时
        httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 900000000);
        httpclient.getParams().setParameter(CoreConnectionPNames.MAX_LINE_LENGTH, 0);
        httpclient.getParams().setParameter(CoreConnectionPNames.SOCKET_BUFFER_SIZE, 81920);
        httpclient.getParams().setParameter(CoreConnectionPNames.MAX_HEADER_COUNT, 0);
        String body = null;

        System.out.println("create httppost:" + url);
        HttpPost post = postForm(url, params);

        body = invoke(httpclient, post);

        httpclient.getConnectionManager().shutdown();

        return body;
    }

    public static String get(String url) {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        String body = null;

        System.out.println("create httppost:" + url);
        HttpGet get = new HttpGet(url);
        body = invoke(httpclient, get);

        httpclient.getConnectionManager().shutdown();

        return body;
    }

    private static String invoke(DefaultHttpClient httpclient, HttpUriRequest httpost) {

        HttpResponse response = sendRequest(httpclient, httpost);
        String body = paseResponse(response);

        return body;
    }

    private static String paseResponse(HttpResponse response) {
        System.out.println("get response from http server..");
        HttpEntity entity = response.getEntity();

        System.out.println("response status: " + response.getStatusLine());
        String charset = EntityUtils.getContentCharSet(entity);
        System.out.println(charset);

        String body = null;
        try {
            body = EntityUtils.toString(entity);
            System.out.println(body);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return body;
    }

    private static HttpResponse sendRequest(DefaultHttpClient httpclient, HttpUriRequest httpost) {
        System.out.println("execute post...");
        HttpResponse response = null;

        try {
            response = httpclient.execute(httpost);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private static HttpPost postForm(String url, Map<String, String> params) {

        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        Set<String> keySet = params.keySet();
        for (String key : keySet) {
            nvps.add(new BasicNameValuePair(key, params.get(key)));
        }

        try {
            System.out.println("set utf-8 form entity to httppost");
            httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return httpost;
    }
}
