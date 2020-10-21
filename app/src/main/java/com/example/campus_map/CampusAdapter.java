package com.example.campus_map;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sfedymob.R;

public class CampusAdapter extends ArrayAdapter<String> {
    private static final String[] mCampus = {"A", "Чехова 22", "Б",
            "Маринская 35", "В", "Александровская 25", "Г", "Некрасовский 44", "Д",
            "Некрасовский 44"};

    Context mContext;
    LayoutInflater inflater;
    public CampusAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId, mCampus);
        this.mContext = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            view = inflater.inflate(R.layout.timetable_list_block, parent ,false);
        }

        TextView label = convertView.findViewById(R.id.time_box);
        label.setText("sometext");
        if (convertView == null) {
            convertView = new TextView(mContext);
            label = (TextView) convertView;
        }
        label.setText(mCampus[position]);
        return (convertView);
    }

    // возвращает содержимое выделенного элемента списка
    public String getItem(int position) {
        return mCampus[position];
    }
}