package com.github.ojh102.chatproject.intro.signup;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.ojh102.chatproject.common.BaseFragment;
import com.github.ojh102.chatproject.common.MessageApi;
import com.github.ojh102.chatproject.common.di.NetworkComponent;
import com.github.ojh102.chatproject.intro.signup.di.DaggerSignUpComponent;
import com.github.ojh102.chatproject.intro.signup.di.SignUpModule;
import com.github.ojh102.chatproject.intro.signup.di.SignUpPresenter;
import com.github.ojh102.chatproject.main.MainActivity;
import com.github.ojh102.chatproject.R;
import com.github.ojh102.chatproject.data.ServerResponse;
import com.github.ojh102.chatproject.util.NetworkManager;
import com.github.ojh102.chatproject.util.PropertyManager;
import com.google.firebase.iid.FirebaseInstanceId;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpFragment extends BaseFragment implements SignUpPresenter.View {

    @BindView(R.id.edtId)
    EditText edtId;

    @BindView(R.id.edtName)
    EditText edtName;

    @BindView(R.id.edtPassword)
    EditText edtPasswd;

    @BindView(R.id.edtConfirmPassword)
    EditText edtConfirmPasswd;

    @Inject
    SignUpPresenter signUpPresenter;

    public static SignUpFragment newInstance() {
        SignUpFragment fragment = new SignUpFragment();
        return fragment;
    }

    @Override
    protected void setupComponent(NetworkComponent networkComponent) {
        DaggerSignUpComponent.builder()
                .networkComponent(networkComponent)
                .signUpModule(new SignUpModule(this))
                .build()
                .inject(this);
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
        String id = edtId.getText().toString();
        String name = edtName.getText().toString();
        String passwd = edtPasswd.getText().toString();
        String confirmPasswd = edtConfirmPasswd.getText().toString();
        String token = FirebaseInstanceId.getInstance().getToken();

        signUpPresenter.signUp(id, name, passwd, confirmPasswd, token);
    }

    @Override
    public void navigateToMain() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
