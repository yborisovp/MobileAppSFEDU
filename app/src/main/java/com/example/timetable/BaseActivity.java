package com.example.timetable;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toolbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sfedymob.R;
import com.example.sfedymob.supporting_functions.Info;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BaseActivity extends AppCompatActivity {

    private List<Info> times = new ArrayList<>();
    private List<Info> lessons = new ArrayList<>();
    private String []Days;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.timetable_activity_main);

        //Устанавливаем tool_bar
        setToolbar();

        Days = setDays(Days);

        ImageButton button_back = (ImageButton) findViewById(R.id.button_back);
        button_back.setRotation(270);

        setDayOfWeak();

        ListView TimeList = (ListView) findViewById(R.id.TimeList);
        ListView LessonsList = (ListView) findViewById(R.id.LessonsList);
        Spinner spinner = (Spinner) findViewById(R.id.days_of_week);



        MyCustomAdapter DAYS= new MyCustomAdapter(BaseActivity.this,
                R.layout.timetable_drop_down_view_spinner, Days);

        Time_Adapter TIME = new Time_Adapter(this, com.example.sfedymob.R.layout.timetable_time_layout, times);
        Lesson_Adapter LESSON = new Lesson_Adapter(this, com.example.sfedymob.R.layout.timetable_lessons_layout, lessons);

        // устанавливаем адаптер

        TimeList.setAdapter(TIME);
        LessonsList.setAdapter(LESSON);
        spinner.setAdapter(DAYS);





    }

    private String []setDays(String []Days) {
        Days = getResources().getStringArray(R.array.dayofweek);
        return Days;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setToolbar() {

        Toolbar toolbar = findViewById(R.id.tool_bar);
        setActionBar(toolbar);
        setInitialData();
    }
    private void setDayOfWeak() {

        Calendar c = Calendar.getInstance();
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

    }

    private void setInitialData(){


        times.add(new Info("08:00 - 09:35"));
        times.add(new Info("09:50 - 11:25"));
        times.add(new Info("11:50 - 13:30"));
        times.add(new Info("13:45 - 15:20"));
        times.add(new Info("15:50 - 17:25"));
        times.add(new Info("19:30 - 21:05"));

        lessons.add(new Info("Пара"));
        lessons.add(new Info("Пара"));
        lessons.add(new Info("Пара"));
        lessons.add(new Info("Пара"));
        lessons.add(new Info("Пара"));
        lessons.add(new Info("Пара"));



    }
}
