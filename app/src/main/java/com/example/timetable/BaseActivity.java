package com.example.timetable;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import java.io.BufferedReader;

import android.util.Log;
import android.view.View;
import android.view.Window;

import android.widget.Button;
import android.widget.EditText;
import java.net.*;
import java.io.*;
import android.widget.ImageButton;
import android.widget.ListView;

import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sfedymob.R;
import com.example.timetable.Parse;
import com.example.sfedymob.StartActivity;
import com.example.timetable.Parse;
import com.example.sfedymob.supporting_functions.Info;

import org.json.JSONArray;
import org.json.JSONException;
import org.w3c.dom.Text;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BaseActivity extends AppCompatActivity {

    private List<Info> times = new ArrayList<>();
    private List<Info> lessons = new ArrayList<>();
    private String []Days;
    private int week_actual;
    private int firstStudyWeek = 35;
    private ListView ListTimeBox;
    ArrayList<TimeList> lists = new ArrayList<TimeList>();
    TimeListAdapter AdapterList;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.timetable_activity_main);

        //Устанавливаем tool_bar
        setToolbar();

//        Days = setDays(Days);

        ImageButton button_back = (ImageButton) findViewById(R.id.button_back);
        button_back.setRotation(270);

        //код добавлен - Ариной : кнопка назад
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                try {
                    Intent intent = new Intent(BaseActivity.this, StartActivity.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){ }
            }
        }
        );

