package com.example.keflavik.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.keflavik.R;
import com.example.keflavik.model.WorkList;
import com.example.keflavik.view.MainActivity;
import com.example.keflavik.view.MyScheduleImageUploadActivity;

import java.util.ArrayList;

public class MyScheduleWorkListRecyclerViewAdapter extends RecyclerView.Adapter<MyScheduleWorkListRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<WorkList> mList;

    public MyScheduleWorkListRecyclerViewAdapter(Context mContext, ArrayList<WorkList> mList){

        this.mContext = mContext;
        this.mList = mList;

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView workSuccessTxt;// 시공 / 실측
        TextView workTitleTxt;// 일 종류? 이름?
        TextView workAdressTxt;// 주소
        TextView workTimeTxt;//시간
        TextView workStateTxt;//결제/완성?

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            workSuccessTxt = itemView.findViewById(R.id.workSuccessTxt);
            workTitleTxt = itemView.findViewById(R.id.workTitleTxt);
            workAdressTxt = itemView.findViewById(R.id.workAdressTxt);
            workTimeTxt = itemView.findViewById(R.id.workTimeTxt);
            workStateTxt = itemView.findViewById(R.id.workStateTxt);

            //결제청구 완료하기 등등 이벤트 처리
            workStateTxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    new AlertDialog.Builder(mContext)
                            .setTitle("작업완료")
                            .setMessage("\'실측\' 일정을 완료하기 위해서는 \'실측서\' 를 업로드 해야 합니다.")
                            .setPositiveButton("업로드 하기", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent myIntent = new Intent((MainActivity)mContext, MyScheduleImageUploadActivity.class);
                                    mContext.startActivity(myIntent);
                                }
                            })
                            .setNegativeButton("취소", null)
                            .show();
                }
            });

        }

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_my_schedule_work_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("문제 position", position+" : "+mList.get(position).getWorkSuccess());
        holder.workSuccessTxt.setText(mList.get(position).getWorkSuccess());
        holder.workSuccessTxt.setSelected(mList.get(position).getWorkBoolean());
        holder.workTitleTxt.setText(mList.get(position).getWorkTitle());
        holder.workAdressTxt.setText(mList.get(position).getWorkAdress());
        holder.workTimeTxt.setText(mList.get(position).getWorkTime());
        holder.workStateTxt.setText(mList.get(position).getWorkState());
        //holder.bind(m[position]);
    }



    @Override
    public int getItemCount() {
        return mList.size();
    }

}
