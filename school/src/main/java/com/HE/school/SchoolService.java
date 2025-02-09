package com.HE.school; // ✅ Ensure the correct package

import com.HE.school.client.StudentClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service // ✅ Make sure this annotation is present
@RequiredArgsConstructor
public class SchoolService {

    private final StudentClient studentClient;
    private final SchoolRepository schoolRepository;

    public School saveSchool(School school) {
        return schoolRepository.save(school);
    }

    public List<School> getAllSchools() {
        return schoolRepository.findAll();
    }

    public FullSchoolResponse findSchoolsWithStudents(Integer schoolid) {
        var school = schoolRepository.findById(schoolid)
                .orElseGet(() -> new School(null, "NOT-FOUND", "NOT-FOUND"));

        var students = studentClient.findAllStudentsBySchool(schoolid);

        return new FullSchoolResponse(school.getName(), school.getEmail(), students);
    }
}
