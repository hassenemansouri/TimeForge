package com.HE.school.client;


import com.HE.school.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@Repository
@FeignClient(name = "student-service" , url = "${application.config.students-url}")
public interface StudentClient {
    @GetMapping(("/school/{school-id}"))
    List<Student> findAllStudentsBySchool(@PathVariable("school-id") Integer schoolId);
}
