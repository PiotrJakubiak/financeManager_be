package pl.edu.wat.trainingManager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FOUND, reason = "Transaction exist in system")
public class TransactionExistException extends RuntimeException {
}
