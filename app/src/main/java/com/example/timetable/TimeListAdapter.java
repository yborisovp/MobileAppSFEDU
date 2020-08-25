package com.example.timetable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sfedymob.R;

import java.util.ArrayList;

public class TimeListAdapter extends BaseAdapter {

    Context ctx;
    LayoutInflater lInFlater;
    ArrayList<TimeList> objects;

    TimeListAdapter(Context context, ArrayList<TimeList> TimeLists){
        ctx = context;
        objects  = TimeLists;
        lInFlater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //кол-во элементов
    @Override
    public int getCount() {
        return objects.size();
    }

    //элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    //пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //используем создание но не используем View
        View view = convertView;
        if (view == null) {
            view = lInFlater.inflate(R.layout.timetable_list_block, parent, false);
        }

        TimeList p = getTimeList(position);

        //заполнение View
        ((TextView) view.findViewById(R.id.time_box)).setText(p.Time);
        ((TextView) view.findViewById(R.id.lesson_box)).setText(p.Lesson);
        return view;
    }

    TimeList getTimeList(int position) {
        return ((TimeList) getItem(position));
    }
}
