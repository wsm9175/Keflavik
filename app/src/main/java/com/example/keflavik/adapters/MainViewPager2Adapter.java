package com.example.keflavik.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.keflavik.pragments.MyScheduleFragment;
import com.example.keflavik.pragments.WorkFragment;

import java.util.ArrayList;

public class MainViewPager2Adapter extends FragmentStateAdapter {
    public int mCount;

    public MainViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0 : {return new MyScheduleFragment();
            }
            case 1 : {return new WorkFragment();
            }
            case 2 : {return new WorkFragment();
            }
/*            case 3 : {
                return null;
            }*/
            case 4 : {return new WorkFragment();
            }
            case 5 : {return new MyScheduleFragment();
            }
            case 6 : {return new MyScheduleFragment();
            }
            default: {return null;//new MyScheduleFragment();
            }
        }
    }

    @Override
    public int getItemCount() {
        return 7;
    }
}
