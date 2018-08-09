package ar.com.wolox.android.training.ui.login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import ar.com.wolox.android.R;
import ar.com.wolox.android.training.ui.errors.ErrorCode;
import ar.com.wolox.android.training.ui.errors.ErrorHandler;
import ar.com.wolox.android.training.ui.home.HomeActivity;
import ar.com.wolox.android.training.ui.signup.SignupActivity;
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
        mEmail.setText(getPresenter().getUserEmail());
    }

    public void goToHome() {
        Intent intent = new Intent(getActivity(), HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        getActivity().finish();
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
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();
        ErrorCode errorCode;

        if ((errorCode = this.validateEmailField(email)) != null) {
            mEmail.setError(ErrorHandler.getErrorMessage(errorCode));
        } else if ((errorCode = this.validatePasswordField(password)) != null) {
            mPassword.setError(ErrorHandler.getErrorMessage(errorCode));
        } else {
            getPresenter().login(email, password);
        }
    }

    private ErrorCode validateEmailField(String email) {
        if (!this.validateEmptyField(email)) {
            return ErrorCode.EMPTY_FIELDS;
        } else if (!this.validateEmail(email)) {
            return ErrorCode.INVALID_EMAIL;
        }

        return null;
    }

    public ErrorCode validatePasswordField(String password) {
        if (!this.validateEmptyField(password)) {
            return ErrorCode.EMPTY_FIELDS;
        }

        return null;
    }

    private Boolean validateEmptyField(String field) {
        return field.length() > 0;
    }

    private Boolean validateEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    public void onLoginSuccess() {
        goToHome();
    }

    @Override
    public void onLoginEmailError(String error) {
        mEmail.setError(error);
    }

    @Override
    public void onLoginPasswordError(String error) {
        mPassword.setError(error);
    }

    @OnClick(R.id.fragment_login_signup)
    public void onClickSignup() {
        Intent intent = new Intent(getActivity(), SignupActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.fragment_login_terms_conditions)
    public void onClickTermsAndConditions() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.wolox.com.ar/"));
        startActivity(intent);
    }
}
