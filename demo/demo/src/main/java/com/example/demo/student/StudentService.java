package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class StudentService {


   private final StudentRepository studentRepository;

   @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents()
    {
        return  studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
      Optional<Student> studentOptional=
              studentRepository.findStudentByEmail(student.getEmail());
      if(studentOptional.isPresent())
      {
          throw new IllegalStateException("email taken");
      }
      studentRepository.save(student);
       System.out.println(student);
    }

    public void deleteStudent(Long studentId) {
       boolean exists=studentRepository.existsById(studentId);
       if(!exists)
       {
           throw new IllegalStateException("student with id "+studentId+" does not exists");
       }
       studentRepository.deleteById(studentId);
    }


    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
       Student student=studentRepository.findById(studentId)
               .orElseThrow(() -> new IllegalStateException("student with id "+studentId+" does not exixts"));

       if(name!=null && !name.isEmpty() && !Objects.equals(student.getName(),name))
       {
           student.setName(name);
       }

        if(email!=null && !email.isEmpty() && !Objects.equals(student.getEmail(),email))
        {
            Optional<Student> studentOptional=
                    studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent())
            {
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }

    }
}
