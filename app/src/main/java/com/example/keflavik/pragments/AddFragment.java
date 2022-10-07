package com.example.keflavik.pragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.keflavik.R;
import com.example.keflavik.databinding.FragmentAddBinding;

public class AddFragment extends BaseFragment{
    private FragmentAddBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpEvent();
    }


    public void setUpEvent(){
        //워크 프레그먼트 싱글턴
        AddApartmentFragment.AddApartmentSington addApartmentSington = new AddApartmentFragment.AddApartmentSington();
        AddGuestAdressFragment.AddGuestAdressSington addGuestAdressSington = new AddGuestAdressFragment.AddGuestAdressSington();
        AddWorkFragment.AddWorkSington addWorkSington = new AddWorkFragment.AddWorkSington();

        changFragment(addApartmentSington.getWorkApartmentFragment(), AddApartmentFragment.class.getSimpleName());

        //여기 아래서 버튼 이벤트 달아줄거임
        binding.apartmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changFragment(addApartmentSington.getWorkApartmentFragment(), AddApartmentFragment.class.getSimpleName());
            }
        });
        binding.guestAdressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changFragment(addGuestAdressSington.getAddGuestAdressFragment(), AddGuestAdressFragment.class.getSimpleName());
            }
        });
        binding.workBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changFragment(addWorkSington.getAddWorkFragment(), AddWorkFragment.class.getSimpleName());
            }
        });




    }







    //여기는 아파트/고객주소/작업 등등을 바꿔주는 것
    public void changFragment(Object fragment, String tag) {
        //Fragment findFragment = fragment.getParentFragment().getChildFragmentManager().findFragmentByTag(tag);
        Fragment findFragment = getChildFragmentManager().findFragmentByTag(tag);

        getChildFragmentManager().getFragments().forEach(fragment1 ->
                getChildFragmentManager().beginTransaction().hide(fragment1).commitAllowingStateLoss());


        if (findFragment == null) {
            getChildFragmentManager().beginTransaction().add(R.id.addLinearLayout, (Fragment) fragment, tag).commitAllowingStateLoss();
        } else {
            getChildFragmentManager().beginTransaction().show(findFragment).commitAllowingStateLoss();
        }
    }


    public static class AddSington{
        private AddFragment addFragment;
        public AddFragment getAddFragment(){
            if(addFragment == null){
                addFragment = new AddFragment();
            }
            return addFragment;
        }
    }

}
