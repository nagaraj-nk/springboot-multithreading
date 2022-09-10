package com.srjons.multithreading.controller;

import com.srjons.multithreading.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/get")
    public String get() {
        log.info("started at "+ new Date());
        Future<String> stringFuture = employeeService.get();
        try {
            String s = stringFuture.get(5, TimeUnit.SECONDS);
            log.info("completed at "+ new Date());
            return s;
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
            log.error("error: "+ e.getMessage());
            if (!stringFuture.cancel(true)) {
                log.info("not cancelled, interrupting");
                stringFuture.cancel(true);
            }
            log.info("failure processing goes here");
        }

        log.info("failed at "+ new Date());
        return "failed";
    }
}
