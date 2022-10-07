package com.example.keflavik.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.keflavik.R;
import com.example.keflavik.adapters.MainViewPager2Adapter;
import com.example.keflavik.databinding.ActivityMainBinding;
import com.example.keflavik.pragments.AddFragment;
import com.example.keflavik.pragments.CalendarFragment;
import com.example.keflavik.pragments.MyScheduleFragment;
import com.example.keflavik.pragments.WorkFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends BaseActivity
{

    private ActivityMainBinding binding;
    private MainViewPager2Adapter mPagerAdapter;


    MyScheduleFragment.MySchedulrSington mySchedulrSington;
    WorkFragment.WorkFragmentSingleton workSington;
    AddFragment.AddSington addSington;
    CalendarFragment.CalendarSington calendarSington;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mPagerAdapter = new MainViewPager2Adapter(this);
//        binding.mainViewPager.setAdapter(mPagerAdapter);
//        binding.mainViewPager.setCurrentItem(1);






        setUpEvent();
    }





    void setUpEvent(){
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        mySchedulrSington = new MyScheduleFragment.MySchedulrSington();
        workSington = new WorkFragment.WorkFragmentSingleton();
        addSington = new AddFragment.AddSington();
        calendarSington = new CalendarFragment.CalendarSington();


        //처음화면 액션 바
        actionBarLayout.setBackgroundColor(mContext.getResources().getColor(R.color.yellow_main));
        titleLeftTxt.setVisibility(View.VISIBLE);
        titleLeftTxt.setText("투데이");
        //하단 뷰 중 마이스케줄에 노란색 띄우기
        binding.bottomNav.setSelectedItemId(R.id.center);
        binding.mySchedulrRadioBtn.setChecked(true);
        changFragment(mySchedulrSington.getMyScheduleFragment() , MyScheduleFragment.class.getSimpleName());




        //가운데 네비게이션 클릭 ㄴㄴ
        binding.bottomNav.getMenu().getItem(2).setEnabled(false);

        //뷰페이저 이벤트임
        binding.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            //작업
            public void checkOut(){
                Log.d("확인", "emf?");
                binding.nullBtn.setChecked(true);
                /*binding.mySchedulrRadioBtn.setChecked(false);
                binding.myCalendarRadioBtn.setChecked(false);*/
            }

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){


                    case R.id.work : {
                        Log.d("문제 ",WorkFragment.class.getSimpleName()+"");
                        checkOut();
                        changFragment(workSington.getWorkFragment(), WorkFragment.class.getSimpleName());

                        //액션 바
                        actionBarLayout.setBackgroundColor(mContext.getResources().getColor(R.color.yellow_main));
                        titleLeftTxt.setText("투데이");

                        break;
                    }
                    //생성
                    case R.id.add: {
                        Log.d("바로 나와라","");
                        checkOut();
                        changFragment(addSington.getAddFragment() , AddFragment.class.getSimpleName());
                        //액션 바
                        actionBarLayout.setBackgroundColor(mContext.getResources().getColor(R.color.yellow_main));
                        titleLeftTxt.setText("작업관리");
                        break;

                    }
                    //채팅
                    case R.id.advice : {
                        checkOut();
                        changFragment(addSington.getAddFragment() , AddFragment.class.getSimpleName());

                        //액션 바
                        actionBarLayout.setBackgroundColor(mContext.getResources().getColor(R.color.yellow_main));

                        break;

                    }
                    //관리자
                    case R.id.admin : {
                        checkOut();
                        //액션 바
                        actionBarLayout.setBackgroundColor(mContext.getResources().getColor(R.color.yellow_main));
                        break;
                    }

                }
                return true;
            }
        });


        //올라오는 메뉴안에 아이콘 선택시 그 아이콘이 선택되도록 하는 함수
        //binding.bottomNav.setSelectedItemId(R.id.center);

        //그리고 저 버튼 누를때마다 화면 올려주기 프레그먼트 컨테이너에 화면 올려줄 것




        Animation bottomMenuCheck_true = AnimationUtils.loadAnimation(this, R.anim.button_slide_up);
        //bottomMenuCheck_true.setFillAfter(true);
        Animation bottomMenuCheck_false = AnimationUtils.loadAnimation(this, R.anim.button_slide_down);
        //bottomMenuCheck_false.setFillAfter(true);



        binding.animBottomMenuLinearLayout.setVisibility(View.INVISIBLE);//안보여

        //체크 해제시 하단 메뉴바 올라오면서 보여주기
        binding.menuInvisibleChebox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(binding.menuInvisibleChebox.isChecked()){ //
                    binding.animBottomMenuLinearLayout.setVisibility(View.VISIBLE);//보여
                    binding.animBottomMenuLinearLayout.startAnimation(bottomMenuCheck_true);
                }else {//체크 해제시 보여주면서 올려주기
                    binding.animBottomMenuLinearLayout.setVisibility(View.INVISIBLE);//안보여
                    binding.animBottomMenuLinearLayout.startAnimation(bottomMenuCheck_false);

                }
            }
        });

        binding.mySchedulrRadioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("확인@@?? 스케줄", binding.mySchedulrRadioBtn.isChecked()+"");
                Log.d("확인@@?? 켈린더", binding.myCalendarRadioBtn.isChecked()+"");
                binding.bottomNav.setSelectedItemId(R.id.center);
                changFragment(mySchedulrSington.getMyScheduleFragment() , MyScheduleFragment.class.getSimpleName());
            }
        });



        binding.myCalendarRadioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.myCalendarRadioBtn.setChecked(true);
                Log.d("확인?? 스케줄", binding.mySchedulrRadioBtn.isChecked()+"");
                Log.d("확인?? 켈린더", binding.myCalendarRadioBtn.isChecked()+"");

                binding.bottomNav.setSelectedItemId(R.id.center);
                changFragment(calendarSington.getCalendarFragment() , CalendarFragment.class.getSimpleName());
            }
        });

    }





    public void changFragment(Fragment fragment, String tag){
        Fragment findFragment = getSupportFragmentManager().findFragmentByTag(tag);

        getSupportFragmentManager().getFragments().forEach( fragment1 ->
                getSupportFragmentManager().beginTransaction().hide(fragment1).commitAllowingStateLoss());


        if(findFragment == null){
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainerView ,fragment , tag).commitAllowingStateLoss();
        }else{
            getSupportFragmentManager().beginTransaction().show(findFragment).commitAllowingStateLoss();
        }
    }

}