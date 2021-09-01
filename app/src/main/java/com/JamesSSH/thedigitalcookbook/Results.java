package com.JamesSSH.thedigitalcookbook;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.JamesSSH.thedigitalcookbook.databinding.ActivityResultsBinding;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class Results extends AppCompatActivity {
    String BaseURL = "https://api.spoonacular.com/recipes/?apiKey=34f84cb9ed024162a3495420c09aced4&query=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.JamesSSH.thedigitalcookbook.databinding.ActivityResultsBinding binding = ActivityResultsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        final TextView textView = findViewById(R.id.t_FinalResults);
        Intent intent = getIntent();
        try {
            String data = intent.getStringExtra("QUERY BODY");
            String query = URLEncoder.encode(data, "UTF-8");
            String FullURL = BaseURL + query;
            RequestQueue queue = Volley.newRequestQueue(this);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, FullURL, null, response -> {
                JSONObject result = new JSONObject((Map) response);
                try {
                    StringBuilder Final = null;
                    int results = result.getInt("number");
                    for (Iterator<String> iter = result.keys(); iter.hasNext();){
                        String key = iter.next();
                        assert false;
                        Final.append(key);
                        Final.append("\n\n");
                    }
                    textView.setText(Final);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }, error -> {
                // TODO: HANDLE ERROR
            });
            queue.add(jsonObjectRequest);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}