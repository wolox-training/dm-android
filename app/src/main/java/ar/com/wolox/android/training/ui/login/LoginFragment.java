package ar.com.wolox.android.training.ui.login;

import android.content.Intent;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import ar.com.wolox.android.R;
import ar.com.wolox.android.training.ui.errors.ErrorCode;
import ar.com.wolox.android.training.ui.errors.ErrorHandler;
import ar.com.wolox.android.training.ui.home.HomeActivity;
import ar.com.wolox.android.training.ui.signup.SignupActivity;
import ar.com.wolox.android.training.ui.views.CustomButtonView;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginFragment extends WolmoFragment<LoginPresenter> implements ILoginView {

    @BindView(R.id.fragment_login_email)
    TextView mEmail;
    @BindView(R.id.fragment_login_password)
    TextView mPassword;
    @BindView(R.id.fragment_login_login)
    CustomButtonView mLoginBtn;
    @BindView(R.id.fragment_login_signup)
    CustomButtonView mSignupBtn;
    @BindView(R.id.fragment_login_terms_conditions)
    TextView mTermsAndConditions;

    @Inject
    public LoginFragment() {}

    @Override
    public int layout() {
        return R.layout.fragment_login;
    }

    @Override
    public void init() {
        mLoginBtn.setText(R.string.login_login_btn_text);
        mLoginBtn.setColor(R.color.white);
        mSignupBtn.setText(R.string.login_signup_btn_text);
        mSignupBtn.setTextColor(R.color.white);
        mSignupBtn.setColor(R.color.colorPrimary);
        mTermsAndConditions.setMovementMethod(LinkMovementMethod.getInstance());

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
    public void setListeners() {
        super.setListeners();
        mLoginBtn.setOnClickListener(v -> onLogin());
        mSignupBtn.setOnClickListener(v -> onSignup());
    }

    public void onLogin() {
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();

        if (this.validateEmailField(email) && this.validatePasswordField(password)) {
            getPresenter().login(email, password);
        }
    }

    public Boolean validateEmailField(String email) {
        if (!this.validateEmptyField(email)) {
            mEmail.setError(ErrorHandler.getErrorMessage(ErrorCode.EMPTY_FIELDS));
            return false;
        } else if (!this.validateEmail(email)) {
            mEmail.setError(ErrorHandler.getErrorMessage(ErrorCode.INVALID_EMAIL));
            return false;
        }

        return true;
    }

    private Boolean validatePasswordField(String password) {

        if (!this.validateEmptyField(password)) {
            mPassword.setError(ErrorHandler.getErrorMessage(ErrorCode.EMPTY_FIELDS));
            return false;
        }

        return true;
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
    public void onLoginError(ErrorCode errorCode) {
        switch (errorCode) {
            case INVALID_CREDENTIALS:
                mPassword.setError(ErrorHandler.getErrorMessage(errorCode));
                break;
        }
    }

    public void onSignup() {
        Intent intent = new Intent(getActivity(), SignupActivity.class);
        startActivity(intent);
    }
}
