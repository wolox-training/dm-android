package ar.com.wolox.android.training.ui.login;

public interface ILoginView {

    void onLoginSuccess();
    void onLoginEmailError(String error);
    void onLoginPasswordError(String error);
}
