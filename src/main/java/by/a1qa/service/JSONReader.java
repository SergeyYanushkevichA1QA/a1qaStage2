package by.a1qa.service;


import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;


public class JSONReader {

    public static String readByKey(String key) {

        try {
            Object obj = new JSONParser().parse(new FileReader("src/test/resources/data.json"));
            JSONObject jsonObject =  (JSONObject) obj;
            String value = (String) jsonObject.get(key);
            return value;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
