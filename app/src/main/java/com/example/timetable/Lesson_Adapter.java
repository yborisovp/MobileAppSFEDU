package com.example.timetable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sfedymob.supporting_functions.Info;
import com.example.sfedymob.R;

import java.util.List;


public class Lesson_Adapter extends ArrayAdapter<Info> {

    private LayoutInflater inflater;
    private int layout;
    private List<Info> legend;

    public Lesson_Adapter(Context context, int resource, List<Info> legend) {
        super(context, resource, legend);
        this.legend = legend;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(this.layout, parent, false);

        TextView number = (TextView) view.findViewById(R.id.lesson_block);

        Info INFO = legend.get(position);


        number.setText(INFO.get_Info());


        return view;
    }
}
