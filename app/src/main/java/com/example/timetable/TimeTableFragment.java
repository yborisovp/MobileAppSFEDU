package com.example.timetable;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sfedymob.R;

import org.json.JSONException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TimeTableFragment extends Fragment {
    private View root;
    private int week_actual;
    private Calendar calendar;
    private ListView ListTimeBox;
    ArrayList<TimeList> lists = new ArrayList<>();
    TimeListAdapter AdapterList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.timetable_activity_main, container, false);
        init();

        Button but_days_of_week = root.findViewById(R.id.days_of_week);
        but_days_of_week.setOnClickListener(v -> {
            onDialogDaysStart();//показать окно
        });

        Button but_week = root.findViewById(R.id.button_of_the_week);
        but_week.setOnClickListener(v -> {
            onDialogDWeekStart();//показать окно
        });

        return root;

    }

    private void init() {
        onDayConfirm();
        try {
            fillData();
        } catch (IOException | SQLException | JSONException e) {
            e.printStackTrace();
        }
        AdapterList.notifyDataSetChanged();
    }

    private void fillData() throws IOException, SQLException, JSONException {

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

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

        AdapterList = new TimeListAdapter(getActivity(), lists);
        ListTimeBox = (ListView) root.findViewById(R.id.TimeList);
        ListTimeBox.setAdapter(AdapterList);
    }

    private void onDialogGroupStart() {
        Dialog dialogGroup;

        dialogGroup = new Dialog(getActivity());
        dialogGroup.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogGroup.setContentView(R.layout.timetable_dialog_group);
        dialogGroup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView textGroup = (TextView) root.findViewById(R.id.textGroup);
        EditText groupEditText = (EditText) dialogGroup.findViewById(R.id.editTextGroup);
        Button EllDiaGroup1 = (Button) dialogGroup.findViewById(R.id.Button_group_left);
        Button EllDiaGroup2 = (Button) dialogGroup.findViewById(R.id.Button_group_right);

        //кнопка "X"
        EllDiaGroup1.setOnClickListener(v -> dialogGroup.dismiss());

        //кнопка "V"
        EllDiaGroup2.setOnClickListener(v -> {
            String stringGroup = groupEditText.getText().toString();
            textGroup.setText(stringGroup);
            dialogGroup.dismiss();
        });

        textGroup.setOnClickListener(v -> {
            dialogGroup.show();//показать окно
        });
    }

    private void onDialogDWeekStart() {
        Dialog dialogWeek;

        dialogWeek = new Dialog(getActivity());
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

        int firstStudyWeek = 35;
        week_actual = calendar.get(Calendar.WEEK_OF_YEAR) - firstStudyWeek;
        ((android.widget.Button) root.findViewById(R.id.button_of_the_week)).setText(String.valueOf(week_actual));

        if (week_actual > 1) {
            DiaButWeek1.setText(String.valueOf(week_actual - 1));
        }
        DiaButWeek2.setText(String.valueOf(week_actual));
        if (week_actual < 24) {
            DiaButWeek1.setText(String.valueOf(week_actual - 1));
        }
        DiaButWeek3.setText(String.valueOf(week_actual + 1));

        //кнопка влево
        PolDiaWeek1.setOnClickListener(v -> {
            if (week_actual > 1) {
                week_actual--;
                DiaButWeek1.setText(String.valueOf(week_actual - 1));
                DiaButWeek2.setText(String.valueOf(week_actual));
                DiaButWeek3.setText(String.valueOf(week_actual + 1));
            }
        });

        //кнопка вправо
        PolDiaWeek2.setOnClickListener(v -> {
            if (week_actual < 24) {
                week_actual++;
                DiaButWeek1.setText(String.valueOf(week_actual - 1));
                DiaButWeek2.setText(String.valueOf(week_actual));
                DiaButWeek3.setText(String.valueOf(week_actual + 1));
            }
        });

        //кнопка "X"
        EllDiaWeek1.setOnClickListener(v -> dialogWeek.dismiss());

        //кнопка "V"
        EllDiaWeek2.setOnClickListener(v -> {
            ((Button) root.findViewById(R.id.button_of_the_week)).setText(String.valueOf(week_actual));
            dialogWeek.dismiss();
        });

        dialogWeek.show();
    }

    private void onDialogDaysStart() {
        Dialog dialogDays;

        dialogDays = new Dialog(getActivity());
        dialogDays.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogDays.setContentView(R.layout.timetable_prev_days_dialog);
        dialogDays.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button Mn_Dialog = (Button) dialogDays.findViewById(R.id.Button_days_Mn);
        Button Ts_Dialog = (Button) dialogDays.findViewById(R.id.Button_days_Ts);
        Button Wd_Dialog = (Button) dialogDays.findViewById(R.id.Button_days_Wd);
        Button Th_Dialog = (Button) dialogDays.findViewById(R.id.Button_days_Th);
        Button Fr_Dialog = (Button) dialogDays.findViewById(R.id.Button_days_Fr);
        Button St_Dialog = (Button) dialogDays.findViewById(R.id.Button_days_St);

        butDaysOfWeek(Mn_Dialog, "Пн", dialogDays);
        butDaysOfWeek(Ts_Dialog, "Вт", dialogDays);
        butDaysOfWeek(Wd_Dialog, "Ср", dialogDays);
        butDaysOfWeek(Th_Dialog, "Чт", dialogDays);
        butDaysOfWeek(Fr_Dialog, "Пт", dialogDays);
        butDaysOfWeek(St_Dialog, "Сб", dialogDays);
//Возможно не будет работать
        dialogDays.show();//показать окно


    }

    private void butDaysOfWeek(Button But, String text, Dialog dialogDays) {

        But.setOnClickListener(v -> {
            ((Button) root.findViewById(R.id.days_of_week)).setText(text);
            dialogDays.dismiss();
        });

    }

    private void onDayConfirm() {
        calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        switch (day) {
            case Calendar.MONDAY:
                ((android.widget.Button) root.findViewById(R.id.days_of_week)).setText("Mon");
                break;
            case Calendar.TUESDAY:
                ((android.widget.Button) root.findViewById(R.id.days_of_week)).setText("Tu");
                break;
            case Calendar.WEDNESDAY:
                ((android.widget.Button) root.findViewById(R.id.days_of_week)).setText("Wen");
                break;
            case Calendar.THURSDAY:
                ((android.widget.Button) root.findViewById(R.id.days_of_week)).setText("Th");
                break;
            case Calendar.FRIDAY:
                ((android.widget.Button) root.findViewById(R.id.days_of_week)).setText("Fri");
                break;
            case Calendar.SATURDAY:
                ((android.widget.Button) root.findViewById(R.id.days_of_week)).setText("Su");
                break;
        }
    }
}
