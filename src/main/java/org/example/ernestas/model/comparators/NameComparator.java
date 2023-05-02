package org.example.ernestas.model.comparators;

import org.example.ernestas.model.Student;

import java.util.Comparator;

public class NameComparator implements Comparator<Student> {
    @Override
    public int compare(Student p1, Student p2) {
        return CharSequence.compare(p1.getName(), p2.getName());
    }
}
