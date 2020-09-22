package com.example.campus_map;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Campus_Adapter extends ArrayAdapter<String>{
    private static final String[] mCampus={"A", "Чехова 22", "Б",
            "Маринская 35", "В", "Александровская 25", "Г", "Некрасовский 44", "Д",
            "Некрасовский 44"};

    Context mContext;

    // Конструктор
    public Campus_Adapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId, mCampus);
        // TODO Auto-generated constructor stub
        this.mContext = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        TextView label = (TextView) convertView;

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