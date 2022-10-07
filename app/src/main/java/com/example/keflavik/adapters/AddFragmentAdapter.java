package com.example.keflavik.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.keflavik.R;
import com.example.keflavik.pragments.AddGuestAdressFragment;
import com.example.keflavik.pragments.AddWorkFragment;
import com.example.keflavik.view.MainActivity;

public class AddFragmentAdapter extends RecyclerView.Adapter<AddFragmentAdapter.ViewHolder> {
    private MainActivity mContext;
    private Context context;

    public AddFragmentAdapter(MainActivity mContext) {
        this.mContext = mContext;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RadioGroup radioGroup;
        RadioButton apartmentBtn;
        RadioButton guestAdressBtn;
        RadioButton workBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            radioGroup = itemView.findViewById(R.id.radioGroup);
            apartmentBtn = itemView.findViewById(R.id.apartmentBtn);
            guestAdressBtn = itemView.findViewById(R.id.guestAdressBtn);
            workBtn = itemView.findViewById(R.id.workBtn);

            //AddFragment addFragment = (MainActivity) context.
            //WorkApartmentFragment.WorkApartmentSington workApartmentFragment = new WorkApartmentFragment.WorkApartmentSington();

            //changFragment(workApartmentFragment.getWorkApartmentFragment(), WorkApartmentFragment.class.getSimpleName());

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {

                }
            });
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


/*    public void changFragment(Fragment fragment, String tag) {
        Fragment findFragment = mContext.getSupportFragmentManager().findFragmentByTag(tag);

        mContext.getSupportFragmentManager().getFragments().forEach(fragment1 ->
                mContext.getSupportFragmentManager().beginTransaction().hide(fragment1).commitAllowingStateLoss());


        if (findFragment == null) {
            mContext.getSupportFragmentManager().beginTransaction().add(R.id.addLinearLayout, fragment, tag).commitAllowingStateLoss();
        } else {
            mContext.getSupportFragmentManager().beginTransaction().show(findFragment).commitAllowingStateLoss();
        }
    }*/



    public static class WorkGuestAdressSington{
        private AddGuestAdressFragment workGuestAdressFragment;

        public AddGuestAdressFragment getWorkGuestAdressFragment() {
            if(workGuestAdressFragment == null){
                workGuestAdressFragment = new AddGuestAdressFragment();
            }
            return workGuestAdressFragment;
        }

    }



    public static class WorkWorkSington{
        private AddWorkFragment workWorkFragment;

        public AddWorkFragment getWorkWorkFragment() {
            if(workWorkFragment == null){
                workWorkFragment = new AddWorkFragment();
            }
            return workWorkFragment;
        }

    }

}
