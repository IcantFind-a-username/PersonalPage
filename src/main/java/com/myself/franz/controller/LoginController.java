package com.myself.franz.controller;

import com.myself.franz.DTO.LoginDTO;
import com.myself.franz.DTO.RegisterDTO;
import com.myself.franz.result.Result;
import com.myself.franz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginDTO loginDTO){
        try{
           return Result.success(userService.login(loginDTO));
        } catch (Exception e){
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("/register")
    public Result register(@RequestBody RegisterDTO registerDTO){
        try{
            return Result.success(userService.register(registerDTO));
        } catch (Exception e){
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/user")
    public Result getCurrentUser(){
        try{
            return Result.success(userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        } catch (Exception e){
            return Result.error(e.getMessage());
        }
    }

}
