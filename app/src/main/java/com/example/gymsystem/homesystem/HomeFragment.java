package com.example.gymsystem.homesystem;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gymsystem.R;
import com.google.android.material.snackbar.Snackbar;

public class HomeFragment extends Fragment {

    private RecyclerView homeitems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeitems = view.findViewById(R.id.homeitems);
        homeitems.setLayoutManager(new LinearLayoutManager(HomeFragment.this.getContext()));
        homeitems.setAdapter(new HomeAdapter(HomeFragment.this.getContext(),
                new HomeAdapter.OnItemClickListener(){
                    @Override
                    public void onClick(int pos) {
                        Snackbar.make(homeitems, "click"+pos,
                                Snackbar.LENGTH_SHORT)
                                .setDuration(2000)
                                .show();
                    }
                }

        ));
    }
}