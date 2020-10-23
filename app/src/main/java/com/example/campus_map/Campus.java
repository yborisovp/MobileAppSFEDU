package com.example.campus_map;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.example.sfedymob.R;

public class Campus extends Activity implements
        AdapterView.OnItemSelectedListener  {
    private Campus_Adapter mAdapter;
    private Integer item;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.campus_list);
        final GridView g = (GridView) findViewById(R.id.gridView1);
        mAdapter = new Campus_Adapter(getApplicationContext(),
                android.R.layout.simple_list_item_1);
        g.setAdapter(mAdapter);
        g.setOnItemSelectedListener(this);
        g.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Intent i = new Intent(Campus.this,ShowActivity.class);
                item=position;
                i.putExtra("pos",item);
                i.putExtra("ind",1);
                startActivity(i);

            }
        });
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position,
                               long id) {
        Intent i = new Intent(Campus.this,ShowActivity.class);
        item=position;
        i.putExtra("pos",item);
        i.putExtra("ind",1);
        startActivity(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    //----------------------------------------------------------------------------------------------
    public void onClickChangeCampus(View view){
        Intent i = new Intent(Campus.this, Dorms.class);
        startActivity(i);
    }
    //----------------------------------------------------------------------------------------------

}