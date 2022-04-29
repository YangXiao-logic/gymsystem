package com.example.gymsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.gymsystem.booksystem.BookFragment;
import com.example.gymsystem.homesystem.HomeFragment;
import com.example.gymsystem.usersystem.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //private RecyclerView homeitems;
    private FrameLayout mainfragment;
    private HomeFragment homefragment;
    private BookFragment bookfragment;
    private UserFragment userfragment;
    private FragmentManager mSupportFragmentManager;
    private FragmentTransaction mTransaction;
    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_main);


        mSupportFragmentManager = getSupportFragmentManager();
        mainfragment = findViewById(R.id.fragment_main);
        mTransaction = mSupportFragmentManager.beginTransaction();
        homefragment = new HomeFragment();
        mFragments.add(homefragment);

        hideOthersFragment(homefragment, true);

        BottomNavigationView view = findViewById(R.id.bottom_navigation_home);

        //底部导航栏监听点击事件
        //警告我照样用
        view.setOnNavigationItemSelectedListener((menuItem)->{
            switch (menuItem.getItemId()){
                case R.id.homeicon:
                    Toast.makeText(this, "点击了第" + 1 + "个", Toast.LENGTH_SHORT).show();
                    hideOthersFragment(homefragment, false);
                    break;
                case R.id.bookicon:
                    Toast.makeText(this, "点击了第" + 2 + "个", Toast.LENGTH_SHORT).show();
                    if (bookfragment == null) {
                        bookfragment = new BookFragment();
                        mFragments.add(bookfragment);
                        hideOthersFragment(bookfragment, true);
                    } else {
                        hideOthersFragment(bookfragment, false);
                    }
                    break;
                case R.id.usericon:
                    Toast.makeText(this, "点击了第" + 4 + "个", Toast.LENGTH_SHORT).show();
                    if (userfragment == null) {
                        userfragment = new UserFragment();
                        mFragments.add(userfragment);
                        hideOthersFragment(userfragment, true);
                    } else {
                        hideOthersFragment(userfragment, false);
                    }
                    break;
            }
            return true;
        });
        //显示book界面
        int id = getIntent().getIntExtra("id", 0);
        switch (id){
            case 2:
                view.setSelectedItemId(R.id.bookicon);
                break;
            case 3:
                view.setSelectedItemId(R.id.usericon);
                break;
        }
    }


    private void hideOthersFragment(Fragment showFragment, boolean add) {
        mTransaction = mSupportFragmentManager.beginTransaction();
        if (add) {
            mTransaction.add(R.id.fragment_main, showFragment);
        }

        for (Fragment fragment : mFragments) {
            if (showFragment.equals(fragment)) {
                mTransaction.show(fragment);
            } else {
                mTransaction.hide(fragment);
            }
        }
        mTransaction.commit();
    }


}