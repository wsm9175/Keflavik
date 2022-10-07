package com.example.keflavik.view;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.keflavik.R;
import com.example.keflavik.databinding.ActivitySignUpImp2Binding;
import com.example.keflavik.model.SignUpImpDatabase2;
import com.example.keflavik.viewmodel.SignUpImpViewModel2;

import org.json.JSONException;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class SignUpImpActivity2 extends BaseActivity {

    private ActivitySignUpImp2Binding binding;
    SignUpImpViewModel2 viewModel;

    //사업자 등록증 올리기 위한 버튼
    private Button btn_open_bt_sheet;
    private TextView cameraTxt;
    private TextView galleryTxt;
    private File file;

    private static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;

    //갤러리 이미지 받아오기 용
    private static final int REQUEST_CODE_GALLERY = 1000;
    private static final int REQUEST_CODE_CAMERA = 2000;
    private ImageView imageView;
    private View view;
    private View dialgoView;
    private View dialgoView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up_imp2);
        titleTxt.setText("회원가입");
        backImg.setVisibility(View.VISIBLE);


        viewModel = new SignUpImpViewModel2(mContext, binding, SignUpImpDatabase2.getInstance(this));
        viewModel.addSkill();


        addBusinessLicense();


        setValue();
        setUpEvent();
    }


    void setUpEvent() {

        binding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ceo면 true
                try {
                    viewModel.signUp(file, binding.BusinessBtn.isChecked());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


    }

    void setValue() {
        //연령층 스피너
        ArrayAdapter<CharSequence> ageAdapter =
                ArrayAdapter.createFromResource(this, R.array.age, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        ageAdapter.setDropDownViewResource(androidx.databinding.library.baseAdapters.R.layout.support_simple_spinner_dropdown_item);
        binding.ageSpinner.setAdapter(ageAdapter);

        //경력 스피너
        ArrayAdapter<CharSequence> careeradapter =
                ArrayAdapter.createFromResource(this, R.array.career, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        careeradapter.setDropDownViewResource(androidx.databinding.library.baseAdapters.R.layout.support_simple_spinner_dropdown_item);
        binding.careerSpinner.setAdapter(careeradapter);

        //회사일경우 사업자 등록증/회사주소/보유기술 등등 보여주고
        binding.BusinessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.businessLicenseBtn.setVisibility(View.VISIBLE);
                binding.individualLay.setVisibility(View.VISIBLE);
            }
        });
        //개인일경우 사업자 등록증/회사주소/보유기술 등등 가리기
        binding.individualBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.businessLicenseBtn.setVisibility(View.GONE);
                binding.individualLay.setVisibility(View.GONE);
            }
        });

    }


    //사업자 등록증 올리기
    public void addBusinessLicense() {
        btn_open_bt_sheet = binding.businessLicenseBtn;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        btn_open_bt_sheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Boolean check =
                checkAndRequestPermissions((Activity) mContext);


            }


        });
    }


    //사진 선택해서 미리보기 보여주기
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //갤러리
        if (requestCode == REQUEST_CODE_GALLERY) {
            if (resultCode == RESULT_OK) {
                try {
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    Bitmap img = BitmapFactory.decodeStream(in);


                    //이미지 받은거 보여주고 확인하기
                    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                    dialgoView1 = View.inflate(mContext, R.layout.alert_business_preview, null);
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

                    //글라이드로 이미지 올리는것
                    ImageView imgImageView = dialgoView1.findViewById(R.id.addImg);

                    //글라이드 이미지
                    Uri uri = data.getData();

                    file = getFile(getApplicationContext(), uri);
                    Log.d("문제 이미지 파일", file + "");
                    Glide.with(mContext).load(data.getData()).into(imgImageView);


                    builder.setView(dialgoView1);


                    final AlertDialog alertDialog = builder.create();
                    alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    alertDialog.show();
                    Log.d("문제", "2. 들어오나?");
                    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


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


                    //이미지 받은거 보여주고 확인하기
                    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                    dialgoView1 = View.inflate(mContext, R.layout.alert_business_preview, null);
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

                    //글라이드로 이미지 올리는것
                    ImageView imgImageView = dialgoView1.findViewById(R.id.addImg);

                    Glide.with(mContext).load(file)
                            //.apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                            .transform(new CenterInside(), new RoundedCorners(8))
                            .into(imgImageView);

                    builder.setView(dialgoView1);


                    final AlertDialog alertDialog = builder.create();
                    alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                    alertDialog.show();
                    Log.d("문제", "2. 들어오나?");
                    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


                } catch (Exception e) {
                    Log.e("Galllery Error ", e.toString());
                }
            } else if (resultCode == RESULT_CANCELED) {

                Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
            }


    }


    // function to check permission 권한
    public void checkAndRequestPermissions(final Activity context) {

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
            AlertDialog.Builder builder = new AlertDialog.Builder(SignUpImpActivity2.this);
            builder.setView(dialgoView);


            final AlertDialog alertDialog = builder.create();
            //alertDialog.getWindow().setLayout(50, 50);

            alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            alertDialog.show();

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(alertDialog.getWindow().getAttributes());
            lp.width = 800;
            lp.height = 400;
            alertDialog.getWindow().setAttributes(lp);


            //갤러리에서 사진 가져오기
            View addgalleryLayout = dialgoView.findViewById(R.id.addgalleryLayout);
            addgalleryLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    /*viewModel.addGallery();*/
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_PICK);
                    startActivityForResult(intent, REQUEST_CODE_GALLERY);
                    Toast.makeText(mContext, "우선 갤러리 갈거다.", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                }
            });

            //사진 찍어서 가져오기
            View addphotoLayout = dialgoView.findViewById(R.id.addphotoLayout);
            addphotoLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent();
                    //intent.setType("image/*");
                    intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    //if(intent.resolveActivity(getPackageManager()) != null){
                    startActivityForResult(intent, REQUEST_CODE_CAMERA);
                    //}
                    Toast.makeText(mContext, "우선 사진찍으러 갈거다.", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                }
            });

            View backLayout = dialgoView.findViewById(R.id.backLayout);
            backLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.dismiss();
                }
            });
        }
    }


    public static File getFile(Context context, Uri uri) throws IOException {
        File destinationFilename = new File(context.getFilesDir().getPath() + File.separatorChar + queryName(context, uri));
        try (InputStream ins = context.getContentResolver().openInputStream(uri)) {
            createFileFromStream(ins, destinationFilename);
        } catch (Exception ex) {
            Log.e("Save File", ex.getMessage());
            ex.printStackTrace();
        }
        return destinationFilename;
    }

    public static void createFileFromStream(InputStream ins, File destination) {
        try (OutputStream os = new FileOutputStream(destination)) {
            byte[] buffer = new byte[4096];
            int length;
            while ((length = ins.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            os.flush();
        } catch (Exception ex) {
            Log.e("Save File", ex.getMessage());
            ex.printStackTrace();
        }
    }

    private static String queryName(Context context, Uri uri) {
        Cursor returnCursor =
                context.getContentResolver().query(uri, null, null, null, null);
        assert returnCursor != null;
        int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
        returnCursor.moveToFirst();
        String name = returnCursor.getString(nameIndex);
        returnCursor.close();
        return name;
    }

}


