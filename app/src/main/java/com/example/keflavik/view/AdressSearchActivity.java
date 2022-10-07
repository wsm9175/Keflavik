package com.example.keflavik.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.keflavik.R;
import com.example.keflavik.adapters.SearchItemAdapter;
import com.example.keflavik.databinding.ActivityAdressSearchBinding;

import java.util.ArrayList;

public class AdressSearchActivity extends BaseActivity {
    private ActivityAdressSearchBinding binding;
    ArrayList<String> cityNameList = new ArrayList<>();//서버에서 정보를 받아서 여기에 저장할 것
    SearchItemAdapter searchAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_adress_search);

        search();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }


    private void search(){
        cityNameList.add("1");
        cityNameList.add("2");
        cityNameList.add("3");
        cityNameList.add("4");
        cityNameList.add("5");
        cityNameList.add("6");
        cityNameList.add("11");
        cityNameList.add("12");
        cityNameList.add("13");




        //서치뷰 ID
        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                //검색어를 입력하고 엔터를 누르게 되면 여기로 들어옴
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<String> filterCityNameList = new ArrayList<>();

                //리사이클러뷰에 해서 보여준다!
                RecyclerView searchViewRecycler = binding.searchContainsRecyclerView;
                SearchView searchView = binding.searchView;

                searchAdapter = new SearchItemAdapter(mContext,filterCityNameList, getListener());


                if(newText.equals("")|| newText.isEmpty()){
                    searchViewRecycler.setVisibility(View.GONE);
                    Log.d("여기","들어옴?");
                    return false;
                }

                for (int i = 0; i < cityNameList.size(); i++){
                    String name = cityNameList.get(i);

                    if(name.contains(newText)){
                        Log.d("리스트에 무엇?", name);
                        filterCityNameList.add(name); //값은 값이 있으면 필터를 통해 리스트에 넣고
                    }
                }

                Log.d("리스트에 무엇?", filterCityNameList.toString());
                Log.d("리스트에 무엇?", filterCityNameList.size()+"");


                searchViewRecycler.setVisibility(View.VISIBLE);
                //레이아웃
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext.getApplicationContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                //뷰 설정하기
                searchViewRecycler.setLayoutManager(linearLayoutManager);
                searchViewRecycler.setAdapter(searchAdapter);


                return true ;
            }
        });


        binding.addBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (binding.searchView.getQuery().length() >1){
                            Toast.makeText(getApplicationContext(), "검색어를 입력해주세요.", Toast.LENGTH_LONG).show();
                            return;
                        }

                        Intent myIntent = new Intent(mContext, MainActivity.class);
                        startActivity(myIntent);
                        finish();
                    }
                }
        );

    }

    public SearchInertface getListener(){
        return new SearchInertface() {
            @Override
            public void onItemSelected(String search) {
                Log.d("들어오니?", search);
                binding.searchView.setQuery(search, false);
                binding.searchContainsRecyclerView.setVisibility(View.GONE);
            }
        };
    }

    public interface SearchInertface {
        void onItemSelected(String search);
    }


}