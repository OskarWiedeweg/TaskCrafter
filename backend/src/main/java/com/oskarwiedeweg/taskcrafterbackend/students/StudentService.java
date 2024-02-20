package com.oskarwiedeweg.taskcrafterbackend.students;

import com.oskarwiedeweg.taskcrafterbackend.exceptions.StudentNotFoundException;
import com.oskarwiedeweg.taskcrafterbackend.util.IdGenerator;
import com.oskarwiedeweg.taskcrafterbackend.util.SecretGenerator;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final IdGenerator idGenerator;
    private final SecretGenerator secretGenerator;

    public void createStudent(String name) {
        Student newStudent = Student.builder()
                .id(idGenerator.generateId(Student.ID_PREFIX))
                .secret(secretGenerator.generateSecret())
                .name(name)
                .build();

        studentRepository.insertStudent(newStudent);
    }

    public void rollStudentSecret(String studentId) {
        String newSecret = secretGenerator.generateSecret();

        if (!studentRepository.updateStudentSecret(studentId, newSecret)) {
            throw new StudentNotFoundException();
        }
    }

}
