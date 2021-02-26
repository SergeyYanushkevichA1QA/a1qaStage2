package by.a1qa;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.bind.DatatypeConverter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class HttpBasicAuth {
    @Test
    public static void main(String[] args) {
        try {
            DefaultHttpClient Client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet("https://httpbin.org/basic-auth/user/passwd");
            String encoding = DatatypeConverter.printBase64Binary("user:passwd".getBytes("UTF-8"));
            httpGet.setHeader("Authorization", "Basic " + encoding);
            HttpResponse response = Client.execute(httpGet);
            System.out.println("response = " + response);
            BufferedReader breader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder responseString = new StringBuilder();
            String line = "";
            while ((line = breader.readLine()) != null) {
                responseString.append(line);
            }
            breader.close();
            String responseStr = responseString.toString();
            System.out.println("repsonseStr = " + responseStr);
            Assert.assertEquals(responseStr.contains("true"), true);
            Assert.assertEquals(responseStr.contains("user"), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
