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

import com.github.ojh102.chatproject.api.MessageApi;
import com.github.ojh102.chatproject.main.MainActivity;
import com.github.ojh102.chatproject.R;
import com.github.ojh102.chatproject.data.ServerResponse;
import com.github.ojh102.chatproject.util.BackPressCloseHandler;
import com.github.ojh102.chatproject.util.NetworkManager;
import com.github.ojh102.chatproject.util.PropertyManager;
import com.google.firebase.iid.FirebaseInstanceId;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInFragment extends Fragment {
    @BindView(R.id.edtId)
    EditText edtId;

    @BindView(R.id.edtPassword)
    EditText edtPasswd;

    public static SignInFragment newInstance() {
        SignInFragment fragment = new SignInFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signin, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.btnSignIn)
    public void onClickSignIn() {

        final String id = edtId.getText().toString();
        String passwd = edtPasswd.getText().toString();
        String token = FirebaseInstanceId.getInstance().getToken();

        if (TextUtils.isEmpty(id)) {
            Toast.makeText(getContext(), "id를 입력해 주세요", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(passwd)) {
            Toast.makeText(getContext(), "비밀번호를 입력해 주세요", Toast.LENGTH_SHORT).show();
        } else {
            MessageApi messageApi = NetworkManager.getInstance().getApi(MessageApi.class);
            Call<ServerResponse> call = messageApi.signIn(id, passwd, token);
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

    @OnClick(R.id.btnSignUp)
    public void onClickSignUp() {
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_intro, SignUpFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }

}
