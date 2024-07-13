package com.example.spring_security.controller;

import org.springframework.web.bind.annotation.RestController;
import com.example.spring_security.dto.Student;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RestController
public class SortingController {


    //Sorting on integer 
    @GetMapping("/sort/roll")
    public List<Student> getSortedByRoll() {
        List<Student> students = new ArrayList<>();
        students.add(Student.builder().rollNo(3).name("Rahul").address("ABC").marks(90).build());
        students.add(Student.builder().rollNo(2).name("Rakesh").address("XYZ").marks(80).build());
        students.add(Student.builder().rollNo(1).name("Rust").address("RUU").marks(99).build());
        students.add(Student.builder().rollNo(4).name("Nikhil").address("POI").marks(10).build());

        log.info("Students info [{}]",  students);


        Collections.sort(students, new Comparator<Student>() {
            public int compare(Student s1, Student s2){
                return s1.getRollNo() - s2.getRollNo();
            }
        });

        return students;
    }


    //Sorting on String
    @GetMapping("/sort/name")
    public List<Student> getSortedByName() {
        List<Student> students = new ArrayList<>();
        students.add(Student.builder().rollNo(3).name("Rahul").address("ABC").marks(90).build());
        students.add(Student.builder().rollNo(2).name("Rakesh").address("XYZ").marks(80).build());
        students.add(Student.builder().rollNo(1).name("Rust").address("RUU").marks(99).build());
        students.add(Student.builder().rollNo(4).name("Nikhil").address("POI").marks(10).build());

        log.info("Students info [{}]",  students);

        Collections.sort(students, new Comparator<Student>() {
            public int compare(Student s1, Student s2){
                return s1.getName().compareTo(s2.getName());
            }
        });

        return students;
    }

    //Sorting on double
    // return (int) (s1.getMarks()- s2.getMarks()); This is wrong as 90.2 and 90.7 will return 0, but in reality they are not equal.
    //To make comaprator wprk we just need to return integer value of -1, 1 or 0.
    @GetMapping("/sort/marks")
    public List<Student> getSortedByMarks() {
        List<Student> students = new ArrayList<>();
        students.add(Student.builder().rollNo(3).name("Rahul").address("ABC").marks(90.2).build());
        students.add(Student.builder().rollNo(2).name("Rakesh").address("XYZ").marks(80.6).build());
        students.add(Student.builder().rollNo(1).name("Rust").address("RUU").marks(99.0).build());
        students.add(Student.builder().rollNo(4).name("Nikhil").address("POI").marks(90.7).build());

        log.info("Students info [{}]",  students);

        Collections.sort(students, new Comparator<Student>() {
            public int compare(Student s1, Student s2){
                double a  = s1.getMarks()- s2.getMarks();
                if (a < 0) return -1;
                if (a > 0) return 1;
                else return 0; 
            }
        });

        return students;
    }


    //Nested sorting
    @GetMapping("/sort/nested")
    public List<Student> getSortedByNameAndRoll() {
        List<Student> students = new ArrayList<>();
        students.add(Student.builder().rollNo(3).name("Rahul").address("ABC").marks(90).build());
        students.add(Student.builder().rollNo(2).name("Rahul").address("XYZ").marks(80).build());
        students.add(Student.builder().rollNo(1).name("Rust").address("RUU").marks(99).build());
        students.add(Student.builder().rollNo(4).name("Nikhil").address("POI").marks(10).build());

        log.info("Students info [{}]",  students);

        Collections.sort(students, new Comparator<Student>() {
            public int compare(Student s1, Student s2){
                int first = s1.getName().compareTo(s2.getName());
                int second  = s2.getRollNo() - s1.getRollNo();
                return (first == 0) ? second : first;
            }
        });

        return students;
    }


    //https://www.baeldung.com/java-8-sort-lambda
    //https://www.digitalocean.com/community/tutorials/comparable-and-comparator-in-java-example
    //Link to see diff between comparator and comparable
    //Comparable uses a default sorting technique, used in compareTo function. Whereas, comparator we can compare on various parameters.


}
