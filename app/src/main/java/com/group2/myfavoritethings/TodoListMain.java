package com.group2.myfavoritethings;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class TodoListMain extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private EditText itemET;
    private Button btn;
    private ListView itemsList;
    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_list_main);
        itemET = findViewById(R.id.item_edit_text);
        btn = findViewById(R.id.add_btn);
        itemsList = findViewById(R.id.items_list);
        items = helper.readData(this);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        itemsList.setAdapter(adapter);
        btn.setOnClickListener(this);
        itemsList.setOnItemClickListener(this);
        bottomNav = findViewById(R.id.navigation);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent home = new Intent(TodoListMain.this,MainActivity.class);
                        startActivity(home);
                        break;
                    case R.id.navigation_todo:
                        break;
                    case R.id.navigation_photos:
                        Intent faveThings = new Intent(TodoListMain.this,MyPhotosMain.class);
                        startActivity(faveThings);
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.add_btn:
                String itemEntered = itemET.getText().toString();
                adapter.add(itemEntered);
                itemET.setText("");
                helper.writeData(items, this);
                Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        items.remove(position);
        adapter.notifyDataSetChanged();
        helper.writeData(items, this);
        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
    }
}
