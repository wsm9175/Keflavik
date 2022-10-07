package com.example.keflavik.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.keflavik.R;
import com.example.keflavik.databinding.ActivitySingUpImpBinding;
import com.example.keflavik.model.SignUpImpDatabase;
import com.example.keflavik.viewmodel.SignUpImpViewModel;

public class SignUpImpActivity extends BaseActivity {

    private ActivitySingUpImpBinding binding;
    SignUpImpViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sing_up_imp);

        titleTxt.setText("회원가입");
        backImg.setVisibility(View.VISIBLE);


        //뷰 모델과 연결하는 것
        viewModel = new SignUpImpViewModel(mContext, binding, SignUpImpDatabase.getInstance(this));


        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.signUp();
                Intent myIntent = new Intent(mContext, SignUpImpActivity2.class);
                startActivity(myIntent);
            }
        });


        setValue();
    }

    void setValue(){
        //통신사 스피너
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.email, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(androidx.databinding.library.baseAdapters.R.layout.support_simple_spinner_dropdown_item);
        binding.emailSpinner.setAdapter(adapter);
    }
}