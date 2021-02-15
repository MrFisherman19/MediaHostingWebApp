package mrfisherman.hosting.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicController {

    @GetMapping("/login")
    public void login(){
        //Spring security
    }

}
