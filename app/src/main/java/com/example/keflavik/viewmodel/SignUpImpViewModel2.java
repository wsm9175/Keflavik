package com.example.keflavik.viewmodel;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.databinding.BaseObservable;

import com.example.keflavik.R;
import com.example.keflavik.TechDTO;
import com.example.keflavik.databinding.ActivitySignUpImp2Binding;
import com.example.keflavik.model.SignUpImpDatabase2;
import com.example.keflavik.datas.UserData;
import com.example.keflavik.datas.GlobalData;
import com.example.keflavik.view.LoginActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.ByteString;

public class SignUpImpViewModel2 extends BaseObservable {
    private static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    private SignUpImpDatabase2 database;
    private Context mContext;
    private ActivitySignUpImp2Binding binding;
    private UserData user;
    //private ArrayList skillList;
    private ArrayList<String> skillList = new ArrayList<String>();
    private static final int REQUEST_CODE = 1000;

    //사업자 등록증 올리기 위한 버튼
    private Button btn_open_bt_sheet;
    private TextView cameraTxt;
    private TextView galleryTxt;
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");


    public SignUpImpViewModel2(Context mContext, ActivitySignUpImp2Binding binding, SignUpImpDatabase2 database) {
        this.database = database;
        this.mContext = mContext;
        this.binding = binding;

        database.setDatabaseListener(getDatabaseListener());
    }


    public GetUserListener getDatabaseListener() {
        return new GetUserListener() {

            @Override
            public void onSuccess() {

                Toast.makeText(mContext, "님 회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(mContext, LoginActivity.class);
                mContext.startActivity(myIntent);
            }

            @Override
            public void onFailed() {

            }

            //여긴 개인으로 하자
            public void onSuccess2() {

            }


            public void onFailed2() {

            }
        };
    }


    //기능이 수만큼 리스너를 만든다
    public interface GetUserListener {
        void onSuccess();

        void onFailed();
    }

    public void signUp(File file, Boolean isCeo) throws JSONException {//개인인지 아닌지 해서 유효성 검사 하고 // 파일 보낼지도 같이 확인하기
        GlobalData gUser = new GlobalData();
        user = gUser.getGlobalUser();


        Log.d("전화번호", user.getPhoneNumber());

        Log.d("유저 정보", gUser.toString());


        if (binding.businessnameEdt.getText().length() < 1) {
            Toast.makeText(mContext, "기업명은 1글자 이상 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!binding.certificationChBox.isChecked()) {
            Toast.makeText(mContext, "약관동의가 필요합니다.", Toast.LENGTH_SHORT).show();
            return;
        }


        //최종회원가입 전 정보를 모아서 넣기
        user.setCeo(binding.BusinessBtn.isChecked());//사업자인지 개인인지
        user.setAccept(binding.certificationChBox.isChecked());//약관 동의 우선 false로
        user.setAccept(false);

        //Log.d("유저 정보들", user.getName());22


        if (isCeo) {//true 사업자
            //정보 넣기전 오브젝트에 넣는 것
            JsonObject object = new JsonObject();
            object.addProperty("constructorDTO", user.toString());
            //objectArray.add(skillList.toString());

            Log.d("문제 skillList.size()", skillList.size() + "");

            JsonArray objectArray = new JsonArray();
            for (int i = 0; i < skillList.size(); i++) {
                JsonObject objectList = new JsonObject();
                objectList.addProperty("name", skillList.get(i).toString());
                objectArray.add(objectList.toString());
            }

            //기술 리스트 구하기
            database.businessSignUp(object, objectArray, file);
            ////////////////////////////////////////////////////////////////////////////////////


        } else {// false 개인
            //정보 넣기전 오브젝트에 넣는 것
            JsonObject object = new JsonObject();

            object.addProperty("pw", user.getPw());
            object.addProperty("name", user.getName());
            object.addProperty("phoneNumber", user.getPhoneNumber());
            object.addProperty("email", user.getEmail());
            object.addProperty("active", user.isActive());
            object.addProperty("accept", user.isAccept());
            object.addProperty("agreeTerm", user.isAgreeTerm());
            object.addProperty("ageGroup", user.getAgeGroup());
            object.addProperty("career", user.getCareer());
            object.addProperty("ceo", user.isCeo());
            object.addProperty("certification", user.isCertification());


            Log.d("유저 값", user.toString());


            Log.d("문제 skillList.size()", skillList.size() + "");

            ArrayList<String> list = new ArrayList<String>();


            /*List<TechDTO> jsonArray = new ArrayList<>();*/
            RequestBody body = RequestBody.create(JSON, "[{\"name\" : \"방충망\"},{\"name\" : \"방충\"}]");


            /*JSONArray jsonArr = new JSONArray(data);*/
         /*   for (int i = 0; i < skillList.size(); i++) {
                TechDTO techDTO = new TechDTO(skillList.get(i));
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", skillList.get(i));
//                list.add(skillList.get(i).toString());
           *//*     JsonObject object2 = new JsonObject();
                object2.addProperty("name", skillList.get(i).toString());*//*
                jsonArray.put(techDTO);
            }
*/
//            JsonArray jarray = (JsonArray) p.parse(jsonReader).getAsJsonObject().get("FoodItemData");
//            objectArray.add(gson.toJsonTree(object2).getAsJsonArray());


            database.individualSignUp(user, body);
        }

    }


    //보유기술 넣는것
    public void addSkill() {
        binding.addSkillBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("문제", skillList.toString());

                if (skillList.contains(binding.skillEdt.getText().toString())) {


                    Toast.makeText(mContext, "중복된 기술입니다.", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    skillList.add(binding.skillEdt.getText().toString());
                    TextView textView = new TextView(mContext);
                    textView.setText(binding.skillEdt.getText().toString());
                    textView.setBackgroundResource(R.drawable.custom_round_button_yello);

                    //이건 마진값 모음
                    LinearLayout.LayoutParams mLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);


                    mLayoutParams.setMargins(0, 0, 10, 5);
                    textView.setLayoutParams(mLayoutParams);

                    binding.skillListLayout.addView(textView);


                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            binding.skillListLayout.removeView(textView);
                            skillList.remove(binding.skillEdt.getText().toString());
                        }
                    });
                }
            }
        });
    }


    //@@@@@@@@@@@@@@@@@@@@@@@@갤러리에서 사진 가져올것
    public void addGallery() {
/*        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mContext.startActivityForResult();
    //(intent, REQUEST_CODE);*/
    }


/*    // function to check permission 권한
    public static boolean checkAndRequestPermissions(final Activity context) {
        int WExtstorePermission = ContextCompat.checkSelfPermission(context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int cameraPermission = ContextCompat.checkSelfPermission(context,
                Manifest.permission.CAMERA);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (WExtstorePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded
                    .add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(context, listPermissionsNeeded
                            .toArray(new String[listPermissionsNeeded.size()]),
                    REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }*/


}
