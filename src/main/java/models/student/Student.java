package models.student;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Student {

    private int id;
    private String name;
    private String email;
    private String phone;
    private int classId;
}

