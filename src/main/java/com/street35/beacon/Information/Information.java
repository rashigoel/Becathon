package com.street35.beacon.Information;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.street35.beacon.BeaconBack.BackProcess;
import com.street35.beacon.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Weirdo on 29-12-2016.
 */

public class Information extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST_CODE = 100;
    protected static final int REQUEST_CHECK_SETTINGS = 0x1;

    private static String[] mPermissions = { Manifest.permission.ACCESS_FINE_LOCATION};
    private BackProcess.OnListRefreshListener onListRefreshListener;

    private ImageAdapter imageAdapter;

    @Override
    protected void onResume() {
        super.onResume();
        onListRefreshListener = new BackProcess.OnListRefreshListener() {
            @Override
            public void onListRefresh() {
                Toast.makeText(getApplicationContext() , "Here bro" , Toast.LENGTH_SHORT).show();
                notifyListChange();
            }
        };
        BackProcess.getInstance().onListRefreshListener = onListRefreshListener;
        BackProcess.getInstance().context = this;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_info);


        if (!havePermissions()) {
            Log.i("Check", "Requesting permissions needed for this app.");
            requestPermissions();
        }

        if(!isBlueEnable()){
            Intent bluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(bluetoothIntent);
        }

        Toast.makeText(getApplicationContext() , "sd" + BackProcess.getInstance().regionList ,Toast.LENGTH_LONG).show();
        List<String> itemlist = new ArrayList<>(BackProcess.getInstance().regionNameList);
        imageAdapter = new ImageAdapter(this , itemlist);

        final GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(imageAdapter);
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


    private boolean havePermissions() {
        for(String permission:mPermissions){
            if(ActivityCompat.checkSelfPermission(this,permission)!= PackageManager.PERMISSION_GRANTED){
                return  false;
            }
        }
        return true;
    }
    private void requestPermissions() {
        ActivityCompat.requestPermissions(this,
                mPermissions, PERMISSIONS_REQUEST_CODE);
    }
    private boolean isBlueEnable() {
        BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(BLUETOOTH_SERVICE);
        BluetoothAdapter bluetoothAdapter = bluetoothManager.getAdapter();
        return bluetoothAdapter.isEnabled();

    }


    private void notifyListChange(){
        if(imageAdapter != null){
            List<String> items = new ArrayList<>(BackProcess.getInstance().regionNameList);
            imageAdapter.clear();
            imageAdapter.addAll(items);
            imageAdapter.notifyDataSetChanged();
        }
    }




    @Override
    protected void onStop() {
        super.onStop();
        BackProcess.getInstance().onListRefreshListener = null;
        BackProcess.getInstance().context = null;
    }

}
