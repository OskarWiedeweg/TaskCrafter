package com.oskarwiedeweg.taskcrafterbackend.students;

import lombok.Data;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Data
@Repository
public class StudentRepository {

    private final JdbcTemplate jdbcTemplate;

    public void insertStudent(Student student) {
        jdbcTemplate.update(
                "insert into students (id, name) values (?, ?)",
                student.getId(),
                student.getName()
        );
    }

}
