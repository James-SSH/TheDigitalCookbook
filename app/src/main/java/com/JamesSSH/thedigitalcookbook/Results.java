package com.JamesSSH.thedigitalcookbook;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.JamesSSH.thedigitalcookbook.databinding.ActivityResultsBinding;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class Results extends AppCompatActivity {
    final String BaseURL = "https://api.spoonacular.com/recipes/complexSearch/?apiKey=34f84cb9ed024162a3495420c09aced4&query=";
    String data, query, FullURL, title, ImageURL;
    JSONObject jsonObject, food;
    JSONArray foods;
    int id;
    ArrayList<String> resultsList = new ArrayList<>();
    ArrayList<String> ImagesURL = new ArrayList<>();
    ArrayList<Integer> idList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RequestQueue queue = APIInstance.getInstance(this.getApplicationContext()).getRequestQueue();
        super.onCreate(savedInstanceState);
        //com.JamesSSH.thedigitalcookbook.databinding.ActivityResultsBinding binding = ActivityResultsBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());
        final ListView listView = findViewById(R.id.l_ResultsList);

        Intent intent = getIntent();
        String data = intent.getStringExtra("QUERY BODY");
        try {
            query = URLEncoder.encode(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        FullURL = BaseURL + query;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, FullURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "We got a request!", Toast.LENGTH_SHORT).show();
                try {
                    jsonObject = new JSONObject(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                foods = jsonObject.getJSONArray("results");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < foods.length() - 1; i++){
                    try {
                        food = foods.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        id = food.getInt("id");
                        title = food.getString("title");
                        ImageURL = food.getString("image");
                        idList.add(id);
                        resultsList.add(title);
                        ImagesURL.add(ImageURL);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                String[] ImageURLs = ImagesURL.toArray(new String[0]);
                String[] results = resultsList.toArray(new String[0]);
                int[] IDs = idList.stream().mapToInt(i -> i).toArray();
                Intent intent = new Intent(getApplicationContext(), FinalResults.class);
                intent.putExtra("IMAGEURLS", ImageURLs);
                intent.putExtra("IDs", IDs);
                intent.putExtra("RESULTS", results);
                startActivity(intent);

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "404: FATAL ERROR, INTERNET OFF OR NOT AVALIABLE", Toast.LENGTH_LONG).show();
                    }
                });

        APIInstance.getInstance(this).addToRequestQueue(stringRequest);


//            RecipeList recipeList = new RecipeList(com.JamesSSH.thedigitalcookbook.Results.this, JSONHandler.getName(), JSONHandler.getID(), JSONHandler.getImageURL());
//            listView.setAdapter(recipeList);
//
//            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Toast.makeText(getApplicationContext(), "ID IS: " + idList.get(position), Toast.LENGTH_SHORT).show();
//                }
//            });

        }
}