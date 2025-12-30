package demoapp.newjwtspringsecurity.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/test")
@Valid
public class TestApi {

    @GetMapping
    public ResponseEntity<String> getString(
        @RequestParam @NotBlank String name){
        if (Objects.equals(name, "ex")) {
            throw new NullPointerException("npe");
        }
        return ResponseEntity.ok().body("String");
    }
}
