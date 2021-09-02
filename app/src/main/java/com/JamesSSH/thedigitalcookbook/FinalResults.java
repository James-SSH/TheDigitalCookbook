package com.JamesSSH.thedigitalcookbook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class FinalResults extends ListActivity {
    String[] names, ImageURLs;
    int[] IDs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_results);
        ListView listView = findViewById(android.R.id.list);
        Intent intent = getIntent();
        names = intent.getStringArrayExtra("RESULTS");
        ImageURLs = intent.getStringArrayExtra("IMAGEURLS");
        IDs = intent.getIntArrayExtra("IDs");
        RecipeList recipeList = new RecipeList(this, names, IDs, ImageURLs);
        listView.setAdapter(recipeList);


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
             public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                 Toast.makeText(getApplicationContext(), "ID IS: " + IDs[position], Toast.LENGTH_SHORT).show();
                 return false;
           }
         });

    }
}