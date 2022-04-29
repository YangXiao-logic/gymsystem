package com.example.gymsystem.homesystem;

import androidx.recyclerview.widget.RecyclerView;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.gymsystem.R;
import com.example.gymsystem.booksystem.FacilityActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;


public class HomeAdapter extends RecyclerView.Adapter <RecyclerView.ViewHolder>{
    @NonNull
    private Context mContext;
    private OnItemClickListener mListener;
    //private List<String> list;

    public HomeAdapter(Context context , OnItemClickListener listener){
        this.mContext = context;
        this.mListener = listener;
    }

    @Override
    public  RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new LinearViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.fragment_youritemsite,parent,false));
    }

    @Override
    //通过getItemViewType的返回值来选择具体的item显示
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        for (int x = 0; x < 10; x = x+1){
            if (position == x){
                ((LinearViewHolder)holder).name.setText(String.valueOf(position));
            }
        }
        /*if(position == 0){
            ((LinearViewHolder)holder).name.setText("NO");
        }else {
            ((LinearViewHolder)holder).name.setText("Yes");
        }*/



        //如果是直接用viewholder的话，是不能用test view的
        //将点击事件放到外面
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
        return 10;
    }

    class LinearViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView time;
        private TextView location;
        private TextView number;
        private ImageView image;
        private Button cancel;
        private Button check;


        public LinearViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.itemname);
            image = itemView.findViewById(R.id.itemimage);
            time = itemView.findViewById(R.id.itemtime);
            location = itemView.findViewById(R.id.itemlocation);
            number = itemView.findViewById(R.id.itemnumber);
            cancel = itemView.findViewById(R.id.btn_cancel);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new MaterialAlertDialogBuilder(mContext)
                            .setTitle("Cancel confirmation")
                            .setMessage("Are you sure you want to cancel this appointment?")
                            .setNegativeButton("Confirm", null)
                            .setPositiveButton("Close", null)
                            .show();
                }
            });

            check = itemView.findViewById(R.id.btn_check);
            check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = null;
                    intent = new Intent(mContext, FacilityActivity.class);
                    intent.putExtra("itemdate","mDate");
                    mContext.startActivity(intent);
                }
            });
        }
    }


    //接口
    public interface  OnItemClickListener{
        void onClick(int pos);
    }
}

