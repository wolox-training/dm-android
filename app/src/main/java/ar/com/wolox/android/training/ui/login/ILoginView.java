package ar.com.wolox.android.training.ui.login;

import ar.com.wolox.android.training.ui.errors.ErrorCode;

public interface ILoginView {

    void onLoginSuccess();

    void onLoginError(ErrorCode errorCode);
}
