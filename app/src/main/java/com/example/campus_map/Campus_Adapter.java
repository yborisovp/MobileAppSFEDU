package com.example.campus_map;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Campus_Adapter extends ArrayAdapter<String>{
    private static final String[] mCampus={"А:Чехова 22А","Б:Чехова 22Б","Г:Энгельса 1","Д:Некрасовский пер. 44Д","И:Чехова 2","К:Шевченко 2"};

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
