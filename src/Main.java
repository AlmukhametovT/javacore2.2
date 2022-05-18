import ru.ufa.Education;
import ru.ufa.Person;
import ru.ufa.Sex;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Collection<Person> persons = personListCreator();
        // Найти количество несовершеннолетних (т.е. людей младше 18 лет)
        long youngCount = persons.stream().filter(value -> value.getAge() < 18).count();
        System.out.println("количество несовершеннолетних (т.е. людей младше 18 лет) " + youngCount);
        // Получить список фамилий призывников (т.е. мужчин от 18 и до 27 лет)
        List<String> conscriptList = persons.stream()
                .filter(value -> value.getSex().equals(Sex.MAN))
                .filter(value -> value.getAge() >= 18 && value.getAge() < 27)
                .map(value -> value.getFamily())
                .collect(Collectors.toList());
        System.out.println("количество призывников в списке " + conscriptList.size());
        // Получить отсортированный по фамилии список потенциально работоспособных людей
        // с высшим образованием в выборке
        // (т.е. людей с высшим образованием от 18 до 60 лет для женщин и до 65 лет для мужчин)
        List<Person> potencialWorkerList = persons.stream()
                .filter(value -> value.getEducation().equals(Education.HIGHER))
                .filter(value -> {
                    boolean check = false;
                    if (value.getAge() >= 18 && value.getAge() < 60 && value.getSex().equals(Sex.WOMAN)) {
                        check = true;
                    } else if (value.getAge() >= 18 && value.getAge() < 65 && value.getSex().equals(Sex.MAN)) {
                        check = true;
                    }
                    return check;
                })
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        System.out.println("количество потенциально работоспособных людей в списке " + potencialWorkerList.size());
    }

    public static Collection<Person> personListCreator() {
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
        return persons;
    }
}