package com.academyproject.student;

import com.academyproject.student.student.faker.StudentFaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StudentApplication {

    @Autowired
    private StudentFaker studentFaker;

    public static void main(String[] args) {
        SpringApplication.run(StudentApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner faker() {
//        return args -> {
//            System.out.println("Creating faker...");
//
//            studentFaker.generateDummyData();
//
//            System.out.println("faker created!");
//        };
//    }
}
