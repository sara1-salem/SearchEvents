package com.example.searchevents;

import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    Database mydb;
    Button viewAllbtn;

    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.my_list);
        List<String> mylist = new ArrayList<>();
        mylist.add("Eraser");

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mylist);
        listView.setAdapter(arrayAdapter);
        mydb = new Database(this);

        viewAllbtn = (Button)findViewById(R.id.button_viewAll);
        viewAll();

    }
    public void viewAll(){
        viewAllbtn.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        Cursor res = mydb.getAllData();
                        if(res.getCount() == 0){
                            //no data available/show message
                            ShowMessage("Error","No Data Found");
                            return;
                        }
                        StringBuffer Buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            Buffer.append("TITLE :"+ res.getString(0)+"\n");
                            Buffer.append("TYPE :"+ res.getString(1)+"\n");
                            Buffer.append("TIME :"+ res.getString(2)+"\n");
                            Buffer.append("DATE :"+ res.getString(3)+"\n");
                            Buffer.append("DESCRIPTION :"+ res.getString(4)+"\n");
                            Buffer.append("LOCATION :"+ res.getString(5)+"\n");
                            Buffer.append("COORDINATORID :"+ res.getString(6)+"\n\n");
                        }
                        //show all
                        ShowMessage("Data", Buffer.toString());
                    }
                }

        );
    }

    public void ShowMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.search_icon);


        button_viewAll.setOnClickListener(this);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() { //
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                arrayAdapter.getFilter().filter(s);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

}