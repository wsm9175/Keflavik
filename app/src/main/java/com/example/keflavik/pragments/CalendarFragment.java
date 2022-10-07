package com.example.keflavik.pragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.keflavik.R;
import com.example.keflavik.adapters.CalendarCalendarRecyclerAdapter;
import com.example.keflavik.adapters.MyScheduleCalendarRecyclerAdapter;
import com.example.keflavik.adapters.MyYearMonthPickerDialog;
import com.example.keflavik.databinding.FragmentCalendarBinding;
import com.example.keflavik.model.WorkList;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarFragment extends BaseFragment{

    private FragmentCalendarBinding binding;


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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_calendar, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        calendar();
    }

    // RecyclerView 생성
    protected void RecyclerViewCreate() {

        //캘린더 어답터
/*        RecyclerView calendarView = binding.calendarRecyclerView;
        CalendarCalendarRecyclerAdapter calendarAdapter = new CalendarCalendarRecyclerAdapter(mContext.getApplicationContext(), StorageCalendar);
        GridLayoutManager layoutManager = new GridLayoutManager(mContext.getApplicationContext(), 7);
        calendarView.setLayoutManager(layoutManager);
        calendarView.setAdapter(calendarAdapter);*/

    }









    //캘린더 부분@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    //캘린더 부분@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    public void calendar(){
        // Calendar 데이터 세팅
        cal = new GregorianCalendar();
        CalendarSetting(cal);
        RecyclerViewCreate();

        //캘린더 이벤트
        calendarEvent();
    }

    public void calendarEvent(){
        // 월 선택 텍스트
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

        binding.monthChangeTxt.setText(calendar.get(Calendar.MONTH)+1+"월");

        // 저번달의 첫번째 1일
        GregorianCalendar prevCalendar = new GregorianCalendar(
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH) - 1,
                1,0,0,0 );

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
    //캘린더 부분@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    //캘린더 부분@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@












    public static class CalendarSington{
        private CalendarFragment calendarFragment;
        public CalendarFragment getCalendarFragment(){
            if(calendarFragment == null){
                calendarFragment = new CalendarFragment();
            }
            return calendarFragment;
        }
    }

}
