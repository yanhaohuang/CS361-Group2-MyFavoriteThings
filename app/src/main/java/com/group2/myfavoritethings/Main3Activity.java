package com.group2.myfavoritethings;

import android.content.Intent;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {

    private FloatingActionButton addNewFavorite;
    private TextView initialMessage;
    String backupPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() + "/saved_images";
    String name;
    GridView gridview;
    ArrayList<String> imagenames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            readfile();
        }catch(Exception e)
        {

        }
        setContentView(R.layout.activity_main3);
        addNewFavorite = findViewById(R.id.addNewFavorite);
        addNewFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewFave();
            }
        });
        initialMessage = findViewById(R.id.initialMessage);
        gridview= findViewById(R.id.gridview);
        gridview.setAdapter(new FavoriteThings(this, imagenames));
        if( imagenames.size() > 0 ){
            initialMessage.setText("");
        }
    }

    public void addNewFave() {
        Toast.makeText(this, "Add New Favorite Thing", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Main4Activity.class);
        startActivity(intent);
    }

    public void readfile()
    {
        //File sdCardRoot = Environment.getExternalStorageDirectory();
        File yourDir = new File(backupPath);
        int i=0;
        for (File f : yourDir.listFiles()) {
            if (f.isFile())
                name = f.getName();
            imagenames.add( backupPath+"/"+name);
            Log.i("Favorite Things", "imagenames["+i+"]= "+name);
            i++;

            // make something with the name
        }
    }
}

