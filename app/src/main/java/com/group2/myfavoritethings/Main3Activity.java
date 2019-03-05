package com.group2.myfavoritethings;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    private FloatingActionButton addNewFavorite;
    private TextView initialMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        addNewFavorite = findViewById(R.id.addNewFavorite);
        addNewFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewFave();
            }
        });
        initialMessage = findViewById(R.id.initialMessage);
        Intent intent = getIntent();
        if( intent.hasExtra("Image") ){
            // Clear out the message saying we don't have anything
            Bundle extras = intent.getExtras();
            initialMessage.setText( extras.toString() );
        } else {
            Log.i("Image", "come on" );
        }
    }

    public void addNewFave() {
        Toast.makeText(this, "Add New Favorite Thing", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Main4Activity.class);
        startActivity(intent);
    }
}
