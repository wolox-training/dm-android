package ar.com.wolox.android.training.ui.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import ar.com.wolox.android.R;
import ar.com.wolox.android.training.ui.home.HomeActivity;
import ar.com.wolox.android.training.ui.signup.SignupActivity;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginFragment extends WolmoFragment<LoginPresenter> implements ILoginView {

    @BindView(R.id.fragment_login_email) TextView mEmail;
    @BindView(R.id.fragment_login_password) TextView mPassword;
    @BindView(R.id.progressbar) ProgressBar mProgressBar;

    @Inject
    public LoginFragment() { }

    @Override
    public int layout() {
        return R.layout.fragment_login;
    }

    @Override
    public void init() {
        if (getPresenter().isUserLoged()) {
            goToHome();
        }
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
    public void onClickLogin() {
        startLoading();

        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();

        Boolean validateErrors =    this.validateEmptyFields(email, password)
                                    && this.validateEmailField(email);
        if (validateErrors) {
            getPresenter().login(email, password);
         } else {
            completeLoading();
        }
    }

    private Boolean validateEmptyFields(String email, String password) {

        if (!this.validateEmptyField(email)) {
            mEmail.setError(ErrorHandler.getErrorMessage(ErrorCode.EMPTY_FIELDS));
            return false;
        } else if (!this.validateEmptyField(password)) {
            mPassword.setError(ErrorHandler.getErrorMessage(ErrorCode.EMPTY_FIELDS));
            return false;
        }

        return true;
    }

    public Boolean validateEmailField(String email) {
        if (!this.validateEmail(email)) {
            mEmail.setError(ErrorHandler.getErrorMessage(ErrorCode.INVALID_EMAIL));
            return false;
        }

        return true;
    }

    private Boolean validateEmptyField(String field) {
        return field.length() > 0;
    }

    private Boolean validateEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
>>>>>>> 63ff750... Login connection with database
    }

    @Override
    public void onLoginSuccess() {
        goToHome();
    }

    @Override
    public void onLoginError(ErrorCode errorCode) {
        completeLoading();
        switch (errorCode) {
            case INVALID_CREDENTIALS:
            case INERNET_CONNECTION_ERROR:
                Toast.makeText(getContext(), ErrorHandler.getErrorMessage(errorCode), Toast.LENGTH_LONG).show();
                break;
        }
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

    public void startLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public void completeLoading() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }
}
