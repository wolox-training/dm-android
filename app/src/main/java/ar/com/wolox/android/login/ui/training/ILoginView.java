package ar.com.wolox.android.login.ui.training;

public interface ILoginView {

    void onLoginSuccess();
    void onLoginEmailError(String error);
    void onLoginPasswordError(String error);
}
