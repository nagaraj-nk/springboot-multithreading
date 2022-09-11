package com.srjons.multithreading.service;

import com.srjons.multithreading.entity.Employee;
import com.srjons.multithreading.repository.EmployeeRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
@Slf4j
public class EmployeeRequestServiceWithTimeout {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private EmployeeService employeeService;

    public boolean addEmployee(Employee employee) {
        try {
            employeeRepo.save(employee);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("error while adding employee. error: " + e.getMessage());
        }
        return false;
    }

    @Async("baseAsyncExecutor")
    public void get() {
        log.info("started at " + new Date());
        Future<String> stringFuture = employeeService.get();
        try {
            stringFuture.get(5, TimeUnit.SECONDS);
            log.info("completed at " + new Date());
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
            log.error("error: " + e.getMessage());
            if (!stringFuture.cancel(true)) {
                log.info("not cancelled, interrupting");
                stringFuture.cancel(true);
            }
            log.info("failure processing goes here");
        }
        log.info("failed at " + new Date());
    }
}
