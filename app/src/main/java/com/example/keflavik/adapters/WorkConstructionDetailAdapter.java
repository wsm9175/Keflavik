package com.example.keflavik.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.keflavik.R;
import com.example.keflavik.model.Construction;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class WorkConstructionDetailAdapter extends RecyclerView.Adapter<WorkConstructionDetailAdapter.viewHolder> {
    Context context;
    ArrayList<Construction> data;

    public WorkConstructionDetailAdapter(Context context, ArrayList<Construction> data){
        super();
        this.context = context;
        this.data = data;
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView constructionData;            //날짜
        TextView constructionTitle;            //타이틀
        TextView constructionSubContent;            //타이틀에 대한 내용
        Button constructionCheckButton;            //내용 버튼
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            constructionData = itemView.findViewById(R.id.constructionDataTxt);
            constructionTitle = itemView.findViewById(R.id.constructionTitleTxt);
            constructionSubContent = itemView.findViewById(R.id.constructionSubContentTxt);
            constructionCheckButton = itemView.findViewById(R.id.constructionCheckBtn);
        }
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_construction_detail, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        //여기서 완료 여부에 따른 선 색상 변경해주기
        //세로 선 색상(마지막은 사이즈 확인 후 비저블로 가리기)
        holder.constructionData.setText(data.get(position).getConstructionData());//게터 세터에서 날짜 형식에 맞게 가져오기
        holder.constructionTitle.setText(data.get(position).getConstructionTitle());
        holder.constructionSubContent.setText(data.get(position).getConstructionSubContent());
        holder.constructionCheckButton.setText(data.get(position).getConstructionBtn());
        //버튼에 맞는 글자랑 색상 등등 수정하기

    }

    @Override
    public int getItemCount() {
        return data.size();
    }





}
