package com.example.uczelnie.endpoints;

import com.example.uczelnie.endpoints.schemas.AddBuildingSchema;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddBuildingHandler {

    private final ObjectMapper objectMapper;

    public AddBuildingHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostMapping("/example")
    public ResponseEntity<String> handlePostRequest(@RequestBody String requestBody) {
        try {
            AddBuildingSchema request = objectMapper.readValue(requestBody, AddBuildingSchema.class);

            // Return a response
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
