package com.example.keflavik.view;

import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.keflavik.R;
import com.example.keflavik.databinding.ActivityLoginBinding;
import com.example.keflavik.model.LoginDatabase;
import com.example.keflavik.viewmodel.LoginViewModel;

public class LoginActivity extends BaseActivity {

    private ActivityLoginBinding binding;
    LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        titleTxt.setVisibility(View.VISIBLE);
        titleTxt.setText("로그인");

        viewModel= new LoginViewModel(mContext, binding, LoginDatabase.getInstance(this));
        binding.setViewModel(viewModel);

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.login();
            }
        });



        binding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(mContext, SignUpCfActivity.class);
                //Intent myIntent = new Intent(mContext, SignUpImpActivity2.class);
                startActivity(myIntent);
            }
        });

    }
}