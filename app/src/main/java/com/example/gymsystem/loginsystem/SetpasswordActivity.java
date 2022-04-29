package com.example.gymsystem.loginsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.gymsystem.MainActivity;
import com.example.gymsystem.R;
import com.google.android.material.snackbar.Snackbar;

public class SetpasswordActivity extends AppCompatActivity {

    private ImageView back_spassword;
    private Button btn_spassword;
    private EditText password_spassword;
    private EditText repassword_spassword;

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
        setContentView(R.layout.activity_setpassword);

        back_spassword = findViewById(R.id.back_spassword);
        back_spassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                intent = new Intent(SetpasswordActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        password_spassword = findViewById(R.id. password_spassword_text);
        repassword_spassword = findViewById(R.id.repassword_spassword_text);
        btn_spassword = findViewById(R.id.btn_spassword);
        btn_spassword.setOnClickListener(this::onClick);
    }

    private void onClick(View view) {
        String password = password_spassword.getText().toString();
        String repassword = repassword_spassword.getText().toString();
        Intent intent = null;
        if(password.equals("")||repassword.equals("")){
            Snackbar.make(btn_spassword, "The input must not be empty, please try again.", Snackbar.LENGTH_SHORT)
                    .setDuration(2000)
                    .show();
        }
        else if (!password.equals(repassword)){
            Snackbar.make(btn_spassword, "The passwords are inconsistent. Please confirm again.",
                    Snackbar.LENGTH_SHORT)
                    .setDuration(2000)
                    .show();
        }
        else if (password.equals(repassword)){
            intent = new Intent(SetpasswordActivity.this, MainActivity.class);
            startActivity(intent);
            Snackbar.make(btn_spassword, "Register successfully.",
                    Snackbar.LENGTH_SHORT)
                    .setDuration(2000)
                    .show();
        }
        else {
            Snackbar.make(btn_spassword, "Unknown error, please try again later.",
                    Snackbar.LENGTH_SHORT)
                    .setDuration(2000)
                    .show();
        }
    }
}