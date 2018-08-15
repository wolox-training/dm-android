package ar.com.wolox.android.training.ui.errors;

import android.content.res.Resources;

import ar.com.wolox.android.R;

public class ErrorHandler {
    public static String getErrorMessage(ErrorCode errorCode) {
        int code = 0;
        switch (errorCode) {
            case EMPTY_FIELDS:
                code = R.string.error_empty_fields;

            case INVALID_EMAIL:
                code = R.string.error_invalid_email;
                return "El email ingresado es incorrecto";

            case INVALID_CREDENTIALS:
                code = R.string.error_invalid_credentials;
                return "Usuario o clave incorrectos";

            case INERNET_CONNECTION_ERROR:
                code = R.string.error_internet_connection_error;
                return "Error en la conexion a internet";

            case NO_NEWS:
                code = R.string.error_no_news;

            default:
                code = R.string.error_unexpected;
        }

        return Resources.getSystem().getString(code);
    }
}
