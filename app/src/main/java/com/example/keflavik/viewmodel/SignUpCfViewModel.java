package com.example.keflavik.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Toast;

import com.example.keflavik.databinding.ActivitySignUpCfBinding;
import com.example.keflavik.model.SignUpCfDatabase;
import com.example.keflavik.datas.UserData;
import com.example.keflavik.datas.GlobalData;
import com.example.keflavik.view.SignUpImpActivity;
import com.google.gson.JsonObject;

public class SignUpCfViewModel {
    private SignUpCfDatabase database;
    private Context mContext;
    private ActivitySignUpCfBinding binding;
    private UserData user;

    //타이머
    private CountDownTimer certifyCountTimer;
    private CountDownTimer buttonCountTimer;

    int minute = 5;
    int second = 00;
    int second2 = 10;
    boolean phone5Minute = false; //인증번호가 가면 5분동안만 true
    boolean phoneDupCheck = false;//전화번호 중복 체크
    boolean phoneCheck = false;//인증번호 확인 후 true

    boolean customDupCheck;//인증번호 확인 후 true

    int phoneCheckCount = 1;

    String certificationNumber;

    public SignUpCfViewModel(Context mContext, ActivitySignUpCfBinding binding, SignUpCfDatabase database) {
        this.database = database;
        this.mContext = mContext;
        this.binding = binding;
        database.setOnDatabaseListener(getDatabaseListener());
    }


