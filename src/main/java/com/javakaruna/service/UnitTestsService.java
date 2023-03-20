package com.javakaruna.service;


import com.javakaruna.repository.UnitTestsRepository;
import com.javakaruna.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnitTestsService {

    @Autowired
    private UnitTestsRepository unitTestsRepository;

    public Iterable<Employee> getEmployees() {
        return unitTestsRepository.findAll();
    }

    public Employee createEmployee(Employee employee) {
        return unitTestsRepository.save(employee);
    }

    public Optional<Employee> getEmployeeById(Integer id)
    {
        return unitTestsRepository.findById(id);
    }


    public Iterable<Employee> createEmployees(List<Employee> emp) {
        return unitTestsRepository.saveAll(emp);
    }
}
