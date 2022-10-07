package com.example.keflavik.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.keflavik.R;
import com.example.keflavik.model.CityWorkDetail;
import com.example.keflavik.view.MainActivity;
import com.example.keflavik.view.MyScheduleImageUploadActivity;
import com.example.keflavik.view.WorkDetailActivity;

import java.util.ArrayList;

public class WorkCityTitleDetailRecyclerAdapter extends RecyclerView.Adapter<WorkCityTitleDetailRecyclerAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<CityWorkDetail> mList;

    public WorkCityTitleDetailRecyclerAdapter(Context mContext, ArrayList<CityWorkDetail> mList){
        this.mContext = mContext;
        this.mList = mList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView workCityTitleViewDetail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            workCityTitleViewDetail = itemView.findViewById(R.id.workCiryTitleDetailTxt);

            workCityTitleViewDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent((MainActivity)mContext, WorkDetailActivity.class);
                    mContext.startActivity(myIntent);
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_work_city_title_datail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.workCityTitleViewDetail.setText(mList.get(position).getAdressTitle()+mList.get(position).getAdressSubTitle());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }



}
