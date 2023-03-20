package com.javakaruna.service;

import com.javakaruna.entity.Employee;
import com.javakaruna.repository.UnitTestsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;



@SpringBootTest
class UnitTestsServiceTest {


    @Autowired
    private UnitTestsService unitTestsService;

    @MockBean
    private UnitTestsRepository unitTestsRepository;

    @Test
    public void testCreateEmployee(){

        Employee emp = new Employee();
        emp.setId(1);
        emp.setAddress("ranchi");
        emp.setName("karuna");
        Mockito.when(unitTestsRepository.save(emp)).thenReturn(emp);
        assertThat(unitTestsService.createEmployee(emp)).isEqualTo(emp);

    }

    @Test
    public void testGetTicketById(){
        Employee emp = new Employee();
        emp.setId(1);
        emp.setAddress("ranchi");
        emp.setName("karuna");

        Mockito.when(unitTestsRepository.findById(1)).thenReturn(Optional.of(emp));
        assertThat(unitTestsService.getEmployeeById(1)).isEqualTo(Optional.of(emp));
    }

    @Test
    public void testGetEmployees(){
        Employee emp1 = new Employee();
        emp1.setId(1);
        emp1.setAddress("ranchi");
        emp1.setName("karuna");

        Employee emp2 = new Employee();
        emp2.setId(1);
        emp2.setAddress("raipur");
        emp2.setName("siddharth");

        List<Employee> empList = new ArrayList<>();
        empList.add(emp1);
        empList.add(emp2);

        Mockito.when(unitTestsRepository.findAll()).thenReturn(empList);

        assertThat(unitTestsService.getEmployees()).isEqualTo(empList);
    }




}