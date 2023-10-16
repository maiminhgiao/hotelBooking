package projectHotel.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @PostMapping("")
    public ResponseEntity<?> getBlog (){
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
}
