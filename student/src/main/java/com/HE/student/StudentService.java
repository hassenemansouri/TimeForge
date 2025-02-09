package com.HE.student;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service

public class StudentService implements IStudent{

    private StudentRepository studentRepository;


    public Student saveStudent(Student student) {
        studentRepository.save(student);
        return student;
    }

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll ();
    }

    public List<Student> findStudentsBySchools(Integer schoolId) {
        return studentRepository.findAllBySchoolId(schoolId);
    }
}
