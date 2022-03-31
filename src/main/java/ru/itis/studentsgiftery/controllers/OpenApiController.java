package ru.itis.studentsgiftery.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.api.BrandsApi;

@RestController
public class OpenApiController implements BrandsApi {
    @Override
    public ResponseEntity<String> brandsGet() {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
}
