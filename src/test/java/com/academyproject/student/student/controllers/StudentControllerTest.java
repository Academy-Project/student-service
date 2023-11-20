package com.academyproject.student.student.controllers;

import com.academyproject.student.student.entities.Student;
import com.academyproject.student.student.repositories.StudentRepository;
import com.academyproject.student.student.request.CreateStudentRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        studentRepository.deleteAll();
    }

    @Test
    void testPopulateStudents() {

    }

    @Test
    void testSuccessCreateStudent() throws Exception {
        // mock data request
        CreateStudentRequest createStudentRequest = new CreateStudentRequest();
        createStudentRequest.setId("177");
        createStudentRequest.setNim("672021077");
        createStudentRequest.setName("Agung Prasetyo Nugroho");
        createStudentRequest.setAddress("Kec. Pabelan Kab. Semarang");
        createStudentRequest.setPhone("0234234324323");

        // ensure  request and response is valid
        mockMvc.perform(post("/api/students")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(createStudentRequest))
        ).andExpectAll(
                status().isCreated(),
                jsonPath("$.message").value("Successfully create new student"),
                jsonPath("$.data.id").value(createStudentRequest.getId()),
                jsonPath("$.data.nim").value(createStudentRequest.getNim()),
                jsonPath("$.data.name").value(createStudentRequest.getName()),
                jsonPath("$.data.address").value(createStudentRequest.getAddress()),
                jsonPath("$.data.phone").value(createStudentRequest.getPhone())
        );

        // Test data is created in database
        Student student = studentRepository.findById(createStudentRequest.getId()).orElse(null);
        assertNotNull(student);
        assertEquals(createStudentRequest.getId(), student.getId());
        assertEquals(createStudentRequest.getNim(), student.getNim());
        assertEquals(createStudentRequest.getName(), student.getName());
        assertEquals(createStudentRequest.getAddress(), student.getAddress());
        assertEquals(createStudentRequest.getPhone(), student.getPhone());
    }


    @Test
    void testCannotBlankCreateStudent() throws Exception {
        // mock data request (Name is blank)
        CreateStudentRequest createStudentRequest = new CreateStudentRequest();
        createStudentRequest.setId("177");
        createStudentRequest.setNim("672021077");
        createStudentRequest.setAddress("Kec. Pabelan Kab. Semarang");
        createStudentRequest.setPhone("0234234324323");

        // ensure  request and response is valid
        mockMvc.perform(post("/api/students")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(createStudentRequest))
        ).andExpectAll(status().isUnprocessableEntity());

        // Test data is created in database
        Student student = studentRepository.findById(createStudentRequest.getId()).orElse(null);
        assertNull(student);
    }

    @Test
    void testCannotDuplicateNIM() throws Exception {
        // mock data request
        CreateStudentRequest createStudentRequest = new CreateStudentRequest();
        createStudentRequest.setId("177");
        createStudentRequest.setNim("672021077");
        createStudentRequest.setName("Agung Prasetyo Nugroho");
        createStudentRequest.setAddress("Kec. Pabelan Kab. Semarang");
        createStudentRequest.setPhone("0234234324323");

        // store first data with same NIM
        studentRepository.save(Student.builder()
                .id(createStudentRequest.getId())
                .nim(createStudentRequest.getNim())
                .name(createStudentRequest.getName())
                .address(createStudentRequest.getAddress())
                .phone(createStudentRequest.getPhone())
                .build());

        // ensure  Cannot create student with same NIM
        mockMvc.perform(post("/api/students")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(createStudentRequest))
        ).andExpectAll(
                status().isUnprocessableEntity(),
                jsonPath("$.errors[0]").value("NIM already exists")
        );

        // Ensure in database only has one data with same NIM
        Long contId = studentRepository.countById(createStudentRequest.getId());
        assertEquals(1L, contId);
    }

}