package com.group2.myfavoritethings;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

public class FavoriteThings extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> imagenames;

    public FavoriteThings(Context context, ArrayList<String> images) {
        mContext = context;
        imagenames = images;
    }
    @Override
    public int getCount() {
        return imagenames.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public ImageView getView(int position, View convertView, ViewGroup parent) {
        // ImageView i = new ImageView(mContext);
        ImageView imageView;
        imageView = new ImageView(mContext);
        if (convertView == null) {

            imageView.setLayoutParams(new GridView.LayoutParams(100, 100));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            imageView.setPadding(0, 0, 0, 0);
        } else {
            imageView = (ImageView) convertView;
        }

        Bitmap b= BitmapFactory.decodeFile(imagenames.get(position).toString());
        Log.i("Favorite Things", "Position"+position+" "+b.getHeight());
        imageView.setImageBitmap(b);

        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        /* Set the Width/Height of the ImageView. */
        imageView.setLayoutParams(new GridView.LayoutParams(300, 300));
        return imageView;
    }

}
