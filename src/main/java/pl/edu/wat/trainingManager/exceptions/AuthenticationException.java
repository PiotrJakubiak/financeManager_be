package pl.edu.wat.trainingManager.exceptions;

/**
 * Created by Piotr on 19.10.2018.
 */
public class AuthenticationException extends RuntimeException{

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
