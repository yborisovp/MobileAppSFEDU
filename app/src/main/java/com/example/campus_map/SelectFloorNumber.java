package com.example.campus_map;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SelectFloorNumber extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public void GetExtraAndSelectFloor(){
        Intent i = getIntent();
        int SelectObject = Integer.parseInt(i.getStringExtra("Object"));
        switch(SelectObject) {
            case(1):
                //переход на активити с 2 мя этажами
                break;
            case(2):
                //переход на активити с 4 мя этажами
                break;

        }

        int CampusFloar = Integer.parseInt(i.getStringExtra("CampusFloor"));
        int DormsFloar = Integer.parseInt(i.getStringExtra("DormsFloor"));

    }

}
