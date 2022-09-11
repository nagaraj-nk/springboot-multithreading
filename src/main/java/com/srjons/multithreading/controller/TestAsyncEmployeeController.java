package com.srjons.multithreading.controller;

import com.srjons.multithreading.service.EmployeeService;
import com.srjons.multithreading.service.TestEmployeeAsyncRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/async")
@Slf4j
public class TestAsyncEmployeeController {

    @Autowired
    TestEmployeeAsyncRequestService asyncRequestService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String get(@PathVariable("id") int id) {
        asyncRequestService.runAsync(id);
        log.info("request submitted");
        return "request submitted";
    }
}
