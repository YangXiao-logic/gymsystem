package com.example.gymsystem.loginsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gymsystem.MainActivity;
import com.example.gymsystem.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class LoginActivity extends AppCompatActivity {
    private Button btn_login;
    private EditText phone_login;
    private EditText password_login;
    private TextView verifcarion_login;
    private TextView fpassword_login;
    private TextView register_login;
    private CheckBox agreement_login;
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
        setContentView(R.layout.activity_login);


        agreement_login = findViewById(R.id.agreement_login);
        agreement_login.setOnCheckedChangeListener(this::onCheckedChanged);
        btn_login = findViewById(R.id.btn_login);
        phone_login = findViewById(R.id.phone_login_text);
        password_login = findViewById(R.id.password_login_text);
        phone_login.setText("111 1111 1111");
        password_login.setText("1");
        btn_login.setOnClickListener(this::onClick);

        verifcarion_login = findViewById(R.id.verification_login);
        verifcarion_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                intent = new Intent(LoginActivity.this,QuickloginActivity.class);
                startActivity(intent);
            }
        });

        fpassword_login = findViewById(R.id.fpassword_login);
        fpassword_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                intent = new Intent(LoginActivity.this, FpasswordActivity.class);
                startActivity(intent);
            }
        });

        register_login = findViewById(R.id.register_login);
        register_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });


    }

    private void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        Boolean agree = agreement_login.isChecked();
        if (agree){
            new MaterialAlertDialogBuilder(this)
                    .setTitle("User agreement")
                    .setMessage("1.Users retain all rights to their information\n" +
                            "2.User privacy must be well protected\n......")
                    .setNegativeButton("Confirm", null)
                    .show();
        }
    }

    private void onClick(View v){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String phone = phone_login.getText().toString();
                    String password = password_login.getText().toString();
                    /*String json = "{\n" +
                            "  \"password\": \"123456\",\n" +
                            "  \"username\": \"admin\"\n" +
                            "}";

                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://192.168.143.167:8080/admin/login")
                            .post(RequestBody.create(MediaType.parse("application/json"),json))
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    System.out.println(responseData);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Snackbar.make(btn_login, "Internet connect succeed", Snackbar.LENGTH_SHORT)
                                    .setBackgroundTintList(ColorStateList.valueOf(0xFF3BB28D))
                                    .setDuration(2000)
                                    .show();
                        }
                    });*/
                    Boolean agree = agreement_login.isChecked();
                    Intent intent = null;

                    if (phone.equals("111 1111 1111")&&password.equals("1")&&agree){
                        intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        Snackbar.make(btn_login, "Login successfully", Snackbar.LENGTH_SHORT)
                                .setDuration(2000)
                                .show();
                    }else if (!agree){
                        Snackbar.make(btn_login, "You haven't agreed to the user agreement", Snackbar.LENGTH_SHORT)
                                .setBackgroundTintList(ColorStateList.valueOf(0xFF3BB28D))
                                .setDuration(2000)
                                .show();
                    }else{
                        Snackbar.make(btn_login, "Wrong password or unexisted phone number", Snackbar.LENGTH_SHORT)
                                .setBackgroundTintList(ColorStateList.valueOf(0xFF3BB28D))
                                .setDuration(2000)
                                .show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Snackbar.make(btn_login, "Internet connect failed", Snackbar.LENGTH_SHORT)
                                    .setBackgroundTintList(ColorStateList.valueOf(0xFF3BB28D))
                                    .setDuration(2000)
                                    .show();
                        }
                    });
                }
            }
        }).start();

    }


}