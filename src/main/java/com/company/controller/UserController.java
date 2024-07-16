package com.company.controller;

import com.company.dto.UserDto;
import com.company.dto.UserFilterDto;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN')")
    @PutMapping("/update/rsv")
    public ResponseEntity<String> updateRoleAndStatusAndVisible(@RequestBody UserDto userDto) {

        String text = userService.updateRSV(userDto);

        return ResponseEntity.ok().body(text);
    }

    @PutMapping("/update/nsp")
    public ResponseEntity<String> updateNameAndSurnameAndPassword(@RequestBody UserDto userDto) {

        String text = userService.updateNSP(userDto);

        return ResponseEntity.ok().body(text);
    }

    @PutMapping("/update/i")
    public ResponseEntity<String> updateImagePath(@RequestBody UserDto userDto) {

        String text = userService.updateI(userDto);

        return ResponseEntity.ok().body(text);
    }

    @GetMapping("/read/all")
    public ResponseEntity<List<UserDto>> readUsers() {

        List<UserDto> userDtos = userService.readUsers();

        return ResponseEntity.ok().body(userDtos);
    }

    @GetMapping("/read/{page}/{size}")
    public ResponseEntity<List<UserDto>> getByPagination(@PathVariable("page") Integer page,
                                                         @PathVariable("size") Integer size){

        List<UserDto> userDtos = userService.getByPagination(page, size);

        return ResponseEntity.ok().body(userDtos);
    }

    @GetMapping("/get_user/list")
    public ResponseEntity<List<UserDto>> getUserList(@RequestBody UserFilterDto userFilterDto) {

        List<UserDto> userDtos = userService.getUserList(userFilterDto);

        return ResponseEntity.ok().body(userDtos);
    }
}
