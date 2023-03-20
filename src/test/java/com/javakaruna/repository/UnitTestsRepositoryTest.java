package com.javakaruna.repository;

import com.javakaruna.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@DataJpaTest
class UnitTestsRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UnitTestsRepository unitTestsRepository;

    @Test
    public void testSaveEmployee(){
        Employee emp = new Employee();
        emp.setName("karuna sharma");
        emp.setAddress("ranchi");
        Employee savedInDb = entityManager.persist(emp);
        Optional<Employee> getFromDb = unitTestsRepository.findById(savedInDb.getId());

        assertThat(getFromDb).isEqualTo(Optional.of(savedInDb));
    }

    @Test
    public void testGetEmployeeById(){
        Employee emp = new Employee();
        emp.setName("karuna sharma");
        emp.setAddress("ranchi");

        Employee employeeSavedInDb = entityManager.persist(emp);
        Optional<Employee> employeeFromInDb = unitTestsRepository.findById(employeeSavedInDb.getId());
        assertThat(employeeFromInDb).isEqualTo(Optional.of(employeeSavedInDb));
    }

    @Test
    public void testGetEmployees(){
        Employee emp1 = new Employee();
        emp1.setName("karuna sharma");
        emp1.setAddress("ranchi");

        Employee emp2 = new Employee();
        emp2.setName("karuna sharma");
        emp2.setAddress("ranchi");

        entityManager.persist(emp1);
        entityManager.persist(emp2);

        Iterable<Employee> allEmployeesFromDb = unitTestsRepository.findAll();
        List<Employee> empList = new ArrayList<>();

        for (Employee e : allEmployeesFromDb) {
            empList.add(e);
        }
        assertThat(empList.size()).isEqualTo(2);
    }


}