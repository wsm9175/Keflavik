package com.example.keflavik.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.keflavik.R;
import com.example.keflavik.adapters.MultiImageAdapter;
import com.example.keflavik.databinding.ActivityMyScheduleImageUploadBinding;
import com.example.keflavik.pragments.MyScheduleFragment;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MyScheduleImageUploadActivity extends BaseActivity {

    private ActivityMyScheduleImageUploadBinding binding;
    //갤러리 이미지 받아오기 용
    private static final int REQUEST_CODE_GALLERY = 1000;
    private static final int REQUEST_CODE_CAMERA = 2000;

    private static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;

    private View dialgoView;
    private View dialgoView1;
    private ImageView imageView;
    private File file;

    //완료냐 결제 청구냐
    Boolean check;
    RecyclerView recyclerView;  // 이미지를 보여줄 리사이클러뷰
    MultiImageAdapter adapter;  // 리사이클러뷰에 적용시킬 어댑터
    ArrayList<Uri> uriList = new ArrayList<>();     // 이미지의 uri를 담을 ArrayList 객체

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_schedule_image_upload);

        check = true;

        //액션 바
        titleTxt.setVisibility(View.VISIBLE);
        titleTxt.setText("작업");
        backImg.setVisibility(View.VISIBLE);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(mContext, MainActivity.class);
                //해당 페이지 / 날짜로 해서 바꾸기
                startActivity(myIntent);
                finish();
            }
        });


        //완료 or 결제청구 어떤걸로?
        if (!check) {
            //완료하기  => 이므로 실측사진 업로드 레이아웃 보여주기
            binding.finishBefordLinearLayout.setVisibility(View.VISIBLE);
            finish();
        } else {
            //결체청구
            binding.paymentBefordLinearLayout.setVisibility(View.VISIBLE);
            payment();
        }

    }


    //완료하기 // 이미지 하나 올리기 위한 메서드
    public void finish() {
        binding.imageUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAndRequestPermissions((Activity) mContext, true);
            }
        });

        //버튼 클릭시 이미지 올리면서 뒤로 가기
        binding.oderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(mContext, MainActivity.class);
                //해당 페이지 / 날짜로 해서 바꾸기
                startActivity(myIntent);
                finish();
            }
        });



    }


    //결제 청구 이미지 여러장 보내기 위한 용도
    public void payment() {
        binding.imageUploadBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAndRequestPermissions((Activity) mContext, false);
            }
        });

        binding.oderBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("???","들어옴?");
                Intent myIntent = new Intent(mContext, MainActivity.class);
                //해당 페이지 / 날짜로 해서 바꾸기
                mContext.startActivity(myIntent);
                finishAffinity();
            }
        });
    }


    //여긴 결과 받아오는 곳
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//임시로 가림
/*        //이미지 하나만 보내기
        imageUpload(requestCode, resultCode, data);
        binding.finishBefordLinearLayout.setVisibility(View.GONE);
        binding.finishAfterLinearLayout.setVisibility(View.VISIBLE);
        //그리고 이미지 addImg에 넣어주기*/





        if(requestCode == 2222){


            //여러장의 사진
            Log.d("여러장의 사진 문제","???");

            recyclerView = findViewById(R.id.recyclerView);
            binding.paymentBefordLinearLayout.setVisibility(View.GONE);
            binding.paymentAfterLinearLayout.setVisibility(View.VISIBLE);

            if(data == null){   // 어떤 이미지도 선택하지 않은 경우
                Toast.makeText(getApplicationContext(), "이미지를 선택하지 않았습니다.", Toast.LENGTH_LONG).show();
            }
            else{   // 이미지를 하나라도 선택한 경우
                if(data.getClipData() == null){     // 이미지를 하나만 선택한 경우
                    Log.e("single choice: ", String.valueOf(data.getData()));
                    Uri imageUri = data.getData();
                    uriList.add(imageUri);

                    adapter = new MultiImageAdapter(uriList, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
                }
                else{      // 이미지를 여러장 선택한 경우
                    ClipData clipData = data.getClipData();
                    Log.e("clipData", String.valueOf(clipData.getItemCount()));

                    if(clipData.getItemCount() > 10){   // 선택한 이미지가 11장 이상인 경우
                        Toast.makeText(getApplicationContext(), "사진은 10장까지 선택 가능합니다.", Toast.LENGTH_LONG).show();
                    }
                    else{   // 선택한 이미지가 1장 이상 10장 이하인 경우
                        //Log.e(TAG, "multiple choice");

                        for (int i = 0; i < clipData.getItemCount(); i++){
                            Uri imageUri = clipData.getItemAt(i).getUri();  // 선택한 이미지들의 uri를 가져온다.
                            try {
                                uriList.add(imageUri);  //uri를 list에 담는다.

                            } catch (Exception e) {
                                //Log.e(TAG, "File select error", e);
                            }
                        }

                        adapter = new MultiImageAdapter(uriList, getApplicationContext());
                        recyclerView.setAdapter(adapter);   // 리사이클러뷰에 어댑터 세팅
                        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));     // 리사이클러뷰 수평 스크롤 적용
                    }
                }
            }
        }


    }


    // function to check permission 권한
    public void checkAndRequestPermissions(final Activity context, Boolean choice) {


        Boolean isCheck;

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
        if (!listPermissionsNeeded.isEmpty()) {//없으면 false //권한 줄건지 물어보는 것
            ActivityCompat.requestPermissions(context, listPermissionsNeeded
                            .toArray(new String[listPermissionsNeeded.size()]),
                    REQUEST_ID_MULTIPLE_PERMISSIONS);
        }


        if (!listPermissionsNeeded.isEmpty()) {
            Toast.makeText(mContext, "접근 권한을 허용해주세요.", Toast.LENGTH_LONG).show();
        } else {
                        dialgoView = View.inflate(mContext, R.layout.alert_choose_bussiness, null);
                        AlertDialog.Builder builder = new AlertDialog.Builder(MyScheduleImageUploadActivity.this);
                        builder.setView(dialgoView);


                        new AlertDialog.Builder(mContext)
                                .setTitle("이미지 업로드")
                                .setPositiveButton("갤러리", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        if(choice) {//한장의 사진
                                            /*viewModel.addGallery();*/
                                            Intent intent = new Intent();
                                            intent.setType("image/*");
                                            intent.setAction(Intent.ACTION_PICK);
                                            startActivityForResult(intent, REQUEST_CODE_GALLERY);
                                        }else{//여러장 사진
                                            Intent intent = new Intent(Intent.ACTION_PICK);
                                            intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                                            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);  // 다중 이미지를 가져올 수 있도록 세팅
                                            intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                            startActivityForResult(intent, 2222);
                                        }
                                    }
                                })
                                .setNeutralButton("사진찍기", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent intent = new Intent();
                                        //intent.setType("image/*");
                                        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                                        //if(intent.resolveActivity(getPackageManager()) != null){
                                        startActivityForResult(intent, REQUEST_CODE_CAMERA);
                                    }
                                })
                                .setNegativeButton("취소", null)
                                .show();

        }

    }


    //1장 사진 올리기 메서든
    public void imageUpload(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_GALLERY) {
            if (resultCode == RESULT_OK) {
                try {
                    InputStream in = getContentResolver().openInputStream(data.getData());

                    Bitmap img = BitmapFactory.decodeStream(in);

                    in.close();

                    imageView.setImageBitmap(img);
                } catch (Exception e) {
                    Log.e("Galllery Error ", e.toString());
                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
            }
        }

        //사진
        if (requestCode == REQUEST_CODE_CAMERA)
            if (resultCode == RESULT_OK) {
                try {

                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");

                } catch (Exception e) {
                    Log.e("Galllery Error ", e.toString());
                }
            } else if (resultCode == RESULT_CANCELED) {

                Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
            }
    }
}



