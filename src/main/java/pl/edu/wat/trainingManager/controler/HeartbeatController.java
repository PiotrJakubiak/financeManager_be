package pl.edu.wat.trainingManager.controler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Created by Piotr on 10.06.2018.
 */
@Controller
public class HeartbeatController
{
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getHeartbeat() throws UnsupportedEncodingException {
        byte[] message = "hello world".getBytes("UTF-8");
        String encoded = DatatypeConverter.printBase64Binary(message);
        System.out.println(encoded);
        return new ResponseEntity<Void> (HttpStatus.OK);
    }
}
