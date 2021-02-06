package by.a1qa;

import by.a1qa.service.ConfProperties;
import by.a1qa.service.JSONReader;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Program {


    public static void main(String [] args) throws IOException, ParseException {
        //CloseableHttpClient httpclient = HttpClients.createDefault();

        System.out.println(JSONReader.readByKey("userId"));
         final HttpPost httpPost = new HttpPost("http://jsonplaceholder.typicode.com/posts");
        final List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("title", ConfProperties.getProperty("title")));
        params.add(new BasicNameValuePair("body", ConfProperties.getProperty("body")));
        params.add(new BasicNameValuePair("userId", ConfProperties.getProperty("userId")));
        params.add(new BasicNameValuePair("id", ConfProperties.getProperty("id")));
        httpPost.setEntity(new UrlEncodedFormEntity(params));
      //  CloseableHttpResponse post1 = httpclient.execute(httpPost);
      //  final HttpEntity postUser = post1.getEntity();
       // System.out.println(EntityUtils.toString(postUser));
      //  httpclient.close();

    }
}
