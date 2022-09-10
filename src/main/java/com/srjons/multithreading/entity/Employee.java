package com.srjons.multithreading.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "employee_collection")
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    private long employeeId;
    private String firstName;
    private String lastName;
    private String emailId;
    private double salary;
    private String bandLevel;
    private String designation;
}
