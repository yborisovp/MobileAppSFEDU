package com.example.timetable;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toolbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sfedymob.R;
import com.example.sfedymob.StartActivity;
import com.example.sfedymob.supporting_functions.Info;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BaseActivity extends AppCompatActivity {

    private List<Info> times = new ArrayList<>();
    private List<Info> lessons = new ArrayList<>();
    private String []Days;
    private  int week_actual;
    private int firstStudyWeek = 33;//надо поставить 35

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

        setDayOfWeak();

        ListView TimeList = (ListView) findViewById(R.id.TimeList);
        ListView LessonsList = (ListView) findViewById(R.id.LessonsList);

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
        //кнопкаа вправо
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

       /* Spinner spinner = (Spinner) findViewById(R.id.days_of_week);
        MyCustomAdapter DAYS= new MyCustomAdapter(BaseActivity.this,
                R.layout.timetable_drop_down_view_spinner, Days);*/

        Time_Adapter TIME = new Time_Adapter(this, com.example.sfedymob.R.layout.timetable_time_layout, times);
        Lesson_Adapter LESSON = new Lesson_Adapter(this, com.example.sfedymob.R.layout.timetable_lessons_layout, lessons);

        // устанавливаем адаптер
        TimeList.setAdapter(TIME);
        LessonsList.setAdapter(LESSON);
        //spinner.setAdapter(DAYS);
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

    //функция для изменения надписей на кнопочках внутри диалогово окна недели

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
