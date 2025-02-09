package com.HE.student;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@RequiredArgsConstructor
public class StudentService implements IStudent{

    private StudentRepository studentRepository;


    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public List<Student> getStudents() {
        return (List<Student>) studentRepository.findAll ();
    }
}
