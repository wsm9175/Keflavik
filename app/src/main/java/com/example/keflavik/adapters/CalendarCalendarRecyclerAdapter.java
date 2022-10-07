package com.example.keflavik.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.keflavik.R;

public class CalendarCalendarRecyclerAdapter extends RecyclerView.Adapter<CalendarCalendarRecyclerAdapter.viewHolder> {
    Context context;
    String[] data;

    public CalendarCalendarRecyclerAdapter(Context context, String[] data) {
        super();
        this.context = context;
        this.data = data;
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView dayTextView;

        public viewHolder(View itemView) {
            super(itemView);
            dayTextView = itemView.findViewById(R.id.day_recycler_calendar);


        }
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_calendar, parent, false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        holder.dayTextView.setText(data[position]);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }
}