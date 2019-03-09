package com.group2.myfavoritethings;

// Imports
import android.content.Intent;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.util.ArrayList;

/**
 * @author      Jared Sulzdorf
 * @version     1.0
 * @since       1.0
 */
public class MyPhotosMain extends AppCompatActivity {

    // Class variables
    private FloatingActionButton addNewFavorite;
    private TextView initialMessage;
    String backupPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() + "/favorite_things";
    String name;
    GridView gridview;
    ArrayList<String> imagenames = new ArrayList<>();

    // Load the view and check to see if there are any favorite things stored in our favorite_things folder.
    // If there are, they are shown and we have a different page header, if there aren't we don't.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            readfile();
        }catch(Exception e)
        {

        }
        setContentView(R.layout.my_photos_main);
        addNewFavorite = findViewById(R.id.addNewFavorite);
        addNewFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewFave(v);
            }
        });
        initialMessage = findViewById(R.id.initialMessage);
        gridview= findViewById(R.id.gridview);
        gridview.setAdapter(new FavoriteThings(this, imagenames));

        // If there are any images in the favorite_things folder then we should change the default string
        if( imagenames.size() > 0 ){
            initialMessage.setText("Favorite Things");
        }
    }

    // If the user clicks on the floating button, then move them to that AddPhotos screen
    public void addNewFave( View v ) {
        Toast.makeText(this, "Add New Favorite Thing", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, AddPhotos.class);
        startActivity(intent);
    }

    // Load all of the available images into our view
    public void readfile()
    {
        File yourDir = new File(backupPath);
        for (File f : yourDir.listFiles()) {
            if (f.isFile())
                name = f.getName();
            imagenames.add( backupPath+"/"+name);

        }
        setContentView(R.layout.my_photos_main);
    }
}

