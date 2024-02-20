package com.oskarwiedeweg.taskcrafterbackend.students;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Student {

    public static final String ID_PREFIX = "stud";

    private final String id;
    private final String name;

}
