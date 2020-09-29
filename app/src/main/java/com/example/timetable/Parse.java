package com.example.timetable;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class Parse implements Runnable{
    ArrayList<TimeList>lists;
    TimeListAdapter AdapterList;
    int dayOfWeek;

    Parse(ArrayList<TimeList>lists, TimeListAdapter AdapterList, int dayOfWeek) {
        this.lists = lists;
        this.AdapterList = AdapterList;
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public void run() {
        String url = "http://165.22.28.187/schedule-api/?group=7.htm&week=5";
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
            //System.out.println(jsonText);
            org.json.JSONObject parsedObject = new org.json.JSONObject(jsonText); //Здесь парсим.
            JSONArray top = parsedObject.getJSONObject("table").getJSONArray("table");
            JSONArray arraylesson;

            arraylesson = (JSONArray) top.get(dayOfWeek+2);
            for (int j = 0; j < arraylesson.length(); j++) {
                lists.add(new TimeList("08:00 - 09:35", arraylesson.get(0).toString()));
                lists.add(new TimeList("09:50 - 11:25", arraylesson.get(1).toString()));
                lists.add(new TimeList("11:55 - 13:30", arraylesson.get(2).toString()));
                lists.add(new TimeList("13:45 - 15:20", arraylesson.get(3).toString()));
                lists.add(new TimeList("15:50 - 17:25", arraylesson.get(4).toString()));
                lists.add(new TimeList("17:40 - 19:15", arraylesson.get(4).toString()));
                lists.add(new TimeList("19:30 - 21:05", arraylesson.get(5).toString()));
            }
        }catch (IOException | JSONException e) {
            e.printStackTrace();
        }

    }
}


