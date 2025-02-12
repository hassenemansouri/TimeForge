package com.HE.student;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/save")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        System.out.println("StudentService: " + studentService); // Debugging
        Student savedStudent = studentService.saveStudent(student);
        return ResponseEntity.ok(savedStudent);
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
