package com.HE.school;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@RequiredArgsConstructor
public class SchoolService implements ISchool {

    private SchoolRepository schoolRepository;

    public void saveSchool(School school) {
        schoolRepository.save(school);
    }

    public List<School> getSchools() {
        return (List<School>) schoolRepository.findAll ();
    }
}
