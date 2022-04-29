package com.example.gymsystem.usersystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.gymsystem.MainActivity;
import com.example.gymsystem.R;
import com.google.android.material.snackbar.Snackbar;

public class UserinformationActivity extends AppCompatActivity {
    private Spinner gender;
    private ImageView backBtn;
    private EditText editusername;
    private TextView phone;
    private TextView username;
    private EditText descrption;
    private Button saveBtn;

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
        setContentView(R.layout.activity_userinformation);

        username = findViewById(R.id.username_userinformation);
        editusername = findViewById(R.id.editusername_userinformation);
        descrption = findViewById(R.id.descrption_userinformation);
        phone = findViewById(R.id.phone_userinformation);
        saveBtn = findViewById(R.id.btn_userinformation);

        editusername.setText("User001");
        descrption.setText("我听不懂你在说什么，因为我只是一只小羊。");
        phone.setText("111 1111 1111");


        backBtn = findViewById(R.id.back_userinformation);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                intent = new Intent(UserinformationActivity.this, MainActivity.class);
                intent.putExtra("id",3);
                startActivity(intent);
            }
        });


        gender = findViewById(R.id.gender_userinformation);
        ArrayAdapter<CharSequence> timeadapter = ArrayAdapter.createFromResource(UserinformationActivity.this,
                R.array.gender_array, android.R.layout.simple_spinner_item);
        timeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(timeadapter);
        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapter, View view, int position, long id) {
                //获取选择的项的值
                String sInfo=adapter.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username.setText(editusername.getText());

                Snackbar.make(saveBtn, "Save Successfully", Snackbar.LENGTH_SHORT)
                        .setBackgroundTintList(ColorStateList.valueOf(0xFF3BB28D))
                        .setDuration(2000)
                        .show();
            }
        });

    }
}