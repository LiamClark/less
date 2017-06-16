import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import nl.tudelft.BetterGrades;
import org.junit.jupiter.api.Test;

import static nl.tudelft.BetterGrades.studentsByGroup;
import static org.assertj.core.api.Assertions.assertThat;

class GroupTest {

    @Test
    void emptyTest() {
        final Map<Integer, List<BetterGrades.Student>> studentsPerGroup = studentsByGroup(List.empty());
        assertThat(studentsPerGroup).isEmpty();
    }

    @Test
    void singleGroup() {
        final List<BetterGrades.Student> students = List.of(new BetterGrades.Student("Arie", "12345", 4));
        final Map<Integer, List<BetterGrades.Student>> studentsPerGroup = studentsByGroup(students);

        assertThat(studentsPerGroup).containsOnly(new Tuple2<>(4, students));
    }

    @Test
    void differentGroups() {
        final BetterGrades.Student arie = new BetterGrades.Student("Arie", "12345", 4);
        final BetterGrades.Student mauricio = new BetterGrades.Student("Mauricio", "12345", 5);
        final List<BetterGrades.Student> students = List.of(arie, mauricio);
        final Map<Integer, List<BetterGrades.Student>> studentsPerGroup = studentsByGroup(students);

        assertThat(studentsPerGroup).containsOnly(new Tuple2<>(4, List.of(arie)), new Tuple2<>(5, List.of(mauricio)));
    }

    @Test
    void sameGroup() {
        final BetterGrades.Student arie = new BetterGrades.Student("Arie", "12345", 4);
        final BetterGrades.Student mauricio = new BetterGrades.Student("Mauricio", "12345", 4);
        final List<BetterGrades.Student> students = List.of(arie, mauricio);
        final Map<Integer, List<BetterGrades.Student>> studentsPerGroup = studentsByGroup(students);

        assertThat(studentsPerGroup).containsOnly(new Tuple2<>(4, students));
    }
}
