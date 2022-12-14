package com.srjons.multithreading.controller;

import com.srjons.multithreading.service.EmployeeRequestServiceWithTimeout;
import com.srjons.multithreading.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeRequestServiceWithTimeout employeeRequestServiceWithTimeout;

    @GetMapping("/get")
    public String get() {
        employeeRequestServiceWithTimeout.get();
        log.info("request submitted");
        return "request submitted";
    }
}
