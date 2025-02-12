package com.HE.school;


import org.springframework.http.ResponseEntity;
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
    public List<School> FindAllSchools() {
        return schoolService.FindAllSchools();
    }

    @GetMapping("/with-students/{school-id}")
    public ResponseEntity<FullSchoolResponse> findAllSchools(@PathVariable("school-id") Integer schoolId) {
        return ResponseEntity.ok (schoolService.findSchoolsWithStudents ( schoolId ));
    }
}
