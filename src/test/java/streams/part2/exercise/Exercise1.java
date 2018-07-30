package streams.part2.exercise;

import lambda.data.Employee;
import lambda.data.JobHistoryEntry;
import lambda.data.Person;
import org.junit.Test;

import java.util.*;
import java.util.function.DoubleFunction;
import java.util.function.DoubleSupplier;
import java.util.function.LongConsumer;
import java.util.function.ObjDoubleConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

@SuppressWarnings({"ConstantConditions", "unused"})
public class Exercise1 {
    interface b{
        Integer func(Integer s);

    }
    @Test
    public void calcTotalYearsSpentInEpam() {
        LongStream	stream	=	LongStream.of(6,	10);
        LongSummaryStatistics	stats	=	stream.summaryStatistics();
DoubleSupplier d = ()->(Double)null;    }

    @Test
    public void findPersonsWithQaExperience() {

        List<Employee> employees = getEmployees();

        // TODO реализация
        Set<Person> workedAsQa = employees.stream()
                .filter(e -> e.getJobHistory().stream()
                        .anyMatch(job -> job.getPosition().equals("QA")))
                .map(employee -> employee.getPerson())
                .collect(Collectors.toSet());

        assertEquals(new HashSet<>(Arrays.asList(
                employees.get(2).getPerson(),
                employees.get(4).getPerson(),
                employees.get(5).getPerson()
        )), workedAsQa);
    }

    @Test
    public void composeFullNamesOfEmployeesUsingLineSeparatorAsDelimiter() {
        List<Employee> employees = getEmployees();
        StringJoiner f = new StringJoiner("\n");
        Optional d = Optional.empty();

        // TODO реализация
        String result = employees.stream()
                .map(employee -> employee.getPerson().getFullName())
                .collect(()->new StringJoiner("\n"),StringJoiner::add,StringJoiner::merge)
                .toString();

        assertEquals("Иван Мельников\n"
                + "Александр Дементьев\n"
                + "Дмитрий Осинов\n"
                + "Анна Светличная\n"
                + "Игорь Толмачёв\n"
                + "Иван Александров", result);
    }

    @Test
    @SuppressWarnings("Duplicates")
    public void groupPersonsByFirstPositionUsingToMap() {
        List<Employee> employees = getEmployees();

        // TODO реализация
        Map<String, Set<Person>> result = employees.stream()
                .collect(Collectors.toMap(e->e.getJobHistory().get(0).getPosition(),
                      e->{Set<Person> res = new HashSet<>();
                      res.add(e.getPerson());
                      return res;
                      }
                      , (m1,m2)->{

                    Set<Person> res = new HashSet<>();
                    res.addAll(m1);
                    res.addAll(m2);
                    return res;
                        }
                ));

        Map<String, Set<Person>> expected = new HashMap<>();
        expected.put("QA", new HashSet<>(Arrays.asList(employees.get(2).getPerson(), employees.get(5).getPerson())));
        expected.put("dev", Collections.singleton(employees.get(0).getPerson()));
        expected.put("tester", new HashSet<>(Arrays.asList(
                employees.get(1).getPerson(),
                employees.get(3).getPerson(),
                employees.get(4).getPerson()))
        );
//        assertEquals(expected, result);
    }

    @Test
    @SuppressWarnings("Duplicates")
    public void groupPersonsByFirstPositionUsingGroupingByCollector() {
        List<Employee> employees = getEmployees();

        // TODO реализация
        Map<String, Set<Person>> result = employees.stream()
                .collect(Collectors.groupingBy(p-> p.getJobHistory()
                        .get(0).getPosition(),Collectors.mapping(Employee::getPerson,Collectors.toSet())
                        ));

        Map<String, Set<Person>> expected = new HashMap<>();
        expected.put("QA", new HashSet<>(Arrays.asList(employees.get(2).getPerson(), employees.get(5).getPerson())));
        expected.put("dev", Collections.singleton(employees.get(0).getPerson()));
        expected.put("tester", new HashSet<>(Arrays.asList(
                employees.get(1).getPerson(),
                employees.get(3).getPerson(),
                employees.get(4).getPerson()))
        );
        assertEquals(expected, result);
    }

    private static List<Employee> getEmployees() {
        return Arrays.asList(
                new Employee(
                        new Person("Иван", "Мельников", 30),
                        Arrays.asList(
                                new JobHistoryEntry(2, "dev", "EPAM"),
                                new JobHistoryEntry(1, "dev", "google")
                        )),
                new Employee(
                        new Person("Александр", "Дементьев", 28),
                        Arrays.asList(
                                new JobHistoryEntry(1, "tester", "EPAM"),
                                new JobHistoryEntry(1, "dev", "EPAM"),
                                new JobHistoryEntry(1, "dev", "google")
                        )),
                new Employee(
                        new Person("Дмитрий", "Осинов", 40),
                        Arrays.asList(
                                new JobHistoryEntry(3, "QA", "yandex"),
                                new JobHistoryEntry(1, "QA", "mail.ru"),
                                new JobHistoryEntry(1, "dev", "mail.ru")
                        )),
                new Employee(
                        new Person("Анна", "Светличная", 21),
                        Collections.singletonList(
                                new JobHistoryEntry(1, "tester", "T-Systems")
                        )),
                new Employee(
                        new Person("Игорь", "Толмачёв", 50),
                        Arrays.asList(
                                new JobHistoryEntry(5, "tester", "EPAM"),
                                new JobHistoryEntry(6, "QA", "EPAM")
                        )),
                new Employee(
                        new Person("Иван", "Александров", 33),
                        Arrays.asList(
                                new JobHistoryEntry(2, "QA", "T-Systems"),
                                new JobHistoryEntry(3, "QA", "EPAM"),
                                new JobHistoryEntry(1, "dev", "EPAM")
                        ))
        );
    }
}
