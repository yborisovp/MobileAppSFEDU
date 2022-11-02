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


    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder  {
        TextView titleTimeView;
        TextView titleLessonView;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = lInFlater.inflate(R.layout.timetable_list_block, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.titleTimeView = (TextView) convertView.findViewById(R.id.time_box);
            viewHolder.titleLessonView = (TextView) convertView.findViewById(R.id.lesson_box);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        TimeList p = getTimeList(position);

        viewHolder.titleLessonView.setText(p.Lesson);
        viewHolder.titleTimeView.setText(p.Time);

        return convertView;
    }

    TimeList getTimeList(int position) {
        return ((TimeList) getItem(position));
    }
}