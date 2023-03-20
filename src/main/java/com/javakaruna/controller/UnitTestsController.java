package com.javakaruna.controller;

import com.javakaruna.service.UnitTestsService;
import com.javakaruna.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UnitTestsController {

    @Autowired
    private UnitTestsService service;


    @PostMapping("/employee")
    public Employee createEmployee(@RequestBody Employee emp)
    {
        return service.createEmployee(emp);
    }

    @GetMapping("/employee/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable Integer id) {

        return service.getEmployeeById(id);
    }

    @GetMapping("/employees")
    public Iterable<Employee> getEmployees() {
        String s="karuna";
        String s1="tarun";

        return service.getEmployees();
    }










}
