package org.example.quiz2.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.quiz2.Api.ApiResponse;
import org.example.quiz2.Model.Teacher;
import org.example.quiz2.Service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/teacher")
public class TeacherController {
     private final TeacherService teacherService;

     @GetMapping("/get")
     public ResponseEntity getTeachers(){
          ArrayList<Teacher> teacher=teacherService.getTeachers();
          return ResponseEntity.status(200).body(teacher);
     }
     @PostMapping("/add")
     public ResponseEntity addTeacher(@RequestBody @Valid Teacher teacher, Errors errors){
          if(errors.hasErrors()){
               String message = errors.getFieldError().getDefaultMessage();
               return ResponseEntity.status(400).body(message);
          }
          teacherService. addTeachers(teacher);
          return ResponseEntity.status(200).body(new ApiResponse("Teacher added"));
     }

     @PutMapping("/update/{id}")
     public ResponseEntity updateTeachers( @PathVariable int id ,@RequestBody @Valid Teacher teacher, Errors errors){
          if(errors.hasErrors()){
               String message = errors.getFieldError().getDefaultMessage();
               return ResponseEntity.status(400).body(message);
          }
          if(teacherService.updateTeacher(id,teacher)) {
               return ResponseEntity.status(200).body(new ApiResponse("Teacher updated"));
          }
          else{
               return ResponseEntity.status(400).body(new ApiResponse("Invalid id"));
          }
     }

     @DeleteMapping("delete/{id}")
     public ResponseEntity deleteTeacher(@PathVariable int id ){

          if(teacherService.deleteTeacher(id)){
               return ResponseEntity.status(200).body(new ApiResponse("Teacher deleted"));

          }
          else{
               return ResponseEntity.status(400).body(new ApiResponse("Invalid id"));
          }

     }

     @GetMapping("getid/{id}")
     public ResponseEntity searchId(@PathVariable int id ){
          if(teacherService.searchId(id)==null){
               return ResponseEntity.status(400).body(new ApiResponse("No Teacher with this id"));
          }
          else{
               return ResponseEntity.status(200).body(teacherService.searchId(id));
          }
     }


     @GetMapping("getsalary/{salary}")
     public ResponseEntity searchSalary(@PathVariable int salary){
          if(teacherService.searchSalary(salary)==null){
               return ResponseEntity.status(400).body(new ApiResponse("No Teachers with this Salary or above"));
          }
          else{
               return ResponseEntity.status(200).body(teacherService.searchSalary(salary));
          }
     }

}
