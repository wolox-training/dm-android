package ar.com.wolox.android.training.ui.errors;

import android.content.Context;
import android.content.res.Resources;

import ar.com.wolox.android.R;
import ar.com.wolox.android.training.TrainingApplication;

public class ErrorHandler {
    public static String getErrorMessage(Context context, ErrorCode errorCode) {
        int code;
        switch (errorCode) {
            case EMPTY_FIELDS:
                code = R.string.error_empty_fields;
                break;

            case INVALID_EMAIL:
                code = R.string.error_invalid_email;
                break;

            case INVALID_CREDENTIALS:
                code = R.string.error_invalid_credentials;
                break;

            case INERNET_CONNECTION_ERROR:
                code = R.string.error_internet_connection_error;
                break;

            case NO_NEWS:
                code = R.string.error_no_news;
                break;

            default:
                code = R.string.error_unexpected;
                break;
        }

        return context.getResources().getString(code);
    }
}
