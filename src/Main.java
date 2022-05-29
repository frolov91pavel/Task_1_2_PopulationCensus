import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");

        Collection<Person> persons = new ArrayList<>();

        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        ///+++Количетво несовершеннолетних
        Stream<Person> count = persons.stream();
        Long x = count.filter(person -> person.getAge() < 18).count();
        System.out.println("Количество несовершеннолетних " + x);
        ///---

        ///+++Список призывников
        Stream<Person> list = persons.stream();
        List<String> recruit = list
                .filter(person -> person.getSex(Sex.MAN))
                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getAge() <= 27)
                .map(person -> person.getFamily())
                .collect(Collectors.toList());

        System.out.println("Список призывников " + recruit);
        ///---

        ///+++Отсортированный по фамилии список потенциально работоспособных людей с высшим образованием
        Stream<Person> list2 = persons.stream();
        List<String> list3 = list2
                .filter(person -> person.getAge() >= 18)
                .filter(person -> (person.getSex().equals(Sex.WOMAN)) ? person.getAge() <= 60 : person.getAge() <= 65)
                .filter(person -> person.getEducation().equals(Education.HIGHER))
                .sorted(Comparator.comparing(Person::getFamily))
                .map(person -> person.getFamily())
                .collect(Collectors.toList());

        System.out.println("Потенциально работоспособные люди " + list3);
        ///---

    }
}
