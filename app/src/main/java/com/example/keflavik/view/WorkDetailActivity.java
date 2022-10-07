package com.example.keflavik.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.keflavik.R;
import com.example.keflavik.databinding.ActivityWorkDetailBinding;

public class WorkDetailActivity extends BaseActivity {

    private ActivityWorkDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_work_detail);
    }
}