package com.group2.myfavoritethings;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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

    public String getItemName( int position ){ return imagenames.get(position); }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // https://stackoverflow.com/questions/35158652/how-to-add-textview-inside-a-gridview-filled-with-imageview
        LinearLayout linearlayout;
        if( convertView == null ){

            // Set up the linear layout view
            linearlayout = new LinearLayout(mContext);
            linearlayout.setOrientation(LinearLayout.VERTICAL);

            // Set up the image view
            ImageView imageView;
            imageView = new ImageView(mContext);
            if (convertView == null) {

                imageView.setLayoutParams(new GridView.LayoutParams(100, 100));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

                imageView.setPadding(0, 0, 0, 0);
            } else {
                imageView = (ImageView) convertView;
            }

            Bitmap b= BitmapFactory.decodeFile(imagenames.get(position));
            imageView.setImageBitmap(b);

            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setLayoutParams(new GridView.LayoutParams(300, 300));

            linearlayout.addView(imageView);

            // set up the text view
            TextView imageViewName = new TextView(mContext);
            imageViewName.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            imageViewName.setText(getItemName( position ) );
            imageViewName.setGravity(Gravity.CENTER);

            linearlayout.addView(imageViewName);

        } else {
            linearlayout = (LinearLayout) convertView;
        }

        return linearlayout;
    }

}
