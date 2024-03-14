package org.example.quiz2.Service;

import org.example.quiz2.Model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService {
    ArrayList<Student>students=new ArrayList<>();

    public ArrayList<Student> getStudents(){
        return students ;
    }

    public void addStudents(Student student){
        students.add(student);
    }

    public boolean updateStudent(int id , Student student){
        for (int i = 0; i < students.size(); i++) {
            if(students.get(i).getId()==id){
                students.set(i,student);
                return true;
            }
        }
        return false;
    }

    public boolean deleteStudent(int id){
        for (int i = 0; i < students.size(); i++) {
            if(students.get(i).getId()==id){
                students.remove(i);
                return true;
            }
        }
        return false ;
    }

    public Student searchName(String name){
        for (int i = 0; i < students.size(); i++) {
            if(students.get(i).getName().equalsIgnoreCase(name)){
                return students.get(i);
            }
        }
        return null;
    }


    public ArrayList<Student> searchMajor(String major){
        ArrayList<Student>search=new ArrayList<>();
        for (int i = 0; i <students.size() ; i++) {
            if(students.get(i).getMajor().equalsIgnoreCase(major)){
                search.add(students.get(i));
                return search;
            }
        }
        return null;
    }
}
