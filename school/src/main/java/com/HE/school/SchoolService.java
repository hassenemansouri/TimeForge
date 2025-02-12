package com.HE.school;

import com.HE.school.client.StudentClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolService {

    private final StudentClient studentClient;
    private final SchoolRepository schoolRepository;

    public School saveSchool(School school) {
        return schoolRepository.save(school);
    }

    public List<School> findAllSchools() {
        return schoolRepository.findAll();
    }

    public FullSchoolResponse findSchoolWithStudents(Integer schoolId) {
        var school = schoolRepository.findById(schoolId)
                .orElse(School.builder()
                        .name("NOT-FOUND")
                        .email("NOT-FOUND")
                        .build());

        var students = studentClient.findAllStudentsBySchool(schoolId);

        return FullSchoolResponse.builder()
                .name(school.getName())
                .email(students.isEmpty() ? "NO-STUDENT" : students.get(0).getEmail())
                .students(students)
                .build();
    }
}
