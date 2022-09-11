package com.srjons.multithreading.service;

import com.srjons.multithreading.entity.Employee;
import com.srjons.multithreading.repository.EmployeeRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.Future;

@Service
@Slf4j
public class TestEmployeeAsyncRequestService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Async("testAsyncExecutor")
    public void runAsync(int id) {
        int records = 100;
        for (int i = 1; i <= records; i++) {
            Employee employee = Employee.builder()
                    .employeeId(System.currentTimeMillis())
                    .firstName(UUID.randomUUID().toString())
                    .lastName(UUID.randomUUID().toString())
                    .emailId(UUID.randomUUID() + "@srjons.com")
                    .bandLevel(UUID.randomUUID().toString())
                    .salary((double) System.currentTimeMillis() / 100000)
                    .designation("Designation-" + id)
                    .build();
            employeeRepo.save(employee);
            log.info("saved employee. reqId=" + id + ", count=" + i);
        }
    }
}
