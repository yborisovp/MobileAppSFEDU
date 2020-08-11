package com.example.timetable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sfedymob.R;


public class MyCustomAdapter extends ArrayAdapter<String> {
    private String[]Days;
    private Context context;
    public MyCustomAdapter(Context context, int textViewResourceId,
                           String[] objects) {
        super(context, textViewResourceId, objects);
        this.Days = objects;
        this.context = context;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);;
        View row = inflater.inflate(R.layout.timetable_drop_down_view_spinner, parent, false);
        TextView label = (TextView) row.findViewById(R.id.dropdown);
        label.setText(Days[position]);
        return row;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);;
        View row = inflater.inflate(R.layout.timetable_not_drop_down_view_spinner, parent, false);
        TextView label = (TextView) row.findViewById(R.id.text_title_of_spinner);
        label.setText("День недели");

        return row;
    }


}

