package nl.tudelft;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Grades {

    public static class Student {
        private String name;
        private String studentNumber;
        private int groupNumber;
    }

    /**
     * Groups the students by their group number
     * @param students the students to be grouped
     * @return a map from group number to the students in that group.
     */
    public static Map<Integer, ArrayList<Student>> studentsByGroup(ArrayList<Student> students) {
        return new HashMap<>();
    }
}
