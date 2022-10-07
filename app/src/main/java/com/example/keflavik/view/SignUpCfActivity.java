package com.example.keflavik.view;

import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.example.keflavik.R;
import com.example.keflavik.databinding.ActivitySignUpCfBinding;
import com.example.keflavik.model.SignUpCfDatabase;
import com.example.keflavik.viewmodel.LoginViewModel;
import com.example.keflavik.viewmodel.SignUpCfViewModel;

import java.lang.reflect.Array;

public class SignUpCfActivity extends BaseActivity {

    private ActivitySignUpCfBinding binding;
    SignUpCfViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up_cf);
        titleTxt.setText("회원가입");
        backImg.setVisibility(View.VISIBLE);

        //통신사 스피너
        ArrayAdapter<CharSequence>adapter =
                ArrayAdapter.createFromResource(this, R.array.phone, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(androidx.databinding.library.baseAdapters.R.layout.support_simple_spinner_dropdown_item);
        binding.phoneSpinner.setAdapter(adapter);

        //뷰 모델과 연결하는 것
        viewModel = new SignUpCfViewModel(mContext, binding, SignUpCfDatabase.getInstance(this));
        binding.setViewModel(viewModel);




        setUpEvent();
        setValues();
    }


    void setUpEvent(){
        //중복확인 인듯?
        binding.phoneCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.phoneCheck();
            }
        });

        //인증확인 버튼
        binding.checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.phoneCertificationCheck();
            }
        });

        //마지막 다음화면 넘어가는 이벤트
        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.singUp();

            }
        });
    }

    void setValues(){

        RadioButton checkBox = binding.radioManBtn;


    }


}