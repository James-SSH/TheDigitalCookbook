package com.JamesSSH.thedigitalcookbook.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.JamesSSH.thedigitalcookbook.R;
import com.JamesSSH.thedigitalcookbook.Results;
import com.JamesSSH.thedigitalcookbook.databinding.FragmentHomeBinding;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class HomeFragment extends Fragment {
    Button button;
    String title = "";

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        button = root.findViewById(R.id.b_Search);
        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("InlinedApi")
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Results.class);
                TextInputEditText tiet = root.findViewById(R.id.et_Search);
                title = Objects.requireNonNull(tiet.getText()).toString();
                intent.putExtra("QUERY BODY", title);
                startActivity(intent);
            }
        });

        final TextInputEditText textInputEditText = binding.etSearch;

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}