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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gymsystem.MainActivity;
import com.example.gymsystem.R;
import com.google.android.material.snackbar.Snackbar;

public class QverificationActivity extends AppCompatActivity {

    private ImageView back_qver;
    private Button btn_qver;
    private TextView resend_qver;

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
        setContentView(R.layout.activity_qverification);

        back_qver = findViewById(R.id.back_qver);
        back_qver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                intent = new Intent(QverificationActivity.this,QuickloginActivity.class);
                startActivity(intent);
            }
        });

        btn_qver = findViewById(R.id.btn_qver);
        btn_qver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                intent = new Intent(QverificationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        resend_qver = findViewById(R.id.resend_qver);
        resend_qver.setOnClickListener(this::onClick);
    }

    private void onClick(View view) {
        Snackbar.make(resend_qver, "The new verification code has been sent to your mobile phone",
                Snackbar.LENGTH_SHORT)
                .setDuration(2000)
                .show();
    }
}