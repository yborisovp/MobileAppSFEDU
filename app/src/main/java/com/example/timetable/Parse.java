package com.example.timetable;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import com.example.sfedymob.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Parse implements Callable<ArrayList<TimeList>> {
    private ArrayList<TimeList> lists;
    private int dayOfWeek;
    private String url = "http://165.22.28.187/schedule-api/?group=44.htm&week=7";
    private Thread thread;
    private JSONArray arraylesson;


    Parse(ArrayList<TimeList> lists, int dayOfWeek) {
        this.lists = lists;
        this.dayOfWeek = dayOfWeek;
    }

    public ArrayList<TimeList> GETList() {
        return this.lists;
    }


    @Override
    public ArrayList<TimeList> call() throws Exception {
        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();


            String jsonText = response.toString();
            org.json.JSONObject parsedObject = new org.json.JSONObject(jsonText); //Здесь парсим.
            JSONArray top = parsedObject.getJSONObject("table").getJSONArray("table");

            dayOfWeek = 2;
            if (dayOfWeek != 1) {
                arraylesson = (JSONArray) top.get(dayOfWeek);

                lists.add(new TimeList("08:00 - 09:35", arraylesson.get(1).toString()));
                lists.add(new TimeList("09:50 - 11:25", arraylesson.get(2).toString()));
                lists.add(new TimeList("11:55 - 13:30", arraylesson.get(3).toString()));
                lists.add(new TimeList("13:45 - 15:20", arraylesson.get(4).toString()));
                lists.add(new TimeList("15:50 - 17:25", arraylesson.get(5).toString()));
                lists.add(new TimeList("17:40 - 19:15", arraylesson.get(6).toString()));
                lists.add(new TimeList("19:30 - 21:05", arraylesson.get(7).toString()));
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return lists;
    }
}




