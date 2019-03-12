package com.group2.myfavoritethings;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

/**
 * @author      Jared Sulzdorf
 * @version     1.0
 * @since       1.0
 */
public class FavoriteThings extends BaseAdapter {

    // Class variables
    private Context mContext;
    private ArrayList<String> imagenames;

    // Initialize the class
    public FavoriteThings(Context context, ArrayList<String> images) {
        mContext = context;
        imagenames = images;
    }

    // Get the number of files that we will show
    @Override
    public int getCount() {
        return imagenames.size();
    }

    // Returns the current position in the string array
    @Override
    public String getItem(int position) {
        return imagenames.get(position);
    }

    // Returns the ID of the item in the array which is also it's position
    @Override
    public long getItemId(int position) {
        return position;
    }

    // Returns a shortened version of the filename
    // Used to build the text underneath each Favorite Thing
    public String getItemName( int position ){
        String longFileName = imagenames.get(position);
        int index = longFileName.lastIndexOf("/");
        String shortFileName = longFileName.substring( index + 1 );
        String finalName = shortFileName.substring(0, shortFileName.lastIndexOf(".") );
        return finalName;
    }

    public void removeItem( int position ){
        String fileName = getItemName(position) + ".jpg";
        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
        File myDir = new File(root + "/favorite_things");
        File finalFile = new File( myDir, fileName );
        finalFile.delete();
        remove(position);
    }

    public void remove( int position ){
        imagenames.remove(position);
        this.notifyDataSetChanged();
    }

    // Builds the view for the Favorite Things
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // https://stackoverflow.com/questions/35158652/how-to-add-textview-inside-a-gridview-filled-with-imageview
        LinearLayout linearlayout;
        if( convertView == null ){

            // Set up some options for our bitmap factory so that we don't run out of memory so quickly
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            options.inSampleSize = 2;
            options.inJustDecodeBounds = false;
            options.inTempStorage = new byte[16 * 1024];

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

            // Turn the images into thumbnails to view
            Bitmap b = BitmapFactory.decodeFile(imagenames.get(position), options );
            // Resize the bitmap
            Bitmap resizedBitmap = Bitmap.createScaledBitmap(b, 960, 730, false);
            imageView.setImageBitmap(resizedBitmap);

            // Position the items
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setLayoutParams(new GridView.LayoutParams(300, 300));

            linearlayout.addView(imageView);

            // set up the text view
            TextView imageViewName = new TextView(mContext);
            imageViewName.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            imageViewName.setText(getItemName( position ) );
            imageViewName.setGravity(Gravity.CENTER);

            linearlayout.addView(imageViewName);

            // Clear out our bitmaps
            b.recycle();

        } else {
            linearlayout = (LinearLayout) convertView;
        }

        // Return everything
        return linearlayout;
    }

}
