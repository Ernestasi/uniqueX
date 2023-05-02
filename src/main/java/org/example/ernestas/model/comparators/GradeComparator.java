package org.example.ernestas.model.comparators;

import org.example.ernestas.model.Student;

import java.util.Comparator;

public class GradeComparator implements Comparator<Student> {
    private boolean ascending;

    public GradeComparator(boolean ascending) {
        this.ascending = ascending;
    }

    @Override
    public int compare(Student p1, Student p2) {
        int result = Double.compare(p1.getGrade(), p2.getGrade());
        return ascending ? result : -result;
    }
}
