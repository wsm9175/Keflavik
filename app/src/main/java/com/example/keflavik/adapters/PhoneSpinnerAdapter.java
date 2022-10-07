package com.example.keflavik.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.List;

public class PhoneSpinnerAdapter extends ArrayAdapter<String[]>{

    Context mContext;
    int resId;
    List<String[]> mList;

    public PhoneSpinnerAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }
}
