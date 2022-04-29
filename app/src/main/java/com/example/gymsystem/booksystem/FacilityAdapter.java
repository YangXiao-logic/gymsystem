package com.example.gymsystem.booksystem;

import android.content.Context;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymsystem.R;
import com.example.gymsystem.eventbus.FirstEvent;
import com.google.android.material.snackbar.Snackbar;


import java.util.ArrayList;

import cn.com.cesgroup.numpickerview.NumberPickerView;

public class FacilityAdapter extends RecyclerView.Adapter <RecyclerView.ViewHolder>{
    @NonNull
    private Context mContext;
    private OnItemClickListener mListener;
    private String mDate;
    ArrayList<String> mFacility =new ArrayList<String>();
    //private List<String> list;

    public FacilityAdapter(Context context , OnItemClickListener listener , ArrayList<String> facility, String date){
        this.mContext = context;
        this.mListener = listener;
        this.mFacility = facility;
        this.mDate = date;
    }

    @Override
    public  RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LinearViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.fragment_facilityitem,parent,false));
    }

    @Override
    //通过getItemViewType的返回值来选择具体的item显示
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ((LinearViewHolder)holder).name.setText(mDate);
        ((LinearViewHolder)holder).date.setText(mDate);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class LinearViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView date;
        private TextView cdate;
        private TextView csites;
        private Button btn_reserve;
        private Spinner time_spinner;
        private NumberPickerView sites;

        public LinearViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.name_facilityitem);
            date = itemView.findViewById(R.id.date_facilityitem);
            cdate = itemView.findViewById(R.id.choosentime);
            csites = itemView.findViewById(R.id.choosensites);

            sites = itemView.findViewById(R.id.sites_facility);
            sites.setMaxValue(10) //最大输入值，也就是限量，默认无限大
                    .setMinDefaultNum(1)  // 最小限定量
                    .setCurrentNum(1); // 当前数量
            sites.setOnInputNumberListener(new NumberPickerView.OnInputNumberListener() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                    csites.setText(String.valueOf(sites.getNumText()));
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    Log.d("MainActivity",editable.toString());
                }
            });


            btn_reserve = itemView.findViewById(R.id.reserve_facilityitem);
            btn_reserve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(btn_reserve, "reserve"+mDate,
                            Snackbar.LENGTH_SHORT)
                            .setDuration(2000)
                            .show();
                }
            });

            time_spinner = itemView.findViewById(R.id.time_spinner_facilityitem);
            ArrayAdapter<CharSequence> timeadapter = ArrayAdapter.createFromResource(mContext,
                    R.array.time_array, android.R.layout.simple_spinner_item);
            timeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            time_spinner.setAdapter(timeadapter);
            time_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                @Override
                public void onItemSelected(AdapterView<?> adapter,View view,int position,long id) {
                    //获取选择的项的值
                    String sInfo=adapter.getItemAtPosition(position).toString();
                    cdate.setText(sInfo);
                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                }
            });
        }
    }

    //接口
    public interface  OnItemClickListener{
        void onClick(int pos);
    }

}