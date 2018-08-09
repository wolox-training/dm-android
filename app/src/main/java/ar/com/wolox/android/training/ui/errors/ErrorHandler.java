package ar.com.wolox.android.training.ui.errors;

public class ErrorHandler {
    public static String getErrorMessage(ErrorCode errorCode) {
        switch (errorCode) {
            case EMPTY_FIELDS:
                return "Todos los campos son obligatorios";

            case INVALID_EMAIL:
                return "El email ingresado es incorrecto";

            case INVALID_CREDENTIALS:
                return "Usuario o clave incorrectos"

            case INERNET_CONNECTION_ERROR:
                return "Error en la conexion a internet";

            default:
                return "Unexpected error";
        }
    }
}
