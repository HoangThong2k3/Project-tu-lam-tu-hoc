package models.student;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Class {
    private int id;
    private String name;
    private String room;
    private String teacher;

}
