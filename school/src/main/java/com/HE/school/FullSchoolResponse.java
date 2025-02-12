package com.HE.school;
import lombok.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class FullSchoolResponse {
    private String name;
    private String email;
    private List<Student> students;

}
