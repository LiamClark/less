package nl.tudelft;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Grades {

    public static class Student {
        private String name;
        private String studentNumber;
        private int groupNumber;

        public Student(String name, String studentNumber, int groupNumber) {
            this.name = name;
            this.studentNumber = studentNumber;
            this.groupNumber = groupNumber;
        }

        public int getGroupNumber() {
            return groupNumber;
        }

        public void setGroupNumber(int groupNumber) {
            this.groupNumber = groupNumber;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStudentNumber() {
            return studentNumber;
        }

        public void setStudentNumber(String studentNumber) {
            this.studentNumber = studentNumber;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Student student = (Student) o;

            if (groupNumber != student.groupNumber) return false;
            if (name != null ? !name.equals(student.name) : student.name != null) return false;
            return studentNumber != null ? studentNumber.equals(student.studentNumber) : student.studentNumber == null;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (studentNumber != null ? studentNumber.hashCode() : 0);
            result = 31 * result + groupNumber;
            return result;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", studentNumber='" + studentNumber + '\'' +
                    ", groupNumber=" + groupNumber +
                    '}';
        }
    }

    public static ArrayList<Double> grades(int amountOfStudents) {
        final double gradeStep = 100.0 / amountOfStudents;
        final ArrayList<Double> grades = new ArrayList<>();

        for (int i = 0; i < amountOfStudents; i++) {
            grades.add(gradeStep * i);
        }

        return grades;
    }

    public static ArrayList<Student> students(int amountOfStudents) {
        final ArrayList<Student> students = new ArrayList<>();

        for (int i = 0; i < amountOfStudents; i++) {
            final String number = Integer.toString(i);
            students.add(new Student(number + 1, number + 1, i));
            students.add(new Student(number, number + 1, i));
        }

        return students;
    }

    public static Map<Integer, ArrayList<Student>> studentsByGroup(ArrayList<Student> studentList) {
        final HashMap<Integer, ArrayList<Student>> groups = new HashMap<>();

        for (Student student : studentList) {
            final int groupNumber = student.getGroupNumber();
            final ArrayList<Student> group = groups.get(groupNumber);

            if (group == null) {
                final ArrayList<Student> newGroup = new ArrayList<>();
                newGroup.add(student);
                groups.put(groupNumber, newGroup);
            } else {
                group.add(student);
            }
        }

        return groups;
    }

    public static ArrayList<GradedStudent> assignGrades(Map<Integer, ArrayList<Student>> groups, ArrayList<Double> gradesPerGroup) {
        final ArrayList<GradedStudent> gradedGroups = new ArrayList<>();

        for (Map.Entry<Integer, ArrayList<Student>> group : groups.entrySet()) {
            final Integer groupNumber = group.getKey();
            final Double gradeForGroup = gradesPerGroup.get(groupNumber);

            for (Student student : group.getValue()) {
                gradedGroups.add(new GradedStudent(gradeForGroup, student));
            }
        }
        return gradedGroups;
    }

    public static class GradedStudent {
        double grade;
        Student student;

        public GradedStudent(double grade, Student student) {
            this.grade = grade;
            this.student = student;
        }

        public double getGrade() {

            return grade;
        }

        public void setGrade(double grade) {
            this.grade = grade;
        }

        public Student getStudent() {
            return student;
        }

        public void setStudent(Student student) {
            this.student = student;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            GradedStudent that = (GradedStudent) o;

            if (Double.compare(that.grade, grade) != 0) return false;
            return student != null ? student.equals(that.student) : that.student == null;
        }

        @Override
        public int hashCode() {
            int result;
            long temp;
            temp = Double.doubleToLongBits(grade);
            result = (int) (temp ^ (temp >>> 32));
            result = 31 * result + (student != null ? student.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "GradedStudent{" +
                    "grade=" + grade +
                    ", student=" + student +
                    '}';
        }
    }

    public static void main(String[] args) {
        System.out.println(assignGrades(studentsByGroup(students(5)), grades(5)));
    }
}
