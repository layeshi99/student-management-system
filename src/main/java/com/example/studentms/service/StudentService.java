package com.example.studentms.service;

import com.example.studentms.dto.StudentDTO;
import com.example.studentms.entity.Student;
import com.example.studentms.repo.StudentRepo;
import com.example.studentms.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private ModelMapper modelMapper;
    public String saveStudent(StudentDTO studentDTO){
        if(studentRepo.existsById(studentDTO.getStdId())){
            return VarList.RSP_DUPLICATED;
        }else{
            studentRepo.save(modelMapper.map(studentDTO, Student.class));
            return VarList.RSP_SUCCESS;
        }

    }
}
