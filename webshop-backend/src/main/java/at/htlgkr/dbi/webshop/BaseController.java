package at.htlgkr.dbi.webshop;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BaseController {
    public <T> ResponseEntity<T> response(T returnVal){
        if(returnVal == null) return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        return new ResponseEntity<>(returnVal, HttpStatus.OK);
    }
}
