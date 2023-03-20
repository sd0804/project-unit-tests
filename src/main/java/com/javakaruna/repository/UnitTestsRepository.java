package com.javakaruna.repository;

import com.javakaruna.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UnitTestsRepository extends CrudRepository<Employee, Integer> {

}
