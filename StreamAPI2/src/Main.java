import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)]));
        }

        long lowAgeStream = persons.stream()
                .filter(age -> age.getAge() < 18)
                .count();
        System.out.println("Несовершеннолетних -> " + lowAgeStream);

        List<String> highAgeStream = persons.stream()
                .filter(highAge -> highAge.getAge() >= 18 && highAge.getAge() <= 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(highAgeStream);

        List<Person> education = persons.stream()
                .filter(highEducation -> highEducation.getEducation().equals(Education.HIGHER))
                .filter(woman -> woman.getSex().equals(Sex.WOMAN) == woman.getAge() >= 18 && woman.getAge() <= 60)
                .filter(man -> man.getSex().equals(Sex.MAN) == man.getAge() >= 18 && man.getAge() <= 65)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());


    }

}
