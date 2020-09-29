package com.example.timetable;

import java.net.*;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;




public class Client {
    public void Run() throws IOException, SQLException  {

        Socket clientSocket = new Socket("127.0.0.1", 8000);

        OutputStreamWriter writer = new OutputStreamWriter(clientSocket.getOutputStream());
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()
                )
        );

        writer.write("give me info\n");
        writer.flush();

        int i = 0;
        String count = "";
        while(i < 30) {
            count += reader.readLine();
            i++;
        }
        reader.close();
        writer.close();

        clientSocket.close();
    }

}

