package com.example.keflavik.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.keflavik.R;
import com.example.keflavik.view.AdressSearchActivity;

import java.util.ArrayList;

public class SearchItemAdapter extends RecyclerView.Adapter<SearchItemAdapter.ViewHolder>{
    Context context;
    ArrayList<String> searchList;
    private AdressSearchActivity.SearchInertface mListener;


    public SearchItemAdapter(Context context, ArrayList searchList, AdressSearchActivity.SearchInertface mListener){
        this.context = context;
        this.searchList = searchList;
        this.mListener = mListener;
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView searchListTxt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            searchListTxt = itemView.findViewById(R.id.searchListTxt);
            searchListTxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemSelected(searchListTxt.getText().toString());
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_search_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.searchListTxt.setText(searchList.get(position));
    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }


}
