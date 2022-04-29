package com.example.gymsystem.booksystem;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymsystem.MainActivity;
import com.example.gymsystem.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import okhttp3.OkHttpClient;

public class BookListsAdapter extends RecyclerView.Adapter <RecyclerView.ViewHolder>{
    @NonNull
    private Context mContext;
    private OnItemClickListener mListener;
    private String mDate;
    ArrayList<String> mFacility =new ArrayList<String>();
    //private List<String> list;

    public BookListsAdapter(Context context , OnItemClickListener listener , ArrayList<String> facility, String date){
        this.mContext = context;
        this.mListener = listener;
        this.mFacility = facility;
        this.mDate = date;


    }

    @Override
    public  RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LinearViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.fragment_bookitem,parent,false));
    }

    @Override
    //通过getItemViewType的返回值来选择具体的item显示
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
            for (int x = 0;x<10;x++){
                if (position == x){
                    ((LinearViewHolder)holder).name.setText(mDate);
                }
            }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(position);
                Snackbar.make(v ,"click" + position +mDate,
                        Snackbar.LENGTH_SHORT)
                        .setDuration(2000)
                        .show();
                Intent intent = null;
                intent = new Intent(mContext, FacilityActivity.class);
                intent.putExtra("itemdate",mDate);
                mContext.startActivity(intent);

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
        private TextView capacity;
        private TextView location;
        private ImageView image;
        private TextView ifOpen;


        public LinearViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.itemname_bookitem);
            image = itemView.findViewById(R.id.itemimage_bookitem);
            capacity = itemView.findViewById(R.id.capacity_bookitem);
            location = itemView.findViewById(R.id.itemlocation_bookitem);
            ifOpen = itemView.findViewById(R.id.ifOpen);
        }
    }

    //接口
    public interface  OnItemClickListener{
        void onClick(int pos);
    }

}
