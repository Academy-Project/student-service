package com.academyproject.student.student_transcript.controllers;

import com.academyproject.student.student.entities.Student;
import com.academyproject.student.student.repositories.StudentRepository;
import com.academyproject.student.student.request.CreateStudentRequest;
import com.academyproject.student.student_transcript.entities.StudentTranscript;
import com.academyproject.student.student_transcript.repositories.StudentTranscriptRepository;
import com.academyproject.student.student_transcript.request.CreateStudentTranscriptRequest;
import com.academyproject.student.student_transcript.response.StudentTranscriptResource;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StudentTranscriptControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentTranscriptRepository studentTranscriptRepository;

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
        Student student = Student.builder()
                .nim("672021077")
                .name("Agung Prasetyo Nugroho")
                .address("Kec. Pabelan Kab. Semarang")
                .phone("0234234324323")
                .build();
        studentRepository.save(student);

        var createRequest = new CreateStudentTranscriptRequest();
        createRequest.setNim(student.getNim());
        createRequest.setSemester("20213/01");
        createRequest.setIpk(3.0);
        createRequest.setSks(20);

        var result = mockMvc.perform(post("/api/students/transcripts")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isOk())
                .andReturn();

        System.out.println("Response content: " + result.getResponse().getContentAsString());


        // ensure  request and response is valid
//        mockMvc.perform(post("/api/students/transcripts")
//                .contentType("application/json")
//                .content(objectMapper.writeValueAsString(createRequest))
//        ).andExpectAll(
//                status().isCreated(),
//                jsonPath("$.message").value("Student transcript created"),
//                jsonPath("$.data.ipk").value(createRequest.getIpk()),
//                jsonPath("$.data.sks").value(createRequest.getSks())
//        );

        // Test data is created in database
//        StudentTranscript studentTranscript = studentTranscriptRepository.findFirstByNimAndSemesterAndIpkAndSks(
//                student
//                createRequest.getSemester(),
//                createRequest.getIpk(),
//                createRequest.getSks()
//        ).orElse(null);
//
//        assertNotNull(studentTranscript);
    }


    @Test
    void testCannotBlankCreateStudent() throws Exception {
        // mock data request (Name is blank)
        CreateStudentRequest createStudentRequest = new CreateStudentRequest();
        createStudentRequest.setNim("672021077");
        createStudentRequest.setAddress("Kec. Pabelan Kab. Semarang");
        createStudentRequest.setPhone("0234234324323");

        // ensure  request and response is valid
        mockMvc.perform(post("/api/students")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(createStudentRequest))
        ).andExpectAll(status().isUnprocessableEntity());

        // Test data is created in database
        Student student = studentRepository.findById(createStudentRequest.getNim()).orElse(null);
        assertNull(student);
    }

    @Test
    void testCannotDuplicateNIM() throws Exception {
        // mock data request
        CreateStudentRequest createStudentRequest = new CreateStudentRequest();
        createStudentRequest.setNim("672021077");
        createStudentRequest.setName("Agung Prasetyo Nugroho");
        createStudentRequest.setAddress("Kec. Pabelan Kab. Semarang");
        createStudentRequest.setPhone("0234234324323");

        // store first data with same NIM
        studentRepository.save(Student.builder()
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
        Long contNim = studentRepository.countByNim(createStudentRequest.getNim());
        assertEquals(1L, contNim);
    }

}