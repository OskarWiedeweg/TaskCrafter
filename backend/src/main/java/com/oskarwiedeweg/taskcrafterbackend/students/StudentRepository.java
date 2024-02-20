package com.oskarwiedeweg.taskcrafterbackend.students;

import lombok.Data;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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

    public boolean updateStudentSecret(String studentId, String newSecret) {
        return jdbcTemplate.update("update students set secret = ? where id = ?", newSecret, studentId) != 0;
    }

    public Optional<String> findStudentSecretById(String id) {
        return jdbcTemplate.query("select secret from students where id = ?", (rs, rn) -> rs.getString("secret"), id).stream().findFirst();
    }
}
