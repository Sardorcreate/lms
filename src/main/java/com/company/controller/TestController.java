package com.company.controller;

import com.company.dto.TestDto;
import com.company.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @PostMapping("/create")
    public ResponseEntity<String> createTest(@RequestBody TestDto testDto) {

        String text = testService.createTest(testDto);

        return ResponseEntity.ok().body(text);
    }

    @GetMapping("/start")
    public ResponseEntity<List<TestDto>> startTest() {

        List<TestDto> testDtos = testService.startTest();

        return ResponseEntity.ok().body(testDtos);
    }
}
