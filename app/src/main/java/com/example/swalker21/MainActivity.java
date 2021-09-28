package com.example.swalker21;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    List<String> items;

    Button btnAdd;
    EditText etItem;
    RecyclerView rvitems;
    itemsAadpter itemsAadpter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btnAdd = findViewById(R.id.button);
        etItem = findViewById(R.id.etitem);
        rvitems = findViewById(R.id.rvitems);


        loadItems();

       itemsAadpter.OnLonGClickListner onLonGClickListner =  new itemsAadpter.OnLonGClickListner(){
            @Override
            public void onItemLongClicked(int position) {
                items.remove(position);
                itemsAadpter.notifyItemRemoved(position);
                Toast.makeText(getApplicationContext(),"item was removed", Toast.LENGTH_SHORT).show();
                saveItems();


            }
       };

       itemsAadpter = new itemsAadpter(items, onLonGClickListner);
       rvitems.setAdapter(itemsAadpter);
       rvitems.setLayoutManager(new LinearLayoutManager(this ));

       btnAdd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String todoItem = etItem.getText().toString();
               items.add(todoItem);
               itemsAadpter.notifyItemInserted(items.size()-1);
               etItem.setText("");
               Toast.makeText(getApplicationContext(), "item was added", Toast.LENGTH_SHORT).show();
               saveItems();
           }
       });


    }
    private File getDataFile() {
        return new File(getFilesDir(), "data.txt");


    }
    private void loadItems(){
        try {
            items = new ArrayList<>(org.apache.commons.io.FileUtils.readLines(getDataFile(), Charset.defaultCharset()));
        } catch (IOException e) {
            Log.e("MainAcitivity", "Error reading Item", e);
            items = new ArrayList<>();
        }
    }

    private void saveItems() {
        try {
            FileUtils.writeLines(getDataFile(), items);
        } catch (IOException e) {
            Log.e("MainAcitivity", "Error writing Item", e);
        }
    }
}