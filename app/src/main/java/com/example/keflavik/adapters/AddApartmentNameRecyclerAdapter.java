package com.example.keflavik.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.keflavik.R;

public class AddApartmentNameRecyclerAdapter extends RecyclerView.Adapter<AddApartmentNameRecyclerAdapter.ViewHolder> {

    private Context mContext;
    private String[] mList;


    public AddApartmentNameRecyclerAdapter(Context context, String[] mList){
        this.mContext = context;
        this.mList = mList;

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView addApartmentListName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            addApartmentListName = itemView.findViewById(R.id.apartmentNameTxt);



        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_add_apartment_list_name, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.addApartmentListName.setText(mList[position]);


    }

    @Override
    public int getItemCount() {
        return mList.length;
    }

}
