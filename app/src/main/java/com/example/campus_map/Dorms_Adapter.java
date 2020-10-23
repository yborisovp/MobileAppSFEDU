package com.example.campus_map;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Dorms_Adapter extends ArrayAdapter<String>{
    private static final String[] mDorms={"3 Александровская 30","7 Тургеневский пер. 44а"};
    Context mContext;
    // Конструктор
    public Dorms_Adapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId, mDorms);
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
        label.setText(mDorms[position]);
        return (convertView);
    }
    // возвращает содержимое выделенного элемента списка
    public String getItem(int position) {
        return mDorms[position];
    }
}
