package com.oskarwiedeweg.taskcrafterbackend.students;

import com.oskarwiedeweg.taskcrafterbackend.util.IdGenerator;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final IdGenerator idGenerator;

    public void createStudent(String name) {
        Student newStudent = Student.builder()
                .id(idGenerator.generateId(Student.ID_PREFIX))
                .name(name)
                .build();

        studentRepository.insertStudent(newStudent);
    }

}
