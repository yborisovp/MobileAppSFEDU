package com.example.campus_map;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;

import android.widget.GridView;

import com.example.sfedymob.R;

public class Dorms extends Activity implements
        AdapterView.OnItemSelectedListener {
    private Dorms_Adapter mAdapter2;
    private String item;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dorms_list);
        final GridView g = (GridView) findViewById(R.id.gridView2);
        mAdapter2 = new Dorms_Adapter(getApplicationContext(),
                android.R.layout.simple_list_item_2);
        g.setAdapter(mAdapter2);
        g.setOnItemSelectedListener(this);
        g.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Intent i = new Intent(Dorms.this,SelectFloorNumber.class);
                item = String.valueOf(position);
                i.putExtra("DormsFloor",item);
                startActivity(i);

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position,
                               long id) {
        Intent i = new Intent(Dorms.this,SelectFloorNumber.class);
        item = String.valueOf(position);
        i.putExtra("DormsFloor",item);
        startActivity(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    //----------------------------------------------------------------------------------------------
    public void onClickHome(View view){
        Intent i = new Intent(Dorms.this,Campus.class);
        startActivity(i);
    }
    //----------------------------------------------------------------------------------------------
}
