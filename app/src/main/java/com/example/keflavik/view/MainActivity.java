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


        //???????????? ?????? ???
        actionBarLayout.setBackgroundColor(mContext.getResources().getColor(R.color.yellow_main));
        titleLeftTxt.setVisibility(View.VISIBLE);
        titleLeftTxt.setText("?????????");
        //?????? ??? ??? ?????????????????? ????????? ?????????
        binding.bottomNav.setSelectedItemId(R.id.center);
        binding.mySchedulrRadioBtn.setChecked(true);
        changFragment(mySchedulrSington.getMyScheduleFragment() , MyScheduleFragment.class.getSimpleName());




        //????????? ??????????????? ?????? ??????
        binding.bottomNav.getMenu().getItem(2).setEnabled(false);

        //???????????? ????????????
        binding.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            //??????
            public void checkOut(){
                Log.d("??????", "emf?");
                binding.nullBtn.setChecked(true);
                /*binding.mySchedulrRadioBtn.setChecked(false);
                binding.myCalendarRadioBtn.setChecked(false);*/
            }

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){


                    case R.id.work : {
                        Log.d("?????? ",WorkFragment.class.getSimpleName()+"");
                        checkOut();
                        changFragment(workSington.getWorkFragment(), WorkFragment.class.getSimpleName());

                        //?????? ???
                        actionBarLayout.setBackgroundColor(mContext.getResources().getColor(R.color.yellow_main));
                        titleLeftTxt.setText("?????????");

                        break;
                    }
                    //??????
                    case R.id.add: {
                        Log.d("?????? ?????????","");
                        checkOut();
                        changFragment(addSington.getAddFragment() , AddFragment.class.getSimpleName());
                        //?????? ???
                        actionBarLayout.setBackgroundColor(mContext.getResources().getColor(R.color.yellow_main));
                        titleLeftTxt.setText("????????????");
                        break;

                    }
                    //??????
                    case R.id.advice : {
                        checkOut();
                        changFragment(addSington.getAddFragment() , AddFragment.class.getSimpleName());

                        //?????? ???
                        actionBarLayout.setBackgroundColor(mContext.getResources().getColor(R.color.yellow_main));

                        break;

                    }
                    //?????????
                    case R.id.admin : {
                        checkOut();
                        //?????? ???
                        actionBarLayout.setBackgroundColor(mContext.getResources().getColor(R.color.yellow_main));
                        break;
                    }

                }
                return true;
            }
        });


        //???????????? ???????????? ????????? ????????? ??? ???????????? ??????????????? ?????? ??????
        //binding.bottomNav.setSelectedItemId(R.id.center);

        //????????? ??? ?????? ??????????????? ?????? ???????????? ??????????????? ??????????????? ?????? ????????? ???




        Animation bottomMenuCheck_true = AnimationUtils.loadAnimation(this, R.anim.button_slide_up);
        //bottomMenuCheck_true.setFillAfter(true);
        Animation bottomMenuCheck_false = AnimationUtils.loadAnimation(this, R.anim.button_slide_down);
        //bottomMenuCheck_false.setFillAfter(true);



        binding.animBottomMenuLinearLayout.setVisibility(View.INVISIBLE);//?????????

        //?????? ????????? ?????? ????????? ??????????????? ????????????
        binding.menuInvisibleChebox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(binding.menuInvisibleChebox.isChecked()){ //
                    binding.animBottomMenuLinearLayout.setVisibility(View.VISIBLE);//??????
                    binding.animBottomMenuLinearLayout.startAnimation(bottomMenuCheck_true);
                }else {//?????? ????????? ??????????????? ????????????
                    binding.animBottomMenuLinearLayout.setVisibility(View.INVISIBLE);//?????????
                    binding.animBottomMenuLinearLayout.startAnimation(bottomMenuCheck_false);

                }
            }
        });

        binding.mySchedulrRadioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("??????@@?? ?????????", binding.mySchedulrRadioBtn.isChecked()+"");
                Log.d("??????@@?? ?????????", binding.myCalendarRadioBtn.isChecked()+"");
                binding.bottomNav.setSelectedItemId(R.id.center);
                changFragment(mySchedulrSington.getMyScheduleFragment() , MyScheduleFragment.class.getSimpleName());
            }
        });



        binding.myCalendarRadioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.myCalendarRadioBtn.setChecked(true);
                Log.d("???????? ?????????", binding.mySchedulrRadioBtn.isChecked()+"");
                Log.d("???????? ?????????", binding.myCalendarRadioBtn.isChecked()+"");

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