package com.github.ojh102.chatproject.intro;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.github.ojh102.chatproject.MainActivity;
import com.github.ojh102.chatproject.R;
import com.github.ojh102.chatproject.api.ChatApi;
import com.github.ojh102.chatproject.data.ServerResponse;
import com.github.ojh102.chatproject.util.NetworkManager;
import com.github.ojh102.chatproject.util.PropertyManager;
import com.google.firebase.iid.FirebaseInstanceId;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpFragment extends Fragment {

    @BindView(R.id.edtId)
    EditText edtId;

    @BindView(R.id.edtPassword)
    EditText edtPasswd;

    @BindView(R.id.edtConfirmPassword)
    EditText edtConfirmPasswd;


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
        String passwd = edtPasswd.getText().toString();
        String confirmPasswd = edtConfirmPasswd.getText().toString();
        String token = FirebaseInstanceId.getInstance().getToken();

        if (TextUtils.isEmpty(id)) {
            Toast.makeText(getContext(), "id를 입력해 주세요", Toast.LENGTH_SHORT).show();
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
}
