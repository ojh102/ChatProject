package com.github.ojh102.chatproject.intro;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.ojh102.chatproject.MyApplication;
import com.github.ojh102.chatproject.main.MainActivity;
import com.github.ojh102.chatproject.R;
import com.github.ojh102.chatproject.api.ChatApi;
import com.github.ojh102.chatproject.data.ServerResponse;
import com.github.ojh102.chatproject.util.NetworkManager;
import com.github.ojh102.chatproject.util.PropertyManager;
import com.google.firebase.iid.FirebaseInstanceId;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpFragment extends Fragment {

    @BindView(R.id.edtId)
    EditText edtId;

    @BindView(R.id.edtName)
    EditText edtName;

    @BindView(R.id.ivTumbnail)
    ImageView ivTumbnail;

    @BindView(R.id.edtPassword)
    EditText edtPasswd;

    @BindView(R.id.edtConfirmPassword)
    EditText edtConfirmPasswd;

    File mSavedFile;

    private static int REQUEST_GALLERY = 1000;

    public static SignUpFragment newInstance() {
        SignUpFragment fragment = new SignUpFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    @OnClick(R.id.btnSignUp)
    public void onClickSignup() {
        final String id = edtId.getText().toString();
        final String name = edtName.getText().toString();
        String passwd = edtPasswd.getText().toString();
        String confirmPasswd = edtConfirmPasswd.getText().toString();
        String token = FirebaseInstanceId.getInstance().getToken();

        if (TextUtils.isEmpty(id)) {
            Toast.makeText(getContext(), "id를 입력해 주세요", Toast.LENGTH_SHORT).show();
        } else if(TextUtils.isEmpty(passwd)) {
            Toast.makeText(getContext(), "닉네임을 입력해 주세요", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(passwd)) {
            Toast.makeText(getContext(), "비밀번호를 입력해 주세요", Toast.LENGTH_SHORT).show();
        } else if (!passwd.equals(confirmPasswd)) {
            Toast.makeText(getContext(), "비밀번호가 맞지 않습니다.", Toast.LENGTH_SHORT).show();
        } else {
            ChatApi chatApi = NetworkManager.getInstance().getApi(ChatApi.class);
            Call<ServerResponse> call = chatApi.signUp(id, passwd, token);
            call.enqueue(new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    if (response.body().getSuccess() == 1) {
                        PropertyManager.getInstance().setId(id);
                        PropertyManager.getInstance().setName(name);

                        mSavedFile.delete();

                        Intent intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }

                @Override
                public void onFailure(Call<ServerResponse> call, Throwable t) {
                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @OnClick(R.id.ivTumbnail)
    public void onClickTumbnail() {
        Intent photoPickerIntent = new Intent(
                Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        photoPickerIntent.setType("image/*");
        photoPickerIntent.putExtra("crop", "true");
        photoPickerIntent.putExtra(MediaStore.EXTRA_OUTPUT, getTempUri());
        photoPickerIntent.putExtra("outputFormat",
                Bitmap.CompressFormat.JPEG.toString());
        photoPickerIntent.putExtra("aspectX", ivTumbnail.getWidth());
        photoPickerIntent.putExtra("aspectY", ivTumbnail.getHeight());

        startActivityForResult(photoPickerIntent, REQUEST_GALLERY);
    }

    private Uri getTempUri() {
        mSavedFile = new File(Environment.getExternalStorageDirectory(), "temp_" + System.currentTimeMillis() / 1000 + ".jpg");
        return Uri.fromFile(mSavedFile);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQUEST_GALLERY && resultCode == Activity.RESULT_OK) {

            Glide.with(MyApplication.getContext())
                    .load(data.getData())
                    .bitmapTransform(new CropCircleTransformation(MyApplication.getContext()))
                    .into(ivTumbnail);

        }
    }

}
