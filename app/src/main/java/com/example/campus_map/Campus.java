package com.example.campus_map;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sfedymob.R;

public class Campus extends Fragment implements AdapterView.OnItemSelectedListener {
    private CampusAdapter mAdapter;
    private String item;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.campus_list, container, false);
        final GridView grid = root.findViewById(R.id.gridView1);
        mAdapter = new CampusAdapter(getActivity(), android.R.layout.simple_list_item_1);
        grid.setAdapter(mAdapter);
        grid.setOnItemSelectedListener(this);
        grid.setOnItemClickListener((parent, v, position, id) -> {
            Toast.makeText(getActivity(), "Sorry that will be fixed", Toast.LENGTH_LONG).show();
//            Intent i = new Intent(Campus.this, SelectFloorNumber.class);
//            item = String.valueOf(position);
//            i.putExtra("CampusFloar",item);
//            startActivity(i);

        });
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        Toast.makeText(getActivity(), "Sorry that will be fixed", Toast.LENGTH_LONG).show();

//        Intent i = new Intent(Campus.this,SelectFloorNumber.class);
//        item = String.valueOf(position);
//        i.putExtra("CampusFloar",item);
//        startActivity(i);
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void onClickHome(View view){
//        Intent i = new Intent(Campus.this, Dorms.class);
//        startActivity(i);
    }
}
