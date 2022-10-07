package com.example.keflavik.pragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.keflavik.R;
import com.example.keflavik.adapters.MyScheduleCalendarRecyclerAdapter;
import com.example.keflavik.adapters.MyScheduleWorkListRecyclerViewAdapter;
import com.example.keflavik.adapters.MyYearMonthPickerDialog;
import com.example.keflavik.databinding.FragmentMyScheduleBinding;
import com.example.keflavik.model.WorkList;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;



public class MyScheduleFragment extends BaseFragment{


    private FragmentMyScheduleBinding binding;

    // 저장된 날짜
    private String[] StorageCalendar = new String[42];

    // 현재날짜
    GregorianCalendar cal;

    private static String TAG = "달력 문제";

    //달력 월!! 변환 창
    Button btnYearMonthPicker;

    //임시 일 목록 객체
    ArrayList<WorkList> workList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_schedule, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        calendar();

        workList.add(new WorkList("시공",true, "미세방충망", "과천힐스테이트 000동 000호","오전 00:00","결제청구"));
        workList.add(new WorkList("실측",true, "방충망", "과천힐스테이트 1동 1호","오후 12:12","???"));
        Log.d("문제 마이스케줄에서","몇개인지 확인"+workList.size());

        RecyclerView workListView = binding.workListRecyclerView;
        MyScheduleWorkListRecyclerViewAdapter workListAdapter = new MyScheduleWorkListRecyclerViewAdapter(mContext, workList);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext.getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        workListView.setLayoutManager(linearLayoutManager);
        workListView.setAdapter(workListAdapter);




    }


    protected void calendar() {
        LinearLayout calendarLayout = binding.calendarLayout;
        // Calendar 데이터 세팅
        cal = new GregorianCalendar();
        CalendarSetting(cal);

        //캘린더 이벤트
        calendarEvent();
    }

    public void calendarEvent(){
        // 월 변경 이벤트
        btnYearMonthPicker = binding.monthChangeTxt;

        btnYearMonthPicker.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                MyYearMonthPickerDialog pd = new MyYearMonthPickerDialog();
                pd.setListener(d);
                pd.show(getActivity().getSupportFragmentManager(), "YearMonthPickerTest");
            }
        });
    }

    //달력 이벤트 리스너
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
            cal.set(cal.get(Calendar.YEAR), monthOfYear-1, 1);

            CalendarSetting(cal);
            binding.monthChangeTxt.setText(monthOfYear+"월");
            RecyclerViewCreate();
            Log.d("YearMonthPickerTest", "year = " + year + ", month = " + monthOfYear + ", day = " + dayOfMonth);
        }
    };



    // 캘린더 날짜 데이터 세팅
    protected void CalendarSetting(GregorianCalendar cal) {
        // 현재 날짜의 첫번째 1일
        GregorianCalendar calendar = new GregorianCalendar(
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                1,0,0,0);

        //캘린더 상단 월 표시
        binding.monthChangeTxt.setText(calendar.get(Calendar.MONTH)+1+"월");

        // 만약 3일이 수요일이다 하면 값이 3이 반환되는데 여기서 -1를 해야 빈공간을 셀수있다.
        int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        // 한달의 최대일 그 이후의 빈공간을 만들기 위해서 사용한다.
        int max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH) - 1;

        for (int i = 0; i < StorageCalendar.length; i++) {
            if (i < week) { // 저번달의 끝의 일수을 설정
                StorageCalendar[i] = "";//Integer.toString(prevCalendar.getActualMaximum(Calendar.DAY_OF_MONTH) - week + i + 1)+"?";
            } else if (i > (max + week)) { // 이번달 끝이후의 일수를 설정
                //StorageCalendar[i] = Integer.toString(i - (max + week));

            } else { // 이번달 일수
                StorageCalendar[i] = " " + (i - week + 1) + " ";
            }
        }

        RecyclerViewCreate();
    }


    // RecyclerView 생성
    protected void RecyclerViewCreate() {
        // Recycler Calendar 생성
        //xml에서 리사이클러 가져옴
        RecyclerView calendarView = binding.calendarRecyclerView;
        //어답터 가져옴
        MyScheduleCalendarRecyclerAdapter calendarAdapter = new MyScheduleCalendarRecyclerAdapter(mContext.getApplicationContext(), StorageCalendar);

        GridLayoutManager layoutManager = new GridLayoutManager(mContext.getApplicationContext(), 7);
        calendarView.setLayoutManager(layoutManager);
        //리사이클러에 어답터 넣어줌
        calendarView.setAdapter(calendarAdapter);
    }









    public static class MySchedulrSington{
        private MyScheduleFragment myScheduleFragment;

        public MyScheduleFragment getMyScheduleFragment() {
            if(myScheduleFragment == null){
                myScheduleFragment = new MyScheduleFragment();
            }
            return myScheduleFragment;
        }

    }


}
























// 버튼 이벤트 등록
/*    View.OnClickListener imageBtnClickEvent = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.prevBtn:
                    cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) - 1, 1);
                    Log.e(TAG, "이전달");
                    break;
                case R.id.nextBtn:
                    cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, 1);
                    Log.e(TAG, "다음달");
                    break;
            }

            CalendarSetting(cal);

            TextView timeTextView = binding.timeTextView;
            timeTextView.setText((cal.get(Calendar.MONTH) + 1) + "월");
        }
    };*/