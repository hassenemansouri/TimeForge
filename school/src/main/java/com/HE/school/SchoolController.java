package com.HE.school;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schools")
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping("/save")
    public ResponseEntity<School> saveSchool(@RequestBody School school) {
        return ResponseEntity.ok(schoolService.saveSchool(school));
    }

    @GetMapping
    public ResponseEntity<List<School>> findAllSchools() {
        return ResponseEntity.ok(schoolService.findAllSchools());
    }

    @GetMapping("/with-students/{schoolId}")
    public ResponseEntity<FullSchoolResponse> findSchoolWithStudents(@PathVariable("schoolId") Integer schoolId) {
        return ResponseEntity.ok(schoolService.findSchoolWithStudents(schoolId));
    }
}
