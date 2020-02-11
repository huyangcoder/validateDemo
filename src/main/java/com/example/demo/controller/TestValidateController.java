package com.example.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.demo.constant.Code;
import com.example.demo.model.User;
import com.example.demo.response.Response;

import javax.validation.Valid;

@RestController
@RequestMapping("/test")
public class TestValidateController {

    @GetMapping("/validate")
    public String say() {
        int i = 1 / 0;
        return "hello";
    }

    @PostMapping(value = "/add",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response<User> add(@RequestBody @Validated User user) {

        //todo 此处为模拟返回
        Response<User> response = new Response<>();
        response.setCode(Code.SUCCESS);
        response.setResult(user);
        return response;
    }
}
