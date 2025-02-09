package com.HE.school;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/schools")
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService schoolService;

    @PostMapping
    public School saveSchool(@RequestBody School school) {
        return schoolService.saveSchool(school);
    }

    @GetMapping
    public List<School> getAllSchools() {
        return schoolService.getAllSchools();
    }

    @GetMapping("/{id}")
    public FullSchoolResponse findSchoolWithStudents(@PathVariable Integer id) {
        return schoolService.findSchoolsWithStudents(id);
    }
}
