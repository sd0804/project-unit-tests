package com.javakaruna.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javakaruna.entity.Employee;
import com.javakaruna.service.UnitTestsService;
import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.assertj.core.api.Assertions.assertThat;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import  static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebMvcTest
class UnitTestsControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UnitTestsService unitTestsService;

    @Test
    public void testCreateEmployee() throws Exception {

        Employee emp= new Employee();
        emp.setId(1);
        emp.setName("Karuna");
        emp.setAddress("Ranchi");

        String inputInJson = this.mapToJson(emp);
        String URI = "/employee";
        Mockito.when(unitTestsService.createEmployee(Mockito.any(Employee.class))).thenReturn(emp);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputInJson = response.getContentAsString();
        assertThat(outputInJson).isEqualTo(inputInJson);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }


        @Test
    void testGetEmployeeById() throws Exception {

            Employee emp = new Employee();
            emp.setId(1);
            emp.setName("Karuna");
            emp.setAddress("Ranchi");
            Mockito.when(unitTestsService.getEmployeeById(Mockito.anyInt())).thenReturn(Optional.of(emp));

            String URI = "/employee/1";
            RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                    URI).accept(
                    MediaType.APPLICATION_JSON);

            MvcResult result = mvc.perform(requestBuilder).andReturn();
            String expectedJson = this.mapToJson(emp);
            String outputInJson = result.getResponse().getContentAsString();
            assertThat(outputInJson).isEqualTo(expectedJson);

    }

    @Test
    public void testGetAllEmployees() throws Exception {

        Employee emp1 = new Employee();
        emp1.setId(1);
        emp1.setName("Karuna");
        emp1.setAddress("Ranchi");

        Employee emp2 = new Employee();
        emp2.setId(2);
        emp2.setName("Siddharth");
        emp2.setAddress("Ranchi");

        List<Employee> empList = new ArrayList<>();
        empList.add(emp1);
        empList.add(emp2);

        Mockito.when(unitTestsService.getEmployees()).thenReturn(empList);

        String URI = "/employees";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                URI).accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mvc.perform(requestBuilder).andReturn();

        String expectedJson = this.mapToJson(empList);
        String outputInJson = result.getResponse().getContentAsString();
        assertThat(outputInJson).isEqualTo(expectedJson);
    }




    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

}