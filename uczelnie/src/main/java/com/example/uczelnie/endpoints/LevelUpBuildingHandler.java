package com.example.uczelnie.endpoints;

import com.example.uczelnie.endpoints.schemas.LevelUpBuildingSchema;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LevelUpBuildingHandler {
    private final ObjectMapper objectMapper;

    public LevelUpBuildingHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostMapping("/examplelevel")
    public ResponseEntity<String> handlePostRequest(@RequestBody String requestBody) {
        try {
            LevelUpBuildingSchema request = objectMapper.readValue(requestBody, LevelUpBuildingSchema.class);


            // Return a response
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
