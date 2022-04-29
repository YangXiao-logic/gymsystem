package com.example.gymsystem.usersystem;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gymsystem.R;
import com.example.gymsystem.loginsystem.LoginActivity;
import com.google.android.material.snackbar.Snackbar;

public class UserFragment extends Fragment {

    private ImageView userinformation;
    private Button btn_member;
    private Button btn_logout;
    private TextView address;
    private TextView security;
    private TextView bookrecord;
    private TextView paymentrecord;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn_member = view.findViewById(R.id.btn_member);
        btn_logout = view.findViewById(R.id.btn_logout);
        address = view.findViewById(R.id.address_user);
        security = view.findViewById(R.id.security_user);
        bookrecord = view.findViewById(R.id.bookrecord_user);
        paymentrecord = view.findViewById(R.id.paymentrecord_user);
        userinformation = view.findViewById(R.id.userinformation);

        userinformation.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = null;
                intent = new Intent(getActivity(), UserinformationActivity.class);
                startActivity(intent);
            }
        });

        address.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = null;
                intent = new Intent(getActivity(), AddressActivity.class);
                startActivity(intent);
            }
        });

        security.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = null;
                intent = new Intent(getActivity(), AccountActivity.class);
                startActivity(intent);
            }
        });

        bookrecord.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = null;
                intent = new Intent(getActivity(), BookrecordActivity.class);
                startActivity(intent);
            }
        });

        paymentrecord.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = null;
                intent = new Intent(getActivity(), PaymentrecordActivity.class);
                startActivity(intent);
            }
        });

        btn_member.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = null;
                intent = new Intent(getActivity(), MemberActivity.class);
                startActivity(intent);
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Snackbar.make(btn_logout, "Log out Successfully", Snackbar.LENGTH_SHORT)
                        .setBackgroundTintList(ColorStateList.valueOf(0xFF3BB28D))
                        .setDuration(2000)
                        .show();
                Intent intent = null;
                intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}