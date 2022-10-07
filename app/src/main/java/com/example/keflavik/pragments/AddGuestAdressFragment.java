package com.example.keflavik.pragments;

public class AddGuestAdressFragment {






    public static class AddGuestAdressSington{
        private AddGuestAdressFragment addGuestAdressFragment;
        public AddGuestAdressFragment getAddGuestAdressFragment(){
            if(addGuestAdressFragment == null){
                addGuestAdressFragment = new AddGuestAdressFragment();
            }
            return addGuestAdressFragment;
        }
    }
}
