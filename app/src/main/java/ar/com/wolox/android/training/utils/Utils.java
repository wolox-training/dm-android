package ar.com.wolox.android.training.utils;

import android.app.Activity;
import android.content.Intent;

import ar.com.wolox.android.training.ui.login.LoginActivity;

public class Utils {

    public static void validateLogin(Activity activity, UserSession userSession) {
        if (userSession.getEmail() == null) {
            Intent intent = new Intent(activity, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            activity.startActivity(intent);
            activity.finish();
        }
    }
}
