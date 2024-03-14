package org.example.quiz2.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.quiz2.Api.ApiResponse;
import org.example.quiz2.Model.Student;
import org.example.quiz2.Service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/student")
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/get")
    public ResponseEntity getStudents(){
        ArrayList<Student>student=studentService.getStudents();
        return ResponseEntity.status(200).body(student);
    }
    @PostMapping("/add")
    public ResponseEntity addStudents(@RequestBody @Valid Student student, Errors errors){
        if(errors.hasErrors()){
        String message = errors.getFieldError().getDefaultMessage();
        return ResponseEntity.status(400).body(message);
        }
        studentService.addStudents(student);
        return ResponseEntity.status(200).body(new ApiResponse("Student added"));
    }


        @PutMapping("/update/{id}")
    public ResponseEntity updateStudents( @PathVariable int id ,@RequestBody @Valid Student student, Errors errors){
        if(errors.hasErrors()){
        String message = errors.getFieldError().getDefaultMessage();
        return ResponseEntity.status(400).body(message);
        }
            if(studentService.updateStudent(id,student)) {
            return ResponseEntity.status(200).body(new ApiResponse("Student updated"));
        }
        else{
            return ResponseEntity.status(400).body(new ApiResponse("Invalid id"));
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteStudents(@PathVariable int id ){

        if(studentService.deleteStudent(id)){
            return ResponseEntity.status(200).body(new ApiResponse("Student deleted"));

        }
        else{
            return ResponseEntity.status(400).body(new ApiResponse("Invalid id"));
        }

    }

    @GetMapping("getname/{name}")
    public ResponseEntity searchName(@PathVariable String name ){
        if(studentService.searchName(name)==null){
            return ResponseEntity.status(400).body(new ApiResponse("No students with this name"));
        }
        else{
            return ResponseEntity.status(200).body(studentService.searchName(name));
        }
    }



    @GetMapping("getmajor/{major}")
    public ResponseEntity searchMajor(@PathVariable String major){
        if(studentService.searchMajor(major)==null){
            return ResponseEntity.status(400).body(new ApiResponse("No students with this Major"));
        }
        else{
            return ResponseEntity.status(200).body(studentService.searchMajor(major));
        }
    }

}
