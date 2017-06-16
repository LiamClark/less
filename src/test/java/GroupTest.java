import nl.tudelft.Grades;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;

import static nl.tudelft.Grades.studentsByGroup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

class GroupTest {

    @Test
    void emptyTest() {
        final Map<Integer, ArrayList<Grades.Student>> studentsPerGroup = studentsByGroup(new ArrayList<>());
        assertThat(studentsPerGroup).isEmpty();
    }

    @Test
    void singleGroup() {
        final ArrayList<Grades.Student> students = Lists.newArrayList(new Grades.Student("Arie", "12345", 4));
        final Map<Integer, ArrayList<Grades.Student>> studentsPerGroup = studentsByGroup(students);

        assertThat(studentsPerGroup).containsOnly(entry(4, students));
    }

    @Test
    void sameGroup() {
        final Grades.Student arie = new Grades.Student("Arie", "12345", 4);
        final Grades.Student mauricio = new Grades.Student("Mauricio", "12345", 4);
        final ArrayList<Grades.Student> students = Lists.newArrayList(arie, mauricio);
        final Map<Integer, ArrayList<Grades.Student>> studentsPerGroup = studentsByGroup(students);

        assertThat(studentsPerGroup).containsOnly(entry(4, students));
    }

    @Test
    void differentGroups() {
        final Grades.Student arie = new Grades.Student("Arie", "12345", 4);
        final Grades.Student mauricio = new Grades.Student("Mauricio", "12345", 5);
        final ArrayList<Grades.Student> students = Lists.newArrayList(arie, mauricio);
        final Map<Integer, ArrayList<Grades.Student>> studentsPerGroup = studentsByGroup(students);

        assertThat(studentsPerGroup).containsOnly(entry(4, Lists.newArrayList(arie)), entry(5, Lists.newArrayList(mauricio)));
    }
}
