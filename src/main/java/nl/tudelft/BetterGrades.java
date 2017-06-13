package nl.tudelft;

import io.vavr.collection.List;
import io.vavr.collection.Map;
import lombok.Value;

public class BetterGrades {

    @Value
    public static class Student {
        private String name;
        private String studentNumber;
        private int groupNumber;
    }

    public static List<Double> grades(int amountOfStudents) {
        return List.rangeBy(0.0, 100.0, 100.0 / amountOfStudents);
    }

    public static List<Student> students(int amountOfStudents) {
        return List.range(0, amountOfStudents).flatMap(i -> {
            final String number = Integer.toString(i);
            return List.of(new Student(number + 1, number + 1, i), new Student(number + 1, number + 1, i));
        });
    }

    public static Map<Integer, List<Student>> studentsByGroup(List<Student> studentList) {
        return studentList.groupBy(Student::getGroupNumber);
    }

    public static List<GradedStudent> assignGrades(Map<Integer, List<Student>> groups, List<Double> gradesPerGroup) {
        return groups.flatMap(group -> group._2.map(student -> {
            final Double gradeForGroup = gradesPerGroup.get(group._1);
            return new GradedStudent(gradeForGroup, student);
        })).toList();
    }

    @Value
    public static class GradedStudent {
        double grade;
        Student student;
    }

    public static void main(String[] args) {
        System.out.println(assignGrades(studentsByGroup(students(5)), grades(5)));
    }
}
