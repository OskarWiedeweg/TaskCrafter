package com.oskarwiedeweg.taskcrafterbackend.students;

import com.oskarwiedeweg.taskcrafterbackend.util.IdGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    private StudentService underTest;

    @Mock private StudentRepository studentRepository;
    @Mock private IdGenerator idGenerator;

    @BeforeEach
    void setUp() {
        underTest = new StudentService(studentRepository, idGenerator);
    }

    @Test
    void testCreateStudent() {
        // given
        String studentName = "studentName";
        String studentId = "studentId";

        // when
        when(idGenerator.generateId(Student.ID_PREFIX)).thenReturn(studentId);

        // then
        underTest.createStudent(studentName);

        verify(studentRepository).insertStudent(Student.builder()
                        .name(studentName)
                        .id(studentId)
                .build());
    }
}