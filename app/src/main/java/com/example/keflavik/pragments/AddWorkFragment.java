package com.example.keflavik.pragments;

public class AddWorkFragment {






    //싱글턴
    public static class AddWorkSington{
        private AddWorkFragment addWorkFragment;
        public AddWorkFragment getAddWorkFragment(){
            if(addWorkFragment == null){
                addWorkFragment = new AddWorkFragment();
            }
            return addWorkFragment;
        }
    }
}
