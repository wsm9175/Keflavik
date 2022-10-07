package com.example.keflavik.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.keflavik.R;
import com.example.keflavik.model.CityWorkDetail;
import com.example.keflavik.model.CityWorkTitle;

import java.util.ArrayList;

public class WorkCityTitleRecyclerAdapter extends RecyclerView.Adapter<WorkCityTitleRecyclerAdapter.ViewHolder>{

    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    private Context mContext;
    private ArrayList<CityWorkTitle> mList;


    public WorkCityTitleRecyclerAdapter(Context mContext, ArrayList<CityWorkTitle> mList){
        this.mContext = mContext;
        this.mList = mList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView workCityTitleView;
        CheckBox visibilityCheck;
        LinearLayout workCityDetailRecyclerViewLayout;
        private RecyclerView subRecyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            workCityTitleView = itemView.findViewById(R.id.workCityTitleTxt);
            subRecyclerView = itemView.findViewById(R.id.workCityDetailRecyclerView);

            //일하는 동네 이름 디테일을 보여주는 레이아웃
            workCityDetailRecyclerViewLayout = itemView.findViewById(R.id.workCityDetailRecyclerViewLayout);

            //상세 리스트 보여주는 체크 버튼
            visibilityCheck = itemView.findViewById(R.id.cityWorkListVisibilityCheckChBtn);
            visibilityCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (visibilityCheck.isChecked()){
                        workCityDetailRecyclerViewLayout.setVisibility(View.VISIBLE);
                    }else{
                        workCityDetailRecyclerViewLayout.setVisibility(View.GONE);
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_work_city_title, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.workCityTitleView.setText(mList.get(position).getAdressTitle()+"("+mList.get(position).getCount()+")");

        //서브에서 사용할 리니어 레이아웃
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext.getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        //여기서는 포지션에 맞는 리스트를 가져와야 한다
        //그래서 포지션에 있는 리스트를 리사이클러뷰에 넣어주기.........ㄴ아래서 아래서 아래서

        //자식 어답터 설정
        WorkCityTitleDetailRecyclerAdapter subItemAdapter = new WorkCityTitleDetailRecyclerAdapter(mContext, mList.get(position).getCityWorkDetailList());
        holder.subRecyclerView.setAdapter(subItemAdapter);
        holder.subRecyclerView.setLayoutManager(linearLayoutManager);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


}
