package com.example.keflavik.pragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.keflavik.adapters.AddCityNameRecyclerAdapter;
import com.example.keflavik.adapters.AddApartmentNameRecyclerAdapter;
import com.example.keflavik.databinding.FragmentAddApartmentBinding;
import com.example.keflavik.view.AdressSearchActivity;

public class AddApartmentFragment extends BaseFragment{
    private FragmentAddApartmentBinding binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_apartment,container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpValue();
        setUpEvent();


    }


    public void setUpValue(){
        String cityName[] = {"서울시", "성남시","하남시","과천시", "파주시","의왕시"};
        //도시명
        RecyclerView cityNameTitleView = binding.cityNameButtonRecyclerView;
        AddCityNameRecyclerAdapter cityNameButtonAdapter = new AddCityNameRecyclerAdapter(mContext, cityName);
        GridLayoutManager layoutManager = new GridLayoutManager(mContext.getApplicationContext(), 4);
        cityNameTitleView.setLayoutManager(layoutManager);
        cityNameTitleView.setAdapter(cityNameButtonAdapter);
        //스크롤 비활성화
        //cityNameTitleView.suppressLayout(true);

        String apartMentName[] = {"OOO아파트","OOO아파트","OOO아파트"};
        //아래 아파트 단지 명
        RecyclerView apartmentListView = binding.workCityTitleRecyclerView;
        AddApartmentNameRecyclerAdapter addApartmentNameRecyclerAdapter = new AddApartmentNameRecyclerAdapter(mContext, apartMentName);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext.getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        apartmentListView.setAdapter(addApartmentNameRecyclerAdapter);
        apartmentListView.setLayoutManager(linearLayoutManager);
        apartmentListView.suppressLayout(true);

    }

    public void setUpEvent(){

        //추가버튼 클릭시 검색창 넘어가기
        binding.searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(mContext, AdressSearchActivity.class);
                mContext.startActivity(myIntent);
            }
        });

    }




    //이거 맞음
    public static class AddApartmentSington{
        private AddApartmentFragment workApartmentFragment;
        public AddApartmentFragment getWorkApartmentFragment() {
            if(workApartmentFragment == null){
                workApartmentFragment = new AddApartmentFragment();
            }
            return workApartmentFragment;
        }
    }
}
