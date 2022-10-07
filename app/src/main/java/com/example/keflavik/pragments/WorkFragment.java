package com.example.keflavik.pragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.keflavik.R;
import com.example.keflavik.adapters.WorkCityNameButtonRecyclerAdapter;
import com.example.keflavik.adapters.WorkCityTitleRecyclerAdapter;
import com.example.keflavik.databinding.FragmentWorkBinding;
import com.example.keflavik.model.CityWorkDetail;
import com.example.keflavik.model.CityWorkDetailList;
import com.example.keflavik.model.CityWorkTitle;

import java.util.ArrayList;

public class WorkFragment extends BaseFragment{


    private FragmentWorkBinding binding;


    String cityName[] = {"서울시", "성남시","하남시","과천시", "파주시","의왕시"};
    ArrayList<CityWorkTitle> CityTitleName = new ArrayList<>();//아파트 이름 타이틀과 갯수


    //동호수를 받아와서 리스트에 넣고 아래서 Title에 넣어 한번에 보내줌
    ArrayList<CityWorkDetail> cityWorkDetailList= new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_work,container, false) ;
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //도시명 리사이클러뷰
        RecyclerView cityNameButtonView = binding.cityNameButtonRecyclerView;
        WorkCityNameButtonRecyclerAdapter mCityAdapter = new WorkCityNameButtonRecyclerAdapter(mContext, cityName);

        GridLayoutManager layoutManager = new GridLayoutManager(mContext.getApplicationContext(), 4);
        binding.cityNameButtonRecyclerView.setAdapter(mCityAdapter);
        cityNameButtonView.setLayoutManager(layoutManager);
        cityNameButtonView.setAdapter(mCityAdapter);
        //스크롤 비활성화
        //binding.cityNameButtonRecyclerView.suppressLayout(true);


        //상세주소들을 받아서 모아 저장하고
        cityWorkDetailList.add(new CityWorkDetail("동작협성휴포레", "1호"));
        cityWorkDetailList.add(new CityWorkDetail("동작협성휴포레", "2호"));
        cityWorkDetailList.add(new CityWorkDetail("동작협성휴포레", "3호"));
        cityWorkDetailList.add(new CityWorkDetail("동작협성휴포레", "4호"));
        cityWorkDetailList.add(new CityWorkDetail("동작협성휴포레", "5호"));

        //타이틀 개수 주소들을 저장해서 사용할거다
        CityTitleName.add(new CityWorkTitle("동작협성휴포레", 5, cityWorkDetailList));


        //xml에서 리사이클러뷰 ID가져와서 넣고
        RecyclerView cityNameTitleView = binding.workCityTitleRecyclerView;
        //어답터 가져오고
        WorkCityTitleRecyclerAdapter mCityNameTitleAdapter = new WorkCityTitleRecyclerAdapter(mContext, CityTitleName);
        //리니어 레이아웃 하나 만들고
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext.getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        cityNameTitleView.setLayoutManager(linearLayoutManager);
        cityNameTitleView.setAdapter(mCityNameTitleAdapter);
    }


    public static class WorkFragmentSingleton{
        private WorkFragment myScheduleFragment;

        public WorkFragment getWorkFragment() {
            if(myScheduleFragment == null){
                myScheduleFragment = new WorkFragment();
            }
            return myScheduleFragment;
        }

    }
}
