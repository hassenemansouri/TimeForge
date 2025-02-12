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

    public List<School> FindAllSchools() {
        return schoolRepository.findAll();
    }

    public FullSchoolResponse findSchoolsWithStudents(Integer schoolId) {
        var school = schoolRepository.findById(schoolId)
                .orElseGet(() -> new School(null, "NOT-FOUND", "NOT-FOUND"));

        var students = studentClient.findAllStudentsBySchool(schoolId);

        return new FullSchoolResponse(school.getName(), school.getEmail(), students);
    }
}
