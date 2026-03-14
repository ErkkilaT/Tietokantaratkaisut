package fi.metropolia.teemuerk.webstoreapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
    @GetMapping("/")
    public String hello(){
        return "Welcome to the Web Store API!";
    }
}
