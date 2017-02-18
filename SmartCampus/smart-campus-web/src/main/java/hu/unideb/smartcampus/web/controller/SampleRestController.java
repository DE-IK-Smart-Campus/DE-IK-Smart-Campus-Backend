package hu.unideb.smartcampus.web.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleRestController {

    @GetMapping(path = "/sample")
    public ResponseEntity<String> getSample() {
        return ResponseEntity.ok("Content");
    }
}
