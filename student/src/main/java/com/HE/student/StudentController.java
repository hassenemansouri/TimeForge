package com.HE.student;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController {

    private StudentService studentService;

    @PostMapping("/save")
    public Student saveStudent(@RequestBody Student student) {
        return studentService.saveStudent ( student );
    }
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getStudents ();
    }
    @GetMapping("/school/{school-id}")
    public List<Student> getAllStudents(@PathVariable("school-id")  Integer schoolId) {
        return studentService.findStudentsBySchools(schoolId);
    }
}