    public SignUpListener getDatabaseListener() {
        return new SignUpListener() {
            @Override
            public void onSuccess(String b) {
                //인증 성공
                if (b.equals("false")) {//false 가 없는번호임(이렇게 해야 인증번호가 온다)
                    phoneCheckCount++;

                    Log.d("문제 제발...", phoneCheckCount + "이고 Boolean은?" + (phoneCheckCount % 3 == 1) + "");
                    //무지막지한 인증요청을 방지하기 위한 것
                    if (phoneCheckCount % 3 == 1) {
                        //3번쨰 들어오는 경우 요청버튼의 카운터를 실행시킨다.
                        startButtonCount();
                    } else {
                        //인증요청을 보낸 후  성공시 타이머를 시작시킨다
                        phoneDupCheck = true;
                        Toast.makeText(mContext, "인증번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                        //인증번호 받는 것
                        //database.certification(binding.phoneNumEdt.getText().toString());

                        //임시적으로 성공했다 처리 성공 번호 123456
                        customDupCheck = true;
                        startCertifyTimer();
                        certificationNumber = "123456";

                        Log.d("문제", "중복검사 성공");
                    }
                } else {
                    phoneDupCheck = false;
                    Toast.makeText(mContext, "이미 가입된 전화번호 입니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailed(String t) {

            }

            @Override
            public void onCertificationSuccess(String b) {
                Log.d("문제 인증번호 받은 것", b);
                customDupCheck = true;
                startCertifyTimer();

                certificationNumber = b;
            }

            @Override
            public void onCertificationFailed() {
                //인증요청이 실패함
            }
        };
    }


    public void phoneCheck() {
        //서버에서 인증을 보내면 그때부터 카운트하고 이 시간안에 인증버튼을 눌러야함
        phoneDupCheck();//중복 확인
    }

    //휴대폰 중복체크 and 인증하기
    public boolean phoneDupCheck() {
        JsonObject object = new JsonObject();
        Log.d("", binding.phoneNumEdt.getText().toString());
        object.addProperty("phoneNumber", binding.phoneNumEdt.getText().toString());
        //여기서 서버랑 연결해서 중복 검사 하기
        database.dupCheck(object);

        return false;
    }


    //인증번호 확인
    public void phoneCertificationCheck() {
        Log.d("인증번호 확인", certificationNumber + ", " + binding.checkNumEdt.getText().toString());

        if (certificationNumber.equals(binding.checkNumEdt.getText().toString())) {
            certifyCountTimer.cancel();// 타이머 끄기
            binding.phoneCheck.setEnabled(false);
            binding.phoneNumEdt.setEnabled(false);
            phoneDupCheck = true;
            phoneCheck = true;
        } else {
            Toast.makeText(mContext, "잘못된 인증번호입니다.", Toast.LENGTH_SHORT).show();
        }
    }

    //회원가입을 위한 다음 버튼
    public void singUp() {
        Log.d("문제 이름?", binding.nameEdt.getText().toString());
        Log.d("문제 번호?", binding.phoneNumEdt.getText().toString());
        if (binding.nameEdt.getText().length() < 2) {
            Toast.makeText(mContext, "2자 이상입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        } else if (!binding.radioManBtn.isChecked() && !binding.radioWomanBtn.isChecked()) {
            Toast.makeText(mContext, "성별을 체크해주세요.", Toast.LENGTH_SHORT).show();
            return;
        } else if (binding.birthEdt.length() != 8) {
            Toast.makeText(mContext, "생년월일을 형식에 맞게 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        } else if (!phoneCheck) {
            Toast.makeText(mContext, "휴대전화 인증을 해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (phoneCheck) {
            Toast.makeText(mContext, "우선 확인", Toast.LENGTH_SHORT).show();

            //넘어가기 전에 글로벌 데이터로 저장해두기
            GlobalData gUser = new GlobalData();
            user = gUser.getGlobalUser();


            user.setName(binding.nameEdt.getText().toString());//이름 임시 저장

            Log.d("유저 문제 이름", user.getName());
            user.setPhoneNumber(binding.phoneNumEdt.getText().toString());//전화번호 임시저장


            Intent myIntent = new Intent(mContext, SignUpImpActivity.class);
            mContext.startActivity(myIntent);
        }
    }


    private void startCertifyTimer() {
        if (customDupCheck) {

            //버튼 클릭시 시간 초기화
            minute = 5;
            minute = 0; //테스트 시에는 0분부터 시작
            second = 00;
            second = 30;

            if (certifyCountTimer != null) {
                //돌아가져있을시 지원준다
                certifyCountTimer.cancel();
                certifyCountTimer = null;

            }

            certifyCountTimer = new CountDownTimer(5 * 1000, 1000) {
                public void onTick(long millisUntilFinished) {

                    phone5Minute = true;
                    if (second != 0) {
                        second--;
                    } else if (minute != 0) {
                        // 1분 = 60초
                        second = 60;
                        second--;
                        minute--;
                    }

                    binding.minuteTxt.setText("0" + Integer.toString(minute));
                    if (second <= 9) {
                        binding.secondTxt.setText("0" + second);
                    } else {
                        binding.secondTxt.setText(Integer.toString(second));
                    }
                }

                @Override
                public void onFinish() {
                    phone5Minute = false;
                    phoneCheck = true;
                    Toast.makeText(mContext, "시간 종료!", Toast.LENGTH_SHORT).show();
                }


            };


            certifyCountTimer.start(); //CountDownTimer 실행
            //인증 완료시 이거 해주자
            //CDT.cancel();// 타이머

        } else {
            if (certifyCountTimer != null) {
                certifyCountTimer.cancel();// 타이머
                certifyCountTimer = null;
            }
        }
    }


    private void startButtonCount() {
        //if (true) {

        if (certifyCountTimer != null) {
            certifyCountTimer.cancel();// 타이머
            certifyCountTimer = null;
        }

        customDupCheck = false;

        //@@@@
        second2 = 10;
        buttonCountTimer = new CountDownTimer(10 * 1000, 1000) {
            public void onTick(long millisUntilFinished) {

                if (second2 != 0) {
                    second2--;
                }

                if (second2 <= 9) {
                    binding.phoneCheck.setEnabled(false);
                    binding.phoneCheck.setText("0" + second2 + "초");
                    Toast.makeText(mContext, "10초 후 인증이 가능합니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFinish() {
                binding.phoneCheck.setEnabled(true);
                binding.phoneCheck.setText("인증요청");
            }
        };

        buttonCountTimer.start(); //CountDownTimer 실행
        //@@@@

        Toast.makeText(mContext, "여기서는 버튼 막고...", Toast.LENGTH_SHORT).show();

    }

    public interface SignUpListener {
        void onSuccess(String b);

        void onFailed(String t);

        void onCertificationSuccess(String b);

        void onCertificationFailed();
    }
}