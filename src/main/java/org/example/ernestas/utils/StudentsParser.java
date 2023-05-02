package org.example.ernestas.utils;

import org.example.ernestas.model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentsParser {

    private StudentsParser() {
    }

    public static List<Student> parseStudents(File file) {
        List<Student> students = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file.getPath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                double grade = Double.parseDouble(parts[1]);
                Student person = new Student(name, grade);
                students.add(person);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return students;
    }

}
