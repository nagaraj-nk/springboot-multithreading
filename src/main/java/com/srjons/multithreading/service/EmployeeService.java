package com.srjons.multithreading.service;

import com.srjons.multithreading.entity.Employee;
import com.srjons.multithreading.repository.EmployeeRepo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
@Slf4j
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    public boolean addEmployee(Employee employee) {
        try {
            employeeRepo.save(employee);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("error while adding employee. error: " + e.getMessage());
        }
        return false;
    }

    @Async("asyncExecutor")
    public Future<String> get() {
        long t =System.currentTimeMillis() + 10000;
        while (System.currentTimeMillis() <= t) {
            try {
                FileUtils.writeStringToFile(new File("test.txt"),
                        System.currentTimeMillis() + "\n", Charset.defaultCharset(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (Thread.currentThread().isInterrupted()) {
                return new AsyncResult<>("Cancelled");
            }
        }
        return new AsyncResult<>("Completed");
    }
}
