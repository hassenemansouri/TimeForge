package com.HE.school;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class School {
    private Integer id;
    private String name;
    private String email;

    public School(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

}
