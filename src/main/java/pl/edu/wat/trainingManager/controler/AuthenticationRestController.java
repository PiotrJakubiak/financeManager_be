package pl.edu.wat.trainingManager.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.trainingManager.exceptions.AuthenticationException;
import pl.edu.wat.trainingManager.security.JwtAuthenticationRequest;
import pl.edu.wat.trainingManager.service.IAuthenticationService;

@RestController
@CrossOrigin(origins = "*")
public class AuthenticationRestController {

    @Autowired
    private IAuthenticationService authenticationService;

    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST,  produces = "application/json")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest)
            throws AuthenticationException {

        authenticationService.authenticate(authenticationRequest);
        return ResponseEntity.ok(authenticationService.generateToken(authenticationRequest));
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
}
