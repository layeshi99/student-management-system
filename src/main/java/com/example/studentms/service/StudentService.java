package com.example.studentms.service;

import com.example.studentms.dto.StudentDTO;
import com.example.studentms.entity.Student;
import com.example.studentms.repo.StudentRepo;
import com.example.studentms.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * this method use to save a student
     * @param studentDTO
     * @return
     */
    public String saveStudent(StudentDTO studentDTO){
        if(studentRepo.existsById(studentDTO.getStdId())){
            return VarList.RSP_DUPLICATED;
        }else{
            studentRepo.save(modelMapper.map(studentDTO, Student.class));
            return VarList.RSP_SUCCESS;
        }

    }

    //To Read Students
    public List<StudentDTO> getAllStudents(){
        List<Student> studentList=studentRepo.findAll();
        return modelMapper.map(studentList,new TypeToken<ArrayList<StudentDTO>>(){
        }.getType());
    }

    //To Update Student
    public String updateStudent(StudentDTO studentDTO){
        if(studentRepo.existsById(studentDTO.getStdId())){
            studentRepo.save(modelMapper.map(studentDTO,Student.class));
            return VarList.RSP_SUCCESS;
        }else{
            return VarList.RSP_NO_DATA_FOUND;
        }

    }

    //To Search Student
    public StudentDTO searchStudent(int stdId){
        if(studentRepo.existsById(stdId)){
            Student student=studentRepo.findById(stdId).orElse(null);
            return modelMapper.map(student,StudentDTO.class);
        }else{
            return null;
        }
    }

    //To delete a student
    public String deleteStudent(int stdId){
        if(studentRepo.existsById(stdId)){
            studentRepo.deleteById(stdId);
            return VarList.RSP_SUCCESS;
        }else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
}
