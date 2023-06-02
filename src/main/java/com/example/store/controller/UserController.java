package com.example.store.controller;

import com.example.store.dto.ResponseMessage;
import com.example.store.dto.UserRequest;
import com.example.store.service.IUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/v1/users")
public class UserController {

    private IUserService userService;

    @PostMapping("/user")
    public ResponseEntity<ResponseMessage> save(@RequestBody UserRequest userRequest) {
        userService.save(userRequest);
        return new ResponseEntity(new ResponseMessage(HttpStatus.OK.value(), "User Saved Successfully"), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ResponseMessage> getAll(@RequestParam(required = false) String query) {
        return new ResponseEntity<>(new ResponseMessage(userService.getAll(query), HttpStatus.OK.value()), HttpStatus.OK);
    }

}
