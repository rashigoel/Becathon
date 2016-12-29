package com.street35.beacon.Information;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.street35.beacon.R;

/**
 * Created by Weirdo on 29-12-2016.
 */

public class Information extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_info);


        final GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(gridview.getChildAt(i).isEnabled()){

                    Toast.makeText(view.getContext() , i+" sd" , Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Information.this , DetailInfo.class);
                    intent.putExtra("id",""+i);
                    startActivity(intent);
                }
            }
        });

    }
}