//        setDayOfWeak();
//
//        ListView TimeList = (ListView) findViewById(R.id.TimeList);
//        ListView LessonsList = (ListView) findViewById(R.id.LessonsList);

        //автоматическое определение дня недели - написано Ариной
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        switch (day) {
            case Calendar.MONDAY:
                ((android.widget.Button)findViewById(R.id.days_of_week)).setText("Пн");
                break;
            case Calendar.TUESDAY:
                ((android.widget.Button)findViewById(R.id.days_of_week)).setText("Вт");
                break;
            case Calendar.WEDNESDAY:
                ((android.widget.Button)findViewById(R.id.days_of_week)).setText("Ср");
                break;
            case Calendar.THURSDAY:
                ((android.widget.Button)findViewById(R.id.days_of_week)).setText("Чт");
                break;
            case Calendar.FRIDAY:
                ((android.widget.Button)findViewById(R.id.days_of_week)).setText("Пт");
                break;
            case Calendar.SATURDAY:
                ((android.widget.Button)findViewById(R.id.days_of_week)).setText("Сб");
                break;
        }

        //диалоговое окно на "день недели" - написано Ариной - начало
        Dialog dialogDays;

        dialogDays = new Dialog(this);
        dialogDays.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogDays.setContentView(R.layout.timetable_prev_days_dialog);
        dialogDays.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button Mn_Dialog = (Button) dialogDays.findViewById(R.id.Button_days_Mn);
        Button Ts_Dialog = (Button) dialogDays.findViewById(R.id.Button_days_Ts);
        Button Wd_Dialog = (Button) dialogDays.findViewById(R.id.Button_days_Wd);
        Button Th_Dialog = (Button) dialogDays.findViewById(R.id.Button_days_Th);
        Button Fr_Dialog = (Button) dialogDays.findViewById(R.id.Button_days_Fr);
        Button St_Dialog = (Button) dialogDays.findViewById(R.id.Button_days_St);

        //Я не знаю как это сделать с помощью массивов

        //изменение надписи кнопки с днём недели в зависимости от выбранной кнопки в диалоговом окне
        butDaysOfWeek(Mn_Dialog, "Пн", dialogDays);
        butDaysOfWeek(Ts_Dialog, "Вт", dialogDays);
        butDaysOfWeek(Wd_Dialog, "Ср", dialogDays);
        butDaysOfWeek(Th_Dialog, "Чт", dialogDays);
        butDaysOfWeek(Fr_Dialog, "Пт", dialogDays);
        butDaysOfWeek(St_Dialog, "Сб", dialogDays);

        Button but_days_of_week = (Button)findViewById(R.id.days_of_week) ;
        but_days_of_week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDays.show();//показать окно
            }
        });

        //диалоговое окно на "номер недели" - написано Ариной
        Dialog dialogWeek;

        dialogWeek = new Dialog(this);
        dialogWeek.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogWeek.setContentView(R.layout.timetable_dialog_week);
        dialogWeek.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button PolDiaWeek1 = (Button) dialogWeek.findViewById(R.id.Button_strelka_left);
        Button PolDiaWeek2 = (Button) dialogWeek.findViewById(R.id.Button_strelka_right);
        Button DiaButWeek1 = (Button) dialogWeek.findViewById(R.id.Button_week1);
        Button DiaButWeek2 = (Button) dialogWeek.findViewById(R.id.Button_week2);
        Button DiaButWeek3 = (Button) dialogWeek.findViewById(R.id.Button_week3);
        Button EllDiaWeek1 = (Button) dialogWeek.findViewById(R.id.Button_ellipse_left);
        Button EllDiaWeek2 = (Button) dialogWeek.findViewById(R.id.Button_ellipse_right);

        //прописывание работы кнопочек на диалоговом окне недели

        //вычисление номера недели
        week_actual = calendar.get(Calendar.WEEK_OF_YEAR) - firstStudyWeek;
        ((android.widget.Button)findViewById(R.id.button_of_the_week)).setText(String.valueOf(week_actual));

        if (week_actual > 1)
        {
            DiaButWeek1.setText(String.valueOf(week_actual-1));
        }
        DiaButWeek2.setText(String.valueOf(week_actual));
        if (week_actual < 24)
        {
            DiaButWeek1.setText(String.valueOf(week_actual-1));
        }
        DiaButWeek3.setText(String.valueOf(week_actual+1));
        //кнопка влево
        PolDiaWeek1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(week_actual > 1) {
                    week_actual--;
                    DiaButWeek1.setText(String.valueOf(week_actual - 1));
                    DiaButWeek2.setText(String.valueOf(week_actual));
                    DiaButWeek3.setText(String.valueOf(week_actual + 1));
                }
            }
        });
        //кнопка вправо
        PolDiaWeek2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (week_actual < 24) {
                    week_actual++;
                    DiaButWeek1.setText(String.valueOf(week_actual - 1));
                    DiaButWeek2.setText(String.valueOf(week_actual));
                    DiaButWeek3.setText(String.valueOf(week_actual + 1));
                }
            }
        });
        //кнопка "X"
        EllDiaWeek1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogWeek.dismiss();
            }
        });
        //кнопка "V"
        EllDiaWeek2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((android.widget.Button)findViewById(R.id.button_of_the_week)).setText(String.valueOf(week_actual));
                dialogWeek.dismiss();
            }
        });

        Button but_week = (Button)findViewById(R.id.button_of_the_week) ;
        but_week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogWeek.show();//показать окно
            }
        });

        Dialog dialogGroup;

        dialogGroup = new Dialog(this);
        dialogGroup.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogGroup.setContentView(R.layout.timetable_dialog_group);
        dialogGroup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView textGroup = (TextView) findViewById(R.id.textGroup) ;
        EditText groupEditText = (EditText) dialogGroup.findViewById(R.id.editTextGroup);
        Button EllDiaGroup1 = (Button) dialogGroup.findViewById(R.id.Button_group_left);
        Button EllDiaGroup2 = (Button) dialogGroup.findViewById(R.id.Button_group_right);

        //кнопка "X"
        EllDiaGroup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogGroup.dismiss();
            }
        });
        //кнопка "V"
        EllDiaGroup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String stringGroup = groupEditText.getText().toString();
                    textGroup.setText(stringGroup);
                    fillData();
                    dialogGroup.dismiss();
                }catch (IOException | SQLException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        textGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogGroup.show();//показать окно
            }
        });

        //список с времемнем и парами

        try {
            fillData();
        } catch (IOException | SQLException | JSONException e) {
            e.printStackTrace();
        }

    }

    //данные для адаптера
    public void fillData() throws IOException, SQLException, JSONException {
        Calendar c = Calendar.getInstance();
        Integer dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        ExecutorService executor;

        executor = Executors.newFixedThreadPool(1);

        Callable<ArrayList<TimeList>> callable = new Parse(lists, dayOfWeek);

        Future<ArrayList<TimeList>> future;
        future = executor.submit(callable);

        try {
            lists = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();

        AdapterList = new TimeListAdapter(this, lists);
        ListTimeBox = (ListView) findViewById(R.id.TimeList);
        ListTimeBox.setAdapter(AdapterList);
    }

    private String []setDays(String []Days) {
        Days = getResources().getStringArray(R.array.dayofweek);
        return Days;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setToolbar() {

        Toolbar toolbar = findViewById(R.id.tool_bar);
        setActionBar(toolbar);
    }
    private void setDayOfWeak() {

        Calendar c = Calendar.getInstance();
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

    }




//упрощает выбор варианта в диалоговом окне: выбор изменяет надпись на кнопке
    private void butDaysOfWeek(Button But, String text, Dialog dialogDays) {

        But.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((android.widget.Button)findViewById(R.id.days_of_week)).setText(text);
                dialogDays.dismiss();
            }
        });

    }

    /*системная кнопка назад*/
        @Override

        public void onBackPressed() {
            try {
                Intent intent = new Intent(BaseActivity.this, StartActivity.class);
                startActivity(intent);
                finish();
            }catch (Exception e){

            }
        }
}
