package com.JamesSSH.thedigitalcookbook;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class RecipeList extends ArrayAdapter {
    private int[] id;
    private String[] Dish;
    private String[] ImageURL;
    private Activity context;

    public RecipeList(Activity context, String[] Dishes, int[] ID, String[] ImageURLS){
        super(context, R.layout.activity_results, Collections.singletonList(ID));
        this.context = context;
        this.Dish = Dishes;
        this.ImageURL = ImageURLS;
        this.id = ID;
    }

    @Override
    public View getView(int index, View convertView, ViewGroup parent){
        View row = convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if (convertView == null){
            row = inflater.inflate(R.layout.activity_final_results, null, true);
        }
        TextView textView = row.findViewById(R.id.Dish_Name);
        TextView textViewID = row.findViewById(R.id.t_ID);
        ImageView DishImage = row.findViewById(R.id.i_foodImage);

        textView.setText(Dish[index]);
        textViewID.setText(id[index]);
        DishImage.setImageResource(R.drawable.ic_menu_camera);
        return row;
    }

}
