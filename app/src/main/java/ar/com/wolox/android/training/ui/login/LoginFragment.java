package ar.com.wolox.android.training.ui.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import ar.com.wolox.android.R;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginFragment extends WolmoFragment<LoginPresenter> implements ILoginView {

    @BindView(R.id.fragment_login_email) TextView mEmail;
    @BindView(R.id.fragment_login_password) TextView mPassword;

    @Inject
    public LoginFragment() {}

    @Override
    public int layout() {
        return R.layout.fragment_login;
    }

    @Override
    public void init() {
        mEmail.setText(getPresenter().getEmail());
        mPassword.setText(getPresenter().getPassword());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick(R.id.fragment_login_login)
    public void onLogin() {
        getPresenter().login(mEmail.getText().toString(), mPassword.getText().toString());
    }

    @Override
    public void onLoginSuccess() {

    }

    @Override
    public void onLoginEmailError(String error) {
        mEmail.setError(error);
    }

    @Override
    public void onLoginPasswordError(String error) {
        mPassword.setError(error);
    }
}
