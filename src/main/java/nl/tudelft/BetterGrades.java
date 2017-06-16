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

    public static Map<Integer, List<Student>> studentsByGroup(List<Student> studentList) {
        return studentList.groupBy((Student s) -> s.getGroupNumber());
    }
}
