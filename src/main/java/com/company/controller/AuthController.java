package com.company.controller;

import com.company.dto.LoginDto;
import com.company.dto.UserDto;
import com.company.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginDto loginDto) {

        UserDto userDto = authService.login(loginDto);

        return ResponseEntity.ok().body(userDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN')")
    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody UserDto userDto1) {

        UserDto userDto = authService.signup(userDto1);

        return ResponseEntity.ok().body(userDto);
    }
}
