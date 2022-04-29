package com.example.gymsystem.booksystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.gymsystem.MainActivity;
import com.example.gymsystem.R;
import com.example.gymsystem.loginsystem.FpasswordActivity;
import com.example.gymsystem.loginsystem.LoginActivity;
import com.example.gymsystem.loginsystem.RverificationActivity;

import java.util.ArrayList;

public class FacilityActivity extends AppCompatActivity {

    private ImageView back_facility;
    private RecyclerView facilityitems;
    ArrayList<String> mfacility =new ArrayList<String>();
    private String nowDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_facility);

        nowDate = getIntent().getStringExtra("itemdate");
        back_facility = findViewById(R.id.back_facility);
        back_facility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                intent = new Intent(FacilityActivity.this, MainActivity.class);
                intent.putExtra("id",2);
                startActivity(intent);
            }
        });

        mfacility.add("1");
        mfacility.add("2");
        mfacility.add("3");
        mfacility.add("4");
        mfacility.add("5");
        mfacility.add("6");
        mfacility.add("7");
        facilityitems = findViewById(R.id.facilityitems);
        facilityitems.setLayoutManager(new LinearLayoutManager(FacilityActivity.this));
        facilityitems.setAdapter(new FacilityAdapter(FacilityActivity.this,
                new FacilityAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(int pos) { }
                }, mfacility, nowDate));
    }

}