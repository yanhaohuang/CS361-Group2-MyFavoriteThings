package com.group2.myfavoritethings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.support.design.widget.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private Button btn2;
    private Button btn3;
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTodoList();
            }
        });
        btn3 = findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity3();
            }
        });
        bottomNav = findViewById(R.id.navigation);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        break;
                    case R.id.navigation_todo:
                        Intent todo = new Intent(MainActivity.this,TodoListMain.class);
                        startActivity(todo);
                        break;
                    case R.id.navigation_photos:
                        Intent faveThings = new Intent(MainActivity.this,MyPhotosMain.class);
                        startActivity(faveThings);
                        break;
                }
                return false;
            }
        });
    }

    public void openTodoList() {
        Intent intent = new Intent(this, TodoListMain.class);
        startActivity(intent);
    }
    public void openActivity3() {
        Intent intent = new Intent(this, MyPhotosMain.class);
        startActivity(intent);
    }

}
