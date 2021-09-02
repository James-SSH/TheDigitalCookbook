package com.JamesSSH.thedigitalcookbook;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.LruCache;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

public class APIInstance {
    private static APIInstance instance;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private static Context ctx;

    private APIInstance(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();

        imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap>
                    cache = new LruCache<String, Bitmap>(20);

            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });
    }

        public static synchronized APIInstance getInstance(Context context){
            if (instance == null){
                instance = new APIInstance(context);
            }
            return instance;
        }
        public RequestQueue getRequestQueue() {
            if (requestQueue == null) {
                requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
            }
            return requestQueue;
        }
        public <T> void addToRequestQueue(Request<T> req){
            getRequestQueue().add(req);
        }

        public ImageLoader getImageLoader() {
            return imageLoader;
        }
    }




//    Intent intent = getIntent();
//        try {
//        String data = intent.getStringExtra("QUERY BODY");
//        String query = URLEncoder.encode(data, "UTF-8");
//        String FullURL = BaseURL + query;
//        RequestQueue queue = Volley.newRequestQueue(this);
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, FullURL, null, response -> {
//            JSONObject result = new JSONObject((Map) response);
//            try {
//                StringBuilder Final = null;
//                int results = result.getInt("number");
//                for (Iterator<String> iter = result.keys(); iter.hasNext();){
//                    String key = iter.next();
//                    assert false;
//                    Final.append(key);
//                    Final.append("\n\n");
//                }
//                textView.setText(Final);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//        }, error -> {
//            // TODO: HANDLE ERROR
//        });
//        queue.add(jsonObjectRequest);
//    } catch (
//    UnsupportedEncodingException e) {
//        e.printStackTrace();
//    }