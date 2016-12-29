package com.street35.beacon.Information;

import android.content.Context;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.street35.beacon.R;

import java.util.List;

/**
 * Created by Weirdo on 29-12-2016.
 */

public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> list;
    // Constructor
    public ImageAdapter(Context c , List<String> a) {
        mContext = c;
        list = a;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);


        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(150,150));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        }
        else
        {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(mThumbIds[position]);
        if(!list.contains(region_names[position]))
        {
            imageView.setClickable(false);
            Log.d("aaaaa","aheyeeeee");
            imageView.setEnabled(false);
            imageView.setActivated(false);
            imageView.setColorFilter(filter);
           // imageView.se
        }
        return imageView;
    }

    // Keep all Images in array
    public Integer[] mThumbIds = {
            R.mipmap.ic_launcher , R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher

    };
    public String[] region_names = {
            "Test Room" , "Git Room","Android Room","iOS Room","Python Room","Office","Ruby Room"
    };

    public void clear(){
        list.clear();
        notifyDataSetChanged();
    }
    public void addAll(List<String> a){
        list=a;
        notifyDataSetChanged();
    }
}