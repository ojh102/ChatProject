package com.github.ojh102.chatproject.intro.signin;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.github.ojh102.chatproject.R;
import com.github.ojh102.chatproject.common.BaseFragment;
import com.github.ojh102.chatproject.common.di.NetworkComponent;
import com.github.ojh102.chatproject.intro.signin.di.DaggerSignInComponent;
import com.github.ojh102.chatproject.intro.signin.di.SignInModule;
import com.github.ojh102.chatproject.intro.signin.di.SignInPresenter;
import com.github.ojh102.chatproject.intro.signup.SignUpFragment;
import com.github.ojh102.chatproject.main.MainActivity;
import com.google.firebase.iid.FirebaseInstanceId;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInFragment extends BaseFragment implements SignInPresenter.View {
    @BindView(R.id.edtId)
    EditText edtId;

    @BindView(R.id.edtPassword)
    EditText edtPasswd;

    @Inject
    SignInPresenter signInPresenter;

    public static SignInFragment newInstance() {
        SignInFragment fragment = new SignInFragment();
        return fragment;
    }

    @Override
    protected void setupComponent(NetworkComponent networkComponent) {
        DaggerSignInComponent.builder()
                .networkComponent(networkComponent)
                .signInModule(new SignInModule(this))
                .build()
                .inject(this);
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

        String id = edtId.getText().toString();
        String passwd = edtPasswd.getText().toString();
        String token = FirebaseInstanceId.getInstance().getToken();

        signInPresenter.signIn(id, passwd, token);
    }

    @Override
    public void navigateToMain() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @OnClick(R.id.btnSignUp)
    public void onClickSignUp() {
       navigateToSignUp();
    }

    @Override
    public void navigateToSignUp() {
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_intro, SignUpFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

}
