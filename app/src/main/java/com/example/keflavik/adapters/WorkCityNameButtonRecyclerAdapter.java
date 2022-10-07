package com.example.keflavik.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.keflavik.R;

public class WorkCityNameButtonRecyclerAdapter extends RecyclerView.Adapter<WorkCityNameButtonRecyclerAdapter.ViewHoler> {
    Context context;
    String[] cityName;

    public int mSelectedItem = -1;

    public WorkCityNameButtonRecyclerAdapter(Context context, String[] cityName){
        this.context = context;
        this.cityName = cityName;
    }

    public class ViewHoler extends RecyclerView.ViewHolder{
        RadioButton cityTextView;
        ViewHoler(View itemView){
            super(itemView);
            cityTextView = itemView.findViewById(R.id.cityRadioButton);
        }
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_city_name_button, parent, false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, @SuppressLint("RecyclerView") int position) {
        holder.cityTextView.setText(cityName[position]);

        holder.cityTextView.setChecked(position == mSelectedItem);


        holder.cityTextView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {

                mSelectedItem = position;



                Log.e("adapterPosition", position + "" ) ;
                notifyDataSetChanged();

            }
        });
        if(mSelectedItem != position){
            holder.cityTextView.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return cityName.length;
    }



}
