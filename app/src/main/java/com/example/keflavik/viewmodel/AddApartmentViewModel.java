package com.example.keflavik.viewmodel;

import android.content.Context;

import com.example.keflavik.databinding.FragmentAddApartmentBinding;
import com.example.keflavik.model.AddApartmentDatabase;

public class AddApartmentViewModel {
    private AddApartmentDatabase database;
    private Context context;
    private FragmentAddApartmentBinding binding;

    public AddApartmentViewModel(Context context, FragmentAddApartmentBinding binding, AddApartmentDatabase database){
        this.database = database;
        this.context = context;
        this.binding = binding;
    }

    public AddApartmentListener getAddApartmentListener(){
        return new AddApartmentListener() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFailed() {

            }
        };
    }

    public interface AddApartmentListener{
        void onSuccess();
        void onFailed();
    }

}
