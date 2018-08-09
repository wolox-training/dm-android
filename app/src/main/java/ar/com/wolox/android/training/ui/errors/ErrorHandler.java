package ar.com.wolox.android.training.ui.errors;

public class ErrorHandler {
    public static String getErrorMessage(ErrorCode errorCode) {
        switch (errorCode) {
            case EMPTY_FIELDS:
                return "Todos los campos son obligatorios";

            case INVALID_EMAIL:
                return "El email ingresado es incorrecto";

            default:
                return "";
        }
    }
}
